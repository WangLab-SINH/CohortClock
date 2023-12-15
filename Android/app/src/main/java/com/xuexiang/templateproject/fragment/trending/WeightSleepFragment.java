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

package com.xuexiang.templateproject.fragment.trending;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.MainActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.templateproject.fragment.profile.HttpResponeCallBack;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.picker.RulerView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xutil.data.DateUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "更新信息",anim = CoreAnim.none)
public class WeightSleepFragment extends BaseFragment {
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/user_info.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;
    ProgressDialog dialog;

    @BindView(R.id.rulerView)
    RulerView rulerView;


    String weight = "null";
    String start_time = "null";
    String end_time = "null";
    String weight_time = "null";
    String sim = "null";

    private String[] mTimeOption;
    private TimePickerView mTimePickerDialog;
    private TimePickerView mTimePicker;
    private TimePickerView mTimePicker1;
    private TimePickerView mDatePicker;
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weight_sleep;
    }

    @Override
    protected void initViews() {
        float mmkv_weight = Float.valueOf(MMKVUtils.getString("latest_weight", "61.7"));
        rulerView.setFirstScale(mmkv_weight);
        initTabControlView();


    }

    @Override
    protected void initListeners() {

    }

    //    private void initValidationEt() {
//        mEtCheck.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
//        mEtAutoCheck.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
//        mEtAutoCheck.addValidator(new RegexpValidator(getString(R.string.tip_number_only_error_message), getString(R.string.regexp_number_only)));
//    }
    private void initTabControlView() {


    }

    @OnClick({R.id.button_yes, R.id.btn_time_period_2, R.id.btn_time_period_3, R.id.btn_date_system})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_yes:
                if((start_time.equals("null") && !end_time.equals("null")) ||(!start_time.equals("null") && end_time.equals("null"))) {
                    XToastUtils.toast("Please enter your bedtime and wake up time correctly");
                }
                else{
                    if((start_time.equals("null")) && (end_time.equals("null"))){
                        weight = String.valueOf(rulerView.getCurrentValue());

                        MMKVUtils.put("latest_weight", weight);

                        Date date = new Date();
                        String upload_time = date.toLocaleString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Calendar cal=Calendar.getInstance();
//                    int h=cal.get(Calendar.HOUR_OF_DAY);
//                    int mi=cal.get(Calendar.MINUTE);


//                    if(Integer.parseInt(select_hour) > h) {
//                        Toast.makeText(getActivity(), "补充上传时间不能超过当前时间", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        if(Integer.parseInt(select_hour) == h && Integer.parseInt(select_minute) > mi){
//
//                            Toast.makeText(getActivity(), "补充上传时间不能超过当前时间", Toast.LENGTH_SHORT).show();
//
//                        }


                        if(weight_time == "null"){
                            sim = dateFormat.format(date);
                        }
                        else{
                            sim = weight_time + " " + dateFormat.format(date).split(" ")[1];
                        }
//
//                    String temp = sim.split(" ")[0];
//                    String select_hour = temp.split()
////                        weight
//                    if()

                        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"user_weight_sleep",null,1);
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("user_name",androidid);
                        values.put("weight",weight);
                        values.put("start_time",start_time);
                        values.put("end_time",end_time);
                        values.put("upload_time",sim);
                        db.insert("user_weight_sleep",null,values);
                        db.close();

                        Calendar calendar = Calendar.getInstance();

                        calendar.setTime(DateUtils.getNowDate());
                        String current_time = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + '_' + String.valueOf(calendar.get(Calendar.MINUTE))+ '_' + String.valueOf(calendar.get(Calendar.SECOND));

                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH)+1;
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
                        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"body_index",null,1);
                        SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();


                        ContentValues values4 = new ContentValues();
                        values4.put("user_name", androidid);
                        values4.put("user_time", time_string);
                        values4.put("index_value", weight);
                        values4.put("index_name","Weight (kg)");
                        db1.insert("body_index", null, values4);
                        db1.close();
                        final MainActivity mainActivity = (MainActivity) getActivity();
//                                    Bundle bundle=getArguments();
//                                    bundle.putString("one","2");

                        mainActivity.onPageSelected(9);

//                    dialog = ProgressDialog.show(getActivity(), "", "...", true);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result = "none";
                                //creating new thread to handle Http Operations
                                Looper.prepare();
                                String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                                result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Weight (kg)");

                                if(result.equals("no"))
                                {
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                }
                                else if(result.equals("yes"))
                                {
                                    Toast.makeText(getActivity(), "Submitted successfully", Toast.LENGTH_SHORT).show();

                                }
                                else if(result.equals("none"))
                                {
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//                            popToBack();
//                            Looper.loop();

                                //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();

//	                    	Map<String, String> params = new HashMap<String, String>();
//	                        params.put("user_name", "asd");
//	                        params.put("user_name", "123");
//	                        String result = null;
//							try {
//								result = sendPostMessage(params,"utf-8");
//							} catch (MalformedURLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	                        System.out.println("result->"+result);
                            }
                        }).start();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result = "none";
                                //creating new thread to handle Http Operations
                                Looper.prepare();
                                result = getRegistData1(weight, start_time, end_time, sim);

                                if (result.equals("no")) {
                                    Toast.makeText(getActivity(), "The user name or password is incorrect", Toast.LENGTH_SHORT).show();
                                } else if (result.equals("yes")) {
                                    Toast.makeText(getActivity(), "Login successfully\n", Toast.LENGTH_SHORT).show();

                                } else if (result.equals("none")) {
                                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(getActivity(), "Submit successfully\n", Toast.LENGTH_SHORT).show();

//                            Looper.loop();
//                            dialog.dismiss();
                                //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();

//	                    	Map<String, String> params = new HashMap<String, String>();
//	                        params.put("user_name", "asd");
//	                        params.put("user_name", "123");
//	                        String result = null;
//							try {
//								result = sendPostMessage(params,"utf-8");
//							} catch (MalformedURLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	                        System.out.println("result->"+result);
                            }

                        }).start();






                    }
                    else{
                        weight = String.valueOf(rulerView.getCurrentValue());

                        MMKVUtils.put("latest_weight", weight);

                        SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd HH:mm");//创建日期转换对象：年月日 时分秒
                        //假设 设定日期是 2018-11-11 11:11:11
                        Date today = new Date(); 	     //今天 实际日期是  Debug：Wed Nov 12 12:00:18 CST 2018
                        boolean flag = true;
                        if(start_time != "null" && end_time != "null") {
                            try {
                                Date dateTemp = sdf.parse(start_time);    //转换为 date 类型 Debug：
                                Date dateDWeight = sdf.parse(end_time);
                                flag = dateTemp.getTime() <= dateDWeight.getTime();

                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }

                        }
                        if(flag == false){
                            XToastUtils.toast("Please enter your bedtime and wake up time correctly");
                        }
                        else {
                            Date date = new Date();
                            String upload_time = date.toLocaleString();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Calendar cal=Calendar.getInstance();
//                    int h=cal.get(Calendar.HOUR_OF_DAY);
//                    int mi=cal.get(Calendar.MINUTE);


//                    if(Integer.parseInt(select_hour) > h) {
//                        Toast.makeText(getActivity(), "补充上传时间不能超过当前时间", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        if(Integer.parseInt(select_hour) == h && Integer.parseInt(select_minute) > mi){
//
//                            Toast.makeText(getActivity(), "补充上传时间不能超过当前时间", Toast.LENGTH_SHORT).show();
//
//                        }


                            if(weight_time == "null"){
                                sim = dateFormat.format(date);
                            }
                            else{
                                sim = weight_time + " " + dateFormat.format(date).split(" ")[1];
                            }
//
//                    String temp = sim.split(" ")[0];
//                    String select_hour = temp.split()
////                        weight
//                    if()

                            String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                            DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"user_weight_sleep",null,1);
                            SQLiteDatabase db = databaseHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("user_name",androidid);
                            values.put("weight",weight);
                            values.put("start_time",start_time);
                            values.put("end_time",end_time);
                            values.put("upload_time",sim);
                            db.insert("user_weight_sleep",null,values);
                            db.close();

                            Calendar calendar = Calendar.getInstance();

                            calendar.setTime(DateUtils.getNowDate());
                            String current_time = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + '_' + String.valueOf(calendar.get(Calendar.MINUTE))+ '_' + String.valueOf(calendar.get(Calendar.SECOND));

                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH)+1;
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
                            DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"body_index",null,1);
                            SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                            ContentValues values2 = new ContentValues();
                            values2.put("user_name", androidid);
                            values2.put("user_time", time_string);
                            values2.put("index_value", start_time);
                            values2.put("index_name","Sleep start time");
                            db1.insert("body_index", null, values2);

                            ContentValues values3 = new ContentValues();
                            values3.put("user_name", androidid);
                            values3.put("user_time", time_string);
                            values3.put("index_value", end_time);
                            values3.put("index_name","Sleep end time");
                            db1.insert("body_index", null, values3);


                            ContentValues values4 = new ContentValues();
                            values4.put("user_name", androidid);
                            values4.put("user_time", time_string);
                            values4.put("index_value", weight);
                            values4.put("index_name","Weight (kg)");
                            db1.insert("body_index", null, values4);
                            db1.close();
                            final MainActivity mainActivity = (MainActivity) getActivity();
//                                    Bundle bundle=getArguments();
//                                    bundle.putString("one","2");

                            mainActivity.onPageSelected(9);

//                    dialog = ProgressDialog.show(getActivity(), "", "...", true);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String result = "none";
                                    //creating new thread to handle Http Operations
                                    Looper.prepare();
                                    String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                                    result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  start_time, "Sleep start time");

                                    if(result.equals("no"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(result.equals("yes"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();

                                    }
                                    else if(result.equals("none"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//                            popToBack();
//                            Looper.loop();

                                    //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();

//	                    	Map<String, String> params = new HashMap<String, String>();
//	                        params.put("user_name", "asd");
//	                        params.put("user_name", "123");
//	                        String result = null;
//							try {
//								result = sendPostMessage(params,"utf-8");
//							} catch (MalformedURLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	                        System.out.println("result->"+result);
                                }
                            }).start();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String result = "none";
                                    //creating new thread to handle Http Operations
                                    Looper.prepare();
                                    String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                                    result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  end_time, "Sleep end time");

                                    if(result.equals("no"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(result.equals("yes"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();

                                    }
                                    else if(result.equals("none"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//                            popToBack();
//                            Looper.loop();

                                    //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();

//	                    	Map<String, String> params = new HashMap<String, String>();
//	                        params.put("user_name", "asd");
//	                        params.put("user_name", "123");
//	                        String result = null;
//							try {
//								result = sendPostMessage(params,"utf-8");
//							} catch (MalformedURLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	                        System.out.println("result->"+result);
                                }
                            }).start();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String result = "none";
                                    //creating new thread to handle Http Operations
                                    Looper.prepare();
                                    String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                                    result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Weight (kg)");

                                    if(result.equals("no"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(result.equals("yes"))
                                    {
                                        Toast.makeText(getActivity(), "Submitted successfully", Toast.LENGTH_SHORT).show();

                                    }
                                    else if(result.equals("none"))
                                    {
                                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//                            popToBack();
//                            Looper.loop();

                                    //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();

//	                    	Map<String, String> params = new HashMap<String, String>();
//	                        params.put("user_name", "asd");
//	                        params.put("user_name", "123");
//	                        String result = null;
//							try {
//								result = sendPostMessage(params,"utf-8");
//							} catch (MalformedURLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	                        System.out.println("result->"+result);
                                }
                            }).start();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String result = "none";
                                    //creating new thread to handle Http Operations
                                    Looper.prepare();
                                    result = getRegistData1(weight, start_time, end_time, sim);

                                    if (result.equals("no")) {
                                        Toast.makeText(getActivity(), "The user name or password is incorrect", Toast.LENGTH_SHORT).show();
                                    } else if (result.equals("yes")) {
                                        Toast.makeText(getActivity(), "Login successfully\n", Toast.LENGTH_SHORT).show();

                                    } else if (result.equals("none")) {
                                        Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getActivity(), "Submit successfully\n", Toast.LENGTH_SHORT).show();

//                            Looper.loop();
//                            dialog.dismiss();
                                    //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();

//	                    	Map<String, String> params = new HashMap<String, String>();
//	                        params.put("user_name", "asd");
//	                        params.put("user_name", "123");
//	                        String result = null;
//							try {
//								result = sendPostMessage(params,"utf-8");
//							} catch (MalformedURLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	                        System.out.println("result->"+result);
                                }

                            }).start();
                    }

                }


//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String result = "none";
//                            //creating new thread to handle Http Operations
//                            Looper.prepare();
//                            result = getRegistData(weight, start_time, end_time, sim);
//
//                            if (result.equals("no")) {
//                                Toast.makeText(getActivity(), "The user name or password is incorrect", Toast.LENGTH_SHORT).show();
//                            } else if (result.equals("yes")) {
//                                Toast.makeText(getActivity(), "Login successfully\n", Toast.LENGTH_SHORT).show();
//
//                            } else if (result.equals("none")) {
//                                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
//                            }
//                            Toast.makeText(getActivity(), "Submit successfully\n", Toast.LENGTH_SHORT).show();
//                            openNewPage(EchartSleepWeightFragment.class);
//                            Looper.loop();
//                            dialog.dismiss();
//                            //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();
//
////	                    	Map<String, String> params = new HashMap<String, String>();
////	                        params.put("user_name", "asd");
////	                        params.put("user_name", "123");
////	                        String result = null;
////							try {
////								result = sendPostMessage(params,"utf-8");
////							} catch (MalformedURLException e) {
////								// TODO Auto-generated catch block
////								e.printStackTrace();
////							}
////	                        System.out.println("result->"+result);
//                        }
//
//                    }).start();

                    //RequestApiData.getInstance().getLoginData(account, password, UserBaseInfo.class, LoginActivity.this);

//                if (!StringUtils.isEmpty(etWeight.getText().toString())) {
//                    rulerView.setCurrentValue(StringUtils.toFloat(etWeight.getText().toString(), 0));
//                } else {
//                    XToastUtils.toast("请输入体重值！");
//                }
                    //WeightSleepFragment.this.getActivity().finish();

//                    final MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.onPageSelected(1);
                }
                break;
            case R.id.btn_time_period_2:
                showTimePicker((TextView) view);
                break;
            case R.id.btn_time_period_3:
                showTimePicker2((TextView) view);
                break;
            case R.id.btn_date_system:
                showDatePicker((TextView) view);
                //showDatePickerDialog(getContext(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (TextView) view, Calendar.getInstance());
                break;

            default:
                break;
        }
    }




    private void showDatePicker(final TextView tv) {
        if (mDatePicker == null) {
            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(DateUtils.getNowDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String sleep_time_string = year + "-" + month + "-" + day;
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            Date date = null;
            try {
                date = sdf.parse(sleep_time_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar1.add(Calendar.MONTH, -1);
            mDatePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
                    String selectTime = DateUtils.date2String(date, DateUtils.yyyyMMdd.get());
                    int year = Integer.valueOf(selectTime.split("-")[0]);
                    int month = Integer.valueOf(selectTime.split("-")[1]);
                    int day = Integer.valueOf(selectTime.split("-")[2]);

                    tv.setText(String.format("%d-%d-%d", year, month, day));
                    String temp_month;
                    String temp_day;
                    if(month < 10){
                        temp_month = '0' + String.valueOf(month);
                    }
                    else{
                        temp_month = String.valueOf(month);
                    }
                    if(day < 10){
                        temp_day = '0' + String.valueOf(day);
                    }
                    else{
                        temp_day = String.valueOf(day);
                    }
                    weight_time = String.valueOf(year) + "-" + temp_month + "-" + temp_day;
                    //XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMddHHmm.get()));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");
                        }
                    })
                    .setTitleText("")
                    .setDate(calendar)
                    .setSubmitText("Confirm")
                    .setCancelText("Cancel")
                    .setLabel("y","m","d","h","min","sec")
                    .setRangDate(calendar1, calendar)
                    .build();
        }
        mDatePicker.show();
    }











//    public String getRegistData(String weight,String start_time, String end_time, String sim)
//    {
//        String string = "none";
//        int serverResponseCode = 0;
//        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//        HttpURLConnection connection;
//        DataOutputStream dataOutputStream;
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//
////        Date date = new Date();
////        String upload_time = date.toLocaleString();
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String sim = dateFormat.format(date);
//
//
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//        String url1 ="http://39.100.73.118/deeplearning_photo/weight_sleep.php?weight="+weight+"&start_time="+start_time+"&end_time="+end_time+"&upload_time="+sim+"&androidid="+androidid+"";
//        //http://39.100.73.118/deeplearning_photo/user_info1.php?sex=null&weight1=null&height=100&diabete=null&workdate=null&disease=null&androidid=11
//        Request request = new Request.Builder()
//                .url(url1)
//                .build();
//        String res = "";
//        try{
//            Response response = okHttpClient.newCall(request).execute();
//            res = response.body().string();
//            //handler.sendEmptyMessage(1);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            //handler.sendEmptyMessage(2);
//        }
//        dialog.dismiss();
//        return (res);
//    }
    public String getRegistData(String user_name,String user_group,String phone_id, String upload_time, String data_time, String index_value, String index_name)
    {
        if(user_name.equals("Cohort Clock")){
            user_name = phone_id;
        }
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        //start

        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/body_index.php?user_name="+user_name+"&user_group="+user_group+"&phone_id="+phone_id+"&upload_time="+upload_time+"&data_time="+data_time+"&index_value="+index_value+"&index_name="+index_name+"";
        //http://39.100.73.118/deeplearning_photo/user_info1.php?sex=null&weight1=null&height=100&diabete=null&workdate=null&disease=null&androidid=11
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



    private void showTimePicker(final TextView tv){
        if (mTimePicker == null) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String sleep_time_string = year + "-" + month + "-" + day + " " + "22:08:00";
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
            Date date = null;
            try {
                date = sdf.parse(sleep_time_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //calendar.setTime(DateUtils.getNowDate());
            calendar.setTime(date);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar1.add(Calendar.MONTH, -1);


            mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
                    //XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMddHHmm.get()));
                    String temp_string = DateUtils.date2String(date, DateUtils.yyyyMMddHHmm.get());
                    start_time= temp_string + ":00";
                    tv.setText(String.format("Your bedtime：" + temp_string));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");

                        }
                    })
                    .setType(TimePickerType.DATE)
                    .setTitleText("")
                    .setDate(calendar)
                    .setLabel("y","m","d","h","min","sec")
                    .setSubmitText("Confirm")
                    .setCancelText("Cancel")
                    .setRangDate(calendar1, calendar)
                    .build();
        }
        mTimePicker.show();
    }

    public String getRegistData1(String weight,String start_time, String end_time, String sim)
    {
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

//        Date date = new Date();
//        String upload_time = date.toLocaleString();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String sim = dateFormat.format(date);



        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/weight_sleep.php?weight="+weight+"&start_time="+start_time+"&end_time="+end_time+"&upload_time="+sim+"&androidid="+androidid+"";
        //http://39.100.73.118/deeplearning_photo/user_info1.php?sex=null&weight1=null&height=100&diabete=null&workdate=null&disease=null&androidid=11
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
//        dialog.dismiss();
        return (res);
    }


    private void showTimePicker2(final TextView tv) {
        if (mTimePicker1 == null) {
            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(DateUtils.getNowDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String sleep_time_string = year + "-" + month + "-" + day + " " + "07:04:00";
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
            Date date = null;
            try {
                date = sdf.parse(sleep_time_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar1.add(Calendar.MONTH, -1);


            mTimePicker1 = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
                    //XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMddHHmm.get()));
                    String temp_string = DateUtils.date2String(date, DateUtils.yyyyMMddHHmm.get());
                    end_time = temp_string + ":00";


                    tv.setText(String.format("Your wake up time：" + temp_string));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");

                        }
                    })
                    .setType(TimePickerType.DATE)
                    .setTitleText("")
                    .setDate(calendar)
                    .setRangDate(calendar1, calendar)
                    .setSubmitText("Confirm")
                    .setCancelText("Cancel")
                    .setLabel("y","m","d","h","min","sec")
                    .build();
        }
        mTimePicker1.show();
    }


    public void showDatePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new DatePickerDialog(context
                , themeResId
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                tv.setText(String.format("You select：%d-%d-%d", year, (monthOfYear + 1), dayOfMonth));
                String temp_month;
                String temp_day;
                if(monthOfYear + 1 < 10){
                    temp_month = '0' + String.valueOf(monthOfYear + 1);
                }
                else{
                    temp_month = String.valueOf(monthOfYear + 1);
                }
                if(dayOfMonth < 10){
                    temp_day = '0' + String.valueOf(dayOfMonth);
                }
                else{
                    temp_day = String.valueOf(dayOfMonth);
                }
                weight_time = String.valueOf(year) + "-" + temp_month + "-" + temp_day;
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH))
                .show();

    }


}
