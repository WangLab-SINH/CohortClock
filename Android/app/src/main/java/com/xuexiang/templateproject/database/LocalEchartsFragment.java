///*
// * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *       http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// */
//
//package com.xuexiang.templateproject.database;
//
//
//import android.Manifest;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.luck.picture.lib.config.PictureConfig;
//import com.xuexiang.templateproject.R;
//import com.xuexiang.templateproject.core.BaseFragment;
//import com.xuexiang.templateproject.utils.Utils;
//import com.xuexiang.templateproject.utils.XToastUtils;
//import com.xuexiang.xpage.annotation.Page;
//import com.xuexiang.xpage.enums.CoreAnim;
//import com.xuexiang.xui.widget.actionbar.TitleBar;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import butterknife.OnClick;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//@Page(name = "test",anim = CoreAnim.none)
//public class LocalEchartsFragment extends BaseFragment {
//    private WebView webview;
//
//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }
//
//    /**
//     * 布局的资源id
//     *
//     * @return
//     */
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_java_h5;
//    }
//
//    /**
//     * 初始化控件
//     */
//    @Override
//    protected void initViews() {
//        Log.i("cbs","isGranted == "+"here");
//        myView = (WebView) findViewById(R.id.myView);
//        myView.getSettings().setAllowFileAccess(true);
//        myView.getSettings().setJavaScriptEnabled(true);
//        //android 是我们在Js中调用当前方法的名字
//        myView.addJavascriptInterface(this, "android");
//        //这是让WebView 自己处理网页加载请求
//        myView.setWebChromeClient(new WebChromeClient());
//        //加载网页
//        myView.loadUrl("file:///android_asset/my.html");
//
//    }
//
//
//
//
//    private WebView myView;
//    private Button xyg;
//    int itme=0;
//
//
//
//
//    /**
//     * 必须添加该接口
//     * @return
//     */
//    @JavascriptInterface
//    public String showWeb() {
//        List<int[]> list = new ArrayList<>();
//        StringBuffer bf = new StringBuffer();
//        String na = "[";
//        for (int i = 0; i < 12; i++) {
//            na += "[" + i + ",0," + i + 2 + "],";
//            //  Log.i("TAG", "showWeb: "+list.get(i).toString());
//        }
//        bf = new StringBuffer(na);
//        bf.delete(na.length() - 1, na.length());
//        bf.insert(na.length() - 1, "]");
//        Log.i("TAG", "showWeb: " + bf.toString());
//        return bf.toString();
//    }
//
//    @JavascriptInterface
//    public String T1WebGetData(String t1)
//    {
//        XToastUtils.toast(t1);
//        String m="[";
//        Random r=new Random();
//        for (int i = 0; i <5 ; i++) {
//            m+=r.nextInt(100)+",";
//        }
//        StringBuffer bf=new StringBuffer(m);
//        bf.delete(m.length()-1,m.length());
//        bf.insert(m.length()-1,']');
//        Log.i("TAG", "T1WebGetData: "+bf.toString());
//        return bf.toString();
//    }
//
//    /**
//     * 数据处理
//     * @return
//     */
//    @JavascriptInterface
//    public String getT4Data()
//    {
//        Random r=new Random();
//        String data="["+r.nextInt(3000)+","+r.nextInt(3000)+"]";
//        return data;
//    }
//
//    /**
//     * 这些方法是对Js中 需要的数据进行处理
//     * @return
//     */
//    @JavascriptInterface
//    public String getT5Data()
//    {
//
//        Random r=new Random();
//        String data="[";
//        for (int i = 0; i <10 ; i++) {
//            data+=r.nextInt(10000)+",";
//        }
//        StringBuffer bf=new StringBuffer(data);
//        bf.delete(data.length()-1,data.length());
//        bf.insert(data.length()-1,']');
//        Log.i("TAG", "getT5Data: "+bf.toString());
//        return bf.toString();
//    }
//
//    /**
//     * 数据处理
//     * @param ss
//     * @return
//     */
//    @JavascriptInterface
//    public String getT5Data(String ss)
//    {
//        Random r=new Random();
//        String data="[";
//        for (int i = 0; i <10 ; i++) {
//            data+="{value:"+r.nextInt(100)+","+"name:\"测试数据"+i+"\"},";
//        }
//        StringBuffer bf=new StringBuffer(data);
//        bf.delete(data.length()-1,data.length());
//        bf.insert(data.length()-1,']');
//        Log.i("TAG", "getT5Data: "+bf.toString());
//        return bf.toString();
//    }
//
//
//
//    @OnClick({R.id.xyg})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.xyg:
//                itme=itme<6?++itme:0;
//                setWebViewItme();
//                break;
//        }
//
//    }
//
//
//    public void setWebViewItme()
//    {
//        switch (itme)
//        {
//            case 0:
//                myView.loadUrl("file:///android_asset/my.html");
//                break;
//            case 1:
//                myView.loadUrl("file:///android_asset/myechart.html");
//                break;
//            case 2:
//                myView.loadUrl("file:///android_asset/echart_test1.html");
//                break;
//            case 3:
//                break;
//            case 4:
//
//                break;
//            case 5:
//
//                break;
//        }
//    }
//
//}


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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.MainActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import butterknife.BindView;

@Page(name = "test",anim = CoreAnim.none)
public class LocalEchartsFragment extends BaseFragment {

    private WebView myView;
    private WebView webview;
    protected boolean isVisible;
    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
    private static final int MSG_FAILURE = 1;//获取图片失败的标识
    int time_redu = 0;
    int firstStartFlag = 0;



    int global_index = 0;

    int this_flag = 1;
    String title = "";
    int isFirstFlag = 0;

    List<Integer> random_num = new ArrayList<>();

    int is_draw_debug_global = -1;

    String global_last_time = "";

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


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
        return R.layout.fragment_java_h5;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        final Button btn = findViewById(R.id.xyg);
        Log.i("cbs","isGranted == "+"here");
        myView = (WebView) findViewById(R.id.myView);
        myView.getSettings().setAllowFileAccess(true);
        myView.getSettings().setJavaScriptEnabled(true);
        //android 是我们在Js中调用当前方法的名字
        myView.addJavascriptInterface(this, "android");
        //这是让WebView 自己处理网页加载请求
        myView.setWebChromeClient(new WebChromeClient());
        myView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        //加载网页
//        myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=1");
        String current_weight = MMKVUtils.getString("latest_weight", "80");
        String current_height = MMKVUtils.getString("height", "170");

        String workday_list = getT5Data(1);
        String weekend_list = getT5Data(2);
        String percentage = "0.8";
        int current_color = 1;
        int time_total = 100;
        String can_eat_flag = "TRUE";
        boolean isShowEatingTime = MMKVUtils.getBoolean("IS_SHOW_EatingTime", true);
        boolean server_time_edit = MMKVUtils.getBoolean("server_time_edit", false);
        if(server_time_edit){
            String eating_start_time = MMKVUtils.getString("eating_time_start","8:00");
            String eating_end_time = MMKVUtils.getString("eating_time_end","16:00");
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date data_begin = null;
            Date date_end = null;
            try {
                data_begin = df.parse(eating_start_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                date_end = df.parse(eating_end_time);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            String hour_num = "";
            if(hour < 10){
                hour_num = "0" + String.valueOf(hour);
            }else{
                hour_num = String.valueOf(hour);
            }
            String minute_num = "";
            if(minute < 10){
                minute_num = "0" + String.valueOf(minute);
            }else{
                minute_num = String.valueOf(minute);
            }
            Date date_current = null;
            try {
                date_current = df.parse(hour_num+":"+minute_num);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar dateC = Calendar.getInstance();
            dateC.setTime(date_current);
            Calendar begin = Calendar.getInstance();
            begin.setTime(data_begin);
            Calendar end = Calendar.getInstance();
            end.setTime(date_end);
            if (dateC.after(begin) && dateC.before(end)) {
                can_eat_flag = "TRUE";
                int difference = (int) (date_end.getTime() - date_current.getTime());
                time_total = difference / 1000;
                percentage = String.valueOf(16*60*60);
            }else{
                can_eat_flag = "FALSE";
                if(dateC.before(begin)){
                    int difference = (int) (data_begin.getTime() - date_current.getTime());
                    time_total = difference / 1000;
                    percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                }else{
                    int difference = 0;
                    try {
                        difference = (int) (df.parse("23:59").getTime() - date_current.getTime()) + (int) (data_begin.getTime() - df.parse("00:00").getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    time_total = difference / 1000;
                    percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                }

            }

            if(can_eat_flag.equals("FALSE")){
//            current_color = "\"#F04864\"";
                current_color = 1;
            }else{
//            current_color = "\"#2FC25B\"";
                current_color = 2;
            }
        }else{
            if(!isShowEatingTime){
                String eating_start_time = MMKVUtils.getString("eating_time_start","8:00");
                String eating_end_time = MMKVUtils.getString("eating_time_end","16:00");
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date data_begin = null;
                Date date_end = null;
                try {
                    data_begin = df.parse(eating_start_time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date_end = df.parse(eating_end_time);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                String hour_num = "";
                if(hour < 10){
                    hour_num = "0" + String.valueOf(hour);
                }else{
                    hour_num = String.valueOf(hour);
                }
                String minute_num = "";
                if(minute < 10){
                    minute_num = "0" + String.valueOf(minute);
                }else{
                    minute_num = String.valueOf(minute);
                }
                Date date_current = null;
                try {
                    date_current = df.parse(hour_num+":"+minute_num);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar dateC = Calendar.getInstance();
                dateC.setTime(date_current);
                Calendar begin = Calendar.getInstance();
                begin.setTime(data_begin);
                Calendar end = Calendar.getInstance();
                end.setTime(date_end);
                if (dateC.after(begin) && dateC.before(end)) {
                    can_eat_flag = "TRUE";
                    int difference = (int) (date_end.getTime() - date_current.getTime());
                    time_total = difference / 1000;
                    percentage = String.valueOf(16*60*60);
                }else{
                    can_eat_flag = "FALSE";
                    if(dateC.before(begin)){
                        int difference = (int) (data_begin.getTime() - date_current.getTime());
                        time_total = difference / 1000;
                        percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                    }else{
                        int difference = 0;
                        try {
                            difference = (int) (df.parse("23:59").getTime() - date_current.getTime()) + (int) (data_begin.getTime() - df.parse("00:00").getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        time_total = difference / 1000;
                        percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                    }

                }

                if(can_eat_flag.equals("FALSE")){
//            current_color = "\"#F04864\"";
                    current_color = 1;
                }else{
//            current_color = "\"#2FC25B\"";
                    current_color = 2;
                }



            }else{
                String current_diet_z = getDietTime("z");
                int index_num = 0;

                String[] diet_level = current_diet_z.split(",");
                if(diet_level[0].equals("1")||diet_level[0].equals("2")||diet_level[0].equals("3")){
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

                time_total = index_num*30*60;
                Calendar calendar = Calendar.getInstance();
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String minute_s;

                if(minute < 30){
                    time_total = time_total - minute*60 - second;
                    minute_s = "0" + String.valueOf(minute);
                }
                else{
                    time_total = time_total - (minute-30)*60 - second;
                }


                time_total = time_total + time_redu;

                if(can_eat_flag.equals("FALSE")&&time_total > 16*60*60){
                    can_eat_flag="TRUE";
                    time_total = time_total - 16*60*60;
                }
                else if(can_eat_flag.equals("TRUE")&&time_total > 8*60*60){
                    can_eat_flag="FALSE";
                    time_total = time_total - 8*60*60;
                }


                if(can_eat_flag.equals("FALSE")){
                    percentage = String.valueOf(16*60*60);
//            current_color = "\"#F04864\"";
                    current_color = 1;
                }else{
//            current_color = "\"#2FC25B\"";
                    current_color = 2;
                    percentage = String.valueOf(8*60*60);
                }
            }
        }





        String test_year = "2018,2019,2020,2021,2022,2023";
        String sleep_x = getSleep(1);
        String sleep_y1 = getSleep(2);
        String sleep_y2 = getSleep(3);
        String sleep_dis_x = getSleep1(1);
        String sleep_dis_y1 = getSleep1(2);
        String sleep_dis_y2 = getSleep1(3);


        String start_time = MMKVUtils.getString("eating_time_start", "8:00");
        String end_time = MMKVUtils.getString("eating_time_end", "16:00");
        String canedittime = "false";
        if(isShowEatingTime){
            canedittime = "false";
        }else{
            canedittime = "true";
        }


        String temp_file_string = "file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color+"&sleep_x="+sleep_x+"&sleep_start_time="+sleep_y1+"&sleep_end_time="+sleep_y2+"&sleep_dis_x="+sleep_dis_x+"&sleep_dis_y1="+sleep_dis_y1+"&sleep_dis_y2="+sleep_dis_y2+"&workday_list="+workday_list+"&weekend_list="+weekend_list
                +"&start_time="+start_time+"&end_time="+end_time+"&canedittime="+canedittime+"&percentage="+percentage;

        myView.loadUrl( "file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color+"&sleep_x="+sleep_x+"&sleep_start_time="+sleep_y1+"&sleep_end_time="+sleep_y2+"&sleep_dis_x="+sleep_dis_x+"&sleep_dis_y1="+sleep_dis_y1+"&sleep_dis_y2="+sleep_dis_y2+"&workday_list="+workday_list+"&weekend_list="+weekend_list
        +"&start_time="+start_time+"&end_time="+end_time+"&canedittime="+canedittime+"&percentage="+percentage);
        //myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color+"&sleep_x="+sleep_x+"&sleep_start_time="+sleep_y1+"&sleep_end_time="+sleep_y2+"&sleep_dis_x="+sleep_dis_x+"&sleep_dis_y1="+sleep_dis_y1+"&sleep_dis_y2="+sleep_dis_y2);

        //myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);

        boolean isShowFood = MMKVUtils.getBoolean("IS_SHOW_FOOD", false);

        myView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);



        if(isShowFood){
            btn.setVisibility(View.VISIBLE);
        }
        else{
            btn.setVisibility(View.INVISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFirstFlag == 0){
                    isFirstFlag = 1;
                    btn.setText("显示推荐饮食时间");
                }
                else{
                    isFirstFlag = 0;
                    btn.setText("显示推荐食物");
                }
                itme=itme<1?++itme:0;
                setWebViewItme();
            }
        });
        final MainActivity mainActivity = (MainActivity) getActivity();
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

//        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
//                refreshLayout.getLayout().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(global_index == 1){
//                            random_num.clear();
//                            myView.loadUrl("file:///android_asset/index3.html");
//                        }else{
////                            myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=1");
//                            String current_weight = MMKVUtils.getString("latest_weight", "80");
//                            String current_height = MMKVUtils.getString("height", "170");
//
//                            String current_diet_z = getDietTime("z");
//                            int index_num = 0;
//                            String can_eat_flag = "TRUE";
//                            String[] diet_level = current_diet_z.split(",");
//                            if(diet_level[0].equals("1")){
//                                can_eat_flag = "FALSE";
//
//                            }else{
//                                can_eat_flag = "TRUE";
//
//                            }
//                            for(int index_i=0; index_i<48; index_i++){
//                                if(can_eat_flag.equals("FALSE"))
//                                {
//                                    if(!(diet_level[index_i].equals("1")||diet_level[index_i].equals("2")||diet_level[index_i].equals("3")))
//                                    {
//                                        break;
//                                    }
//                                    else{
//                                        index_num = index_num + 1;
//                                    }
//
//                                }
//                                else{
//                                    if(diet_level[index_i].equals("1")||diet_level[index_i].equals("2")||diet_level[index_i].equals("3"))
//                                    {
//                                        break;
//                                    }
//                                    else{
//                                        index_num = index_num + 1;
//                                    }
//                                }
//                            }
//
//                            int time_total = index_num*30*60;
//                            String current_color = "2fc25b";
//                            if(can_eat_flag.equals("FALSE")){
//                                current_color = "#F04864";
//                            }else{
//                                current_color = "#2FC25B";
//                            }
//                            //myView.loadUrl( "http://10.10.114.202:8080/#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);
//                            myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);
//
//                        }
////                        Status status = getRefreshStatus();
////                        switch(status) {
////                            case SUCCESS:
////
////                                break;
////                            case EMPTY:
////
////                                break;
////                            case ERROR:
////
////                                break;
////                            case NO_NET:
////
////                                break;
////                            default:
////                                break;
////                        }
//                        refreshLayout.finishRefresh();
//
//                    }
//                }, 2000);
//            }
//        });















    }










    private Button xyg;
    int itme=0;
//    public class Rank {
//        public static void main(String[] args) throws ParseException {
//            String s1 = "2019-08-10 22:8:22";
//            String s2 = "2018-08-10 22:18:22";
//            String s3 = "2017-08-10 22:19:22";
//            List<Date> arr = new ArrayList<Date>();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            arr.add(df.parse(s1));
//            arr.add(df.parse(s2));
//            arr.add(df.parse(s3));
//            for (int i = 0; i < getd(arr).size(); i++) {
//                System.out.println(arr.get(i));
//            }
//
//        }
//        public List<Date> getd(List<Date> dateList){
//
//            dateList.sort((a1, a2) -> {
//                return a1.compareTo(a2);
//            });
//            return dateList;
//        }
//
//    }

    @JavascriptInterface
    public void htmlRefresh() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                lazyLoad();
            }
        });


    }



    @JavascriptInterface
    public void getTimefromHtml(String start_time, String end_time, String time_flag) {
        boolean isShowEatingTime = MMKVUtils.getBoolean("IS_SHOW_EatingTime", true);
        boolean server_time_edit = MMKVUtils.getBoolean("server_time_edit", false);
        if(server_time_edit){
            XToastUtils.toast("The administrator has already selected the time, you cannot choose it yourself.");
        }else{
            if(!isShowEatingTime){
                if(time_flag.equals("false")){
                    XToastUtils.toast("Start time can not exceed end time.");
                }else{
                    MMKVUtils.put("eating_time_start", start_time);
                    MMKVUtils.put("eating_time_end", end_time);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            lazyLoad();
                        }
                    });
                }
            }else{
                XToastUtils.toast("If you need to manually modify eating time, please go to the Settings to modify it.");
            }
        }



    }



    public String getSleep1(int isx)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"user_weight_sleep",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("user_weight_sleep", new String[]{"id","user_name","weight","start_time","end_time","upload_time"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中

        String textview_data = "";
        List<String> weight_time_list = new ArrayList<String>();
        List<String> sleep_time_list = new ArrayList<String>();
        Map<String, Double> weight_dict = new HashMap<String, Double>();
        Map<String, Double> start_dict = new HashMap<String, Double>();
        Map<String, Double> end_dict = new HashMap<String, Double>();
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
            Double weight = Double.valueOf(cursor.getString(cursor.getColumnIndex("weight")));
            String start_time = cursor.getString(cursor.getColumnIndex("start_time"));
            String end_time = cursor.getString(cursor.getColumnIndex("end_time"));
            String upload_time = cursor.getString(cursor.getColumnIndex("upload_time"));
            if(!weight_time_list.contains(upload_time.split(" ")[0])) {
                weight_time_list.add(upload_time.split(" ")[0]);
            }
            weight_dict.put(upload_time.split(" ")[0], weight);
            if(start_time.equals("null")){
                String start_day_time = null;
            }
            else{
                String start_day = start_time.split(" ")[0];
                String start_day_time = start_time.split(" ")[1];
                Double start_data = Double.valueOf(start_day_time.split(":")[0] + "." + start_day_time.split(":")[1]);
                if(!sleep_time_list.contains(start_day)) {
                    sleep_time_list.add(start_day);
                }
                start_dict.put(start_day, start_data);
            }
            if(end_time.equals("null")){
                String end_day_time = null;
            }
            else{
                String end_day = end_time.split(" ")[0];
                String end_day_time = end_time.split(" ")[1];
                Double end_data = Double.valueOf(end_day_time.split(":")[0] + "." + end_day_time.split(":")[1]);
                if(!sleep_time_list.contains(end_day)) {
                    sleep_time_list.add(end_day);
                }
                end_dict.put(end_day, end_data);
            }



        }
        Collections.sort(weight_time_list);
        Collections.sort(sleep_time_list);

        List<Double> weight_input_data = new ArrayList<Double>();
        for(int i =0; i < weight_time_list.size(); i ++){
            weight_input_data.add(weight_dict.get(weight_time_list.get(i)));
        }

        List<Double> start_input_data = new ArrayList<Double>();
        List<Double> end_input_data = new ArrayList<Double>();
        for(int i =0; i < sleep_time_list.size(); i ++){
            if(start_dict.containsKey(sleep_time_list.get(i))){
                Double temp_time;
                if(start_dict.get(sleep_time_list.get(i)) % 1 >= 0.75){
                    temp_time = Math.ceil(start_dict.get(sleep_time_list.get(i)));
                }
                else if(start_dict.get(sleep_time_list.get(i)) % 1 >= 0.5){
                    temp_time = Math.floor(start_dict.get(sleep_time_list.get(i))) + 0.5;
                }
                else if(start_dict.get(sleep_time_list.get(i)) % 1 >= 0.25){
                    temp_time = Math.floor(start_dict.get(sleep_time_list.get(i))) + 0.5;
                }
                else{
                    temp_time = Math.floor(start_dict.get(sleep_time_list.get(i)));
                }

                start_input_data.add(temp_time);
            }
            else{
                start_input_data.add(null);
            }
            if(end_dict.containsKey(sleep_time_list.get(i))){
                Double temp_time;
                if(end_dict.get(sleep_time_list.get(i)) % 1 >= 0.75){
                    temp_time = Math.ceil(end_dict.get(sleep_time_list.get(i)));
                }
                else if(end_dict.get(sleep_time_list.get(i)) % 1 >= 0.5){
                    temp_time = Math.floor(end_dict.get(sleep_time_list.get(i))) + 0.5;
                }
                else if(end_dict.get(sleep_time_list.get(i)) % 1 >= 0.25){
                    temp_time = Math.floor(end_dict.get(sleep_time_list.get(i))) + 0.5;
                }
                else{
                    temp_time = Math.floor(end_dict.get(sleep_time_list.get(i)));
                }
                end_input_data.add(temp_time);
            }
            else{
                end_input_data.add(null);
            }

        }

        String x_axis = "0:00, 0:30,1:00, 1:30,2:00, 2:30,3:00, 3:30,4:00, 4:30,5:00, 5:30,6:00, 6:30,7:00, 7:30,8:00, 8:30,9:00, 9:30,10:00, 10:30,11:00, 11:30," +
                "12:00, 12:30,13:00, 13:30,14:00, 14:30,15:00, 15:30,16:00, 16:30,17:00, 17:30,18:00, 18:30,19:00, 19:30,20:00, 20:30,21:00, 21:30,22:00, 22:30,23:00, 23:30";
        String y_axis1 = "[";
        String y_axis2 = "[";

        for(int i = 0; i < 48; i++){
            int temp_y = 0;
            for(int j = 0; j < start_input_data.size(); j++){
                if(start_input_data.get(j) == null){
                    continue;
                }
                if(start_input_data.get(j) * 2 == i){
                    temp_y += 1;
                }
            }
            y_axis1 += temp_y + ",";

            temp_y = 0;
            for(int j = 0; j < end_input_data.size(); j++){
                if(end_input_data.get(j) == null){
                    continue;
                }
                if(end_input_data.get(j) * 2 == i){
                    temp_y += 1;
                }
            }
            y_axis2 += temp_y + ",";

        }

        y_axis1 += "]";
        y_axis2 += "]";



        if(isx == 1){
            return x_axis;
        }
        else if(isx == 2){
            return y_axis1;
        }
        else{
            return y_axis2;
        }



    }














    public String getSleep(int isx)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"user_weight_sleep",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("user_weight_sleep", new String[]{"id","user_name","weight","start_time","end_time","upload_time"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中

        String textview_data = "";
        List<String> weight_time_list = new ArrayList<String>();
        List<String> sleep_time_list = new ArrayList<String>();
        Map<String, Double> weight_dict = new HashMap<String, Double>();
        Map<String, Double> start_dict = new HashMap<String, Double>();
        Map<String, Double> end_dict = new HashMap<String, Double>();
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
            Double weight = Double.valueOf(cursor.getString(cursor.getColumnIndex("weight")));
            String start_time = cursor.getString(cursor.getColumnIndex("start_time"));
            String end_time = cursor.getString(cursor.getColumnIndex("end_time"));
            String upload_time = cursor.getString(cursor.getColumnIndex("upload_time"));
            if(!weight_time_list.contains(upload_time.split(" ")[0])) {
                weight_time_list.add(upload_time.split(" ")[0]);
            }
            weight_dict.put(upload_time.split(" ")[0], weight);
            if(start_time.equals("null")){
                String start_day_time = null;
            }
            else{
                String start_day = start_time.split(" ")[0];
                String start_day_time = start_time.split(" ")[1];
                Double start_data = Double.valueOf(start_day_time.split(":")[0] + "." + start_day_time.split(":")[1]);
                if(!sleep_time_list.contains(start_day)) {
                    sleep_time_list.add(start_day);
                }
                start_dict.put(start_day, start_data);
            }
            if(end_time.equals("null")){
                String end_day_time = null;
            }
            else{
                String end_day = end_time.split(" ")[0];
                String end_day_time = end_time.split(" ")[1];
                Double end_data = Double.valueOf(end_day_time.split(":")[0] + "." + end_day_time.split(":")[1]);
                if(!sleep_time_list.contains(end_day)) {
                    sleep_time_list.add(end_day);
                }
                end_dict.put(end_day, end_data);
            }



        }
        Collections.sort(weight_time_list);
        Collections.sort(sleep_time_list);


        List<String> day_30 = new ArrayList<String>();
        Calendar now = Calendar.getInstance();
        for(int k=30;k>=0;k--){
            now.setTime(new Date());
            now.add(Calendar.DATE, -k);
            String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
            day_30.add(endDate);
        }





        List<Double> start_input_data = new ArrayList<Double>();
        List<Double> end_input_data = new ArrayList<Double>();
        for(int i =0; i < day_30.size(); i ++){
            if(start_dict.containsKey(day_30.get(i))){
                start_input_data.add(start_dict.get(day_30.get(i)));
            }
            else{
                start_input_data.add(null);
            }
            if(end_dict.containsKey(day_30.get(i))){
                end_input_data.add(end_dict.get(day_30.get(i)));
            }
            else{
                end_input_data.add(null);
            }

        }

        String x_axis = "";
        String y_axis1 = "";
        String y_axis2 = "";

        for(int i =0; i < day_30.size(); i++){
            x_axis += "" + day_30.get(i) + ",";
        }
        x_axis += "";

        for(int i =0; i < day_30.size(); i++){
            y_axis1 += start_input_data.get(i) + ",";
        }
        y_axis1 += "";

        for(int i =0; i < day_30.size(); i++){
            y_axis2 += end_input_data.get(i) + ",";
        }
        y_axis2 += "";
        if(isx == 1){
            return x_axis;
        }
        else if(isx == 2){
            return y_axis1;
        }
        else{
            return y_axis2;
        }

    }














    /**
     * 必须添加该接口
     * @return
     */
    @JavascriptInterface
    public String showWeb() {
        List<int[]> list = new ArrayList<>();
        StringBuffer bf = new StringBuffer();
        String na = "[";
        for (int i = 0; i < 12; i++) {
            na += "[" + i + ",0," + i + 2 + "],";
            //  Log.i("TAG", "showWeb: "+list.get(i).toString());
        }
        bf = new StringBuffer(na);
        bf.delete(na.length() - 1, na.length());
        bf.insert(na.length() - 1, "]");
        Log.i("TAG", "showWeb: " + bf.toString());
        return bf.toString();
    }

    @JavascriptInterface
    public String T1WebGetData(String t1)
    {
        XToastUtils.toast(t1);
        String m="[";
        Random r=new Random();
        for (int i = 0; i <5 ; i++) {
            m+=r.nextInt(100)+",";
        }
        StringBuffer bf=new StringBuffer(m);
        bf.delete(m.length()-1,m.length());
        bf.insert(m.length()-1,']');
        Log.i("TAG", "T1WebGetData: "+bf.toString());
        return bf.toString();
    }

    /**
     * 数据处理
     * @return
     */
    @JavascriptInterface
    public String getT4Data()
    {
        Random r=new Random();
        String data="["+r.nextInt(3000)+","+r.nextInt(3000)+"]";
        return data;
    }

    /**
     * 这些方法是对Js中 需要的数据进行处理
     * @return
     */
    @JavascriptInterface
    public String getT5Data(int isWork)
    {
//        List<String> list = new ArrayList<String>();
//        list.add("2014-09-04 10:34:41");
//        list.add("2013-08-04 13:42:19");
//        list.add("2014-09-04 16:46:49");
//        list.add("2010-01-04 12:32:00");
//        list.add("2004-04-04 10:34:41");
//        list.add("2009-05-14  23:42:19");
//        list.add("2014-12-04  06:08:49");
//        list.add("2010-01-24  01:32:00");
//        list.add("2010-01-24  02:32:00");
//        list.add("2010-01-24  06:32:00");
//        list.add("2010-01-24  14:32:00");
//        list.add("2010-01-24  04:32:00");
//        list.add("2010-01-24  14:31:00");
//
//        Collections.sort(list);
        DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
        SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
        Cursor cursor1 = db1.query("photo_path_new", new String[]{"workday", "weekend"}, null, null, null, null, null);

//        DatabaseHelper databaseHelper2 = new DatabaseHelper(getContext(),"weekend_data_new",null,1);
//        SQLiteDatabase db2 = databaseHelper2.getWritableDatabase();
//        Cursor cursor2 = db2.query("weekend_data_new", new String[]{"data"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中

        String textview_data = "";
        List<String> time_list = new ArrayList<String>();
        String id_work = "";
        String string_work_data = "";
//        while(cursor1.moveToNext()){
//
//            string_work_data = cursor1.getString(cursor1.getColumnIndex("data"));
//
//        }
//        cursor1.close();

        String id_weekend = "";
        String string_work_weekend = "";
        while(cursor1.moveToNext()){
            if(cursor1.getString(cursor1.getColumnIndex("workday")) != null){
                string_work_data = cursor1.getString(cursor1.getColumnIndex("workday"));
                string_work_weekend  = cursor1.getString(cursor1.getColumnIndex("weekend"));
            }

        }
        String new_string_work_weekend = "";
        String new_string_work_data = "";
        String[] temp_work_data = string_work_data.split(",");
        String[] temp_work_weekend = string_work_weekend.split(",");
        for(int j = 0; j < temp_work_data.length; j++){
            if(temp_work_data[j].equals("nan")){
                if(j == (temp_work_data.length - 1)){
                    new_string_work_data += "0";
                }else{
                    new_string_work_data += "0,";
                }

            }
            else{
                if(j == (temp_work_data.length - 1)){
                    new_string_work_data += temp_work_data[j];
                }else{
                    new_string_work_data += temp_work_data[j] + ",";
                }

            }
        }

        for(int j = 0; j < temp_work_weekend.length; j++){
            if(temp_work_weekend[j].equals("nan")){
                if(j == (temp_work_weekend.length - 1)){
                    new_string_work_weekend += "0";
                }else{
                    new_string_work_weekend += "0,";
                }

            }
            else{
                if(j == (temp_work_weekend.length - 1)){
                    new_string_work_weekend += temp_work_weekend[j];
                }else{
                    new_string_work_weekend += temp_work_weekend[j] + ",";
                }

            }
        }


        cursor1.close();
        db1.close();
//        db2.close();



//            List<Integer> work_data = fun_result.get("workday");
//            List<Integer> weekend_data = fun_result.get("weekend");
//            int[] work_data_array = work_data.stream().mapToInt(k->k).toArray();
//            int[] weekend_data_array = weekend_data.stream().mapToInt(k->k).toArray();
            if(isWork == 1){
                String data = "";
//                for (int i = 0; i < 48; i++) {
//                    data += work_data_array[i] + ",";
//                }
                data += new_string_work_data + ",";
                StringBuffer bf = new StringBuffer(data);
                bf.delete(data.length() - 1, data.length());
//                bf.insert(data.length() - 1, ']');
                Log.i("TAG", "getT5Data: " + bf.toString());
                return bf.toString();
            }
            else {

                String data = "";
//                for (int i = 0; i < 48; i++) {
//                    data += weekend_data_array[i] + ",";
//                }
                data += new_string_work_weekend + ",";
                StringBuffer bf = new StringBuffer(data);
                bf.delete(data.length() - 1, data.length());
//                bf.insert(data.length() - 1, ']');
                Log.i("TAG", "getT5Data: " + bf.toString());
                return bf.toString();
            }



//        Random r=new Random();
//        String data="[";
//        for (int i = 0; i <10 ; i++) {
//            data+=r.nextInt(10000)+",";
//        }
//        StringBuffer bf=new StringBuffer(data);
//        bf.delete(data.length()-1,data.length());
//        bf.insert(data.length()-1,']');
//        Log.i("TAG", "getT5Data: "+bf.toString());
//        return bf.toString();
    }


    public static int isLUN(int year){
        if(year % 100 == 0){
            if(year % 400 == 0){
                return 1;
            }
        }
        else{
            if(year % 4 == 0){
                return 1;
            }
        }
        return 0;
    }

    public static int isDay(int YEAR, int Month, int day){
        int ret = 0;
        int [] ping = new int []{31,28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int [] lun = new int []{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(isLUN(YEAR) == 1){
            for(int i = 0; i < (Month - 1); i++){
                ret = ret + lun[i];
            }
        }
        else{
            for(int i = 0; i < (Month - 1); i++){
                ret = ret + ping[i];
            }
        }
        return ret + day;


    }

    public static int isWeekEnd(int YEAR, int Month, int day){
        double S = ((YEAR + Math.ceil((YEAR - 1) / 4) - Math.ceil((YEAR - 1) / 100) +Math.ceil((YEAR - 1) / 400)) % 7);
        double days = (isDay(YEAR, Month, day) + S - 1) % 7;
        if(days == 0.00 || days == 6.00){
            return 1;
        }
        else{
            return 0;
        }
    }
    /**
     * 数据处理
     * @param ss
     * @return
     */

    @JavascriptInterface
    public String getT4Data(String ss)
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



            String heapmap_result = PlotFunction.getDataHeatmapType(time_list, diet_type, first_diet_time, is_now_flag, is_next_flag, time_string);
            String[] temp = heapmap_result.split("&");
            String plot_data = "[" + temp[0] + "]";
            String x_asix = "[" + temp[1] + "]";
            if(ss.equals("x")){
                StringBuffer bf = new StringBuffer(plot_data);
//            bf.delete(plot_data.length() - 1, plot_data.length());
                Log.i("heatmap", "getT5Data: " + bf.toString());
                return bf.toString();
            }
            else{
                StringBuffer bf = new StringBuffer(x_asix);
//            bf.delete(x_asix.length() - 1, x_asix.length());
                Log.i("heatmapaxis", "getT5Data: " + bf.toString());
                return bf.toString();
            }
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
            if(ss.equals("x")){
                StringBuffer bf = new StringBuffer(plot_data);
//            bf.delete(plot_data.length() - 1, plot_data.length());
                Log.i("heatmap", "getT5Data: " + bf.toString());
                return bf.toString();
            }
            else{
                StringBuffer bf = new StringBuffer(x_asix);
//            bf.delete(x_asix.length() - 1, x_asix.length());
                Log.i("heatmapaxis", "getT5Data: " + bf.toString());
                return bf.toString();
            }
        }









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
        diet_type = "Your eating window";
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
                time_redu = Integer.valueOf(temp[3]);
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




















    @JavascriptInterface
    public String getT41Data()
    {
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


        String heapmap_result = PlotFunction.getCircledata(time_list);


        StringBuffer bf = new StringBuffer(heapmap_result);
//            bf.delete(plot_data.length() - 1, plot_data.length());
        Log.i("heatmap", "getT5Data: " + bf.toString());
        return bf.toString();




    }

    @JavascriptInterface
    public boolean isWeekendData(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        boolean isWeekendFlag = false;
        if(isWeekEnd(year, month, day) == 0){
            Log.i("isweekend", "isWeekendData: " + "false");
            return false;

        }
        else{
            Log.i("isweekend", "isWeekendData: " + "true");
            return  true;
        }
    }

    @JavascriptInterface
    public boolean isWorkData(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        boolean isWeekendFlag = false;
        if(isWeekEnd(year, month, day) == 0){
            Log.i("isWork", "isWorkData: " + "true");
            return true;
        }
        else{
            Log.i("isWork", "isWorkData: " + "false");
            return false;
        }
    }


    @JavascriptInterface
    public String getT42Data(int num)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_path_new",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_cal","photo_kind","photo_web_url"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中

        String diet_type = MMKVUtils.getString("diet_type", "8:16 diet");
        int is_now_flag = MMKVUtils.getInt("is_now_flag", 0);
        int is_next_flag = MMKVUtils.getInt("is_next_flag", 0);

        String[] diet_6_8_10_12 = {"A"};
        String[] diet_5_2_alternate_eatstopeat = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
        String[] diet_warrior = {"Z"};
        String[] diet_spontaneous = {"A1", "A2"};

        List<String> diet_5_2_alternate_eatstopeat_list = Arrays.asList(diet_5_2_alternate_eatstopeat);
        int show_low_flag = 0;
        if(diet_5_2_alternate_eatstopeat_list.contains(diet_type)){
            if(is_now_flag == 1 || is_next_flag == 1){
                show_low_flag = 1;
            }
        }



        List<String> all_photo_url_list = new ArrayList<String>();
        List<String> all_photo_kind_list = new ArrayList<String>();
        List<String> low_photo_url_list = new ArrayList<String>();
        List<String> low_photo_kind_list = new ArrayList<String>();

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String photo_type = cursor.getString(cursor.getColumnIndex("photo_type"));
            //String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + cursor.getString(cursor.getColumnIndex("photo_web_url"));
            String photo_url = cursor.getString(cursor.getColumnIndex("photo_web_url"));
            String photo_cal = cursor.getString(cursor.getColumnIndex("photo_cal"));
            String photo_kind = cursor.getString(cursor.getColumnIndex("photo_kind"));
            if(photo_type.equals("food")){
                if(!(null == photo_cal)){
                    if(!all_photo_kind_list.contains(photo_kind)){
                        all_photo_url_list.add(photo_url);
                        all_photo_kind_list.add(photo_kind);
                        if(photo_cal.equals("1")){
                            low_photo_url_list.add(photo_url);
                            low_photo_kind_list.add(photo_kind);
                        }
                    }


                }
            }
        }
        int all_length = all_photo_url_list.size();
        int low_length = low_photo_url_list.size();

        List<String> all_output_url = new ArrayList<>();
        List<String> Low_output_url = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        if(all_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                all_output_url.add(all_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < all_photo_url_list.size(); i++){
                all_output_url.add(all_photo_url_list.get(i));
            }
        }


        if(low_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                Low_output_url.add(low_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < low_photo_url_list.size(); i++){
                Low_output_url.add(low_photo_url_list.get(i));
            }
        }

        String heapmap_result = "";


        if(show_low_flag == 1){
            if(num >= low_photo_url_list.size()){
                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
            }else{
                heapmap_result = low_photo_url_list.get(num);
            }




        }else{
            if(num >= all_photo_url_list.size()){
                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
            }else{
                heapmap_result = all_photo_url_list.get(num);
            }

        }


        String textview_data = "";


        StringBuffer bf = new StringBuffer(heapmap_result);
        Log.i("heatmap", "getT42Data: " + bf.toString());

        return bf.toString();




    }









    @JavascriptInterface
    public String getT42Data_url(int num)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"photo_web_url_table",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("photo_web_url_table", new String[]{"photo_type","photo_cal","photo_kind","photo_url"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中

        String diet_type = MMKVUtils.getString("diet_type", "8:16 diet");
        int is_now_flag = MMKVUtils.getInt("is_now_flag", 0);
        int is_next_flag = MMKVUtils.getInt("is_next_flag", 0);

        String[] diet_6_8_10_12 = {"A"};
        String[] diet_5_2_alternate_eatstopeat = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
        String[] diet_warrior = {"Z"};
        String[] diet_spontaneous = {"A1", "A2"};

        List<String> diet_5_2_alternate_eatstopeat_list = Arrays.asList(diet_5_2_alternate_eatstopeat);
        int show_low_flag = 0;
        if(diet_5_2_alternate_eatstopeat_list.contains(diet_type)){
            if(is_now_flag == 1 || is_next_flag == 1){
                show_low_flag = 1;
            }
        }



        List<String> all_photo_url_list = new ArrayList<String>();
        List<String> all_photo_kind_list = new ArrayList<String>();
        List<String> low_photo_url_list = new ArrayList<String>();
        List<String> low_photo_kind_list = new ArrayList<String>();
        List<String> high_photo_url_list = new ArrayList<String>();
        List<String> high_photo_kind_list = new ArrayList<String>();

        while(cursor.moveToNext()){
            String photo_type = cursor.getString(cursor.getColumnIndex("photo_type"));
            //String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + cursor.getString(cursor.getColumnIndex("photo_web_url"));
            String photo_url = cursor.getString(cursor.getColumnIndex("photo_url"));
            String photo_cal = cursor.getString(cursor.getColumnIndex("photo_cal"));
            String photo_kind = cursor.getString(cursor.getColumnIndex("photo_kind"));
            if(photo_type.equals("food")){
                if(!(null == photo_cal)){
                    if(!all_photo_kind_list.contains(photo_kind)){
                        all_photo_url_list.add(photo_url);
                        all_photo_kind_list.add(photo_kind);
                        if(photo_cal.equals("1")){
                            low_photo_url_list.add(photo_url);
                            low_photo_kind_list.add(photo_kind);
                        }
                        else if(photo_cal.equals("3")){
                            high_photo_url_list.add(photo_url);
                            high_photo_kind_list.add(photo_kind);
                        }
                    }


                }
            }
        }
        int all_length = all_photo_url_list.size();
        int low_length = low_photo_url_list.size();
        int high_length = high_photo_url_list.size();

        List<String> all_output_url = new ArrayList<>();
        List<String> Low_output_url = new ArrayList<>();
        List<String> High_output_url = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        if(all_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                all_output_url.add(all_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < all_photo_url_list.size(); i++){
                all_output_url.add(all_photo_url_list.get(i));
            }
        }


        if(low_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                Low_output_url.add(low_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < low_photo_url_list.size(); i++){
                Low_output_url.add(low_photo_url_list.get(i));
            }
        }


        if(high_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                High_output_url.add(high_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < high_photo_url_list.size(); i++){
                High_output_url.add(high_photo_url_list.get(i));
            }
        }

        String heapmap_result = "";


        if(show_low_flag == 1){
            if(num >= low_photo_url_list.size()){
                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
            }else{
                heapmap_result = low_photo_url_list.get(num);
            }




        }else{
            Double weight = Double.valueOf(MMKVUtils.getString("weight", "66.7"));
            Double height = Double.valueOf(MMKVUtils.getString("height", "167.1")) / 100;
            Double bmi = weight / (height * height);


            if(num >= all_photo_url_list.size()){
                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
            }else{
                if(bmi < 18.5){
                    int index = -1;
                    while(index == -1 || random_num.contains(index)){
                        index = (int)(Math.random() * high_photo_url_list.size());
                    }
                    random_num.add(index);
                    heapmap_result = high_photo_url_list.get(index);

                }else if(bmi > 23.9){
                    int index = -1;
                    while(index == -1 || random_num.contains(index)){
                        index = (int)(Math.random() * low_photo_url_list.size());
                    }
                    random_num.add(index);
                    heapmap_result = low_photo_url_list.get(index);

                }else{
                    int index = -1;
                    while(index == -1 || random_num.contains(index)){
                        index = (int)(Math.random() * all_photo_url_list.size());
                    }
                    random_num.add(index);
                    heapmap_result = all_photo_url_list.get(index);
                }
            }






//            if(num >= all_photo_url_list.size()){
//                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
//            }else{
//                heapmap_result = all_photo_url_list.get(num);
//            }

        }


        String textview_data = "";


        StringBuffer bf = new StringBuffer(heapmap_result);
        Log.i("heatmap", "getT42Data: " + bf.toString());

        return bf.toString();




    }
















    @JavascriptInterface
    public String getT42Data_local_url(int num)
    {

        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中

        String diet_type = MMKVUtils.getString("diet_type", "8:16 diet");
        int is_now_flag = MMKVUtils.getInt("is_now_flag", 0);
        int is_next_flag = MMKVUtils.getInt("is_next_flag", 0);

        String[] diet_6_8_10_12 = {"A"};
        String[] diet_5_2_alternate_eatstopeat = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
        String[] diet_warrior = {"Z"};
        String[] diet_spontaneous = {"A1", "A2"};

        List<String> diet_5_2_alternate_eatstopeat_list = Arrays.asList(diet_5_2_alternate_eatstopeat);
        int show_low_flag = 0;
        if(diet_5_2_alternate_eatstopeat_list.contains(diet_type)){
            if(is_now_flag == 1 || is_next_flag == 1){
                show_low_flag = 1;
            }
        }



        List<String> all_photo_url_list = new ArrayList<String>();
        List<String> all_photo_kind_list = new ArrayList<String>();
        List<String> low_photo_url_list = new ArrayList<String>();
        List<String> low_photo_kind_list = new ArrayList<String>();
        List<String> high_photo_url_list = new ArrayList<String>();
        List<String> high_photo_kind_list = new ArrayList<String>();

        List<String> namesList_all = Arrays.asList("img/l1.jpg","img/l2.jpg","img/l3.jpg","img/l4.jpg","img/l5.jpg","img/l6.jpg","img/l7.jpg","img/l8.jpg","img/l9.jpg","img/l10.jpg","img/l11.jpg","img/l12.jpg","img/l13.jpg","img/l14.jpg"
                ,"img/l15.jpg","img/l16.jpg","img/l17.jpg","img/l18.jpg","img/l19.jpg","img/l20.jpg","img/l21.jpg","img/l22.jpg","img/l23.jpg","img/l24.jpg","img/l25.jpg","img/l26.jpg","img/l27.jpg","img/l28.jpg","img/m1.jpg","img/m2.jpg"
                ,"img/m3.jpg","img/m4.jpg","img/m5.jpg","img/m6.jpg","img/m7.jpg","img/m8.jpg","img/m9.jpg","img/m10.jpg","img/m11.jpg","img/m12.jpg","img/m13.jpg","img/m14.jpg","img/m15.jpg","img/m16.jpg","img/m17.jpg","img/m18.jpg","img/m19.jpg"
                ,"img/m20.jpg","img/m21.jpg","img/m22.jpg","img/m23.jpg","img/m24.jpg","img/m25.jpg");
        all_photo_url_list.addAll(namesList_all);

        List<String> namesList_high = Arrays.asList("img/h1.jpg","img/h2.jpg","img/h3.jpg","img/h4.jpg","img/h5.jpg","img/h6.jpg","img/h7.jpg","img/h8.jpg","img/h9.jpg","img/h10.jpg","img/h11.jpg","img/h12.jpg","img/h13.jpg","img/h14.jpg"
                ,"img/h15.jpg","img/h16.jpg","img/h17.jpg","img/h18.jpg","img/h19.jpg","img/h20.jpg","img/h21.jpg","img/h22.jpg","img/h23.jpg","img/h24.jpg","img/h25.jpg","img/h26.jpg");
        high_photo_url_list.addAll(namesList_high);

        List<String> namesList_low = Arrays.asList("img/l1.jpg","img/l2.jpg","img/l3.jpg","img/l4.jpg","img/l5.jpg","img/l6.jpg","img/l7.jpg","img/l8.jpg","img/l9.jpg","img/l10.jpg","img/l11.jpg","img/l12.jpg","img/l13.jpg","img/l14.jpg"
                ,"img/l15.jpg","img/l16.jpg","img/l17.jpg","img/l18.jpg","img/l19.jpg","img/l20.jpg","img/l21.jpg","img/l22.jpg","img/l23.jpg","img/l24.jpg","img/l25.jpg","img/l26.jpg","img/l27.jpg","img/l28.jpg");
        low_photo_url_list.addAll(namesList_low);






//        while(cursor.moveToNext()){
//            String photo_type = cursor.getString(cursor.getColumnIndex("photo_type"));
//            //String photo_url = "http://39.100.73.118/deeplearning_photo/uploads/" + cursor.getString(cursor.getColumnIndex("photo_web_url"));
//            String photo_url = cursor.getString(cursor.getColumnIndex("photo_url"));
//            String photo_cal = cursor.getString(cursor.getColumnIndex("photo_cal"));
//            String photo_kind = cursor.getString(cursor.getColumnIndex("photo_kind"));
//            if(photo_type.equals("food")){
//                if(!(null == photo_cal)){
//                    if(!all_photo_kind_list.contains(photo_kind)){
//                        all_photo_url_list.add(photo_url);
//                        all_photo_kind_list.add(photo_kind);
//                        if(photo_cal.equals("1")){
//                            low_photo_url_list.add(photo_url);
//                            low_photo_kind_list.add(photo_kind);
//                        }
//                        else if(photo_cal.equals("3")){
//                            high_photo_url_list.add(photo_url);
//                            high_photo_kind_list.add(photo_kind);
//                        }
//                    }
//
//
//                }
//            }
//        }
        int all_length = all_photo_url_list.size();
        int low_length = low_photo_url_list.size();
        int high_length = high_photo_url_list.size();

        List<String> all_output_url = new ArrayList<>();
        List<String> Low_output_url = new ArrayList<>();
        List<String> High_output_url = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        if(all_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                all_output_url.add(all_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < all_photo_url_list.size(); i++){
                all_output_url.add(all_photo_url_list.get(i));
            }
        }


        if(low_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                Low_output_url.add(low_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < low_photo_url_list.size(); i++){
                Low_output_url.add(low_photo_url_list.get(i));
            }
        }

        if(high_length > 12){

            while(true) {
                int n = (int)(Math.random()*(12+1));
                set.add(n);
                if(set.size()>=12)
                    break;
            }
            List<Integer> set_list = new ArrayList(set);
            for(int i = 0; i < 12; i++){
                High_output_url.add(high_photo_url_list.get(set_list.get(i)));
            }
        }else{
            for(int i = 0; i < high_photo_url_list.size(); i++){
                High_output_url.add(high_photo_url_list.get(i));
            }
        }

        String heapmap_result = "";


        if(show_low_flag == 1){
            if(num >= low_photo_url_list.size()){
                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
            }else{
                heapmap_result = low_photo_url_list.get(num);
            }




        }else{
            Double weight = Double.valueOf(MMKVUtils.getString("weight", "66.7"));
            Double height = Double.valueOf(MMKVUtils.getString("height", "167.1")) / 100;
            Double bmi = weight / (height * height);


            if(num >= all_photo_url_list.size()){
                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
            }else{
                if(bmi < 18.5){
                    int index = -1;
                    while(index == -1 || random_num.contains(index)){
                        index = (int)(Math.random() * high_photo_url_list.size());
                    }
                    random_num.add(index);
                    heapmap_result = high_photo_url_list.get(index);

                }else if(bmi > 23.9){
                    int index = -1;
                    while(index == -1 || random_num.contains(index)){
                        index = (int)(Math.random() * low_photo_url_list.size());
                    }
                    random_num.add(index);
                    heapmap_result = low_photo_url_list.get(index);

                }else{
                    int index = -1;
                    while(index == -1 || random_num.contains(index)){
                        index = (int)(Math.random() * all_photo_url_list.size());
                    }
                    random_num.add(index);
                    heapmap_result = all_photo_url_list.get(index);
                }
            }




//
//
//            if(num >= all_photo_url_list.size()){
//                heapmap_result = "http://39.100.73.118/deeplearning_photo/uploads/temp_food.jpg";
//            }else{
//                heapmap_result = all_photo_url_list.get(num);
//            }

        }


        String textview_data = "";
//
//
        StringBuffer bf = new StringBuffer(heapmap_result);
        Log.i("heatmap", "getT42Data: " + bf.toString());

        return bf.toString();




    }







//    @OnClick({R.id.xyg})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.xyg:
//                if(isFirstFlag == 0){
//                    view.setText("显示推荐饮食时间");
//                    isFirstFlag = 1;
//                }
//                else{
//                    view.setText("显示推荐食物");
//                    isFirstFlag = 0;
//                }
//                itme=itme<2?++itme:0;
//                setWebViewItme();
//                break;
//        }
//
//    }
//
//
    public void setWebViewItme()
    {
        switch (itme)
        {
            case 0:
//                myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=1");
                String current_weight = MMKVUtils.getString("latest_weight", "80");
                String current_height = MMKVUtils.getString("height", "170");

                String workday_list = getT5Data(1);
                String weekend_list = getT5Data(2);
                String percentage = "0.8";
                int current_color = 1;
                int time_total = 100;
                String can_eat_flag = "TRUE";
                boolean isShowEatingTime = MMKVUtils.getBoolean("IS_SHOW_EatingTime", true);
                boolean server_time_edit = MMKVUtils.getBoolean("server_time_edit", false);
                if(server_time_edit){
                    String eating_start_time = MMKVUtils.getString("eating_time_start","8:00");
                    String eating_end_time = MMKVUtils.getString("eating_time_end","16:00");
                    Date date = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                    Date data_begin = null;
                    Date date_end = null;
                    try {
                        data_begin = df.parse(eating_start_time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        date_end = df.parse(eating_end_time);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    String hour_num = "";
                    if(hour < 10){
                        hour_num = "0" + String.valueOf(hour);
                    }else{
                        hour_num = String.valueOf(hour);
                    }
                    String minute_num = "";
                    if(minute < 10){
                        minute_num = "0" + String.valueOf(minute);
                    }else{
                        minute_num = String.valueOf(minute);
                    }
                    Date date_current = null;
                    try {
                        date_current = df.parse(hour_num+":"+minute_num);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar dateC = Calendar.getInstance();
                    dateC.setTime(date_current);
                    Calendar begin = Calendar.getInstance();
                    begin.setTime(data_begin);
                    Calendar end = Calendar.getInstance();
                    end.setTime(date_end);
                    if (dateC.after(begin) && dateC.before(end)) {
                        can_eat_flag = "TRUE";
                        int difference = (int) (date_end.getTime() - date_current.getTime());
                        time_total = difference / 1000;
                        percentage = String.valueOf(16*60*60);
                    }else{
                        can_eat_flag = "FALSE";
                        if(dateC.before(begin)){
                            int difference = (int) (data_begin.getTime() - date_current.getTime());
                            time_total = difference / 1000;
                            percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                        }else{
                            int difference = 0;
                            try {
                                difference = (int) (df.parse("23:59").getTime() - date_current.getTime()) + (int) (data_begin.getTime() - df.parse("00:00").getTime());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            time_total = difference / 1000;
                            percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                        }

                    }

                    if(can_eat_flag.equals("FALSE")){
//            current_color = "\"#F04864\"";
                        current_color = 1;
                    }else{
//            current_color = "\"#2FC25B\"";
                        current_color = 2;
                    }
                }else{
                    if(!isShowEatingTime){
                        String eating_start_time = MMKVUtils.getString("eating_time_start","8:00");
                        String eating_end_time = MMKVUtils.getString("eating_time_end","16:00");
                        Date date = new Date();
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                        Date data_begin = null;
                        Date date_end = null;
                        try {
                            data_begin = df.parse(eating_start_time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            date_end = df.parse(eating_end_time);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        String hour_num = "";
                        if(hour < 10){
                            hour_num = "0" + String.valueOf(hour);
                        }else{
                            hour_num = String.valueOf(hour);
                        }
                        String minute_num = "";
                        if(minute < 10){
                            minute_num = "0" + String.valueOf(minute);
                        }else{
                            minute_num = String.valueOf(minute);
                        }
                        Date date_current = null;
                        try {
                            date_current = df.parse(hour_num+":"+minute_num);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Calendar dateC = Calendar.getInstance();
                        dateC.setTime(date_current);
                        Calendar begin = Calendar.getInstance();
                        begin.setTime(data_begin);
                        Calendar end = Calendar.getInstance();
                        end.setTime(date_end);
                        if (dateC.after(begin) && dateC.before(end)) {
                            can_eat_flag = "TRUE";
                            int difference = (int) (date_end.getTime() - date_current.getTime());
                            time_total = difference / 1000;
                            percentage = String.valueOf(16*60*60);
                        }else{
                            can_eat_flag = "FALSE";
                            if(dateC.before(begin)){
                                int difference = (int) (data_begin.getTime() - date_current.getTime());
                                time_total = difference / 1000;
                                percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                            }else{
                                int difference = 0;
                                try {
                                    difference = (int) (df.parse("23:59").getTime() - date_current.getTime()) + (int) (data_begin.getTime() - df.parse("00:00").getTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                time_total = difference / 1000;
                                percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                            }

                        }

                        if(can_eat_flag.equals("FALSE")){
//            current_color = "\"#F04864\"";
                            current_color = 1;
                        }else{
//            current_color = "\"#2FC25B\"";
                            current_color = 2;
                        }



                    }else{
                        String current_diet_z = getDietTime("z");
                        int index_num = 0;

                        String[] diet_level = current_diet_z.split(",");
                        if(diet_level[0].equals("1")||diet_level[0].equals("2")||diet_level[0].equals("3")){
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

                        time_total = index_num*30*60;
                        Calendar calendar = Calendar.getInstance();
                        int minute = calendar.get(Calendar.MINUTE);
                        int second = calendar.get(Calendar.SECOND);
                        String minute_s;

                        if(minute < 30){
                            time_total = time_total - minute*60 - second;
                            minute_s = "0" + String.valueOf(minute);
                        }
                        else{
                            time_total = time_total - (minute-30)*60 - second;
                        }


                        time_total = time_total + time_redu;

                        if(can_eat_flag.equals("FALSE")&&time_total > 16*60*60){
                            can_eat_flag="TRUE";
                            time_total = time_total - 16*60*60;
                        }
                        else if(can_eat_flag.equals("TRUE")&&time_total > 8*60*60){
                            can_eat_flag="FALSE";
                            time_total = time_total - 8*60*60;
                        }


                        if(can_eat_flag.equals("FALSE")){
                            percentage = String.valueOf(16*60*60);
//            current_color = "\"#F04864\"";
                            current_color = 1;
                        }else{
//            current_color = "\"#2FC25B\"";
                            current_color = 2;
                            percentage = String.valueOf(8*60*60);
                        }
                    }
                }





                String test_year = "2018,2019,2020,2021,2022,2023";
                String sleep_x = getSleep(1);
                String sleep_y1 = getSleep(2);
                String sleep_y2 = getSleep(3);
                String sleep_dis_x = getSleep1(1);
                String sleep_dis_y1 = getSleep1(2);
                String sleep_dis_y2 = getSleep1(3);


                String start_time = MMKVUtils.getString("eating_time_start", "8:00");
                String end_time = MMKVUtils.getString("eating_time_end", "16:00");
                String canedittime = "false";
                if(isShowEatingTime){
                    canedittime = "false";
                }else{
                    canedittime = "true";
                }


                myView.loadUrl( "file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color+"&sleep_x="+sleep_x+"&sleep_start_time="+sleep_y1+"&sleep_end_time="+sleep_y2+"&sleep_dis_x="+sleep_dis_x+"&sleep_dis_y1="+sleep_dis_y1+"&sleep_dis_y2="+sleep_dis_y2+"&workday_list="+workday_list+"&weekend_list="+weekend_list
                        +"&start_time="+start_time+"&end_time="+end_time+"&canedittime="+canedittime+"&percentage="+percentage);
                //myView.loadUrl( "http://10.10.114.202:8080/#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);
                //myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color);

                global_index = 0;
                break;
//            case 1:
//                myView.loadUrl("file:///android_asset/linear_echart_new.html");
//                break;
            case 1:



                myView.loadUrl("file:///android_asset/index3.html");
                global_index = 1;
//                String[] loop_time_list = {"2020.09.06-03:00:00", "2020.09.06-03:30:00", "2020.09.06-04:00:00", "2020.09.06-04:30:00", "2020.09.06-05:00:00", "2020.09.06-05:30:00", "2020.09.06-06:00:00", "2020.09.06-06:30:00", "2020.09.06-07:00:00", "2020.09.06-07:30:00",
//                    "2020.09.06-08:00:00", "2020.09.06-08:30:00", "2020.09.06-09:00:00", "2020.09.06-09:30:00", "2020.09.06-10:00:00", "2020.09.06-10:30:00", "2020.09.06-11:00:00", "2020.09.06-11:30:00", "2020.09.06-12:00:00", "2020.09.06-12:30:00", "2020.09.06-13:00:00",
//                    "2020.09.06-13:30:00", "2020.09.06-14:00:00", "2020.09.06-14:30:00", "2020.09.06-15:00:00", "2020.09.06-15:30:00", "2020.09.06-16:00:00", "2020.09.06-16:30:00", "2020.09.06-17:00:00", "2020.09.06-17:30:00", "2020.09.06-18:00:00", "2020.09.06-18:30:00",
//                    "2020.09.06-19:00:00", "2020.09.06-19:30:00", "2020.09.06-20:00:00", "2020.09.06-20:30:00", "2020.09.06-21:00:00", "2020.09.06-21:30:00", "2020.09.06-22:00:00", "2020.09.06-22:30:00", "2020.09.06-23:00:00", "2020.09.06-23:30:00", "2020.09.07-00:00:00",
//                    "2020.09.07-00:30:00", "2020.09.07-01:00:00", "2020.09.07-01:30:00", "2020.09.07-02:00:00", "2020.09.07-02:30:00"};
//
//                String[] loop_time_list1 = {"2020.09.05-03:00:00",  "2020.09.05-04:00:00",  "2020.09.05-05:00:00",  "2020.09.05-06:00:00",  "2020.09.05-07:00:00",
//                        "2020.09.05-08:00:00",  "2020.09.05-09:00:00", "2020.09.05-10:00:00",  "2020.09.05-11:00:00",  "2020.09.05-12:00:00",  "2020.09.05-13:00:00",
//                         "2020.09.05-14:00:00",  "2020.09.05-15:00:00", "2020.09.05-16:00:00",  "2020.09.05-17:00:00",  "2020.09.05-18:00:00", "2020.09.05-19:00:00",
//                        "2020.09.05-20:00:00",  "2020.09.05-21:00:00",  "2020.09.05-22:00:00", "2020.09.05-22:30:00", "2020.09.05-23:00:00", "2020.09.05-23:30:00", "2020.09.06-00:00:00",
//                        "2020.09.06-00:30:00", "2020.09.06-01:00:00", "2020.09.06-01:30:00", "2020.09.06-02:00:00", "2020.09.06-02:30:00","2020.09.06-03:00:00", "2020.09.06-03:30:00", "2020.09.06-04:00:00", "2020.09.06-04:30:00", "2020.09.06-05:00:00", "2020.09.06-05:30:00", "2020.09.06-06:00:00", "2020.09.06-06:30:00", "2020.09.06-07:00:00", "2020.09.06-07:30:00",
//                        "2020.09.06-08:00:00",  "2020.09.06-09:00:00",  "2020.09.06-10:00:00",  "2020.09.06-11:00:00",  "2020.09.06-12:00:00",  "2020.09.06-13:00:00",
//                         "2020.09.06-14:00:00",  "2020.09.06-15:00:00",  "2020.09.06-16:00:00", "2020.09.06-17:00:00",  "2020.09.06-18:00:00",
//                        "2020.09.06-19:00:00",  "2020.09.06-20:00:00",  "2020.09.06-21:00:00",  "2020.09.06-22:00:00",  "2020.09.06-23:00:00",  "2020.09.07-00:00:00",
//                         "2020.09.07-01:00:00",  "2020.09.07-02:00:00", "2020.09.07-03:00:00", "2020.09.07-03:30:00", "2020.09.07-04:00:00", "2020.09.07-04:30:00", "2020.09.07-05:00:00", "2020.09.07-05:30:00", "2020.09.07-06:00:00", "2020.09.07-06:30:00", "2020.09.07-07:00:00", "2020.09.07-07:30:00",
//                        "2020.09.07-08:00:00", "2020.09.07-08:30:00", "2020.09.07-09:00:00", "2020.09.07-09:30:00", "2020.09.07-10:00:00", "2020.09.07-10:30:00", "2020.09.07-11:00:00", "2020.09.07-11:30:00", "2020.09.07-12:00:00", "2020.09.07-12:30:00", "2020.09.07-13:00:00",
//                        "2020.09.07-13:30:00", "2020.09.07-14:00:00", "2020.09.07-14:30:00", "2020.09.07-15:00:00", "2020.09.07-15:30:00", "2020.09.07-16:00:00", "2020.09.07-16:30:00", "2020.09.07-17:00:00", "2020.09.07-17:30:00", "2020.09.07-18:00:00", "2020.09.07-18:30:00",
//                        "2020.09.07-19:00:00", "2020.09.07-19:30:00", "2020.09.07-20:00:00", "2020.09.07-20:30:00", "2020.09.07-21:00:00", "2020.09.07-21:30:00", "2020.09.07-22:00:00", "2020.09.07-22:30:00", "2020.09.07-23:00:00", "2020.09.07-23:30:00", "2020.09.08-00:00:00",
//                        "2020.09.08-00:30:00", "2020.09.08-01:00:00", "2020.09.08-01:30:00", "2020.09.08-02:00:00", "2020.09.08-02:30:00"};
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        for(int i = 0; i < 48; i++){
//                            String total_txt_output = "";
//                            for(int j = i; j < 48; j++){
//                                List<String> time_list = new ArrayList<>();
//                                time_list.add(loop_time_list1[i]);
//                                if(j != i){
//                                    time_list.add(loop_time_list1[j]);
//                                }
//                                for(int h = j + 1; h < j + 49; h ++){
//                                    total_txt_output += loop_time_list1[i] + "&" + loop_time_list1[j] + "&" + loop_time_list1[h] + "&";
//                                    total_txt_output += PlotFunction.getDataHeatmapType_txtoutput(time_list, loop_time_list1[h]) + "\n";
//
//                                }
//                            }
//
//                            String strFilePath = Environment.getExternalStorageDirectory() + "/DietClock_output_txt/output_all_contain_input_new1.txt";
//                            try {
//                                File file = new File(strFilePath);
//                                if (!file.exists()) {
//                                    Log.d("TestFile", "Create the file:" + strFilePath);
//                                    file.getParentFile().mkdirs();
//                                    file.createNewFile();
//                                }
//                                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
//                                raf.seek(file.length());
//                                raf.write(total_txt_output.getBytes());
//                                raf.close();
//                            } catch (Exception e) {
//                                Log.e("TestFile", "Error on write File:" + e);
//                            }
//                        }
//
//                    }
//                }).start();




//                String total_txt_output = "";
//                for(int i = 0; i < 47; i++){
//                    for(int j = i + 1; j < 48; j++){
//
//                        List<String> time_list = new ArrayList<>();
//                        time_list.add(loop_time_list[i]);
//                        for(int k = 0; k < j - i - 1; k ++){
//                            int rd=Math.random()>0.5?1:0;
//                            if(rd == 1){
//                                time_list.add(loop_time_list[i + k + 1]);
//                            }
//                        }
//                        time_list.add(loop_time_list[j]);
//                        total_txt_output += PlotFunction.getDataHeatmapType_txtoutput(time_list, loop_time_list[j]) + "\n";
//                    }
//                }
//                String strFilePath = Environment.getExternalStorageDirectory() + "/DietClock_output_txt/output_random.txt";
//                try {
//                    File file = new File(strFilePath);
//                    if (!file.exists()) {
//                        Log.d("TestFile", "Create the file:" + strFilePath);
//                        file.getParentFile().mkdirs();
//                        file.createNewFile();
//                    }
//                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
//                    raf.seek(file.length());
//                    raf.write(total_txt_output.getBytes());
//                    raf.close();
//                } catch (Exception e) {
//                    Log.e("TestFile", "Error on write File:" + e);
//                }



















//                myView.loadUrl("file:///android_asset/index1.html");
//                List<String>time_list_array = new ArrayList<>();
//                time_list_array.add("2020.09.06-00:15:00");
//                time_list_array.add("2020.09.06-00:35:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:05:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:35:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:05:00");
//                time_list_array.add("2020.09.06-00:35:00;2020.09.06-01:43:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-00:35:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-01:05:00");//7
//
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-03:05:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-17:05:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-22:05:00");
//                time_list_array.add("2020.09.06-00:15:00;2020.09.06-12:05:00");//11
//
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-12:35:00");
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-15:05:00");
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-18:05:00");
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-22:35:00");
//                time_list_array.add("2020.09.06-03:15:00;2020.09.07-01:05:00");
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-08:05:00");//17
//
//                time_list_array.add("2020.09.06-10:35:00;2020.09.06-14:05:00");
//                time_list_array.add("2020.09.06-10:35:00;2020.09.06-23:05:00");
//                time_list_array.add("2020.09.06-10:35:00;2020.09.06-17:05:00");
//                time_list_array.add("2020.09.06-10:35:00;2020.09.07-01:30:00");
//                time_list_array.add("2020.09.06-10:35:00;2020.09.06-19:35:00");//22
//
//                time_list_array.add("2020.09.06-17:35:00;2020.09.06-18:15:00");
//                time_list_array.add("2020.09.06-17:35:00");
//                time_list_array.add("2020.09.06-17:35:00;2020.09.06-22:15:00");
//                time_list_array.add("2020.09.06-17:35:00;2020.09.07-00:15:00");//26
//
//                time_list_array.add("2020.09.06-22:05:00;2020.09.06-22:15:00");
//                time_list_array.add("2020.09.06-22:05:00");
//                time_list_array.add("2020.09.06-22:05:00;2020.09.07-01:35:00");
//                time_list_array.add("2020.09.07-00:15:00;2020.09.07-01:35:00");//30
//
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-16:05:00");
//
//
//
//                time_list_array.add("2020.09.06-03:15:00;2020.09.06-16:05:00");
//
//                time_list_array.add("2020.09.05-21:00:00;2020.09.06-02:30:00");
//
//
//                MMKVUtils.put("is_draw_debug", 8);
//
//
//
//                    is_draw_debug_global = 33;
//                    global_last_time = "2020.09.07-04:05:00";
//                    myView.loadUrl("file:///android_asset/linear_echart.html");
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            View dView = (WebView) findViewById(R.id.myView);
//                            dView.setDrawingCacheEnabled(true);
//                            dView.buildDrawingCache();
//                            Bitmap bmp = dView.getDrawingCache();
//                            String time_string = time_list_array.get(is_draw_debug_global) + "_" + "1";
//                            String photo_name = time_string + ".jpg";
//
//                            saveBitmap(bmp, photo_name);
//                        }
//                }).start();
//
//
//
//
//                MMKVUtils.put("is_draw_debug", -1);
                //is_draw_debug_global = -1;
                break;

        }
    }



//    @Override
//    public void onResume() {
//        super.onResume();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }





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

        if(firstStartFlag == 0){
            firstStartFlag = 1;
        }
        else{
            myView = (WebView) findViewById(R.id.myView);
            myView.getSettings().setAllowFileAccess(true);
            myView.getSettings().setJavaScriptEnabled(true);
            //android 是我们在Js中调用当前方法的名字
            myView.addJavascriptInterface(this, "android");
            //这是让WebView 自己处理网页加载请求
            myView.setWebChromeClient(new WebChromeClient());
            //加载网页
//            myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=1");
            String current_weight = MMKVUtils.getString("latest_weight", "80");
            String current_height = MMKVUtils.getString("height", "170");

            String workday_list = getT5Data(1);
            String weekend_list = getT5Data(2);
            String percentage = "0.8";
            int current_color = 1;
            int time_total = 100;
            String can_eat_flag = "TRUE";
            boolean isShowEatingTime = MMKVUtils.getBoolean("IS_SHOW_EatingTime", true);
            boolean server_time_edit = MMKVUtils.getBoolean("server_time_edit", false);
            if(server_time_edit){
                String eating_start_time = MMKVUtils.getString("eating_time_start","8:00");
                String eating_end_time = MMKVUtils.getString("eating_time_end","16:00");
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date data_begin = null;
                Date date_end = null;
                try {
                    data_begin = df.parse(eating_start_time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date_end = df.parse(eating_end_time);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                String hour_num = "";
                if(hour < 10){
                    hour_num = "0" + String.valueOf(hour);
                }else{
                    hour_num = String.valueOf(hour);
                }
                String minute_num = "";
                if(minute < 10){
                    minute_num = "0" + String.valueOf(minute);
                }else{
                    minute_num = String.valueOf(minute);
                }
                Date date_current = null;
                try {
                    date_current = df.parse(hour_num+":"+minute_num);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar dateC = Calendar.getInstance();
                dateC.setTime(date_current);
                Calendar begin = Calendar.getInstance();
                begin.setTime(data_begin);
                Calendar end = Calendar.getInstance();
                end.setTime(date_end);
                if (dateC.after(begin) && dateC.before(end)) {
                    can_eat_flag = "TRUE";
                    int difference = (int) (date_end.getTime() - date_current.getTime());
                    time_total = difference / 1000;
                    percentage = String.valueOf(16*60*60);
                }else{
                    can_eat_flag = "FALSE";
                    if(dateC.before(begin)){
                        int difference = (int) (data_begin.getTime() - date_current.getTime());
                        time_total = difference / 1000;
                        percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                    }else{
                        int difference = 0;
                        try {
                            difference = (int) (df.parse("23:59").getTime() - date_current.getTime()) + (int) (data_begin.getTime() - df.parse("00:00").getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        time_total = difference / 1000;
                        percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                    }

                }

                if(can_eat_flag.equals("FALSE")){
//            current_color = "\"#F04864\"";
                    current_color = 1;
                }else{
//            current_color = "\"#2FC25B\"";
                    current_color = 2;
                }
            }else{
                if(!isShowEatingTime){
                    String eating_start_time = MMKVUtils.getString("eating_time_start","8:00");
                    String eating_end_time = MMKVUtils.getString("eating_time_end","16:00");
                    Date date = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                    Date data_begin = null;
                    Date date_end = null;
                    try {
                        data_begin = df.parse(eating_start_time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        date_end = df.parse(eating_end_time);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    String hour_num = "";
                    if(hour < 10){
                        hour_num = "0" + String.valueOf(hour);
                    }else{
                        hour_num = String.valueOf(hour);
                    }
                    String minute_num = "";
                    if(minute < 10){
                        minute_num = "0" + String.valueOf(minute);
                    }else{
                        minute_num = String.valueOf(minute);
                    }
                    Date date_current = null;
                    try {
                        date_current = df.parse(hour_num+":"+minute_num);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar dateC = Calendar.getInstance();
                    dateC.setTime(date_current);
                    Calendar begin = Calendar.getInstance();
                    begin.setTime(data_begin);
                    Calendar end = Calendar.getInstance();
                    end.setTime(date_end);
                    if (dateC.after(begin) && dateC.before(end)) {
                        can_eat_flag = "TRUE";
                        int difference = (int) (date_end.getTime() - date_current.getTime());
                        time_total = difference / 1000;
                        percentage = String.valueOf(16*60*60);
                    }else{
                        can_eat_flag = "FALSE";
                        if(dateC.before(begin)){
                            int difference = (int) (data_begin.getTime() - date_current.getTime());
                            time_total = difference / 1000;
                            percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                        }else{
                            int difference = 0;
                            try {
                                difference = (int) (df.parse("23:59").getTime() - date_current.getTime()) + (int) (data_begin.getTime() - df.parse("00:00").getTime());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            time_total = difference / 1000;
                            percentage = String.valueOf((24*60*60-(double)((date_end.getTime() - data_begin.getTime())/1000)));
                        }

                    }

                    if(can_eat_flag.equals("FALSE")){
//            current_color = "\"#F04864\"";
                        current_color = 1;
                    }else{
//            current_color = "\"#2FC25B\"";
                        current_color = 2;
                    }



                }else{
                    String current_diet_z = getDietTime("z");
                    int index_num = 0;

                    String[] diet_level = current_diet_z.split(",");
                    if(diet_level[0].equals("1")||diet_level[0].equals("2")||diet_level[0].equals("3")){
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

                    time_total = index_num*30*60;
                    Calendar calendar = Calendar.getInstance();
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);
                    String minute_s;

                    if(minute < 30){
                        time_total = time_total - minute*60 - second;
                        minute_s = "0" + String.valueOf(minute);
                    }
                    else{
                        time_total = time_total - (minute-30)*60 - second;
                    }


                    time_total = time_total + time_redu;

                    if(can_eat_flag.equals("FALSE")&&time_total > 16*60*60){
                        can_eat_flag="TRUE";
                        time_total = time_total - 16*60*60;
                    }
                    else if(can_eat_flag.equals("TRUE")&&time_total > 8*60*60){
                        can_eat_flag="FALSE";
                        time_total = time_total - 8*60*60;
                    }


                    if(can_eat_flag.equals("FALSE")){
                        percentage = String.valueOf(16*60*60);
//            current_color = "\"#F04864\"";
                        current_color = 1;
                    }else{
//            current_color = "\"#2FC25B\"";
                        current_color = 2;
                        percentage = String.valueOf(8*60*60);
                    }
                }
            }





            String test_year = "2018,2019,2020,2021,2022,2023";
            String sleep_x = getSleep(1);
            String sleep_y1 = getSleep(2);
            String sleep_y2 = getSleep(3);
            String sleep_dis_x = getSleep1(1);
            String sleep_dis_y1 = getSleep1(2);
            String sleep_dis_y2 = getSleep1(3);


            String start_time = MMKVUtils.getString("eating_time_start", "8:00");
            String end_time = MMKVUtils.getString("eating_time_end", "16:00");
            String canedittime = "false";
            if(isShowEatingTime){
                canedittime = "false";
            }else{
                canedittime = "true";
            }



            myView.loadUrl( "file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color+"&sleep_x="+sleep_x+"&sleep_start_time="+sleep_y1+"&sleep_end_time="+sleep_y2+"&sleep_dis_x="+sleep_dis_x+"&sleep_dis_y1="+sleep_dis_y1+"&sleep_dis_y2="+sleep_dis_y2+"&workday_list="+workday_list+"&weekend_list="+weekend_list
                    +"&start_time="+start_time+"&end_time="+end_time+"&canedittime="+canedittime+"&percentage="+percentage);
            final Button btn = findViewById(R.id.xyg);
            boolean isShowFood = MMKVUtils.getBoolean("IS_SHOW_FOOD", false);
        if(isShowFood){
            btn.setVisibility(View.VISIBLE);
        }
        else{
            btn.setVisibility(View.INVISIBLE);
            myView = (WebView) findViewById(R.id.myView);
            myView.getSettings().setAllowFileAccess(true);
            myView.getSettings().setJavaScriptEnabled(true);
            //android 是我们在Js中调用当前方法的名字
            myView.addJavascriptInterface(this, "android");
            //这是让WebView 自己处理网页加载请求
            myView.setWebChromeClient(new WebChromeClient());

            //加载网页
//            myView.loadUrl("file:///android_asset/index.html#/pages/index/second?abc=1");
            myView.loadUrl( "file:///android_asset/index.html#/pages/index/second?abc=35, 36, 31, 33, 13, 34&categories=\"a2018\", \"2019\", \"2020\", \"2021\", \"2022\", \"2023\"&weight="+current_weight+"&height="+current_height+"&timetotal="+time_total+"&eatflag="+current_color+"&sleep_x="+sleep_x+"&sleep_start_time="+sleep_y1+"&sleep_end_time="+sleep_y2+"&sleep_dis_x="+sleep_dis_x+"&sleep_dis_y1="+sleep_dis_y1+"&sleep_dis_y2="+sleep_dis_y2+"&workday_list="+workday_list+"&weekend_list="+weekend_list
                    +"&start_time="+start_time+"&end_time="+end_time+"&canedittime="+canedittime+"&percentage="+percentage);
        }
        }


        Bundle bundle=getArguments();
        String s=bundle.getString("one");

        //                String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
//                String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
//                String pic_name = "heatmap.html";
//                String temp_url = url_head + pic_name;
                //目前Echarts-Java只支持3.x

//        if(s.equals("2")) {
//            if (getActivity() != null) {
////                String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
////                String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
////                String pic_name = "heatmap.html";
////                String temp_url = url_head + pic_name;
//                //目前Echarts-Java只支持3.x
//                myView = (WebView) findViewById(R.id.myView);
//                myView.getSettings().setAllowFileAccess(true);
//                myView.getSettings().setJavaScriptEnabled(true);
//                //android 是我们在Js中调用当前方法的名字
//                myView.addJavascriptInterface(this, "android");
//                //这是让WebView 自己处理网页加载请求
//                myView.setWebChromeClient(new WebChromeClient());
//                //加载网页
//                myView.loadUrl("file:///android_asset/linear_echart.html");
//            }
//            bundle.putString("one","1");
//        }


    }

    protected void onInvisible() {


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