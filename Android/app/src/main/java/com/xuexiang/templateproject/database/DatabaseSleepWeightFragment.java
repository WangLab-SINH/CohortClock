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

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.view.View;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.core.SmartTable;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 首页动态
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Page(name = "数据库1",anim = CoreAnim.none)
public class DatabaseSleepWeightFragment extends BaseFragment {
    private SmartTable table;

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


    }

    @Override
    protected void initListeners() {


    }

    @com.bin.david.form.annotation.SmartTable(name = "饮食时间数据")
    public class UserInfo {
        public UserInfo(String id, String weight, String start_time, String end_time, String upload_time) {
            this.id = id;
            this.weight = weight;
            this.start_time = start_time;
            this.end_time = end_time;
            this.upload_time = upload_time;

        }
        @SmartColumn(id =0,name = "查询编号")
        private String id;
        @SmartColumn(id=1,name="体重")
        private String weight;
        @SmartColumn(id=2,name="入睡时间")
        private String start_time;
        @SmartColumn(id=2,name="起床时间")
        private String end_time;
        @SmartColumn(id=2,name="上传时间")
        private String upload_time;

    }


    @OnClick({R.id.query})
    public void onViewClicked(View view) {
//        EditText insert_text = (EditText)findViewById(R.id.insert_text);
//        String insert_data = insert_text.getText().toString();

//        EditText delete_text = (EditText)findViewById(R.id.delete_text);
//        String delete_data = delete_text.getText().toString();

//        EditText update_before_text = (EditText)findViewById(R.id.update_before_text);
//        String update_before_data = update_before_text.getText().toString();
//        EditText update_after_text = (EditText)findViewById(R.id.update_after_text);
//        String update_after_data = update_after_text.getText().toString();

//        TextView textView = (TextView)findViewById(R.id.query_text);

        //新建了一个test_db的数据库
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"user_weight_sleep",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("user_weight_sleep", new String[]{"id","user_name","weight","start_time","end_time","upload_time"}, null, null, null, null, null);
        switch (view.getId()){
//            case R.id.insert_button:
//                ContentValues values = new ContentValues();
//                values.put("name",insert_data);
//                db.insert("user",null,values);
//                break;
//            case R.id.clear_insert_button:
//                insert_text.setText("");
//                break;

//            case R.id.delete_button:
//
//                break;
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
                //创建游标对象
                //Cursor cursor = db.query("photo_path", new String[]{"id","user_name","user_time","photo_type"}, null, null, null, null, null);
                //利用游标遍历所有数据对象
                //为了显示全部，把所有对象连接起来，放到TextView中
                //String textview_data = "";
                List<UserInfo> list = new ArrayList<>();

//                Calendar calendar = Calendar.getInstance();
//                //��ȡϵͳ������
//                //��
//                int year = calendar.get(Calendar.YEAR);
//                //��
//                int month = calendar.get(Calendar.MONTH)+1;
//                //��
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                String current_time = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);


                while(cursor.moveToNext()){
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
                    String weight = cursor.getString(cursor.getColumnIndex("weight"));
                    String start_time = cursor.getString(cursor.getColumnIndex("start_time"));
                    String end_time = cursor.getString(cursor.getColumnIndex("end_time"));
                    String upload_time = cursor.getString(cursor.getColumnIndex("upload_time"));

                        list.add(new UserInfo(id, weight, start_time, end_time, upload_time));



                }
                table = findViewById(R.id.table);
                table.setData(list);
                //textView.setText(textview_data);
                break;
            //查询全部按钮下面的清除查询按钮
//            case R.id.clear_query:
//                //textView.setText("");
//                //textView.setHint("查询内容为空");
//
//                break;

            default:
                break;
        }
    }


    public String getDeleteData(String user_time)
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
}
