package com.xuexiang.templateproject.fragment.profile;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.picker.RulerView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xutil.common.StringUtils;
import com.xuexiang.xutil.data.DateUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "Body Metrics",anim = CoreAnim.none)
public class BodyIndexFragment extends BaseFragment{
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/body_index.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;
    private TimePickerView mTimePicker;
    private TimePickerView mTimePicker1;
    @BindView(R.id.name1)
    TextView index_name_text;
    @BindView(R.id.tcv_select)
    TabControlView mTabControlView;
    @BindView(R.id.tcv_select1)
    TabControlView mTabControlView11;
    @BindView(R.id.tcv_select11)
    TabControlView mTabControlView1;
    @BindView(R.id.tcv_select12)
    TabControlView mTabControlView12;
    @BindView(R.id.tcv_select2)
    TabControlView mTabControlView2;
    @BindView(R.id.rulerView)
    RulerView rulerView;
    @BindView(R.id.rulerView1)
    RulerView rulerView1;
    @BindView(R.id.et_name)
    EditText etDisease;
    @BindView(R.id.btn_time_period_2)
    Button btn_time_period2;
    @BindView(R.id.btn_time_period_3)
    Button btn_time_period_3;

    String sex = "null";
    String weight = "null";
    String height = "null";
    String diabete = "null";
    String workdate = "null";
    String disease = "null";
    String press_min = "null";
    String press_max = "null";
    String current_index = "";

    String start_time = "null";
    String end_time = "null";


    String index_unit = "";
    String min_num = "";
    String max_num = "";
    String scale = "0";
    String average = "0";
    String index_name = "";
    String index_console = "";
    String is_add = "FALSE";

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        String name = arguments.getString("null");
        current_index = name;

        List<String> index_raw = new ArrayList<>();
        index_raw.add("weight");
        index_raw.add("height");
        index_raw.add("blood");
        index_raw.add("heart");
        index_raw.add("waist");
        index_raw.add("exercise");
        index_raw.add("temperature");
        index_raw.add("medicine");


        if(index_raw.contains(name)){
            if(name.equals("weight")){
                index_name_text.setText("Weight");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else if(name.equals("height")){
                index_name_text.setText("Height");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                rulerView.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else if(name.equals("sleep")){
                index_name_text.setText("Sleep time");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                rulerView.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
            }else if(name.equals("blood")){
                index_name_text.setText("Blood Pressure");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else if(name.equals("heart")){
                index_name_text.setText("Heart Rate");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else if(name.equals("waist")){
                index_name_text.setText("Waist Circumference");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else if(name.equals("exercise")){
                index_name_text.setText("Exercise Duration");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                rulerView.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
            }else if(name.equals("temperature")){
                index_name_text.setText("Body Temperature");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else if(name.equals("medicine")){
                index_name_text.setText("Medicine");
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                rulerView.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }
        }else{


            String temp_res1 = MMKVUtils.getString("index_table_string", "");
            String[] index_info = temp_res1.split("<");
            for(int i = 0; i < index_info.length;i++ ){
                String temp_index_name = index_info[i].split(";")[2];
                if(!index_raw.contains(current_index)){
                    if(temp_index_name.equals(current_index)){
                        is_add = "TRUE";
                        index_console = index_info[i].split(";")[1];
                        index_unit = index_info[i].split(";")[3];
                        min_num = index_info[i].split(";")[4];
                        max_num = index_info[i].split(";")[5];
                        scale = index_info[i].split(";")[6];
                        average = index_info[i].split(";")[7];
                        index_name = index_info[i].split(";")[8];

                    }

                }
            }
            index_name_text.setText(current_index);

            if(index_console.equals("Ruler")){
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                etDisease.setVisibility(View.GONE);
                //rulerView.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }else{
                mTabControlView.setVisibility(View.GONE);
                mTabControlView11.setVisibility(View.GONE);
                mTabControlView1.setVisibility(View.GONE);
                mTabControlView12.setVisibility(View.GONE);
                mTabControlView2.setVisibility(View.GONE);
                //etDisease.setVisibility(View.GONE);
                rulerView.setVisibility(View.GONE);
                rulerView1.setVisibility(View.GONE);
                btn_time_period2.setVisibility(View.GONE);
                btn_time_period_3.setVisibility(View.GONE);
            }



        }










    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bodyindex;
    }

    @Override
    protected void initViews() {

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
        Bundle arguments = getArguments();
        current_index = arguments.getString("null");

        List<String> index_raw = new ArrayList<>();
        index_raw.add("weight");
        index_raw.add("height");
        index_raw.add("blood");
        index_raw.add("heart");
        index_raw.add("waist");
        index_raw.add("exercise");
        index_raw.add("temperature");
        index_raw.add("medicine");
        if(!index_raw.contains(current_index)){
            String temp_res1 = MMKVUtils.getString("index_table_string", "");
            String[] index_info = temp_res1.split("<");
            for(int i = 0; i < index_info.length;i++ ){
                String temp_index_name = index_info[i].split(";")[2];
                if(!index_raw.contains(current_index)){
                    if(temp_index_name.equals(current_index)){
                        is_add = "TRUE";
                        index_console = index_info[i].split(";")[1];
                        index_unit = index_info[i].split(";")[3];
                        min_num = index_info[i].split(";")[4];
                        max_num = index_info[i].split(";")[5];
                        scale = index_info[i].split(";")[6];
                        average = index_info[i].split(";")[7];
                        index_name = index_info[i].split(";")[8];

                    }

                }
            }
        }

        if(is_add.equals("FALSE")){
            if(current_index.equals("blood")){
                try {

                    float mmkv_weight = Float.valueOf(MMKVUtils.getString("dia_pre", "80"));
                    float mmkv_height = Float.valueOf(MMKVUtils.getString("sys_pre", "100"));
                    rulerView.setFirstScale(mmkv_weight);
                    rulerView.setMaxScale(200);
                    rulerView.setMinScale(0);
                    rulerView.setUnit("mmHg");
                    rulerView1.setFirstScale(mmkv_height);
                    rulerView1.setMaxScale(200);
                    rulerView1.setMinScale(0);
                    rulerView1.setUnit("mmHg");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(current_index.equals("heart")){
                try {

                    float mmkv_weight = Float.valueOf(MMKVUtils.getString("rate", "60"));
                    rulerView.setFirstScale(mmkv_weight);
                    rulerView.setMaxScale(200);
                    rulerView.setMinScale(0);
                    rulerView.setUnit("BPM");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(current_index.equals("waist")){
                try {

                    float mmkv_weight = Float.valueOf(MMKVUtils.getString("waist", "80"));
                    rulerView.setFirstScale(mmkv_weight);
                    rulerView.setMaxScale(200);
                    rulerView.setMinScale(0);
                    rulerView.setUnit("cm");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(current_index.equals("exercise")){
                btn_time_period2.setText("Choose exercise start imte");
                btn_time_period_3.setText("Choose exercise end imte");

            }else if(current_index.equals("temperature")){
                try {

                    float mmkv_weight = Float.valueOf(MMKVUtils.getString("degree", "36.5"));
                    rulerView.setFirstScale(mmkv_weight);
                    rulerView.setMaxScale(42);
                    rulerView.setMinScale(30);
                    rulerView.setUnit("degree");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(current_index.equals("height")){
                try {

                    float mmkv_weight = Float.valueOf(MMKVUtils.getString("height", "167.1"));
                    rulerView.setFirstScale(mmkv_weight);
                    rulerView.setMaxScale(0);
                    rulerView.setMinScale(220);
                    rulerView.setUnit("cm");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    mTabControlView.setItems(ResUtils.getStringArray(R.array.course_param_option),ResUtils.getStringArray(R.array.course_param_value));
                    String mmkv_sex = MMKVUtils.getString("sex", "male");

                    if(mmkv_sex.equals("male")){
                        mTabControlView.setDefaultSelection(0);
                    }else{
                        mTabControlView.setDefaultSelection(1);
                    }




                    float mmkv_weight = Float.valueOf(MMKVUtils.getString("latest_weight", "66.2"));
//                float mmkv_height = Float.valueOf(MMKVUtils.getString("height", "167.1"));
                    rulerView.setFirstScale(mmkv_weight);
//                rulerView1.setFirstScale(mmkv_height);

//            rulerView.setMaxScale(100);
                    mTabControlView1.setItems(ResUtils.getStringArray(R.array.course_param_option1),ResUtils.getStringArray(R.array.course_param_value1));
                    mTabControlView1.setDefaultSelection(1);
                    mTabControlView11.setItems(ResUtils.getStringArray(R.array.course_param_option3),ResUtils.getStringArray(R.array.course_param_value3));
                    mTabControlView11.setDefaultSelection(1);
                    mTabControlView12.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
                    mTabControlView12.setDefaultSelection(1);
                    mTabControlView2.setItems(ResUtils.getStringArray(R.array.course_param_option2),ResUtils.getStringArray(R.array.course_param_value2));
                    mTabControlView2.setDefaultSelection(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            if(index_console.equals("Ruler")){
                float mmkv_weight = Float.valueOf(MMKVUtils.getString(current_index, "0"));
                rulerView.setFirstScale(mmkv_weight);
                rulerView.setMaxScale((int) Math.ceil(Float.valueOf(max_num)));
                rulerView.setMinScale((int) Math.floor(Float.valueOf(min_num)));
                rulerView.setUnit(index_unit);
            }else{

            }

        }



        mTabControlView.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {

                sex = value;
                if(value.equals("female")){
                    rulerView.setFirstScale(57.3f);
                    rulerView1.setFirstScale(155.8f);
                }
                else{
                    rulerView.setFirstScale(66.2f);
                    rulerView1.setFirstScale(167.1f);

                }
                //XToastUtils.toast("选中了：" + title + ", 选中的值为：" + value);
            }
        });
        mTabControlView1.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                diabete = value;
                //XToastUtils.toast("选中了：" + title + ", 选中的值为：" + value);
            }
        });
        mTabControlView2.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                workdate = value;
                //XToastUtils.toast("选中了：" + title + ", 选中的值为：" + value);
            }
        });
    }

    @OnClick({R.id.button_yes, R.id.btn_time_period_2,R.id.btn_time_period_3,R.id.button_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_yes:
                if(current_index.equals("blood")){
                    weight = String.valueOf(rulerView.getCurrentValue());
                    height = String.valueOf(rulerView1.getCurrentValue());
                    MMKVUtils.put("dia_pre", weight);
                    MMKVUtils.put("sys_pre", height);


                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                    ContentValues values = new ContentValues();
                    values.put("user_name", androidid);
                    values.put("user_time", time_string);
                    values.put("index_value", weight);
                    values.put("index_name","Diastolic blood pressure (mmHg)");
                    db1.insert("body_index", null, values);


                    ContentValues values2 = new ContentValues();
                    values2.put("user_name", androidid);
                    values2.put("user_time", time_string);
                    values2.put("index_value", height);
                    values2.put("index_name","Systolic blood pressure (mmHg)");
                    db1.insert("body_index", null, values2);

                    db1.close();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "none";
                            //creating new thread to handle Http Operations
                            Looper.prepare();
                            String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                            if(user_name.equals("Cohort Clock")){
                                user_name = androidid;
                            }
                            result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Diastolic blood pressure (mmHg)");

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
                            popToBack();
                            Looper.loop();

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
                            if(user_name.equals("Cohort Clock")){
                                user_name = androidid;
                            }
                            result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  height, "Systolic blood pressure (mmHg)");

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
                            popToBack();
                            Looper.loop();

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




                }else if(current_index.equals("heart")){
                    weight = String.valueOf(rulerView.getCurrentValue());
                    MMKVUtils.put("rate", weight);

                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                    ContentValues values = new ContentValues();
                    values.put("user_name", androidid);
                    values.put("user_time", time_string);
                    values.put("index_value", weight);
                    values.put("index_name","Heart rate (BPM)");
                    db1.insert("body_index", null, values);


                    db1.close();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "none";
                            //creating new thread to handle Http Operations
                            Looper.prepare();
                            String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                            if(user_name.equals("Cohort Clock")){
                                user_name = androidid;
                            }
                            result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Heart rate (BPM)");

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
                            popToBack();
                            Looper.loop();

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


                }else if(current_index.equals("waist")){
                    weight = String.valueOf(rulerView.getCurrentValue());
                    MMKVUtils.put("waist", weight);

                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                    ContentValues values = new ContentValues();
                    values.put("user_name", androidid);
                    values.put("user_time", time_string);
                    values.put("index_value", weight);
                    values.put("index_name","Waist circumference (cm)");
                    db1.insert("body_index", null, values);


                    db1.close();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "none";
                            //creating new thread to handle Http Operations
                            Looper.prepare();
                            String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                            result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Waist circumference (cm)");

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
                            popToBack();
                            Looper.loop();

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


                }else if(current_index.equals("height")){
                    weight = String.valueOf(rulerView1.getCurrentValue());
                    MMKVUtils.put("height", weight);

                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                    ContentValues values = new ContentValues();
                    values.put("user_name", androidid);
                    values.put("user_time", time_string);
                    values.put("index_value", weight);
                    values.put("index_name","Height (cm)");
                    db1.insert("body_index", null, values);


                    db1.close();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "none";
                            //creating new thread to handle Http Operations
                            Looper.prepare();
                            String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                            result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Height (cm)");

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
                            popToBack();
                            Looper.loop();

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


                }else if(current_index.equals("temperature")){
                    weight = String.valueOf(rulerView.getCurrentValue());
                    MMKVUtils.put("temperature", weight);

                    final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                    ContentValues values = new ContentValues();
                    values.put("user_name", androidid);
                    values.put("user_time", time_string);
                    values.put("index_value", weight);
                    values.put("index_name","Temperature");
                    db1.insert("body_index", null, values);


                    db1.close();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "none";
                            //creating new thread to handle Http Operations
                            Looper.prepare();
                            String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                            result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, "Temperature");

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
                            popToBack();
                            Looper.loop();

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


                }else if(current_index.equals("exercise")){
                    if(!start_time.equals("null") && !end_time.equals("null")){
                        final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                        ContentValues values = new ContentValues();
                        values.put("user_name", androidid);
                        values.put("user_time", time_string);
                        values.put("index_value", start_time);
                        values.put("index_name","Exercise start time");
                        db1.insert("body_index", null, values);

                        ContentValues values2 = new ContentValues();
                        values2.put("user_name", androidid);
                        values2.put("user_time", time_string);
                        values2.put("index_value", end_time);
                        values2.put("index_name","Exercise end time");
                        db1.insert("body_index", null, values2);


                        db1.close();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result = "none";
                                //creating new thread to handle Http Operations
                                Looper.prepare();
                                String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                                result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  start_time, "Exercise start time");

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
                                popToBack();
                                Looper.loop();

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
                                result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  end_time, "Exercise end time");

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
                                popToBack();
                                Looper.loop();

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


                    }else{
                        Toast.makeText(getActivity(), "Please select time first", Toast.LENGTH_SHORT).show();
                    }




                }
                else{
                    if(is_add.equals("TRUE")){
                        if(index_console.equals("Ruler")){
                            weight = String.valueOf(rulerView.getCurrentValue());
                            MMKVUtils.put(current_index, weight);

                            final String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                            ContentValues values = new ContentValues();
                            values.put("user_name", androidid);
                            values.put("user_time", time_string);
                            values.put("index_value", weight);
                            values.put("index_name",current_index);
                            db1.insert("body_index", null, values);


                            db1.close();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String result = "none";
                                    //creating new thread to handle Http Operations
                                    Looper.prepare();
                                    String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                                    result = getRegistData(user_name, "1", androidid,  time_string,  time_string,  weight, current_index);

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
                                    popToBack();
                                    Looper.loop();

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
                        }else{

                        }
                    }
//                    weight = String.valueOf(rulerView.getCurrentValue());
//                    height = String.valueOf(rulerView1.getCurrentValue());
//                    MMKVUtils.put("latest_weight", weight);
//                    MMKVUtils.put("height", height);
                }


                if (!StringUtils.isEmpty(etDisease.getText().toString())) {
                    disease = etDisease.getText().toString();
                }


                //RequestApiData.getInstance().getLoginData(account, password, UserBaseInfo.class, LoginActivity.this);

//                if (!StringUtils.isEmpty(etWeight.getText().toString())) {
//                    rulerView.setCurrentValue(StringUtils.toFloat(etWeight.getText().toString(), 0));
//                } else {
//                    XToastUtils.toast("请输入体重值！");
//                }

                //openNewPage(ProfileFragment.class);
                break;
            case R.id.btn_time_period_2:
                showTimePicker((TextView) view);
                break;
            case R.id.btn_time_period_3:
                showTimePicker2((TextView) view);
                break;
            case R.id.button_no:
                popToBack();
                break;

            default:
                break;
        }
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
                    tv.setText(String.format("" + temp_string));
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


                    tv.setText(String.format("" + temp_string));
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
        mTimePicker1.show();
    }



















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




}
