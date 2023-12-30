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

package com.xuexiang.templateproject.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.templateproject.database.LocalEchartsFragment;
import com.xuexiang.templateproject.database.PlotFunction;
import com.xuexiang.templateproject.fragment.AboutFragment;
import com.xuexiang.templateproject.fragment.SettingsFragment;
import com.xuexiang.templateproject.fragment.news.HowtouseFragment;
import com.xuexiang.templateproject.fragment.news.QuestionFragment;
import com.xuexiang.templateproject.fragment.profile.ProfileFragment;
import com.xuexiang.templateproject.fragment.trending.ReloadedFragment;
import com.xuexiang.templateproject.fragment.trending.TrendingFragment;
import com.xuexiang.templateproject.utils.DrawLongPictureUtil;
import com.xuexiang.templateproject.utils.Info;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.Utils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;
import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.common.ClickUtils;
import com.xuexiang.xutil.common.CollectionUtils;
import com.xuexiang.xutil.display.Colors;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.xuexiang.xui.XUI.getContext;

/**
 * 程序主页面,只是一个简单的Tab例子
 *
 * @author xuexiang
 * @since 2019-07-07 23:53
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener, ClickUtils.OnClick2ExitListener, Toolbar.OnMenuItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    /**
     * 底部导航栏
     */
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    /**
     * 侧边栏
     */
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private String[] mTitles;
    private XUISimplePopup mMenuPopup;
    private ProgressBar progressBar;
    Bitmap saveBitmap = null;
    String global_last_time = "";
    int is_draw_debug_global = -1;



    private List<String> mCurrentSelectedPath = new ArrayList<>();
    private String resultPath;


    private static final int MSG_SUCCESS = 1;//获取图片成功的标识
    private static final int MSG_FAILURE = 0;//获取图片失败的标识


    String res = "";
    String res_url = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private Handler handler  = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MSG_SUCCESS){
                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url","photo_cal","photo_kind"}, null, null, null, null, null);
                String result = res;
                List<String> user_time_list = new ArrayList<String>();

                while(cursor2.moveToNext()){
                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
                    String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                    String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                    String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                    user_time_list.add(user_time);
                }
                String [] each_data = result.split("<");
                for(int i  = 0; i < each_data.length; i ++){
                    String [] temp_data = each_data[i].split(";");
                    if(temp_data.length < 7){
                        continue;
                    }
                    String user_name = temp_data[0];
                    String user_time = temp_data[1];
                    String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + temp_data[2];
                    String food_type = temp_data[3];
                    String photo_cal = temp_data[4];
                    String photo_kind = temp_data[5];
                    String workday = temp_data[6];
                    String weekend = temp_data[7];

                    String temp_date = user_time.split("-")[0];
                    String temp_time = user_time.split("-")[1];
                    String temp_hour = temp_time.split(":")[0];
                    String temp_minute = temp_time.split(":")[1];
                    String temp_second = temp_time.split(":")[2];
                    if(Integer.valueOf(temp_hour) < 10){
                        temp_hour = "0" + temp_hour;
                    }
                    if(Integer.valueOf(temp_minute) < 10){
                        temp_minute = "0" + temp_minute;
                    }
                    if(Integer.valueOf(temp_second) < 10){
                        temp_second = "0" + temp_second;
                    }
                    String new_user_time = temp_date + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;
                    if(!user_time_list.contains(new_user_time)){
                        ContentValues values = new ContentValues();
                        values.put("user_name", user_name);
                        values.put("user_time", new_user_time);
                        values.put("photo_web_url", photo_url);
                        values.put("photo_type",food_type);
                        values.put("photo_cal",photo_cal);
                        values.put("photo_kind",photo_kind);
                        values.put("workday",workday);
                        values.put("weekend",weekend);
                        db1.insert("photo_path_new", null, values);

                    }else{
                        ContentValues values = new ContentValues();
                        values.put("photo_type",food_type);
                        values.put("photo_cal",photo_cal);
                        values.put("photo_kind",photo_kind);
                        values.put("photo_web_url", photo_url);
                        values.put("workday",workday);
                        values.put("weekend",weekend);
                        db1.update("photo_path_new", values, "user_time = ?", new String[]{new_user_time});
                    }


                }
                cursor2.close();
                db1.close();
                msg.what = 0;
            }
        }
    };



    private Handler handler1  = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MSG_SUCCESS){
                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_web_url_table",null,1);
                SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                Cursor cursor2 = db1.query("photo_web_url_table", new String[]{"photo_type","photo_url","photo_cal","photo_kind"}, null, null, null, null, null);
                String result = res_url;
                List<String> user_url = new ArrayList<String>();

                while(cursor2.moveToNext()){
                    String photo_url = cursor2.getString(cursor2.getColumnIndex("photo_url"));
                    user_url.add(photo_url);
                }
                String [] each_data = result.split("<");
                for(int i  = 0; i < each_data.length; i ++){
                    String [] temp_data = each_data[i].split(";");
                    if(temp_data.length >= 4){
                        String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + temp_data[0];
                        String food_type = temp_data[1];
                        String photo_cal = temp_data[2];
                        String photo_kind = temp_data[3];

                        if(!user_url.contains(temp_data[0])){
                            ContentValues values = new ContentValues();
                            values.put("photo_url", photo_url);
                            values.put("photo_type",food_type);
                            values.put("photo_cal",photo_cal);
                            values.put("photo_kind",photo_kind);
                            db1.insert("photo_web_url_table", null, values);

                        }else{

                        }
                    }



                }
                cursor2.close();
                db1.close();
                msg.what = 0;
            }
        }
    };
















    private Timer timer = new Timer(true);

    //任务
    private TimerTask task = new TimerTask() {
        public void run() {
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init();
        initViews();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        initListeners();

        timer.schedule(task, 0, 60*60*1000);

//        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
//        SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
//        Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url","photo_cal","photo_kind"}, null, null, null, null, null);


        new Thread(new Runnable() {
            @Override
            public void run() {
                getSynData();
                getSynDataTime();

                handler.obtainMessage(MSG_SUCCESS).sendToTarget();
            }
        }).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getSynDataUrl();
//
//                handler1.obtainMessage(MSG_SUCCESS).sendToTarget();
//            }
//        }).start();







//        String result = getSynData();
//        List<String> user_time_list = new ArrayList<String>();
//
//        while(cursor2.moveToNext()){
//            String id = cursor2.getString(cursor2.getColumnIndex("id"));
//            String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
//            String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
//            String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
//            user_time_list.add(user_time);
//        }
//        String [] each_data = result.split("<");
//        for(int i  = 0; i < each_data.length; i ++){
//            String [] temp_data = each_data[i].split(";");
//            if(temp_data.length < 7){
//                continue;
//            }
//            String user_name = temp_data[0];
//            String user_time = temp_data[1];
//            String photo_url = temp_data[2];
//            String food_type = temp_data[3];
//            String photo_cal = temp_data[4];
//            String photo_kind = temp_data[5];
//            String workday = temp_data[6];
//            String weekend = temp_data[7];
//
//            String temp_date = user_time.split("-")[0];
//            String temp_time = user_time.split("-")[1];
//            String temp_hour = temp_time.split(":")[0];
//            String temp_minute = temp_time.split(":")[1];
//            String temp_second = temp_time.split(":")[2];
//            if(Integer.valueOf(temp_hour) < 10){
//                temp_hour = "0" + temp_hour;
//            }
//            if(Integer.valueOf(temp_minute) < 10){
//                temp_minute = "0" + temp_minute;
//            }
//            if(Integer.valueOf(temp_second) < 10){
//                temp_second = "0" + temp_second;
//            }
//            String new_user_time = temp_date + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;
//            if(!user_time_list.contains(new_user_time)){
//                ContentValues values = new ContentValues();
//                values.put("user_name", user_name);
//                values.put("user_time", new_user_time);
//                values.put("photo_url", photo_url);
//                values.put("photo_type",food_type);
//                values.put("photo_cal",photo_cal);
//                values.put("photo_kind",photo_kind);
//                values.put("workday",workday);
//                values.put("weekend",weekend);
//                db1.insert("photo_path_new", null, values);
//
//            }else{
//                ContentValues values = new ContentValues();
//                values.put("photo_type",food_type);
//                values.put("photo_cal",photo_cal);
//                values.put("photo_kind",photo_kind);
//                values.put("workday",workday);
//                values.put("weekend",weekend);
//                db1.update("photo_path_new", values, "user_time = ?", new String[]{new_user_time});
//            }
//
//
//        }
//        cursor2.close();
//        db1.close();

    }
    private List<LocalMedia> mSelectList = new ArrayList<>();

    Bundle bundle=new Bundle();
    BaseFragment[] fragments = new BaseFragment[]{
            //new UploadedFragment(),
            //new EchartInnerFragment(),
            new LocalEchartsFragment(),
            new ReloadedFragment(),
            new TrendingFragment(),
            //new TestPictureSelector(),

            new ProfileFragment()

    };



    @Override
    protected boolean isSupportSlideBack() {
        return false;
    }

    private void initViews() {
        mTitles = ResUtils.getStringArray(R.array.home_titles);
        toolbar.setTitle(mTitles[0]);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);




        initHeader();

        //主页内容填充


        bundle.putString("one","1");
        bundle.putString("open_camera","1");

        fragments[0].setArguments(bundle);
        fragments[1].setArguments(bundle);
        fragments[2].setArguments(bundle);

        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
//        viewPager.setOffscreenPageLimit(mTitles.length - 1);
        viewPager.setOffscreenPageLimit(2);
        //viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);

        int isUploadDatabase = MMKVUtils.getInt("IS_UPLOAD_DATABASE", 1);


//        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
//        SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
//        databaseHelper1.onUpgrade(db1, isUploadDatabase, 3);


        MMKVUtils.put("IS_UPLOAD_DATABASE", 3);



        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                Cursor cursor1 = db1.query("photo_path_new", new String[]{"user_time"}, null, null, null, null, null);
                List<String> time_list = new ArrayList<>();
                while(cursor1.moveToNext()){
                        String temp_time = cursor1.getString(cursor1.getColumnIndex("user_time"));
                        String temp_day = temp_time.split("-")[0];
                        if(!time_list.contains(temp_day)){
                            time_list.add(temp_day);
                        }



                }
                cursor1.close();
                db1.close();
                MMKVUtils.put("user_use_day", time_list.size());
            }
        }).start();

//        Utils.checkUpdate(this, false);




    }
    public String getSynDataUrl()
    {
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();

        Double weight = Double.valueOf(MMKVUtils.getString("weight", "66.7"));
        Double height = Double.valueOf(MMKVUtils.getString("height", "167.1")) / 100;
        Double bmi = weight / (height * height);
        String  url1 ="http://39.100.73.118/deeplearning_photo/getWebUrl.php"+ "";

//        if(bmi < 18.5){
//            url1 ="http://39.100.73.118/deeplearning_photo/getWebUrl.php?isRecommendLow="+"false"+"&isRecommendCommon=" + "false" + "&isRecommendHigh=" + "true" + "";
//        }else if(bmi > 23.9){
//            url1 ="http://39.100.73.118/deeplearning_photo/getWebUrl.php?isRecommendLow="+"true"+"&isRecommendCommon=" + "false" + "&isRecommendHigh=" + "false" + "";
//        }else{
//            url1 ="http://39.100.73.118/deeplearning_photo/getWebUrl.php?isRecommendLow="+"false"+"&isRecommendCommon=" + "true" + "&isRecommendHigh=" + "false" + "";
//        }


        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        //String url1 ="http://39.100.73.118/deeplearning_photo/getWebUrl.php?isRecommendLow="+androidid+"";
        Request request = new Request.Builder()
                .url(url1)
                .build();
        res_url = "";
        try{
            Response response = okHttpClient.newCall(request).execute();
            res_url = response.body().string();

            //handler.sendEmptyMessage(1);
        }catch (Exception e)
        {
            e.printStackTrace();
            //handler.sendEmptyMessage(2);
        }
        return (res_url);
    }



    public String getSynData()
    {
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 = "";
        String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
        if(user_name == "Cohort Clock"){
            url1 ="http://39.100.73.118/deeplearning_photo/syn_database_new_new.php?androidid="+androidid+"";
        }else{
            url1 ="http://39.100.73.118/deeplearning_photo/syn_database_new_new.php?androidid="+user_name+"";
        }

        Request request = new Request.Builder()
                .url(url1)
                .build();
        res = "";
        try{
            Response response = okHttpClient.newCall(request).execute();
            res = response.body().string();

            //handler.sendEmptyMessage(1);
        }catch (Exception e)
        {
            e.printStackTrace();
            //handler.sendEmptyMessage(2);
        }
        return (res);
    }


    public String getSynDataTime()
    {
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 = "";
        String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
        if(user_name == "Cohort Clock"){
            url1 ="http://39.100.73.118/deeplearning_photo/syn_database_new_time.php?androidid="+androidid+"";
        }else{
            url1 ="http://39.100.73.118/deeplearning_photo/syn_database_new_time.php?androidid="+androidid+"";
        }

        Request request = new Request.Builder()
                .url(url1)
                .build();

        try{
            Response response = okHttpClient.newCall(request).execute();
            String temp_res = response.body().string();
            String[] res_list = temp_res.split(";");
            if(!res_list[2].equals("false")){
                MMKVUtils.put("server_time_edit", true);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date data_begin = null;
                Date date_end = null;
                try {
                    data_begin = df.parse( res_list[0]);
                    date_end = df.parse( res_list[1]);
                    MMKVUtils.put("eating_time_start", res_list[0]);
                    MMKVUtils.put("eating_time_end", res_list[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                MMKVUtils.put("server_time_edit", false);
            }

            //handler.sendEmptyMessage(1);
        }catch (Exception e)
        {
            e.printStackTrace();
            //handler.sendEmptyMessage(2);
        }
        return (res);
    }

















    public String getSynWebUrlData()
    {
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/syn_database.php?androidid="+androidid+"";
        Request request = new Request.Builder()
                .url(url1)
                .build();
        res = "";
        try{
            Response response = okHttpClient.newCall(request).execute();
            res = response.body().string();

            //handler.sendEmptyMessage(1);
        }catch (Exception e)
        {
            e.printStackTrace();
            //handler.sendEmptyMessage(2);
        }
        return (res);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 0:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "�����������Ȩ��", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "��ܾ������Ȩ��", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case 1:
//                //TODO multi permissions request result notification
//                boolean result = true;
//                for (int resultCode : grantResults) {
//                    if (resultCode != PackageManager.PERMISSION_GRANTED) {
//                        result = false;
//                    }
//                }
//                break;
//        }
//
//    }




    private void initHeader() {
        navView.setItemIconTintList(null);
        View headerView = navView.getHeaderView(0);
        LinearLayout navHeader = headerView.findViewById(R.id.nav_header);
        RadiusImageView ivAvatar = headerView.findViewById(R.id.iv_avatar);
        TextView tvAvatar = headerView.findViewById(R.id.tv_avatar);
        TextView tvSign = headerView.findViewById(R.id.tv_sign);

        if (Utils.isColorDark(ThemeUtils.resolveColor(this, R.attr.colorAccent))) {
            tvAvatar.setTextColor(Colors.WHITE);
            tvSign.setTextColor(Colors.WHITE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivAvatar.setImageTintList(ResUtils.getColors(R.color.xui_config_color_white));
            }
        } else {
            tvAvatar.setTextColor(ThemeUtils.resolveColor(this, R.attr.xui_config_color_title_text));
            tvSign.setTextColor(ThemeUtils.resolveColor(this, R.attr.xui_config_color_explain_text));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivAvatar.setImageTintList(ResUtils.getColors(R.color.xui_config_color_gray_3));
            }
        }

        // TODO: 2019-10-09 初始化数据
        ivAvatar.setImageResource(R.drawable.ic_default_head);
        String isUploadDatabase = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
//        tvAvatar.setText(R.string.app_name);
        tvAvatar.setText(isUploadDatabase);
        tvSign.setText("");
        navHeader.setOnClickListener(this);
    }

    protected void initListeners() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //侧边栏点击事件
        navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isCheckable()) {
                drawerLayout.closeDrawers();
                return handleNavigationItemSelected(menuItem);
            } else {
                switch (menuItem.getItemId()) {
                    case R.id.nav_settings:
                        openNewPage(SettingsFragment.class);
                        break;
                    case R.id.nav_about:
                        openNewPage(AboutFragment.class);
                        break;
                    case R.id.nav_data:
                        openNewPage(HowtouseFragment.class);
                        break;
                    case R.id.nav_notifications:
                        openNewPage(QuestionFragment.class);
                        break;
                    default:
                        XToastUtils.toast("点击了:" + menuItem.getTitle());
                        break;
                }
            }
            return true;
        });

        //主页事件监听
        viewPager.addOnPageChangeListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 处理侧边栏点击事件
     *
     * @param menuItem
     * @return
     */
    private boolean handleNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);
            return true;
        }
        return false;
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_privacy:
//                bundle.putString("one","1");
//                bundle.remove("open_camera");
//                bundle.putString("open_camera","2");
//                fragments[0].setArguments(bundle);
//                fragments[1].setArguments(bundle);
//                fragments[2].setArguments(bundle);
//                this.onPageSelected(1);
               // mMenuPopup.showDown(item.getActionView());

               showSimpleBottomSheetGrid(this);

//                List<String>time_list_array = new ArrayList<>();
//                time_list_array.add("2020.09.05-00:15:00");
//                time_list_array.add("2020.09.05-00:35:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-01:05:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-01:35:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-01:05:00");
//                time_list_array.add("2020.09.05-00:35:00;2020.09.05-01:43:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-00:35:00");
//                time_list_array.add("2020.09.04-00:15:00;2020.09.05-01:05:00");
//
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-03:05:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-17:05:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-22:05:00");
//                time_list_array.add("2020.09.05-00:15:00;2020.09.05-12:05:00");
//
//                time_list_array.add("2020.09.05-03:15:00;2020.09.05-12:35:00");
//                time_list_array.add("2020.09.05-03:15:00;2020.09.05-15:05:00");
//                time_list_array.add("2020.09.05-03:15:00;2020.09.05-18:05:00");
//                time_list_array.add("2020.09.05-03:15:00;2020.09.05-22:35:00");
//                time_list_array.add("2020.09.05-03:15:00;2020.09.06-01:05:00");
//                time_list_array.add("2020.09.05-03:15:00;2020.09.05-08:05:00");
//
//                time_list_array.add("2020.09.05-10:35:00;2020.09.05-14:05:00");
//                time_list_array.add("2020.09.05-10:35:00;2020.09.05-23:05:00");
//                time_list_array.add("2020.09.05-10:35:00;2020.09.05-17:05:00");
//                time_list_array.add("2020.09.05-10:35:00;2020.09.06-01:30:00");
//                time_list_array.add("2020.09.05-10:35:00;2020.09.05-19:35:00");
//
//                time_list_array.add("2020.09.05-17:35:00;2020.09.05-18:15:00");
//                time_list_array.add("2020.09.05-17:35:00");
//                time_list_array.add("2020.09.05-17:35:00;2020.09.05-22:15:00");
//                time_list_array.add("2020.09.05-17:35:00;2020.09.06-00:15:00");
//
//                time_list_array.add("2020.09.05-22:05:00;2020.09.05-22:15:00");
//                time_list_array.add("2020.09.05-22:05:00");
//                time_list_array.add("2020.09.05-22:05:00;2020.09.06-01:35:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:35:00");
//
//                int k = 0;
//                int j = 0;
//
//                for(k = 0; k< 1;k++){
//                    MMKVUtils.put("current_flag", k);
//                    for(j =12; j < 20;j++){
//                        MMKVUtils.put("is_draw_debug", j);
//                        this.onPageSelected(1);
//                        this.onPageSelected(0);
//
//
//                        int finalJ = j;
//                        int finalK = k;
////                        new Thread(new Runnable() {
////                            @Override
////                            public void run() {
////                                //Utils.showCaptureBitmap((WebView) findViewById(R.id.myView));
////                                View dView = (WebView) findViewById(R.id.myView);
////                                dView.setDrawingCacheEnabled(true);
////                                dView.buildDrawingCache();
////                                Bitmap bmp = dView.getDrawingCache();
////                                String time_string = time_list_array.get(finalJ) + "_" + finalK;
////                                String photo_name = time_string + ".jpg";
////
////                                saveBitmap(bmp,photo_name);
////                            }
////                        }).start();
//
//
//                    }
//                }
//                MMKVUtils.put("is_draw_debug", -1);

                break;

            default:
                break;
        }
        return false;
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_header:
                //XToastUtils.toast("点击头部！");
                break;

            default:
                break;
        }
    }

    //=============ViewPager===================//

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 9) {
            bundle.putString("one","2");
            position = 0;
        }


        Log.e("-----------","---onPageSelected");
        MenuItem item = bottomNavigation.getMenu().getItem(position);
        toolbar.setTitle(item.getTitle());
        item.setChecked(true);
        viewPager.setCurrentItem(position, true);
        updateSideNavStatus(item);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    //================Navigation================//

    /**
     * 底部导航栏点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);

            updateSideNavStatus(menuItem);
            return true;
        }
        return false;
    }

    /**
     * 更新侧边栏菜单选中状态
     *
     * @param menuItem
     */
    private void updateSideNavStatus(MenuItem menuItem) {
        MenuItem side = navView.getMenu().findItem(menuItem.getItemId());
        if (side != null) {
            side.setChecked(true);
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this);
        }
        return true;
    }





    @Override
    public void recreate(){
       this.onCreate(null);
    }










    @Override
    public void onRetry() {
        XToastUtils.toast("再按一次退出程序");
    }

    @Override
    public void onExit() {
        XUtil.get().exitApp();
    }

    private void showSimpleBottomSheetGrid(MainActivity mainActivity) {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;
        mainActivity.onPageSelected(0);
        final Button btn = findViewById(R.id.xyg);
        btn.setText("显示推荐食物");
        WebView myView;
        myView = (WebView) findViewById(R.id.myView);
//        myView.loadUrl("file:///android_asset/final_html.html");

        String current_weight = MMKVUtils.getString("latest_weight", "80");
        String current_height = MMKVUtils.getString("height", "170");

        String current_diet_z = getDietTime("z");
        int index_num = 0;
        String can_eat_flag = "TRUE";
        String[] diet_level = current_diet_z.split(",");
        if(diet_level[0].equals("1")){
            can_eat_flag = "FALSE";

        }else{
            can_eat_flag = "TRUE";

        }
        for(int index_i=0; index_i<48; index_i++){
            if(can_eat_flag.equals("FALSE"))
            {
                if(!(diet_level[index_i].equals("1")||diet_level[index_i].equals("2")||diet_level[index_i].equals("3")))
                {
                    break;
                }
                else{
                    index_num = index_num + 1;
                }

            }
            else{
                if(diet_level[index_i].equals("1")||diet_level[index_i].equals("2")||diet_level[index_i].equals("3"))
                {
                    break;
                }
                else{
                    index_num = index_num + 1;
                }
            }
        }

        int time_total = index_num*30*60;
        String current_color = "2fc25b";
        if(can_eat_flag.equals("FALSE")){
            current_color = "#F04864";
        }else{
            current_color = "#2FC25B";
        }
        //myView.loadUrl( "http://10.10.114.202:8080/#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);
        myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);






        BottomSheet.BottomGridSheetBuilder builder = new BottomSheet.BottomGridSheetBuilder(this);
        builder
                .addItem(R.drawable.icon_more_operation_share_friend, "Send to wechat", TAG_SHARE_WECHAT_FRIEND, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_moment, "Send to discover", TAG_SHARE_WECHAT_MOMENT, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_weibo, "Send to weibo", TAG_SHARE_WEIBO, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_chat, "Send to chat", TAG_SHARE_CHAT, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                //.addItem(R.drawable.icon_more_operation_save, "保存到本地", TAG_SHARE_LOCAL, BottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new BottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, BottomSheetItemView itemView) {
                        dialog.dismiss();


//                        int tag = (int) itemView.getTag();
//                        XToastUtils.toast("tag:" + tag + ", content:" + itemView.toString());
                        if(itemView.toString().equals("Send to wechat")){

                            DialogLoader.getInstance().showConfirmDialog(
                                    MainActivity.this,
                                    "Do you mind to share",
                                    "是",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            Utils.showCaptureBitmap((WebView) findViewById(R.id.myView));



                                            View dView = (WebView) findViewById(R.id.myView);
                                            dView.clearFocus();
                                            dView.setDrawingCacheEnabled(true);
                                            dView.buildDrawingCache();
                                            Bitmap bmp = dView.getDrawingCache();

                                            Calendar calendar = Calendar.getInstance();
                                            int hour = calendar.get(Calendar.HOUR_OF_DAY);
                                            String hour_s;
                                            if(hour < 10){
                                                hour_s = "0" + String.valueOf(hour);
                                            }
                                            else{
                                                hour_s = String.valueOf(hour);
                                            }
                                            //����
                                            int minute = calendar.get(Calendar.MINUTE);
                                            String minute_s;
                                            if(minute < 10){
                                                minute_s = "0" + String.valueOf(minute);
                                            }
                                            else{
                                                minute_s = String.valueOf(minute);
                                            }
                                            //��
                                            int second = calendar.get(Calendar.SECOND);
                                            String second_s;
                                            if(second < 10){
                                                second_s = "0" + String.valueOf(second);
                                            }
                                            else{
                                                second_s = String.valueOf(second);
                                            }
                                            String time_string;
                                            time_string = hour_s + "_" + minute_s + "_" + second_s;
                                            String photo_name = "share_photo_" + time_string + ".jpg";

                                            saveBitmap(bmp,photo_name);


//                            View dView = getWindow().getDecorView();
//                            dView.setDrawingCacheEnabled(true);
//                            dView.buildDrawingCache();
//                            Bitmap bmp = dView.getDrawingCache();

//                            if (bmp != null) {
//                                try {
//                                    // 获取状态栏高度
//                                    Rect rect = new Rect();
//                                    dView.getWindowVisibleDisplayFrame(rect);
//                                    int statusBarHeights = rect.top;
//                                    Display display = getWindowManager().getDefaultDisplay();
//                                    int widths = display.getWidth();
//                                    int heights = display.getHeight();
//                                    // 去掉状态栏
//                                    saveBitmap = Bitmap.createBitmap(dView.getDrawingCache(), 0, statusBarHeights,
//                                            widths, heights - statusBarHeights);
//
//
//
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
////            initHandler.sendMessage(Message.obtain());
//                            }


                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                            mCurrentSelectedPath.add(Environment.getExternalStorageDirectory() + "/share_photo/" + photo_name);

                                            //mCurrentSelectedPath.add(Environment.getExternalStorageDirectory() + "/zip/zipped.jpg");
                                            progressBar.setVisibility(View.VISIBLE);

                                            DrawLongPictureUtil drawLongPictureUtil;


                                            drawLongPictureUtil = new DrawLongPictureUtil(MainActivity.this);
                                            drawLongPictureUtil.setListener(new DrawLongPictureUtil.Listener() {
                                                @Override public void onSuccess(String path) {
                                                    runOnUiThread(new Runnable() {
                                                        @Override public void run() {
                                                            progressBar.setVisibility(View.GONE);
//                                            Toast.makeText(MainActivity.this.getApplicationContext(), "长图生成完成，点击下方按钮查看！",
//                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                    resultPath = path;
                                                    Bitmap bitmap = BitmapFactory.decodeFile(resultPath);
                                                    ShareUtils.sharePhoto(mainActivity, bitmap, Defaultcontent.title
                                                            , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.a6, SHARE_MEDIA.WEIXIN
                                                    );
                                                    mCurrentSelectedPath.clear();

//                                    ShareUtils.sharePhoto(mainActivity, bitmap, Defaultcontent.title
//                                            , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.a6, SHARE_MEDIA.WEIXIN
//                                    );
                                                }

                                                @Override public void onFail() {
                                                    runOnUiThread(new Runnable() {
                                                        @Override public void run() {
                                                            mCurrentSelectedPath.clear();
//                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    });
                                                }
                                            });
                                            Info info = new Info();
                                            Double weight = Double.valueOf(MMKVUtils.getString("weight", "66.2"));
                                            Double latest = Double.valueOf(MMKVUtils.getString("latest_weight", "66.2"));
                                            int user_use_day = MMKVUtils.getInt("user_use_day", 0);
                                            Double all_weight = (weight - latest);
                                            String setContentString = "您已经使用本应用" + user_use_day + "天" + "\n" + "您已经使用本应用减轻体重" + new DecimalFormat("0.00").format(all_weight) + "千克";
                                            info.setContent(setContentString);
                                            info.setImageList(mCurrentSelectedPath);
                                            drawLongPictureUtil.setData(info);
                                            drawLongPictureUtil.startDraw();



















                                            dialog.dismiss();
                                        }
                                    },
                                    "否",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            XToastUtils.toast("您拒绝了分享到微信");
                                            dialog.dismiss();
                                        }
                                    }
                            );




//                            ShareUtils.shareWeb(mainActivity, Defaultcontent.url, Defaultcontent.title
//                                    , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.icon_logo_share, SHARE_MEDIA.WEIXIN
//                            );

                        }
                        else if(itemView.toString().equals("Send to discover")){


                            DialogLoader.getInstance().showConfirmDialog(
                                    MainActivity.this,
                                    "是否同意分享到朋友圈，该操作会打开微信",
                                    "是",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mainActivity.onPageSelected(0);
                                            Utils.showCaptureBitmap((WebView) findViewById(R.id.myView));
                                            View dView = (WebView) findViewById(R.id.myView);
                                            dView.clearFocus();
                                            dView.setDrawingCacheEnabled(true);
                                            dView.buildDrawingCache();
                                            Bitmap bmp = dView.getDrawingCache();

                                            Calendar calendar = Calendar.getInstance();
                                            int hour = calendar.get(Calendar.HOUR_OF_DAY);
                                            String hour_s;
                                            if(hour < 10){
                                                hour_s = "0" + String.valueOf(hour);
                                            }
                                            else{
                                                hour_s = String.valueOf(hour);
                                            }
                                            //����
                                            int minute = calendar.get(Calendar.MINUTE);
                                            String minute_s;
                                            if(minute < 10){
                                                minute_s = "0" + String.valueOf(minute);
                                            }
                                            else{
                                                minute_s = String.valueOf(minute);
                                            }
                                            //��
                                            int second = calendar.get(Calendar.SECOND);
                                            String second_s;
                                            if(second < 10){
                                                second_s = "0" + String.valueOf(second);
                                            }
                                            else{
                                                second_s = String.valueOf(second);
                                            }
                                            String time_string;
                                            time_string = hour_s + "_" + minute_s + "_" + second_s;
                                            String photo_name = "share_photo_" + time_string + ".jpg";

                                            saveBitmap(bmp,photo_name);


//                            View dView = getWindow().getDecorView();
//                            dView.setDrawingCacheEnabled(true);
//                            dView.buildDrawingCache();
//                            Bitmap bmp = dView.getDrawingCache();

//                            if (bmp != null) {
//                                try {
//                                    // 获取状态栏高度
//                                    Rect rect = new Rect();
//                                    dView.getWindowVisibleDisplayFrame(rect);
//                                    int statusBarHeights = rect.top;
//                                    Display display = getWindowManager().getDefaultDisplay();
//                                    int widths = display.getWidth();
//                                    int heights = display.getHeight();
//                                    // 去掉状态栏
//                                    saveBitmap = Bitmap.createBitmap(dView.getDrawingCache(), 0, statusBarHeights,
//                                            widths, heights - statusBarHeights);
//
//
//
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
////            initHandler.sendMessage(Message.obtain());
//                            }


                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                            mCurrentSelectedPath.add(Environment.getExternalStorageDirectory() + "/share_photo/" + photo_name);

                                            //mCurrentSelectedPath.add(Environment.getExternalStorageDirectory() + "/zip/zipped.jpg");
                                            progressBar.setVisibility(View.VISIBLE);

                                            DrawLongPictureUtil drawLongPictureUtil;


                                            drawLongPictureUtil = new DrawLongPictureUtil(MainActivity.this);
                                            drawLongPictureUtil.setListener(new DrawLongPictureUtil.Listener() {
                                                @Override public void onSuccess(String path) {
                                                    runOnUiThread(new Runnable() {
                                                        @Override public void run() {
                                                            progressBar.setVisibility(View.GONE);
//                                            Toast.makeText(MainActivity.this.getApplicationContext(), "长图生成完成，点击下方按钮查看！",
//                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                    resultPath = path;
                                                    Bitmap bitmap = BitmapFactory.decodeFile(resultPath);
                                                    ShareUtils.sharePhoto(mainActivity, bitmap, Defaultcontent.title
                                                            , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.a6, SHARE_MEDIA.WEIXIN_CIRCLE
                                                    );
                                                    mCurrentSelectedPath.clear();

//                                    ShareUtils.sharePhoto(mainActivity, bitmap, Defaultcontent.title
//                                            , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.a6, SHARE_MEDIA.WEIXIN
//                                    );
                                                }

                                                @Override public void onFail() {
                                                    runOnUiThread(new Runnable() {
                                                        @Override public void run() {
                                                            mCurrentSelectedPath.clear();
//                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    });
                                                }
                                            });
                                            Info info = new Info();
                                            Double weight = Double.valueOf(MMKVUtils.getString("weight", "66.2"));
                                            Double latest = Double.valueOf(MMKVUtils.getString("latest_weight", "66.2"));
                                            int user_use_day = MMKVUtils.getInt("user_use_day", 0);
                                            Double all_weight = (weight - latest);
                                            String setContentString = "您已经使用本应用" + user_use_day + "天" + "\n" + "您已经使用本应用减轻体重" + new DecimalFormat("0.00").format(all_weight) + "千克";
                                            info.setContent(setContentString);
                                            info.setImageList(mCurrentSelectedPath);
                                            drawLongPictureUtil.setData(info);
                                            drawLongPictureUtil.startDraw();


                                            dialog.dismiss();
                                        }
                                    },
                                    "否",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            XToastUtils.toast("您拒绝了分享到朋友圈");
                                            dialog.dismiss();
                                        }
                                    }
                            );














//                            ShareUtils.shareWeb(mainActivity, Defaultcontent.url, Defaultcontent.title
//                                    , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.icon_logo_share, SHARE_MEDIA.WEIXIN
//                            );
                        }

                        else if(itemView.toString().equals("Send to weibo")){
                            XToastUtils.toast("抱歉，该功能目前还不可用");
//                            List<String>time_list_array = new ArrayList<>();
//                            time_list_array.add("2020.09.05-00:15:00");
//                            time_list_array.add("2020.09.05-00:35:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-01:05:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-01:35:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-01:05:00");
//                            time_list_array.add("2020.09.05-00:35:00;2020.09.05-01:43:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-00:35:00");
//                            time_list_array.add("2020.09.04-00:15:00;2020.09.05-01:05:00");
//
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-03:05:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-17:05:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-22:05:00");
//                            time_list_array.add("2020.09.05-00:15:00;2020.09.05-12:05:00");
//
//                            time_list_array.add("2020.09.05-03:15:00;2020.09.05-12:35:00");
//                            time_list_array.add("2020.09.05-03:15:00;2020.09.05-15:05:00");
//                            time_list_array.add("2020.09.05-03:15:00;2020.09.05-18:05:00");
//                            time_list_array.add("2020.09.05-03:15:00;2020.09.05-22:35:00");
//                            time_list_array.add("2020.09.05-03:15:00;2020.09.06-01:05:00");
//                            time_list_array.add("2020.09.05-03:15:00;2020.09.05-08:05:00");
//
//                            time_list_array.add("2020.09.05-10:35:00;2020.09.05-14:05:00");
//                            time_list_array.add("2020.09.05-10:35:00;2020.09.05-23:05:00");
//                            time_list_array.add("2020.09.05-10:35:00;2020.09.05-17:05:00");
//                            time_list_array.add("2020.09.05-10:35:00;2020.09.06-01:30:00");
//                            time_list_array.add("2020.09.05-10:35:00;2020.09.05-19:35:00");
//
//                            time_list_array.add("2020.09.05-17:35:00;2020.09.05-18:15:00");
//                            time_list_array.add("2020.09.05-17:35:00");
//                            time_list_array.add("2020.09.05-17:35:00;2020.09.05-22:15:00");
//                            time_list_array.add("2020.09.05-17:35:00;2020.09.06-00:15:00");
//
//                            time_list_array.add("2020.09.05-22:05:00;2020.09.05-22:15:00");
//                            time_list_array.add("2020.09.05-22:05:00");
//                            time_list_array.add("2020.09.05-22:05:00;2020.09.06-01:35:00");
//                            time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:35:00");
//
//
//
//                            for(int k = 0; k< 1;k++){
//                                MMKVUtils.put("current_flag", k);
//                                for(int j =0; j < 10;j++){
//                                    MMKVUtils.put("is_draw_debug", j);
//                                    mainActivity.onPageSelected(1);
//                                    mainActivity.onPageSelected(0);
////                                    Utils.showCaptureBitmap((WebView) findViewById(R.id.myView));
////                                    View dView = (WebView) findViewById(R.id.myView);
////                                    dView.setDrawingCacheEnabled(true);
////                                    dView.buildDrawingCache();
////                                    Bitmap bmp = dView.getDrawingCache();
////                                    String time_string = time_list_array.get(j) + "_" + k;
////                                    String photo_name = time_string + ".jpg";
////
////                                    saveBitmap(bmp,photo_name);
//                                }
//                            }
//                            MMKVUtils.put("is_draw_debug", -1);






                        }
                        else if(itemView.toString().equals("Send to chat")) {
                            XToastUtils.toast("抱歉，该功能目前还不可用");

                        }

                    }
                }).build().show();


    }

    public String getDietTime(String ss)
    {
        List<String>time_list_array = new ArrayList<>();
        time_list_array.add("2020.09.06-00:15:00");
        time_list_array.add("2020.09.06-00:35:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:05:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:35:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:05:00");
        time_list_array.add("2020.09.06-00:35:00;2020.09.06-01:43:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-00:35:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:05:00");

        time_list_array.add("2020.09.06-00:15:00;2020.09.06-03:05:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-17:05:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-22:05:00");
        time_list_array.add("2020.09.06-00:15:00;2020.09.06-12:05:00");

        time_list_array.add("2020.09.06-03:15:00;2020.09.06-12:35:00");
        time_list_array.add("2020.09.06-03:15:00;2020.09.06-15:05:00");
        time_list_array.add("2020.09.06-03:15:00;2020.09.06-18:05:00");
        time_list_array.add("2020.09.06-03:15:00;2020.09.06-22:35:00");
        time_list_array.add("2020.09.06-03:15:00;2020.09.06-01:05:00");
        time_list_array.add("2020.09.06-03:15:00;2020.09.06-08:05:00");

        time_list_array.add("2020.09.06-10:35:00;2020.09.06-14:05:00");
        time_list_array.add("2020.09.06-10:35:00;2020.09.06-23:05:00");
        time_list_array.add("2020.09.06-10:35:00;2020.09.06-17:05:00");
        time_list_array.add("2020.09.06-10:35:00;2020.09.07-01:30:00");
        time_list_array.add("2020.09.06-10:35:00;2020.09.06-19:35:00");

        time_list_array.add("2020.09.06-17:35:00;2020.09.06-18:15:00");
        time_list_array.add("2020.09.06-17:35:00");
        time_list_array.add("2020.09.06-17:35:00;2020.09.06-22:15:00");
        time_list_array.add("2020.09.06-17:35:00;2020.09.07-00:15:00");

        time_list_array.add("2020.09.06-22:05:00;2020.09.06-22:15:00");
        time_list_array.add("2020.09.06-22:05:00");
        time_list_array.add("2020.09.06-22:05:00;2020.09.07-01:35:00");
        time_list_array.add("2020.09.07-00:15:00;2020.09.07-01:35:00");

        time_list_array.add("2020.09.06-03:15:00;2020.09.06-16:05:00");
        time_list_array.add("2020.09.06-03:15:00;2020.09.06-16:05:00");

        time_list_array.add("2020.09.05-21:00:00;2020.09.06-02:30:00");


        String diet_type = MMKVUtils.getString("diet_type", "8:16 diet");
        String first_diet_time = MMKVUtils.getString("first_diet_time", "8");
        int is_now_flag = MMKVUtils.getInt("is_now_flag", 0);
        int is_next_flag = MMKVUtils.getInt("is_next_flag", 0);


        int is_draw_debug = MMKVUtils.getInt("is_draw_debug", -1);
        if(is_draw_debug_global == -1){
            DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path_new",null,1);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type"}, null, null, null, null, null);
            //利用游标遍历所有数据对象
            //为了显示全部，把所有对象连接起来，放到TextView中

            String textview_data = "";
            List<String> time_list = new ArrayList<String>();
            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
                String user_time = cursor.getString(cursor.getColumnIndex("user_time"));
                String photo_type = cursor.getString(cursor.getColumnIndex("photo_type"));
                if(photo_type.equals("food")){
                    String[] temp123 = user_time.split("-");
                    String temp_year = temp123[0].split("\\.")[0];
                    String temp_month = temp123[0].split("\\.")[1];
                    String temp_day = temp123[0].split("\\.")[2];
                    if(Integer.valueOf(temp_month) < 10){
                        temp_month = "0" + temp_month;
                    }
                    if(Integer.valueOf(temp_day) < 10){
                        temp_day = "0" + temp_day;
                    }
                    user_time = temp_year + "." + temp_month + "." + temp_day + "-" + temp123[1];

                    time_list.add(user_time);
                }
            }
            Collections.sort(time_list);


//        String heapmap_result = PlotFunction.getDataHeatmap(time_list);

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String hour_s;
            if(hour < 10){
                hour_s = "0" + String.valueOf(hour);
            }
            else{
                hour_s = String.valueOf(hour);
            }
            int minute = calendar.get(Calendar.MINUTE);
            String minute_s;
            if(minute < 10){
                minute_s = "0" + String.valueOf(minute);
            }
            else{
                minute_s = String.valueOf(minute);
            }
            int second = calendar.get(Calendar.SECOND);
            String second_s;
            if(second < 10){
                second_s = "0" + String.valueOf(second);
            }
            else{
                second_s = String.valueOf(second);
            }
            String time_string;
            time_string = year + "." + month + "." + day + "-" + hour_s + ":" + minute_s + ":" + second_s;


            if(time_list.size()==0){
                return "4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1";
            }else{
                String heapmap_result = PlotFunction.getDataHeatmapType(time_list, diet_type, first_diet_time, is_now_flag, is_next_flag, time_string);
                String[] temp = heapmap_result.split("&");
                String plot_data = "[" + temp[0] + "]";
                String x_asix = "[" + temp[1] + "]";
                String time_level = temp[2];
                if(ss.equals("x")){
                    StringBuffer bf = new StringBuffer(plot_data);
//            bf.delete(plot_data.length() - 1, plot_data.length());
                    Log.i("heatmap", "getT5Data: " + bf.toString());
                    return bf.toString();
                }
                else if(ss.equals("y")){
                    StringBuffer bf = new StringBuffer(x_asix);
//            bf.delete(x_asix.length() - 1, x_asix.length());
                    Log.i("heatmapaxis", "getT5Data: " + bf.toString());
                    return bf.toString();
                }else{
                    return time_level;
                }
            }

//            else{
//                StringBuffer bf = new StringBuffer(x_asix);
////            bf.delete(x_asix.length() - 1, x_asix.length());
//                Log.i("heatmapaxis", "getT5Data: " + bf.toString());
//                return bf.toString();
//            }
        }else{
            int current_time_input = MMKVUtils.getInt("current_flag", 0);
            String input_time_list = time_list_array.get(is_draw_debug_global);
            List<String> time_list = new ArrayList<>();
            if(input_time_list.contains(";")){
                String[] temp_list = input_time_list.split(";");
                for(int h = 0; h < temp_list.length; h ++){
                    time_list.add(temp_list[h]);
                }
            }else{
                time_list.add(input_time_list);
            }
            String last_time = time_list.get(time_list.size() - 1);

            DateFormat df1 = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");


            Date temp_time111 = null;
            try {
                temp_time111 = df1.parse(last_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(temp_time111);
            calendar1.add(Calendar.HOUR_OF_DAY, current_time_input);
            last_time = df1.format(calendar1.getTime());







            String time_string = "";
            String heapmap_result = PlotFunction.getDataHeatmapType(time_list, diet_type, first_diet_time, is_now_flag, is_next_flag, global_last_time);
            String[] temp = heapmap_result.split("&");
            String plot_data = "[" + temp[0] + "]";
            String x_asix = "[" + temp[1] + "]";
            String time_level = temp[2];
            if(ss.equals("x")){
                StringBuffer bf = new StringBuffer(plot_data);
//            bf.delete(plot_data.length() - 1, plot_data.length());
                Log.i("heatmap", "getT5Data: " + bf.toString());
                return bf.toString();
            }
            else if(ss.equals("y")){
                StringBuffer bf = new StringBuffer(x_asix);
//            bf.delete(x_asix.length() - 1, x_asix.length());
                Log.i("heatmapaxis", "getT5Data: " + bf.toString());
                return bf.toString();
            }else{
                return time_level;
            }
        }









    }






    public void saveBitmap(Bitmap bmp, String photo_name) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        int options = 90;
////        while (baos.toByteArray().length / 1024 > 100) {
////            baos.reset();
////            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
////            options -= 10;
////        }
//        baos.reset();
//        image.compress(Bitmap.CompressFormat.JPEG, 10, baos);
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);


        Double current_bandwidth = MMKVUtils.getDouble("current_bandwidth", 200);
        if(current_bandwidth < 10){
            current_bandwidth = 10.0;
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String sdCardDir = Environment.getExternalStorageDirectory() + "/share_photo/";
            File dirFile = new File(sdCardDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File file = new File(sdCardDir, photo_name);
            FileOutputStream out;
            out = new FileOutputStream(file);
            int options = 90;
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);

            //bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
            // QQFilePath = Environment.getExternalStorageDirectory() + "/zip/" + "zipped.jpg";
//            showShare(QQFilePath);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Toast.makeText(HahItemActivity.this,"�����Ѿ���"+Environment.getExternalStorageDirectory()+"/CoolImage/"+"Ŀ¼�ļ�����", Toast.LENGTH_SHORT).show();
    }



}
