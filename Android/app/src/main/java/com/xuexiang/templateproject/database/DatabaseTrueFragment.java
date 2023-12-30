/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
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

package com.xuexiang.templateproject.database;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Keep;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.IFormat;
import com.bin.david.form.data.format.draw.BitmapDrawFormat;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
import com.bin.david.form.data.format.draw.MultiLineDrawFormat;
import com.bin.david.form.data.format.tip.MultiLineBubbleTip;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.UserInfo;
import com.xuexiang.templateproject.activity.UserInfo_WeightSleep;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.DialogLoader;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * 首页动态
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Keep
@Page(name = "数据库",anim = CoreAnim.none)
public class DatabaseTrueFragment extends BaseFragment {
    protected boolean isVisible;
    SmartTable<UserInfo_WeightSleep> table;

    SmartTable<UserInfo> table1;

    private Map<String,Bitmap> map = new HashMap<>();
    @BindView(R.id.detail_view)
    ImageView getImg;
    private Handler handler = null;
    private byte[] data_img;
    private Bitmap bitmap;
//    Column<Boolean> operation;
//    Column<String> nameColumn;
    List<String> name_selected = new ArrayList<String>();
    int Button_flag = 0;
    int firstStartFlag = 0;

    Column<Boolean> operation;
    Column<String> nameColumn;


    List<String> old_id_list = new ArrayList<>();
    List<String> old_user_list = new ArrayList<>();
    List<String> old_photo_list = new ArrayList<>();
    List<String> old_photo_url_list = new ArrayList<>();



    List<String> id_list = new ArrayList<>();
    List<String> user_list = new ArrayList<>();
    List<String> weight_list = new ArrayList<>();
    List<String> start_time_list = new ArrayList<>();
    List<String> end_time_list = new ArrayList<>();




    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
    private static final int MSG_FAILURE = 1;//获取图片失败的标识

    /**
     * @return 返回为 null意为不需要导航栏
     */
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
        return R.layout.fragment_database;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        table = (SmartTable<UserInfo_WeightSleep>) findViewById(R.id.table);
        table1 = (SmartTable<UserInfo>) findViewById(R.id.table);



        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                //SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                SQLiteDatabase db1 = databaseHelper1.getReadableDatabase();
                Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url"}, null, null, null, null, null);
//                        getTabledata(cursor2);


                List<UserInfo> list = new ArrayList<>();

                Calendar calendar = Calendar.getInstance();
                //��ȡϵͳ������
                //��
                int year = calendar.get(Calendar.YEAR);
                //��
                int month = calendar.get(Calendar.MONTH)+1;
                //��
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);


                old_id_list.clear();
                old_user_list.clear();
                old_photo_list.clear();
                old_photo_url_list.clear();
                while(cursor2.moveToNext()){

                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
                    String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                    String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                    String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                    String photo_url = cursor2.getString(cursor2.getColumnIndex("photo_url"));
                    String[] temp1 = user_time.split("-");
                    if(temp1[0].equals(current_time)){
                        old_id_list.add(id);
                        old_user_list.add(user_time);
                        old_photo_list.add(photo_type);
                        old_photo_url_list.add(photo_url);
                        //list.add(new UserInfo(id, user_time, photo_type));
                    }


                }


                cursor2.close();
                db1.close();
                mHandler1.obtainMessage(MSG_SUCCESS).sendToTarget();
            }
        }).start();




    }

    @Override
    protected void initListeners() {


    }

//    @com.bin.david.form.annotation.SmartTable(name = "饮食时间数据")
//    public class UserInfo {
//        public UserInfo(String id, String time, String food) {
//            this.id = id;
//            this.time = time;
//            this.food = food;
//
//        }
//        @SmartColumn(id =0,name = "查询编号")
//        private String id;
//        @SmartColumn(id=1,name="饮食时间")
//        private String time;
//        @SmartColumn(id=2,name="图片类型")
//        private String food;
//
//    }

//    public static class UserInfo {
//        private String id;
//        private String time;
//        private String food;
//        private String url;
//        private Boolean operation;
//        private Boolean isTest;
//
//
//        public UserInfo(String id, String time, String food, boolean operation) {
//            this.id = id;
//            this.time = time;
//            this.food = food;
//            this.operation = operation;
//
//        }
//
//
//
//        public String getName() {
//            return id;
//        }
//
//
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//        public Boolean getOperation() {
//            return operation;
//        }
//
//        public void setOperation(Boolean operation) {
//            this.operation = operation;
//        }
//
//
//    }

//    public static class UserInfo_WeightSleep {
//        private String id;
//        private String time;
//        private String weight;
//        private String start_time;
//        private String end_time;
//        private Boolean operation;
//
//
//        public UserInfo_WeightSleep(String id, String time, String weight, String start_time,String end_time,boolean operation) {
//            this.id = id;
//            this.time = time;
//            this.weight = weight;
//            this.start_time = start_time;
//            this.end_time = end_time;
//            this.operation = operation;
//
//        }
//
//
//
//        public String getId() {
//            return id;
//        }
//        public void setId(String id) {
//            this.id = id;
//        }
//
//
//        public String getWeight() {
//            return weight;
//        }
//        public void setWeight(String weight) {
//            this.weight = weight;
//        }
//
//        public String getTime() {
//            return time;
//        }
//        public void setTime(String time) {
//            this.time = time;
//        }
//
//        public String getStart_time() {
//            return start_time;
//        }
//        public void setStart_time(String start_time) {
//            this.start_time = start_time;
//        }
//
//        public String getEnd_time() {
//            return end_time;
//        }
//        public void setEnd_time(String end_time) {
//            this.end_time = end_time;
//        }
//
//        public Boolean getOperation() {
//            return operation;
//        }
//        public void setOperation(Boolean operation) {
//            this.operation = operation;
//        }
//
//
//
//
//
//    }







//    @OnClick({R.id.delete_button, R.id.clear_delete_button,R.id.query,  R.id.syn})
    @OnClick({R.id.delete_button, R.id.query, R.id.query2})
    public void onViewClicked(View view) {
//        EditText insert_text = (EditText)findViewById(R.id.insert_text);
//        String insert_data = insert_text.getText().toString();


        String delete_data = "";

//        EditText update_before_text = (EditText)findViewById(R.id.update_before_text);
//        String update_before_data = update_before_text.getText().toString();
//        EditText update_after_text = (EditText)findViewById(R.id.update_after_text);
//        String update_after_data = update_after_text.getText().toString();

//        TextView textView = (TextView)findViewById(R.id.query_text);

        //新建了一个test_db的数据库
//        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path",null,1);
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();


        switch (view.getId()){
//            case R.id.insert_button:
//                ContentValues values = new ContentValues();
//                values.put("name",insert_data);
//                db.insert("user",null,values);
//                break;
//            case R.id.clear_insert_button:
//                insert_text.setText("");
//                break;
//            case R.id.query2:
//                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
//                SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
////        Cursor cursor = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, null, null, null, null, null);
//                Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url","photo_cal","photo_kind"}, null, null, null, null, null);
//                String result = getSynData();
//                List<String> user_time_list = new ArrayList<String>();
//
//                while(cursor2.moveToNext()){
//                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
//                    String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
//                    String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
//                    String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
//                    user_time_list.add(user_time);
//                }
//                String [] each_data = result.split("<");
//                for(int i  = 0; i < each_data.length; i ++){
//                    String [] temp_data = each_data[i].split(";");
//                    if(temp_data.length < 7){
//                        continue;
//                    }
//                    String user_name = temp_data[0];
//                    String user_time = temp_data[1];
//                    String photo_url = temp_data[2];
//                    String food_type = temp_data[3];
//                    String photo_cal = temp_data[4];
//                    String photo_kind = temp_data[5];
//                    String workday = temp_data[6];
//                    String weekend = temp_data[7];
//
//                    String temp_date = user_time.split("-")[0];
//                    String temp_time = user_time.split("-")[1];
//                    String temp_hour = temp_time.split(":")[0];
//                    String temp_minute = temp_time.split(":")[1];
//                    String temp_second = temp_time.split(":")[2];
//                    if(Integer.valueOf(temp_hour) < 10){
//                        temp_hour = "0" + temp_hour;
//                    }
//                    if(Integer.valueOf(temp_minute) < 10){
//                        temp_minute = "0" + temp_minute;
//                    }
//                    if(Integer.valueOf(temp_second) < 10){
//                        temp_second = "0" + temp_second;
//                    }
//                    String new_user_time = temp_date + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;
//                    if(!user_time_list.contains(new_user_time)){
//                        ContentValues values = new ContentValues();
//                        values.put("user_name", user_name);
//                        values.put("user_time", new_user_time);
//                        values.put("photo_url", photo_url);
//                        values.put("photo_type",food_type);
//                        values.put("photo_cal",photo_cal);
//                        values.put("photo_kind",photo_kind);
//                        values.put("workday",workday);
//                        values.put("weekend",weekend);
//                        db1.insert("photo_path_new", null, values);
//
//                    }else{
//                        ContentValues values = new ContentValues();
//                        values.put("photo_type",food_type);
//                        values.put("photo_cal",photo_cal);
//                        values.put("photo_kind",photo_kind);
//                        values.put("workday",workday);
//                        values.put("weekend",weekend);
//                        db1.update("photo_path_new", values, "user_time = ?", new String[]{new_user_time});
//                    }
//
//
//                }
//                cursor2.close();
//                db1.close();
//
//                break;
            case R.id.delete_button:
                if(Button_flag == 0){
                    if(name_selected.size() == 0){
                        XToastUtils.toast("Please select the data you want to delete");
                    }
                    else{
                        int is_flag = 0;
                        DialogLoader.getInstance().showConfirmDialog(
                                getContext(),
                                "Whether to delete the currently selected image",
                               "Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Calendar calendar1 = Calendar.getInstance();
                                        //��ȡϵͳ������
                                        //��
                                        int year1 = calendar1.get(Calendar.YEAR);
                                        //��
                                        int month1 = calendar1.get(Calendar.MONTH)+1;
                                        //��
                                        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                                        String current_time1 = String.valueOf(year1) + "." + String.valueOf(month1) + "." + String.valueOf(day1);
                                        List<String> temp_id_list = new ArrayList<>();
                                        List<String> temp_id_list_new = new ArrayList<>();
                                        List<String> temp_time_list = new ArrayList<>();

                                        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                                        SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                                        Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url","photo_cal","photo_kind"}, null, null, null, null, null);
                                        while(cursor2.moveToNext()){
                                            String id = cursor2.getString(cursor2.getColumnIndex("id"));
                                            String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                                            String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                                            String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                                            String[] temp1 = user_time.split("-");
                                            if(temp1[0].equals(current_time1)){
                                                temp_id_list_new.add(id);
                                                temp_time_list.add(user_time);
                                            }
                                        }

                                        for(int k = 0; k < name_selected.size(); k ++){
                                            String delete_data1 = name_selected.get(k);
                                            int list_index = -1;
                                            if(temp_id_list_new.contains(delete_data1)){
                                                list_index = temp_id_list_new.indexOf(delete_data1);
                                                String user_time1 = temp_time_list.get(list_index);
//                    String [] temp2 = user_time1.split("-");
//                    String temp_hour = temp2[1].split(":")[0];
//                    String temp_minute = temp2[1].split(":")[1];
//                    String temp_second = temp2[1].split(":")[2];
//                    if(Integer.valueOf(temp_hour) < 10){
//                        temp_hour = temp_hour.substring(1,2);
//                    }
//                    if(Integer.valueOf(temp_minute) < 10){
//                        temp_minute = temp_minute.substring(1,2);
//                    }
//                    if(Integer.valueOf(temp_second) < 10){
//                        temp_second = temp_second.substring(1,2);
//                    }
//                    String temp_current_time = temp2[0] + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;
                                                db1.delete("photo_path_new","id=?",new String[]{delete_data1});
                                                String [] temp2 = user_time1.split("-");
                                                String temp_hour = temp2[1].split(":")[0];
                                                String temp_minute = temp2[1].split(":")[1];
                                                String temp_second = temp2[1].split(":")[2];
                                                if(Integer.valueOf(temp_hour) < 10){
                                                    temp_hour = temp_hour.substring(1,2);
                                                }
                                                if(Integer.valueOf(temp_minute) < 10){
                                                    temp_minute = temp_minute.substring(1,2);
                                                }
                                                if(Integer.valueOf(temp_second) < 10){
                                                    temp_second = temp_second.substring(1,2);
                                                }
                                                String temp_current_time = temp2[0] + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;

                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        String delete_result = getDeleteData(temp_current_time);
                                                    }
                                                }).start();



//                    String delete_result = getDeleteData(temp_current_time);
//                    if(delete_result.equals("ok")){
//                        XToastUtils.toast("删除成功");
//                    }
                                            }
                                            else{
                                                XToastUtils.toast("Only the time of the current day can be deleted");
                                            }
//                                            if(list_index != -1){
//                                                while(cursor.moveToNext()){
//                                                    String id = cursor.getString(cursor.getColumnIndex("id"));
//                                                    String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
//                                                    String user_time = cursor.getString(cursor.getColumnIndex("user_time"));
//                                                    String photo_type = cursor.getString(cursor.getColumnIndex("photo_type"));
//                                                    String[] temp1 = user_time.split("-");
//                                                    if(temp1[0].equals(current_time1)){
//                                                        temp_id_list.add(id);
//                                                    }
//                                                }
//                                                Cursor cursor1 = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, "id=?", new String[]{temp_id_list.get(list_index)}, null, null, null,null);
//                                                String user_time1 = "";
//                                                while(cursor1.moveToNext()) {
//                                                    user_time1 = cursor1.getString(cursor1.getColumnIndex("user_time"));
//                                                }
//
//                                                String [] temp2 = user_time1.split("-");
//                                                String temp_hour = temp2[1].split(":")[0];
//                                                String temp_minute = temp2[1].split(":")[1];
//                                                String temp_second = temp2[1].split(":")[2];
//                                                if(Integer.valueOf(temp_hour) < 10){
//                                                    temp_hour = temp_hour.substring(1,2);
//                                                }
//                                                if(Integer.valueOf(temp_minute) < 10){
//                                                    temp_minute = temp_minute.substring(1,2);
//                                                }
//                                                if(Integer.valueOf(temp_second) < 10){
//                                                    temp_second = temp_second.substring(1,2);
//                                                }
//                                                String temp_current_time = temp2[0] + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;
//                                                db.delete("photo_path","id=?",new String[]{temp_id_list.get(list_index)});
//                                                String delete_result = getDeleteData(temp_current_time);
////                                                if(delete_result.equals("ok")){
////                                                    XToastUtils.toast("删除成功");
////                                                }
//
//                                            }
                                        }

                                        cursor2.close();
                                        db1.close();



                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                                                //SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                                                SQLiteDatabase db1 = databaseHelper1.getReadableDatabase();
                                                Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url"}, null, null, null, null, null);
//                        getTabledata(cursor2);


                                                List<UserInfo> list = new ArrayList<>();

                                                Calendar calendar = Calendar.getInstance();
                                                //��ȡϵͳ������
                                                //��
                                                int year = calendar.get(Calendar.YEAR);
                                                //��
                                                int month = calendar.get(Calendar.MONTH)+1;
                                                //��
                                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);


                                                old_id_list.clear();
                                                old_user_list.clear();
                                                old_photo_list.clear();
                                                old_photo_url_list.clear();
                                                while(cursor2.moveToNext()){

                                                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
                                                    String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                                                    String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                                                    String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                                                    String photo_url = cursor2.getString(cursor2.getColumnIndex("photo_url"));
                                                    String[] temp1 = user_time.split("-");
                                                    if(temp1[0].equals(current_time)){
                                                        old_id_list.add(id);
                                                        old_user_list.add(user_time);
                                                        old_photo_list.add(photo_type);
                                                        old_photo_url_list.add(photo_url);
                                                        //list.add(new UserInfo(id, user_time, photo_type));
                                                    }


                                                }


                                                cursor2.close();
                                                db1.close();
                                                mHandler1.obtainMessage(MSG_SUCCESS).sendToTarget();
                                            }
                                        }).start();


                                    }
                                },
                                "No",

                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                    XToastUtils.toast(temp_string);
                                        dialog.dismiss();

                                    }
                                }

                        );

                    }
                }
                else{
                    if(name_selected.size() == 0){
                        XToastUtils.toast("Please select the data you want to delete");
                    }
                    else{
                        int is_flag = 0;
                        DialogLoader.getInstance().showConfirmDialog(
                                getContext(),
                                "Whether to delete the selected data",
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Calendar calendar1 = Calendar.getInstance();
                                        //��ȡϵͳ������
                                        //��
                                        int year1 = calendar1.get(Calendar.YEAR);
                                        //��
                                        int month1 = calendar1.get(Calendar.MONTH)+1;
                                        //��
                                        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                                        String current_time1 = String.valueOf(year1) + "." + String.valueOf(month1) + "." + String.valueOf(day1);
                                        List<String> temp_id_list = new ArrayList<>();
                                        List<String> temp_id_list_new = new ArrayList<>();
                                        List<String> temp_time_list_new = new ArrayList<>();
                                        DatabaseHelper databaseHelper2 = new DatabaseHelper(getContext(),"body_index",null,1);
                                        SQLiteDatabase db2 = databaseHelper2.getWritableDatabase();
                                        Cursor cursor3 = db2.query("body_index", new String[]{"id","user_name","user_time","index_value","index_name"}, null, null, null, null, null);
                                        while(cursor3.moveToNext()){

                                            String id = cursor3.getString(cursor3.getColumnIndex("id"));
                                            String user_name = cursor3.getString(cursor3.getColumnIndex("user_name"));
                                            String upload_time = cursor3.getString(cursor3.getColumnIndex("user_time"));
                                            String start_time = cursor3.getString(cursor3.getColumnIndex("user_time"));
                                            String end_time = cursor3.getString(cursor3.getColumnIndex("index_name"));
                                            String[] temp1 = upload_time.split(" ");
//                                            temp1[0] = temp1[0].split("-")[0] + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[1])) + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[2]));
                                            temp1[0] = temp1[0].split("-")[0];
                                            if(temp1[0].equals(current_time1)){
                                                temp_id_list_new.add(id);
                                                temp_time_list_new.add(upload_time);
                                                //list.add(new UserInfo(id, user_time, photo_type));
                                            }


                                        }



                                        for(int k = 0; k < name_selected.size(); k ++){
                                            String delete_data1 = name_selected.get(k);
                                            int list_index = -1;
                                            if(temp_id_list_new.contains(delete_data1)){
                                                list_index = temp_id_list_new.indexOf(delete_data1);
                                                String upload_time = "";
                                                upload_time = temp_time_list_new.get(list_index);
                                                db2.delete("body_index","id=?",new String[]{delete_data1});



                                                String delete_result = getDeleteSleepWeightData(upload_time);
//                                                if(delete_result.equals("ok")){
//                                                    XToastUtils.toast("删除成功");
//                                                }
                                            }
                                            else{
                                                XToastUtils.toast("Only the time of the current day can be deleted");
                                            }

                                        }
                                        cursor3.close();
                                        db2.close();









                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                DatabaseHelper databaseHelper2 = new DatabaseHelper(getContext(),"body_index",null,1);
                                                SQLiteDatabase db2 = databaseHelper2.getWritableDatabase();
                                                Cursor cursor3 = db2.query("body_index", new String[]{"id","user_name","user_time","index_value","index_name"}, null, null, null, null, null);
                                                List<UserInfo> list = new ArrayList<>();

                                                Calendar calendar = Calendar.getInstance();
                                                //��ȡϵͳ������
                                                //��ȡϵͳ������
                                                //��
                                                int year = calendar.get(Calendar.YEAR);
                                                //��
                                                int month = calendar.get(Calendar.MONTH)+1;
                                                //��
                                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);
                                                id_list.clear();
                                                user_list.clear();
                                                weight_list.clear();
                                                start_time_list.clear();
                                                end_time_list.clear();


                                                while(cursor3.moveToNext()){

                                                    String id = cursor3.getString(cursor3.getColumnIndex("id"));
                                                    String user_name = cursor3.getString(cursor3.getColumnIndex("user_name"));
                                                    String upload_time = cursor3.getString(cursor3.getColumnIndex("user_time"));
                                                    String start_time = cursor3.getString(cursor3.getColumnIndex("index_value"));
                                                    String end_time = cursor3.getString(cursor3.getColumnIndex("index_name"));
                                                    String[] temp1 = upload_time.split(" ");
//                                                    temp1[0] = temp1[0].split("-")[0] + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[1])) + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[2]));
                                                    temp1[0] = temp1[0].split("-")[0];
                                                    if(temp1[0].equals(current_time)){
                                                        id_list.add(id);
                                                        user_list.add(upload_time);
                                                        start_time_list.add(start_time);
                                                        end_time_list.add(end_time);
                                                        //list.add(new UserInfo(id, user_time, photo_type));
                                                    }


                                                }
                                                cursor3.close();
                                                db2.close();
                                                getSleepWeightTabledata();
                                            }
                                        }).start();



//
//
//
//
//
//
//                                        databaseHelper2 = new DatabaseHelper(getContext(),"user_weight_sleep",null,1);
//                                        db2 = databaseHelper2.getWritableDatabase();
//                                        cursor3 = db2.query("user_weight_sleep", new String[]{"id","user_name","weight","start_time","end_time","upload_time"}, null, null, null, null, null);
//
//                                        getSleepWeightTabledata();
//                                        cursor3.close();
//                                        db2.close();

                                    }
                                },
                                "No",

                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                    XToastUtils.toast(temp_string);
                                        dialog.dismiss();

                                    }
                                }

                        );

                    }
                }
                //Cursor cursor1 = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, "id=?",new String[]{delete_data},null, null, null);

                break;
//            case R.id.clear_delete_button:
//                delete_text.setText("");
//                break;
            //更新数据按钮
//            case R.id.update_button:
//                ContentValues values2 = new ContentValues();
//                values2.put("user_time", update_after_data);
//                db.update("photo_path", values2, "user_time = ?", new String[]{update_before_data});
//                break;
//            //更新数据按钮后面的清除按钮
//            case R.id.clear_update_button:
//                update_before_text.setText("");
//                update_after_text.setText("");
//                break;

            //查询全部按钮
            case R.id.query:
                Button_flag = 0;






                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                        //SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                        SQLiteDatabase db1 = databaseHelper1.getReadableDatabase();
                        Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url"}, null, null, null, null, null);
//                        getTabledata(cursor2);


                        List<UserInfo> list = new ArrayList<>();

                        Calendar calendar = Calendar.getInstance();
                        //��ȡϵͳ������
                        //��
                        int year = calendar.get(Calendar.YEAR);
                        //��
                        int month = calendar.get(Calendar.MONTH)+1;
                        //��
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);


                        old_id_list.clear();
                        old_user_list.clear();
                        old_photo_list.clear();
                        old_photo_url_list.clear();
                        while(cursor2.moveToNext()){

                            String id = cursor2.getString(cursor2.getColumnIndex("id"));
                            String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                            String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                            String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                            String photo_url = cursor2.getString(cursor2.getColumnIndex("photo_url"));
                            String[] temp1 = user_time.split("-");
                            if(temp1[0].equals(current_time)){
                                old_id_list.add(id);
                                old_user_list.add(user_time);
                                old_photo_list.add(photo_type);
                                old_photo_url_list.add(photo_url);
                                //list.add(new UserInfo(id, user_time, photo_type));
                            }


                        }


                        cursor2.close();
                        db1.close();
                        mHandler1.obtainMessage(MSG_SUCCESS).sendToTarget();
                    }
                }).start();

                MMKVUtils.put("show_food_table", true);
























//                handler = new Handler();
//                //创建游标对象
//                //Cursor cursor = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, null, null, null, null, null);
//                //利用游标遍历所有数据对象
//                //为了显示全部，把所有对象连接起来，放到TextView中
//                //String textview_data = "";
//                List<UserInfo> list = new ArrayList<>();
//
//                Calendar calendar = Calendar.getInstance();
//                //��ȡϵͳ������
//                //��
//                int year = calendar.get(Calendar.YEAR);
//                //��
//                int month = calendar.get(Calendar.MONTH)+1;
//                //��
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);
//
//
//
//                List<String> id_list = new ArrayList<>();
//                List<String> user_list = new ArrayList<>();
//                List<String> photo_list = new ArrayList<>();
//                List<String> photo_url_list = new ArrayList<>();
//                while(cursor2.moveToNext()){
//
//                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
//                    String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
//                    String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
//                    String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
//                    String photo_url = cursor2.getString(cursor2.getColumnIndex("photo_url"));
//                    String[] temp1 = user_time.split("-");
//                    if(temp1[0].equals(current_time)){
//                        id_list.add(id);
//                        user_list.add(user_time);
//                        photo_list.add(photo_type);
//                        photo_url_list.add(photo_url);
//                        //list.add(new UserInfo(id, user_time, photo_type));
//                    }
//
//
//                }
//
//                final List<UserInfo> testData = new ArrayList<>();
//                Random random = new Random();
//                List<TanBean> tanBeans = TanBean.initDatas();
//                int urlSize = tanBeans.size();
//                for(int i = 0;i <id_list.size(); i++) {
//                    UserInfo userData = new UserInfo(id_list.get(i), user_list.get(i), photo_list.get(i), false);
//                    //userData.setUrl(tanBeans.get(i%urlSize).getUrl());
//                    userData.setUrl(photo_url_list.get(i));
//                    testData.add(userData);
//                }
//                nameColumn = new Column<>("序号", "id");
//                nameColumn.setAutoCount(true);
//                final Column<Integer> ageColumn = new Column<>("             拍照时间             ", "time",new MultiLineDrawFormat<Integer>(100));
//                ageColumn.setFixed(true);
//                ageColumn.setAutoCount(true);
//
//                int imgSize = DensityUtils.dp2px(getActivity(),50);
//                final Column<String> avatarColumn = new Column<>("图片", "url", new BitmapDrawFormat<String>(imgSize,imgSize) {
//                    @Override
//                    protected Bitmap getBitmap(final String s, String value, int position) {
//                        if(map.get(s)== null) {
//                            Glide.with(getActivity()).asBitmap().load(s)
//                                    .apply(bitmapTransform(new CenterCrop())).into(new SimpleTarget<Bitmap>() {
//                                @Override
//                                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
//                                    map.put(s, bitmap);
//                                    table.invalidate();
//                                }
//                            });
//                        }
//                        return map.get(s);
//                    }
//                });
//                avatarColumn.setFixed(true);
//                final IFormat<Long> format =  new IFormat<Long>() {
//                    @Override
//                    public String format(Long aLong) {
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.setTimeInMillis(aLong);
//                        return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
//                    }
//                };
//                final Column<Long> timeColumn = new Column<>("时间", "time",format);
//
//                int size = DensityUtils.dp2px(getActivity(),30);
//                operation = new Column<>("勾选", "operation", new ImageResDrawFormat<Boolean>(size,size) {    //设置"操作"这一列以图标显示 true、false 的状态
//                    @Override
//                    protected Context getContext() {
//                        return getActivity();
//                    }
//                    @Override
//                    protected int getResourceID(Boolean isCheck, String value, int position) {
//                        if(isCheck){
//                            return R.mipmap.check;      //将图标提前放入 app/res/mipmap 目录下
//                        }
//                        return R.mipmap.uncheck;
//                    }
//                });
//                operation.setComputeWidth(40);
//                operation.setOnColumnItemClickListener(new OnColumnItemClickListener<Boolean>() {
//                    @Override
//                    public void onClick(Column<Boolean> column, String value, Boolean bool, int position) {
////                Toast.makeText(CodeListActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
//                        if(operation.getDatas().get(position)){
//                            showName(position, false);
//                            operation.getDatas().set(position,false);
//
//                        }else{
//                            showName(position, true);
//                            operation.getDatas().set(position,true);
//
//
//                        }
//                        table.refreshDrawableState();
//                        table.invalidate();
//                    }
//                });
//
//
//
//
//
//                final TableData<UserInfo> tableData = new TableData<>("",testData,nameColumn,ageColumn,
//                        avatarColumn,operation);
//
//                FontStyle fontStyle = new FontStyle();
//                fontStyle.setTextColor(getResources().getColor(android.R.color.white));
//                MultiLineBubbleTip<Column> tip = new MultiLineBubbleTip<Column>(getActivity(),R.mipmap.round_rect,R.mipmap.triangle,fontStyle) {
//                    @Override
//                    public boolean isShowTip(Column column, int position) {
//                        if(column == nameColumn || column == avatarColumn){
//                            return true;
//                        }
//                        return false;
//                    }
//
//
//                    @Override
//                    public String[] format(Column column, int position) {
//                        UserInfo data = testData.get(position);
////                        String[] strings = {"批注","地址："+data.getUrl()};
//                        String[] strings = {""};
//                        new Thread() {
//                            public void run() {
//                                try {
//                                    // 通过url获得图片数据
//                                    data_img = GetUserHead(data.getUrl());
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                bitmap = BitmapFactory.decodeByteArray(data_img, 0, data_img.length);
//                                // 子线程runnable将会执行
//                                handler.post(runnable);
//                            }
//                        }.start();
//
//
//                        return strings;
//
//                    }
//                };
//                tip.setColorFilter(Color.parseColor("#FA8072"));
//                tip.setAlpha(0.8f);
//
////                tableData.setShowCount(true);
//                table = (SmartTable<UserInfo>) findViewById(R.id.table);
//                table.getProvider().setTip(tip);
//                table.getConfig().setShowXSequence(false);
//                table.getConfig().setShowYSequence(false);
//                table.setTableData(tableData);


//
//
//
//
//
//                table = findViewById(R.id.table);
//                table.setData(list);
//                final TableData<UserInfo> tableData = new TableData<>("测试",testData,nameColumn,
//                        avatarColumn,column4,column5,column6,column7,column8,column9,totalColumn,totalColumn1,totalColumn2,timeColumn);
//                tableData.setShowCount(true);
//                table.getConfig().setShowTableTitle(true);
//                textView.setText(textview_data);
                break;
            //查询全部按钮下面的清除查询按钮
//            case R.id.clear_query:
//                //textView.setText("");
//                //textView.setHint("查询内容为空");
//
//                break;
            case R.id.query2:
                Button_flag = 1;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseHelper databaseHelper2 = new DatabaseHelper(getContext(),"body_index",null,1);
                        SQLiteDatabase db2 = databaseHelper2.getWritableDatabase();
                        Cursor cursor3 = db2.query("body_index", new String[]{"id","user_name","user_time","index_value","index_name"}, null, null, null, null, null);
                        List<UserInfo> list = new ArrayList<>();

                        Calendar calendar = Calendar.getInstance();
                        //��ȡϵͳ������
                        //��ȡϵͳ������
                        //��
                        int year = calendar.get(Calendar.YEAR);
                        //��
                        int month = calendar.get(Calendar.MONTH)+1;
                        //��
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);
                        id_list.clear();
                        user_list.clear();
                        weight_list.clear();
                        start_time_list.clear();
                        end_time_list.clear();


                        while(cursor3.moveToNext()){

                            String id = cursor3.getString(cursor3.getColumnIndex("id"));
                            String user_name = cursor3.getString(cursor3.getColumnIndex("user_name"));
                            String upload_time = cursor3.getString(cursor3.getColumnIndex("user_time"));
                            String start_time = cursor3.getString(cursor3.getColumnIndex("index_value"));
                            String end_time = cursor3.getString(cursor3.getColumnIndex("index_name"));
                            String[] temp1 = upload_time.split(" ");
//                            temp1[0] = temp1[0].split("-")[0] + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[1])) + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[2]));
                            temp1[0] = temp1[0].split("-")[0];

                            if(temp1[0].equals(current_time)){
                                id_list.add(id);
                                user_list.add(upload_time);
                                start_time_list.add(start_time);
                                end_time_list.add(end_time);
                                //list.add(new UserInfo(id, user_time, photo_type));
                            }


                        }
                        cursor3.close();
                        db2.close();
                        getSleepWeightTabledata();
                    }
                }).start();





                MMKVUtils.put("show_food_table", false);

            default:
                break;
        }
//        cursor.close();
//        cursor2.close();
//        db.close();
//        db1.close();
    }

    Runnable runnable = new Runnable() {

        public void run() {

            getImg.setImageBitmap(bitmap);
            getImg.setVisibility(View.VISIBLE);
            getImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getImg.setVisibility(View.INVISIBLE);
                }
            });


        }
    };


    static class StreamTool {

        public static byte[] readInputStream(InputStream inputStream)throws IOException {
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            return bos.toByteArray();
        }
    }

    public static byte[] GetUserHead(String urlpath) throws IOException {
        URL url = new URL(urlpath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // 设置请求方法为GET
        conn.setReadTimeout(5 * 1000); // 设置请求过时时间为5秒
        InputStream inputStream = conn.getInputStream(); // 通过输入流获得图片数据

        byte[] data = StreamTool.readInputStream(inputStream); // 获得图片的二进制数据
        return data;

    }





    public static int[] Arraysort(Double[] arr, boolean desc) {
        double temp;
        int index;
        int k = arr.length;
        int[] Index = new int[k];
        for (int i = 0; i < k; i++) {
            Index[i] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (desc) {
                    if (arr[j] < arr[j + 1]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;

                        index = Index[j];
                        Index[j] = Index[j + 1];
                        Index[j + 1] = index;
                    }
                } else {
                    if (arr[j] > arr[j + 1]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;

                        index = Index[j];
                        Index[j] = Index[j + 1];
                        Index[j + 1] = index;
                    }
                }
            }
        }
        return Index;
    }

    /**
     * 排序并返回对应原始数组的下标【默认升序】
     *
     * @param arr
     * @return
     */
    public static int[] Arraysort(Double[] arr) {
        return Arraysort(arr, false);
    }



























    public void getTabledata()
    {
        handler = new Handler();

        //创建游标对象
        //Cursor cursor = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中
        //String textview_data = "";
        List<UserInfo> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        //��ȡϵͳ������
        //��
        int year = calendar.get(Calendar.YEAR);
        //��
        int month = calendar.get(Calendar.MONTH)+1;
        //��
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);





        List<Double> new_time_list = new ArrayList<>();
        for(int j =0; j < old_user_list.size(); j++){
            String temp_time = old_user_list.get(j);
            String temp_time_hour = temp_time.split("-")[1];
            double temp_hour = Double.valueOf(temp_time_hour.split(":")[0]);
            double temp_minute = Double.valueOf(temp_time_hour.split(":")[1]);
            double temp_second = Double.valueOf(temp_time_hour.split(":")[2]);
            new_time_list.add(temp_hour * 60 * 60 + temp_minute * 60 + temp_second);

        }

        Double[] sort_array = new_time_list.toArray(new Double[new_time_list.size()]);
        int[] index = Arraysort(sort_array);
        List<String> id_list = new ArrayList<>();
        List<String> user_list = new ArrayList<>();
        List<String> photo_list = new ArrayList<>();
        List<String> photo_url_list = new ArrayList<>();


        for(int j = 0; j < index.length; j ++){
            id_list.add(old_id_list.get(index[j]));
            user_list.add(old_user_list.get(index[j]));
            photo_list.add(old_photo_list.get(index[j]));
            photo_url_list.add(old_photo_url_list.get(index[j]));
        }





        final List<UserInfo> testData = new ArrayList<>();
        Random random = new Random();
        List<TanBean> tanBeans = TanBean.initDatas();
        int urlSize = tanBeans.size();
        for(int i = 0;i <id_list.size(); i++) {
            UserInfo userData = new UserInfo(id_list.get(i), user_list.get(i), photo_list.get(i), false);
            //userData.setUrl(tanBeans.get(i%urlSize).getUrl());
            userData.setUrl(photo_url_list.get(i));
            testData.add(userData);
        }
        nameColumn = new Column<>("ID", "id");
        nameColumn.setAutoCount(true);
        final Column<String> ageColumn = new Column<>("                 Upload time                 ", "time",new MultiLineDrawFormat<String>(100));
        ageColumn.setFixed(true);
        ageColumn.setAutoCount(true);
        int imgSize = DensityUtils.dp2px(getActivity(),50);
        final Column<String> avatarColumn = new Column<>("  Photo  ", "url", new BitmapDrawFormat<String>(imgSize,imgSize) {
            @Override
            protected Bitmap getBitmap(final String s, String value, int position) {
                if(map.get(s)== null) {
                    Glide.with(getActivity()).asBitmap().load(s)
                            .apply(bitmapTransform(new CenterCrop())).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            map.put(s, bitmap);
                            table1.invalidate();
                        }
                    });
                }
                return map.get(s);
            }
        });
        avatarColumn.setFixed(true);
        final IFormat<Long> format =  new IFormat<Long>() {
            @Override
            public String format(Long aLong) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(aLong);
                return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            }
        };
        final Column<Long> timeColumn = new Column<>("Time", "time",format);

        int size = DensityUtils.dp2px(getActivity(),30);
        operation = new Column<>("  Select  ", "operation", new ImageResDrawFormat<Boolean>(size,size) {    //设置"操作"这一列以图标显示 true、false 的状态
            @Override
            protected Context getContext() {
                return getActivity();
            }
            @Override
            protected int getResourceID(Boolean isCheck, String value, int position) {
                if(isCheck){
                    return R.mipmap.check;      //将图标提前放入 app/res/mipmap 目录下
                }
                return R.mipmap.uncheck;
            }
        });
        operation.setComputeWidth(40);
        operation.setOnColumnItemClickListener(new OnColumnItemClickListener<Boolean>() {
            @Override
            public void onClick(Column<Boolean> column, String value, Boolean bool, int position) {
//                Toast.makeText(CodeListActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                if(operation.getDatas().get(position)){
                    showName(position, false);
                    operation.getDatas().set(position,false);

                }else{
                    showName(position, true);
                    operation.getDatas().set(position,true);


                }
                table1.refreshDrawableState();
                table1.invalidate();
            }
        });





        final TableData<UserInfo> tableData = new TableData<>("",testData,nameColumn,ageColumn,
                avatarColumn,operation);

        FontStyle fontStyle = new FontStyle();
        fontStyle.setTextColor(getResources().getColor(android.R.color.white));
        MultiLineBubbleTip<Column> tip = new MultiLineBubbleTip<Column>(getActivity(),R.mipmap.round_rect,R.mipmap.triangle,fontStyle) {
            @Override
            public boolean isShowTip(Column column, int position) {
                if(column == nameColumn || column == avatarColumn){
                    return true;
                }
                return false;
            }


//            @Override
//            public String[] format(Column column, int position) {
//                UserInfo data = testData.get(position);
////                        String[] strings = {"批注","地址："+data.getUrl()};
//                String[] strings = {""};
//                new Thread() {
//                    public void run() {
//                        try {
//
//                            // 通过url获得图片数据
//                            data_img = GetUserHead(data.getUrl());
//                            data_img = GetUserHead(data.getName());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        bitmap = BitmapFactory.decodeByteArray(data_img, 0, data_img.length);
//                        // 子线程runnable将会执行
//                        handler.post(runnable);
//                    }
//                }.start();


                @Override
                public String[] format(Column column, int position) {
                    UserInfo data = testData.get(position);
//                        String[] strings = {"批注","地址："+data.getUrl()};
                    String[] strings = {""};
                    new Thread() {
                        public void run() {
                            try {
                                FileInputStream fis = new FileInputStream(data.getUrl());
                                bitmap = BitmapFactory.decodeStream(fis);
//                                Uri uri = data.getName();
//                                String img_url = uri.getPath();//这是本机的图片路径
//
//                                // 通过url获得图片数据
//                                data_img = GetUserHead(data.getUrl());
//                                data_img = GetUserHead(data.getName());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

//                            bitmap = BitmapFactory.decodeByteArray(data_img, 0, data_img.length);
                            // 子线程runnable将会执行
                            handler.post(runnable);
                        }
                    }.start();


                return strings;

            }
        };
        tip.setColorFilter(Color.parseColor("#FA8072"));
        tip.setAlpha(0.8f);

//                tableData.setShowCount(true);
//        table.setSortColumn(ageColumn, false);
//        table = (SmartTable<UserInfo>) findViewById(R.id.table);
        table1.getProvider().setTip(tip);
        table1.getConfig().setShowXSequence(false);
        table1.getConfig().setShowYSequence(false);
        table1.setTableData(tableData);

    }



    public void getSleepWeightTabledata()
    {
//        handler = new Handler();
        //创建游标对象
        //Cursor cursor = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中
        //String textview_data = "";
//        List<UserInfo> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        //��ȡϵͳ������
        //��ȡϵͳ������
        //��
        int year = calendar.get(Calendar.YEAR);
        //��
        int month = calendar.get(Calendar.MONTH)+1;
        //��
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);



//        List<String> id_list = new ArrayList<>();
//        List<String> user_list = new ArrayList<>();
//        List<String> weight_list = new ArrayList<>();
//        List<String> start_time_list = new ArrayList<>();
//        List<String> end_time_list = new ArrayList<>();
//        while(cursor2.moveToNext()){
//
//            String id = cursor2.getString(cursor2.getColumnIndex("id"));
//            String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
//            String weight = cursor2.getString(cursor2.getColumnIndex("weight"));
//            String start_time = cursor2.getString(cursor2.getColumnIndex("start_time"));
//            String end_time = cursor2.getString(cursor2.getColumnIndex("end_time"));
//            String upload_time = cursor2.getString(cursor2.getColumnIndex("upload_time"));
//            String[] temp1 = upload_time.split(" ");
//            temp1[0] = temp1[0].split("-")[0] + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[1])) + "." + String.valueOf(Integer.valueOf(temp1[0].split("-")[2]));
//            if(temp1[0].equals(current_time)){
//                id_list.add(id);
//                user_list.add(upload_time);
//                weight_list.add(weight);
//                start_time_list.add(start_time);
//                end_time_list.add(end_time);
//                //list.add(new UserInfo(id, user_time, photo_type));
//            }
//
//
//        }

        List<UserInfo_WeightSleep> testData = new ArrayList<>();
        List<TanBean> tanBeans = TanBean.initDatas();
        for(int i = 0;i <id_list.size(); i++) {
            UserInfo_WeightSleep userData = new UserInfo_WeightSleep(id_list.get(i), user_list.get(i), start_time_list.get(i), end_time_list.get(i), false);
            //UserInfo_WeightSleep userData = new UserInfo_WeightSleep(String.valueOf(user_list.get(i)));
            testData.add(userData);
        }

        nameColumn = new Column<>("ID", "id");
        nameColumn.setFixed(true);
        nameColumn.setAutoCount(true);
        Column<String> ageColumn = new Column<>("Upload time", "time",new MultiLineDrawFormat<String>(100));


//        final Column<String> weightColumn = new Column<>("Weight", "weight",new MultiLineDrawFormat<String>(100));
//        weightColumn.setFixed(true);
//        weightColumn.setAutoCount(true);

        final Column<String> starttimeColumn = new Column<>("Value", "start_time",new MultiLineDrawFormat<String>(100));
        starttimeColumn.setFixed(true);
        starttimeColumn.setAutoCount(true);

        final Column<String> endtimeColumn = new Column<>("Body metrics", "end_time",new MultiLineDrawFormat<String>(100));
        endtimeColumn.setFixed(true);
        endtimeColumn.setAutoCount(true);

//        int imgSize = DensityUtils.dp2px(getActivity(),50);
//        final Column<String> avatarColumn = new Column<>("图片", "url", new BitmapDrawFormat<String>(imgSize,imgSize) {
//            @Override
//            protected Bitmap getBitmap(final String s, String value, int position) {
//                if(map.get(s)== null) {
//                    Glide.with(getActivity()).asBitmap().load(s)
//                            .apply(bitmapTransform(new CenterCrop())).into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
//                            map.put(s, bitmap);
//                            table.invalidate();
//                        }
//                    });
//                }
//                return map.get(s);
//            }
//        });
//        avatarColumn.setFixed(true);
        final IFormat<Long> format =  new IFormat<Long>() {
            @Override
            public String format(Long aLong) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(aLong);
                return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            }
        };
        final Column<Long> timeColumn = new Column<>("时间", "time",format);

        int size = DensityUtils.dp2px(getActivity(),30);
        operation = new Column<>("Select", "operation", new ImageResDrawFormat<Boolean>(size,size) {    //设置"操作"这一列以图标显示 true、false 的状态
            @Override
            protected Context getContext() {
                return getActivity();
            }
            @Override
            protected int getResourceID(Boolean isCheck, String value, int position) {
                if(isCheck){
                    return R.mipmap.check;      //将图标提前放入 app/res/mipmap 目录下
                }
                return R.mipmap.uncheck;
            }
        });
        operation.setComputeWidth(40);
        operation.setOnColumnItemClickListener(new OnColumnItemClickListener<Boolean>() {
            @Override
            public void onClick(Column<Boolean> column, String value, Boolean bool, int position) {
//                Toast.makeText(CodeListActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                if(operation.getDatas().get(position)){
                    showName(position, false);
                    operation.getDatas().set(position,false);

                }else{
                    showName(position, true);
                    operation.getDatas().set(position,true);


                }
                table.refreshDrawableState();
                //table.invalidate();
                table.postInvalidate();
            }
        });



//        TableData<UserInfo_WeightSleep> tableData = new TableData<UserInfo_WeightSleep>("test",testData,ageColumn);

        final TableData<UserInfo_WeightSleep> tableData = new TableData<>("",testData,nameColumn,ageColumn,
                starttimeColumn,endtimeColumn,operation);


        FontStyle fontStyle = new FontStyle();
        fontStyle.setTextColor(getResources().getColor(android.R.color.white));


//                tableData.setShowCount(true);

        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.setTableData(tableData);



//        List<MergeInfo> list = new ArrayList<>();
//        for(int i = 0;i <id_list.size(); i++) {
//           // UserInfo_WeightSleep userData = new UserInfo_WeightSleep(id_list.get(i), user_list.get(i), weight_list.get(i), start_time_list.get(i), end_time_list.get(i), false);
//            MergeInfo userData = new MergeInfo(String.valueOf(user_list.get(i)));
//            list.add(userData);
//        }
//
//        Column<String> ageColumn = new Column<>("拍照时间", "time",new MultiLineDrawFormat<String>(100));
//        TableData<MergeInfo> tableData = new TableData<MergeInfo>("test",list,ageColumn);
////        for(int i = 0;i <50; i++) {
////            list.add(new MergeInfo("huangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("huangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("huangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("huangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("lihuangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("lihuangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("lihuangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////            list.add(new MergeInfo("lihuangyanbinhuangyanbinhuangyanbinhuangyanbin"));
////        }
//        table.setTableData(tableData);
//        table.getConfig().setShowTableTitle(false);
//        table.setZoom(true,2,0.2f);
    }

    public String getDeleteData(String user_time)
    {
        String string = "none";
        int serverResponseCode = 0;
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/delete.php?androidid="+androidid+"&user_time="+user_time+"";
        Request request = new Request.Builder()
                .url(url1)
                .build();
        String res = "";
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


    public String getDeleteSleepWeightData(String user_time)
    {
        String string = "none";
        int serverResponseCode = 0;
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/delete_sleep_weight.php?androidid="+androidid+"&user_time="+user_time+"";
        Request request = new Request.Builder()
                .url(url1)
                .build();
        String res = "";
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


    public String getSynData()
    {
        String string = "none";
        int serverResponseCode = 0;
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
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
        String res = "";
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


    public String getUrlPhoto(String user_time)
    {
        String string = "none";
        int serverResponseCode = 0;
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/syn_database.php?androidid="+androidid+"&user_time="+user_time+"";
        Request request = new Request.Builder()
                .url(url1)
                .build();
        String res = "";
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

    /**
     * 收集所有被勾选的姓名记录到 name_selected 集合中，并实时更新
     * @param position  被选择记录的行数，根据行数用来找到其他列中该行记录对应的信息
     * @param selectedState   当前的操作状态：选中 || 取消选中
     */
    public void showName(int position, boolean selectedState){

        List<String> rotorIdList = nameColumn.getDatas();
        if(position >-1){
            String rotorTemp = rotorIdList.get(position);
            if(selectedState && !name_selected.contains(rotorTemp)){            //当前操作是选中，并且“所有选中的姓名的集合”中没有该记录，则添加进去
                name_selected.add(rotorTemp);
            }else if(!selectedState && name_selected.contains(rotorTemp)){     // 当前操作是取消选中，并且“所有选中姓名的集合”总含有该记录，则删除该记录
                name_selected.remove(rotorTemp);
            }
        }
        for(String s:name_selected){
            System.out.print(s + " -- ");
        }
    }




        private Handler mHandler1 = new Handler() {
        public void handleMessage (Message msg) {//此方法在ui线程运行
            switch(msg.what) {
                case MSG_SUCCESS:
                    getTabledata();





                    break;

                case MSG_FAILURE:

                    break;
            }
        }
    };







    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {


        //Log.e("-----------","---reuploadedrun");
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }


    }


    protected void onVisible() {

       lazyLoad();
    }

    protected void lazyLoad() {

//        if(firstStartFlag == 0){
//            firstStartFlag = 1;
//        }
//        else{
            final Button btn = findViewById(R.id.xyg);
            boolean isShowFood = MMKVUtils.getBoolean("show_food_table", true);
//            if(isShowFood){
//
//                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
//                //SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
//                SQLiteDatabase db1 = databaseHelper1.getReadableDatabase();
//                Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url"}, null, null, null, null, null);
//                getTabledata(cursor2);
//                cursor2.close();
//                db1.close();
//
//
//            }
            if(isShowFood){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                        //SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                        SQLiteDatabase db1 = databaseHelper1.getReadableDatabase();
                        Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url"}, null, null, null, null, null);
//                        getTabledata(cursor2);


                        List<UserInfo> list = new ArrayList<>();

                        Calendar calendar = Calendar.getInstance();
                        //��ȡϵͳ������
                        //��
                        int year = calendar.get(Calendar.YEAR);
                        //��
                        int month = calendar.get(Calendar.MONTH)+1;
                        //��
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);


                        old_id_list.clear();
                        old_user_list.clear();
                        old_photo_list.clear();
                        old_photo_url_list.clear();
                        while(cursor2.moveToNext()){

                            String id = cursor2.getString(cursor2.getColumnIndex("id"));
                            String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                            String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                            String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                            String photo_url = cursor2.getString(cursor2.getColumnIndex("photo_url"));
                            String[] temp1 = user_time.split("-");
                            if(temp1[0].equals(current_time)){
                                old_id_list.add(id);
                                old_user_list.add(user_time);
                                old_photo_list.add(photo_type);
                                old_photo_url_list.add(photo_url);
                                //list.add(new UserInfo(id, user_time, photo_type));
                            }


                        }


                        cursor2.close();
                        db1.close();
                        mHandler1.obtainMessage(MSG_SUCCESS).sendToTarget();
                    }
                }).start();


            }
            else{

            }
        //}





    }

    protected void onInvisible() {


    }

}
