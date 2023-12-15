/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.fragment.trending;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.MainActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.Utils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListItem;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xutil.data.DateUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class ReuploadPhotoFragment extends BaseFragment implements ImageSelectGridAdapter.OnAddPicClickListener{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.Button2)
    Button button2;
    private ImageSelectGridAdapter mAdapter;
    String current_time = null;
    protected boolean isVisible;
    FileOutputStream out;
    String bitmapName = "zipped.jpg";
    File file;
    String QQFilePath;
    ProgressDialog dialog;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = TrendingFragment.class.getSimpleName();
    public String selectedFilePath;
    static public String flag1;
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/local_insert_byturn_new.php";
    String total_path = Environment.getExternalStorageDirectory() + "/zip/zipped.jpg";
    private String mImagePath;
    private String mFileName; //ͼƬ����
    private Uri mImageUri; //ͼƬ·��Uri
    public static final int TAKE_PHOTO = 2;
    public static final int CROP_PHOTO = 3;
    private final int PICK = 4;
    private String imgString = "";
    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
    private static final int MSG_FAILURE = 1;//获取图片失败的标识
    private TimePickerView mTimePicker;
    private RxPermissions rxPermissions;

    private static final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private List<LocalMedia> mSelectList = new ArrayList<>();

//    private Handler mHandler = new Handler() {
//        public void handleMessage (Message msg) {//此方法在ui线程运行
//            switch(msg.what) {
//                case MSG_SUCCESS:
//                    final MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.onPageSelected(0);
//
//                    break;
//
//                case MSG_FAILURE:
//
//                    break;
//            }
//        }
//    };

//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
    /**
     * @return 返回为 null意为不需要导航栏
     */
//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reload;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        flag1 = "1";
        rxPermissions = new RxPermissions(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        boolean isGranted = true;
        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果没有写sd卡权限
            isGranted = false;
        }
        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
        }
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
        }

        if(!isGranted){
            if(String.valueOf(MMKVUtils.getString("permission", "agree")).equals("agree")){
                rxPermissions.request(PERMISSIONS).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    int temp_1 = 1;
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(getActivity(), ReuploadPhotoFragment.this));
                            mAdapter.setSelectList(mSelectList);
                            mAdapter.setSelectMax(8);
                            mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View v) {


                                    PictureSelector.create(ReuploadPhotoFragment.this).themeStyle(R.style.XUIPictureStyle).openExternalPreview(position, mSelectList);

                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "You have refused to enable storage permission", Toast.LENGTH_SHORT).show();
                            MMKVUtils.put("permission", "refuse");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }else{
                Toast.makeText(getActivity(), "Please enable the application storage permission in the Settings for using", Toast.LENGTH_SHORT).show();
            }
        }else{
            recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(getActivity(), this));
            mAdapter.setSelectList(mSelectList);
            mAdapter.setSelectMax(8);
            mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, View v) {


                    PictureSelector.create(ReuploadPhotoFragment.this).themeStyle(R.style.XUIPictureStyle).openExternalPreview(position, mSelectList);

                }
            });
        }






















//        recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(getActivity(), this));
//        mAdapter.setSelectList(mSelectList);
//        mAdapter.setSelectMax(8);
//        mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                PictureSelector.create(ReuploadPhotoFragment.this).themeStyle(R.style.XUIPictureStyle).openExternalPreview(position, mSelectList);
//            }
//        });
    }


    @Override
    public void onResume() {
        super.onResume();
        initViews();
        // MobclickAgent.onPageStart(getPageName());
    }

    @Override
    public void onPause() {
        super.onPause();
        initViews();
        //MobclickAgent.onPageEnd(getPageName());
    }

    @OnClick({R.id.Button1, R.id.Button2,  R.id.btn_time_system})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Button1:
                boolean isShowHand = MMKVUtils.getBoolean("IS_SHOW_HAND", false);
                if(isShowHand){
                    showPictureItemDialog(this);
                }else{
//                    boolean isGranted = true;
//                    if (android.os.Build.VERSION.SDK_INT >= 23) {
//                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                            //如果没有写sd卡权限
//                            isGranted = false;
//                        }
//                        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                            isGranted = false;
//                        }
//                        Log.i("cbs","isGranted == "+isGranted);
//                        if (!isGranted) {
//                            (getActivity()).requestPermissions(
//                                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
//                                            .ACCESS_FINE_LOCATION,
//                                            Manifest.permission.READ_EXTERNAL_STORAGE,
//                                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                    102);
//                            isGranted = true;
//                        }
//                    }
//
//
////                Utils.getPictureSelector(this,1)
////                        .selectionMedia(mSelectList)
////                        .maxSelectNum(1)
////                        .compress(true)
////                        .minimumCompressSize(50)// 小于100kb的图片不压缩
////                        .selectionMode(PictureConfig.SINGLE)
////                        .forResult(PictureConfig.CHOOSE_REQUEST);
//                    Utils.getPictureSelector(this,1)
//                            .selectionMedia(mSelectList)
//                            .maxSelectNum(1)
//                            .compress(false)
//                            .selectionMode(PictureConfig.SINGLE)
//                            .forResult(PictureConfig.CHOOSE_REQUEST);



                    boolean isGranted = true;
                    if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //如果没有写sd卡权限
                        isGranted = false;
                    }
                    if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        isGranted = false;
                    }
                    if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        isGranted = false;
                    }

                    if(!isGranted){
                        if(String.valueOf(MMKVUtils.getString("permission", "agree")).equals("agree")){
                            rxPermissions.request(PERMISSIONS).subscribe(new Observer<Boolean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }
                                int temp_1 = 1;
                                @Override
                                public void onNext(Boolean aBoolean) {
                                    if (aBoolean) {
                                        Utils.getPictureSelector(ReuploadPhotoFragment.this,1)
                                            .selectionMedia(mSelectList)
                                            .maxSelectNum(1)
                                            .compress(false)
                                            .selectionMode(PictureConfig.SINGLE)
                                            .forResult(PictureConfig.CHOOSE_REQUEST);
                                    } else {
                                        Toast.makeText(getActivity(), "You have refused to enable camera and storage permissions", Toast.LENGTH_SHORT).show();
                                        MMKVUtils.put("permission", "refuse");
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                        }else{
                            Toast.makeText(getActivity(), "Please enable camera and storage permissions for the app in Settings for using", Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        Utils.getPictureSelector(ReuploadPhotoFragment.this,1)
                                .selectionMedia(mSelectList)
                                .maxSelectNum(1)
                                .compress(false)
                                .selectionMode(PictureConfig.SINGLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);


                    }









                }
//                Utils.getPictureSelector(this,1)
//                        .selectionMedia(mSelectList)
//                        .maxSelectNum(1)
//                        .selectionMode(PictureConfig.SINGLE)
//                        .forResult(PictureConfig.CHOOSE_REQUEST);
//                button2.setVisibility(View.VISIBLE);
                break;
//            case R.id.Button3:
//                openNewPage(WeightSleepFragment.class);
//                break;
            case R.id.Button2:
                if(current_time != null) {
                    String[] strArr = current_time.split("_");
                    String select_hour = strArr[0];
                    String select_minute = strArr[1];
                    Calendar cal=Calendar.getInstance();
                    int h=cal.get(Calendar.HOUR_OF_DAY);
                    int mi=cal.get(Calendar.MINUTE);
                    if(Integer.parseInt(select_hour) > h) {
                        Toast.makeText(getActivity(), "The time you select cannot exceed the current time", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Integer.parseInt(select_hour) == h && Integer.parseInt(select_minute) > mi){

                                Toast.makeText(getActivity(), "The time you select cannot exceed the current time", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            if (isFastDoubleClick() == false) {
                                if (selectedFilePath != null) {
                                    //ImageLoader.get().clearMemoryCache(getActivity().getApplicationContext());
                                    //dialog = ProgressDialog.show(getActivity(), "", "请等待返回结果...", true);
                                    //startActivity(new Intent(this,SimplePerActivity.class));
                                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                                    Calendar calendar = Calendar.getInstance();
                                    //��ȡϵͳ������
                                    //��
                                    int year = calendar.get(Calendar.YEAR);
                                    //��
                                    int month = calendar.get(Calendar.MONTH)+1;
                                    //��
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);


                                    String time_string;
                                    String [] temp = current_time.split("_");
                                    if(Integer.valueOf(temp[0]) < 10){
                                        temp[0] = "0" + temp[0];
                                    }
                                    if(Integer.valueOf(temp[1]) < 10){
                                        temp[1] = "0" + temp[1];
                                    }

                                    time_string = year + "." + month + "." + day + "-" + temp[0] + ":" + temp[1] + ":" + "00";
//                                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path",null,1);
//                                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                                    ContentValues values = new ContentValues();
//                                    values.put("user_name",androidid);
//                                    values.put("user_time",time_string);
//                                    values.put("photo_type","food");
//                                    db.insert("photo_path",null,values);
//                                    db.close();



                                    DatabaseHelper databaseHelpernew = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                                    SQLiteDatabase dbnew = databaseHelpernew.getWritableDatabase();
                                    ContentValues valuesnew = new ContentValues();
                                    valuesnew.put("user_name",androidid);
                                    valuesnew.put("user_time",time_string);
                                    valuesnew.put("photo_type","food");
                                    valuesnew.put("photo_url","");
                                    dbnew.insert("photo_path_new",null,valuesnew);
                                    dbnew.close();

                                    mSelectList.clear();
                                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 4, RecyclerView.VERTICAL, false);
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(getActivity(), this));
                                    mAdapter.setSelectList(mSelectList);
                                    mAdapter.setSelectMax(8);
                                    mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position, View v) {

                                            PictureSelector.create(ReuploadPhotoFragment.this).themeStyle(R.style.XUIPictureStyle).openExternalPreview(position, mSelectList);

                                        }
                                    });

                                    final MainActivity mainActivity = (MainActivity) getActivity();
//                                    Bundle bundle=getArguments();
//                                    bundle.putString("one","2");

                                    mainActivity.onPageSelected(9);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            //creating new thread to handle Http Operations
                                            uploadFile(selectedFilePath, time_string);
                                            //mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
                                            //dialog.dismiss();
                                        }
                                    }).start();
//                                    button2.setVisibility(View.INVISIBLE);
                                } else {
                                    Toast.makeText(getActivity(), "Please select a picture first", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                }
                else{
                    Calendar calendar = Calendar.getInstance();

                    calendar.setTime(DateUtils.getNowDate());
                    current_time = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + '_' + String.valueOf(calendar.get(Calendar.MINUTE))+ '_' + String.valueOf(calendar.get(Calendar.SECOND));


                    String[] strArr = current_time.split("_");
                    String select_hour = strArr[0];
                    String select_minute = strArr[1];
                    Calendar cal=Calendar.getInstance();
                    int h=cal.get(Calendar.HOUR_OF_DAY);
                    int mi=cal.get(Calendar.MINUTE);
                    if(Integer.parseInt(select_hour) > h) {
                        Toast.makeText(getActivity(), "The time you select cannot exceed the current time", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Integer.parseInt(select_hour) == h && Integer.parseInt(select_minute) > mi){

                            Toast.makeText(getActivity(), "The time you select cannot exceed the current time", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            if (isFastDoubleClick() == false) {
                                if (selectedFilePath != null) {
                                    //ImageLoader.get().clearMemoryCache(getActivity().getApplicationContext());
                                    //dialog = ProgressDialog.show(getActivity(), "", "请等待返回结果...", true);
                                    //startActivity(new Intent(this,SimplePerActivity.class));
                                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                                    calendar = Calendar.getInstance();
                                    //��ȡϵͳ������
                                    //��
                                    int year = calendar.get(Calendar.YEAR);
                                    //��
                                    int month = calendar.get(Calendar.MONTH)+1;
                                    //��
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);


                                    String time_string;
                                    String [] temp = current_time.split("_");
                                    if(Integer.valueOf(temp[0]) < 10){
                                        temp[0] = "0" + temp[0];
                                    }
                                    if(Integer.valueOf(temp[1]) < 10){
                                        temp[1] = "0" + temp[1];
                                    }
                                    if(Integer.valueOf(temp[2]) < 10){
                                        temp[2] = "0" + temp[2];
                                    }

                                    time_string = year + "." + month + "." + day + "-" + temp[0] + ":" + temp[1] + ":" + temp[2];
//                                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path",null,1);
//                                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                                    ContentValues values = new ContentValues();
//                                    values.put("user_name",androidid);
//                                    values.put("user_time",time_string);
//                                    values.put("photo_type","food");
//                                    db.insert("photo_path",null,values);
//                                    db.close();



                                    DatabaseHelper databaseHelpernew = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                                    SQLiteDatabase dbnew = databaseHelpernew.getWritableDatabase();
                                    ContentValues valuesnew = new ContentValues();
                                    valuesnew.put("user_name",androidid);
                                    valuesnew.put("user_time",time_string);
                                    valuesnew.put("photo_type","food");
                                    valuesnew.put("photo_url","");
                                    dbnew.insert("photo_path_new",null,valuesnew);
                                    dbnew.close();

                                    mSelectList.clear();
                                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 4, RecyclerView.VERTICAL, false);
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(getActivity(), this));
                                    mAdapter.setSelectList(mSelectList);
                                    mAdapter.setSelectMax(8);
                                    mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position, View v) {

                                            PictureSelector.create(ReuploadPhotoFragment.this).themeStyle(R.style.XUIPictureStyle).openExternalPreview(position, mSelectList);

                                        }
                                    });

                                    final MainActivity mainActivity = (MainActivity) getActivity();
//                                    Bundle bundle=getArguments();
//                                    bundle.putString("one","2");

                                    mainActivity.onPageSelected(9);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            //creating new thread to handle Http Operations
                                            uploadFile(selectedFilePath, time_string);
                                            //mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
                                            //dialog.dismiss();
                                        }
                                    }).start();
//                                    button2.setVisibility(View.INVISIBLE);
                                } else {
                                    Toast.makeText(getActivity(), "Please select a picture first", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }


                    //Toast.makeText(getActivity(), "请先选择时间", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_time_system:
                boolean isShowClock = MMKVUtils.getBoolean("IS_SHOW_CLOCK", false);
                if(!isShowClock){
                    showTimePickerDialog(getContext(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (TextView) view, Calendar.getInstance());
                }else{
                    showTimePicker((TextView) view);
                }


                break;
            default:
                break;
        }
    }

    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = SystemClock.uptimeMillis(); // �˷���������Android
        if (time - lastClickTime < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }





    private void showPictureItemDialog(Fragment thisFragment) {
        List<MaterialSimpleListItem> list = new ArrayList<>();
        list.add(new MaterialSimpleListItem.Builder(getContext())
                .content("Take pictures and upload")
                .icon(R.drawable.icon_update)
                .iconPaddingDp(8)
                .build());
//        list.add(new MaterialSimpleListItem.Builder(getContext())
//                .content(R.string.lab_add)
//                .icon(R.drawable.icon_add)
//                .build());
//        list.add(new MaterialSimpleListItem.Builder(getContext())
//                .content(R.string.lab_delete)
//                .icon(R.drawable.icon_delete)
//                .build());
        list.add(new MaterialSimpleListItem.Builder(getContext())
                .content("Manual input")
                .icon(R.drawable.icon_edit)
                .iconPaddingDp(8)
                .build());
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(list)
                .setOnItemClickListener(new MaterialSimpleListAdapter.OnItemClickListener() {
                    @Override
                    public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                        if(index == 1){
                            openNewPage(TextFoodUploadFragment.class);
                        }else if(index == 0){

                            boolean isGranted = true;
                            if (android.os.Build.VERSION.SDK_INT >= 23) {
                                if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    //如果没有写sd卡权限
                                    isGranted = false;
                                }
                                if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    isGranted = false;
                                }
                                Log.i("cbs","isGranted == "+isGranted);
//                                if (!isGranted) {
//                                    (getActivity()).requestPermissions(
//                                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
//                                                    .ACCESS_FINE_LOCATION,
//                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                            102);
//                                    isGranted = true;
//                                }
                            }

                            Utils.getPictureSelector(thisFragment,1)
                                    .selectionMedia(mSelectList)
                                    .maxSelectNum(1)
                                    .compress(false)
                                    .selectionMode(PictureConfig.SINGLE)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                });
        new MaterialDialog.Builder(getContext()).adapter(adapter, null).show();
    }




    public void showTimePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new TimePickerDialog(context
                , themeResId
                , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                view.setIs24HourView(true);
                current_time = String.valueOf(hourOfDay) + '_' + String.valueOf(minute) + "_0";
                tv.setText(String.format("You selected the time：%d:%d", hourOfDay, minute));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , false)
                .show();
    }




    private void showTimePicker(final TextView tv){
        if (mTimePicker == null) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(DateUtils.getNowDate());
            mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
                    //XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMddHHmm.get()));
                    String temp_string = DateUtils.date2String(date, DateUtils.HHmm.get());
                    String[] temp = temp_string.split(":");
                    int hourOfDay = Integer.valueOf(temp[0]);
                    int minute = Integer.valueOf(temp[1]);
                    current_time = String.valueOf(hourOfDay) + '_' + String.valueOf(minute) + "_0";
                    tv.setText(String.format("You selected the time：%d:%d", hourOfDay, minute));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");

                        }
                    })
                    .setType(new boolean[]{false, false, false, true, true, false})
                    .setTitleText("")
                    .setSubmitText("Confirm")
                    .setCancelText("Cancel")
                    .setLabel("y","m","d","h","min","sec")
                    .setDate(calendar)
                    .build();
        }
        mTimePicker.show();
    }


    public void saveBitmap(Bitmap bmp) {

        try { // ��ȡSDCardָ��Ŀ¼��
            String sdCardDir = Environment.getExternalStorageDirectory() + "/zip/";
            File dirFile = new File(sdCardDir);  //Ŀ¼ת�����ļ���
            if (!dirFile.exists()) {              //��������ڣ��Ǿͽ�������ļ���
                dirFile.mkdirs();
            }                          //�ļ����������Ϳ��Ա���ͼƬ��
            File file = new File(sdCardDir, bitmapName);// ��SDcard��Ŀ¼�´���ͼƬ��,�Ե�ǰʱ��Ϊ������
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
//            System.out.println("_________���浽____sd______ָ��Ŀ¼�ļ�����____________________");
            Log.e("saveBitMap", "saveBitmap: ͼƬ���浽" + Environment.getExternalStorageDirectory() + "/zip/" + bitmapName);
            QQFilePath = Environment.getExternalStorageDirectory() + "/zip/" + "zipped.jpg";
//            showShare(QQFilePath);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Toast.makeText(HahItemActivity.this,"�����Ѿ���"+Environment.getExternalStorageDirectory()+"/CoolImage/"+"Ŀ¼�ļ�����", Toast.LENGTH_SHORT).show();
    }

    public static String getRealPathFromUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19
            return getRealPathFromUriAboveApi19(context, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(context, uri);
        }
    }

    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * ����api19������,����uri��ȡͼƬ�ľ���·��
     *
     * @param context �����Ķ���
     * @param uri     ͼƬ��Uri
     * @return ���Uri��Ӧ��ͼƬ����, ��ô���ظ�ͼƬ�ľ���·��, ���򷵻�null
     */

    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // �����document���͵� uri, ��ͨ��document id�����д���
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // ʹ��':'�ָ�
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // ����� content ���͵� Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // ����� file ���͵� Uri,ֱ�ӻ�ȡͼƬ��Ӧ��·��
            filePath = uri.getPath();
        }
        return filePath;
    }


    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    mSelectList = PictureSelector.obtainMultipleResult(data);
                    mAdapter.setSelectList(mSelectList);
                    mAdapter.notifyDataSetChanged();
//                    Uri selectedFileUri = data.getData();
//                    selectedFilePath = getRealPathFromUri(getActivity(), selectedFileUri);
                    selectedFilePath = mSelectList.get(0).getPath();
                    break;

//                case PICK_FILE_REQUEST:
//                    if (data == null) {
//                        //no data present
//                        return;
//                    }
//
//
//
//
//
//
//                    //selectedFilePath = FilePath.getPath(this, selectedFileUri);
//
//
//                    Log.i(TAG, "Selected File Path:" + selectedFilePath);
//                    break;


//                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
//                        tvFileName.setText(selectedFilePath);
//                    } else {
//                        Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
//                    }


//                    if (selectedFilePath != null)
//                    {
//                        Bitmap bm = BitmapFactory.decodeFile(selectedFilePath);
//                        img_show.setImageBitmap(bm);
//                    }
                default:
                    break;
            }
        }
    }



    @Override
    public void onAddPicClick() {
        Utils.getPictureSelector(this)
                .selectionMedia(mSelectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
//        button2.setVisibility(View.VISIBLE);
    }

    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
        int options = 90;
        while (baos.toByteArray().length / 1024 > 500) { // ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��
            baos.reset(); // ����baos�����baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ����ѹ��options%����ѹ��������ݴ�ŵ�baos��
            options -= 10;// ÿ�ζ�����10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ��ѹ���������baos��ŵ�ByteArrayInputStream��
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ��ByteArrayInputStream��������ͼƬ
        return bitmap;
    }


    public int uploadFile(final String selectedFilePath, String new_string) {


        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 10 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(selectedFilePath, newOpts);// ��ʱ����bmΪ��
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ���������ֻ��Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ
        float hh = 512;// �������ø߶�Ϊ800f
        float ww = 512f;// �������ÿ��Ϊ480f
        // ���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ�����ݽ��м��㼴��
        int be = 1;// be=1��ʾ������
        if (w > h && w > ww) {// �����ȴ�Ļ����ݿ�ȹ̶���С����
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// ����߶ȸߵĻ����ݿ�ȹ̶���С����
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// �������ű���
        // ���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��
        bitmap = BitmapFactory.decodeFile(selectedFilePath, newOpts);
        bitmap = compressImage(bitmap);// ѹ���ñ�����С���ٽ�������ѹ��
        saveBitmap(bitmap);

        String[] parts = total_path.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            //dialog.dismiss();


            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(total_path);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", total_path);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + total_path + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                //String androidid = "unknown";
                final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);






                Calendar calendar = Calendar.getInstance();
                //��ȡϵͳ������
                //��
                int year = calendar.get(Calendar.YEAR);
                //��
                int month = calendar.get(Calendar.MONTH)+1;
                //��
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //��ȡϵͳʱ��
                //Сʱ
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                //����
                int minute = calendar.get(Calendar.MINUTE);
                //��
                int second = calendar.get(Calendar.SECOND);
                String time_string;
                time_string = year + "." + month + "." + day + "-" + hour + ":" + minute + ":" + second;


                Calendar calendar2 = Calendar.getInstance();

                calendar2.setTime(DateUtils.getNowDate());
                String real_current_time = String.valueOf(calendar2.get(Calendar.HOUR_OF_DAY)) + '_' + String.valueOf(calendar2.get(Calendar.MINUTE))+ '_' + String.valueOf(calendar2.get(Calendar.SECOND));



                String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                if(user_name.equals("Cohort Clock")){
                    user_name = androidid;
                }

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"usr_name\";filename=\""
                        + androidid + "_" + time_string + "_" + current_time + "_" + real_current_time + "_" + user_name + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);





                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);
                InputStreamReader reader =null;
                BufferedReader reader2 =null;
                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {


                    reader = new InputStreamReader(connection.getInputStream(),"utf-8");
                    reader2 = new BufferedReader(reader);
                    String string = reader2.readLine();

                    DatabaseHelper databaseHelper3 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                    SQLiteDatabase db3 = databaseHelper3.getWritableDatabase();
                    ContentValues values3 = new ContentValues();
                    String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + string;
                    values3.put("photo_url", photo_url);
                    db3.update("photo_path_new", values3, "user_time = ?", new String[]{new_string});
                    db3.close();
//                    reader = new InputStreamReader(connection.getInputStream(),"utf-8");
//                    reader2 = new BufferedReader(reader);
//                    String string = reader2.readLine();
//                    String food_type = string.split(";")[0];
//                    String workday = string.split(";")[1];
//                    if(workday.split(",")[0].equals("nan")){
//                        workday = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
//                    }
//                    String weekend = string.split(";")[2];
//                    if(weekend.split(",")[0].equals("nan")){
//                        weekend = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
//                    }
//                    String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + string.split(";")[3];
//                    String photo_cal = string.split(";")[4];
//                    String photo_kind = string.split(";")[5];
//                    String diet_type = string.split(";")[6];
//                    String first_diet_time = string.split(";")[7];
//                    int is_now_flag = Integer.valueOf(string.split(";")[8]);
//                    int is_next_flag = Integer.valueOf(string.split(";")[9]);
//
//                    MMKVUtils.put("diet_type", diet_type);
//                    MMKVUtils.put("first_diet_time", first_diet_time);
//                    MMKVUtils.put("is_now_flag", is_now_flag);
//                    MMKVUtils.put("is_next_flag", is_next_flag);
//
//
//
//                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path",null,1);
//                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                    ContentValues values = new ContentValues();
//
//
//                    DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"workday_data_new",null,1);
//                    SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
//                    ContentValues values1 = new ContentValues();
//
//                    DatabaseHelper databaseHelper2 = new DatabaseHelper(getContext(),"weekend_data_new",null,1);
//                    SQLiteDatabase db2 = databaseHelper2.getWritableDatabase();
//                    ContentValues values2 = new ContentValues();
//                    values1.put("data", workday);
//                    values2.put("data", weekend);
////                    for(int j = 0; j < 48; j++){
////                        String temp_name = "time" + String.valueOf(j);
////                        values1.put(temp_name, workday[j]);
////                        values2.put(temp_name, weekend[j]);
////                    }
//                    db1.insert("workday_data_new", null, values1);
//                    db2.insert("weekend_data_new", null, values2);
//                    //db.insert("photo_path",null,values);
//                    DatabaseHelper databaseHelper3 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
//                    SQLiteDatabase db3 = databaseHelper3.getWritableDatabase();
//                    ContentValues values3 = new ContentValues();
//                    values3.put("photo_cal", photo_cal);
//                    if(food_type.equals("not food"))
//                    {
//                        values3.put("photo_type", "not food");
//                        values.put("photo_type", "not food");
//                        db.update("photo_path", values, "user_time = ?", new String[]{new_string});
//                    }
//
//                    values3.put("photo_url", photo_url);
//                    db3.update("photo_path_new", values3, "user_time = ?", new String[]{new_string});
//                    db.close();
//                    db1.close();
//                    db2.close();
//                    db3.close();
                    fileInputStream.close();
                    dataOutputStream.flush();
                    dataOutputStream.close();
//                    reader = new InputStreamReader(connection.getInputStream(),"utf-8");
//                    reader2 = new BufferedReader(reader);
//                    String string = reader2.readLine();
//
//                    String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + string.split(";")[1];
//                    String food_type = string.split(";")[0];
//                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path",null,1);
//                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                    ContentValues values = new ContentValues();
//                    DatabaseHelper databaseHelper3 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
//                    SQLiteDatabase db3 = databaseHelper3.getWritableDatabase();
//                    ContentValues values3 = new ContentValues();
//                    if(food_type.equals("not food"))
//                    {
//                        values3.put("photo_type", "not food");
//                        values.put("photo_type", "not food");
//                        db.update("photo_path", values, "user_time = ?", new String[]{new_string});
//                    }
//
//                    values3.put("photo_url", photo_url);
//                    db3.update("photo_path_new", values3, "user_time = ?", new String[]{new_string});
//
//                    db.close();
//                    db3.close();


                    //openNewPage(UploadedFragment.class);
                    //dialog.dismiss();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            download_headportrait_new(androidid);
//                            //tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/" + fileName);
//                        }
//                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //dialog.dismiss();
                return serverResponseCode;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                //dialog.dismiss();
                return serverResponseCode;
                //Toast.makeText(getActivity(), "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                //dialog.dismiss();
                return serverResponseCode;
                //Toast.makeText(getActivity(), "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            //dialog.dismiss();
            return serverResponseCode;
        }

    }





}
