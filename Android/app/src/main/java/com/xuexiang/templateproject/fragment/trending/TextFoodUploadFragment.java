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
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.MainActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.templateproject.utils.DemoDataProvider;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.IOThread;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xutil.data.DateUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "手动选择上传")
public class TextFoodUploadFragment extends BaseFragment {


    String current_time = null;
    String food_type = "null";
    String food_kind = "null";
//    @BindView(R.id.tcv_select)
//    TabControlView mTabControlView;

    private List<FoodTypeInfo> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
//    private List<List<List<String>>> options3Items = new ArrayList<>();

    private boolean mHasLoaded;
    private TimePickerView mTimePicker;
    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_calorie;
    }

    @Override
    protected void initArgs() {
        loadData(DemoDataProvider.getProvinceInfos());
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        initTabControlView();
    }

    private void initTabControlView() {
        loadData(DemoDataProvider.getProvinceInfos());
//        try {
//            mTabControlView.setItems(ResUtils.getStringArray(R.array.calorie),ResUtils.getStringArray(R.array.calorie_value));
//            mTabControlView.setDefaultSelection(0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mTabControlView.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
//            @Override
//            public void newSelection(String title, String value) {
//                food_type = value;
//
//            }
//        });

    }


    @OnClick({R.id.Button2,  R.id.btn_time_system, R.id.btn_picker})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_picker:
                showPickerView(false, (TextView)view);
                break;

            case R.id.Button2:
                if(current_time != null) {
                    String[] strArr = current_time.split("_");
                    String select_hour = strArr[0];
                    String select_minute = strArr[1];
                    Calendar cal=Calendar.getInstance();
                    int h=cal.get(Calendar.HOUR_OF_DAY);
                    int mi=cal.get(Calendar.MINUTE);
                    if(Integer.parseInt(select_hour) > h) {
                        Toast.makeText(getActivity(), "补充上传时间不能超过当前时间", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Integer.parseInt(select_hour) == h && Integer.parseInt(select_minute) > mi){

                            Toast.makeText(getActivity(), "补充上传时间不能超过当前时间", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            if (isFastDoubleClick() == false) {
                                if (!food_type.equals("null")) {
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
                                    time_string = year + "." + month + "." + day + "-" + hour_s + ":" + minute_s + ":" + second_s;
                                    getRegistData(time_string);
                                    String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                                    DatabaseHelper databaseHelpernew = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                                    SQLiteDatabase dbnew = databaseHelpernew.getWritableDatabase();
                                    ContentValues valuesnew = new ContentValues();
                                    valuesnew.put("user_name",androidid);
                                    valuesnew.put("user_time",time_string);
                                    valuesnew.put("photo_type","food");
                                    valuesnew.put("photo_url","http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg");
                                    valuesnew.put("photo_cal", food_type);
                                    valuesnew.put("photo_kind","0");
                                    dbnew.insert("photo_path_new",null,valuesnew);
                                    dbnew.close();
//                                    final MainActivity mainActivity = (MainActivity) getActivity();
//                                    Bundle bundle=getArguments();
//                                    bundle.putString("one","2");
//                                    mainActivity.onPageSelected(0);
                                    Intent intent = new Intent();

                                    intent.setClass(getActivity() , MainActivity.class );

                                    startActivity(intent);


                                } else {
                                    Toast.makeText(getActivity(), "Please choose a File First", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Please choose time First", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_time_system:
                boolean isShowClock = MMKVUtils.getBoolean("IS_SHOW_CLOCK", false);
                if(!isShowClock){
                    showTimePickerDialog(getContext(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (TextView) view, Calendar.getInstance());
                }else{
                    showTimePicker((TextView) view);
                }
//                showTimePickerDialog(getContext(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (TextView) view, Calendar.getInstance());
                break;
            default:
                break;
        }
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
                    current_time = String.valueOf(hourOfDay) + '_' + String.valueOf(minute);
                    tv.setText(String.format("您选择了：%d时%d分", hourOfDay, minute));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");

                        }
                    })
                    .setType(new boolean[]{false, false, false, true, true, false})
                    .setTitleText("时间选择")
                    .setDate(calendar)
                    .build();
        }
        mTimePicker.show();
    }




    private void showPickerView(boolean isDialog, final TextView tv) {// 弹出选择器
        if (!mHasLoaded) {
            XToastUtils.toast("数据加载中...");
            return;
        }

        int[] defaultSelectOptions = getDefaultFood();

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + "-" +
                        options2Items.get(options1).get(options2);
                tv.setText(String.format("%s-%s", options1Items.get(options1).getPickerViewText(), options2Items.get(options1).get(options2)));
                food_kind = options2Items.get(options1).get(options2);
                if(options1Items.get(options1).getPickerViewText().equals("低热量食物")){
                    food_type = "1";
                }else if(options1Items.get(options1).getPickerViewText().equals("中热量食物")){
                    food_type = "2";
                }else if(options1Items.get(options1).getPickerViewText().equals("高热量食物")){
                    food_type = "3";
                }

                XToastUtils.toast(tx);
                return false;
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                //切换选项时，还原到第一项
                .isRestoreItem(true)
                //设置选中项文字颜色
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .isDialog(isDialog)
                .setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1], defaultSelectOptions[2])
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items,  options2Items);//三级选择器
        pvOptions.show();
    }

    @IOThread
    private void loadData(List<FoodTypeInfo> foodtypeInfos) {//加载数据
        /**
         * 添加省份数据
         */
        options1Items = foodtypeInfos;

        //遍历省份（第一级）
        for (FoodTypeInfo provinceInfo : foodtypeInfos) {
            //该省的城市列表（第二级）
            List<String> cityList = new ArrayList<>();
            //该省的所有地区列表（第三级）
            List<List<String>> areaList = new ArrayList<>();

            for (FoodTypeInfo.City city : provinceInfo.getCityList()) {
                //添加城市
                String cityName = city.getName();
                cityList.add(cityName);
                //该城市的所有地区列表
                List<String> cityAreaList = new ArrayList<>();
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (city.getArea() == null || city.getArea().size() == 0) {
                    cityAreaList.add("");
                } else {
                    cityAreaList.addAll(city.getArea());
                }
                //添加该省所有地区数据
                areaList.add(cityAreaList);
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
//            options3Items.add(areaList);

        }

        mHasLoaded = true;
    }


    private int[] getDefaultFood() {
        int[] res = new int[3];
        FoodTypeInfo provinceInfo;
        List<FoodTypeInfo.City> cities;
        FoodTypeInfo.City city;
        List<String> ares;
        for (int i = 0; i < options1Items.size(); i++) {
            provinceInfo = options1Items.get(i);
            if ("江苏省".equals(provinceInfo.getName())) {
                res[0] = i;
                cities = provinceInfo.getCityList();
                for (int j = 0; j < cities.size(); j++) {
                    city = cities.get(j);
                    if ("南京市".equals(city.getName())) {
                        res[1] = j;
                        ares = city.getArea();
                        for (int k = 0; k < ares.size(); k++) {
                            if ("雨花台区".equals(ares.get(k))) {
                                res[2] = k;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }














    public void showTimePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new TimePickerDialog(context
                , themeResId
                , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                current_time = String.valueOf(hourOfDay) + '_' + String.valueOf(minute);
                tv.setText(String.format("您选择了：%d时%d分", hourOfDay, minute));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , false)
                .show();
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


    public String getRegistData(String time_string)
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
        String url1 ="http://39.100.73.118/deeplearning_photo/word_food_type.php?food_type="+food_type+"&time="+time_string+"&androidid="+androidid+"";
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

    @Override
    public void onDestroyView() {
        options1Items.clear();
        options2Items.clear();
        mHasLoaded = false;
        super.onDestroyView();
    }
}
