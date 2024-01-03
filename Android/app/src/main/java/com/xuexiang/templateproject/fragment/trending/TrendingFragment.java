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

package com.xuexiang.templateproject.fragment.trending;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.fragment.profile.BodyIndexFragment;
import com.xuexiang.templateproject.fragment.profile.DiseaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Page(name = "test",anim = CoreAnim.none)
public class TrendingFragment extends BaseFragment {

    private WebView myView;
    private WebView webview;
    protected boolean isVisible;
    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
    private static final int MSG_FAILURE = 1;//获取图片失败的标识

    int firstStartFlag = 0;



    int global_index = 0;

    int this_flag = 1;
    String title = "";
    int isFirstFlag = 0;

    List<Integer> random_num = new ArrayList<>();

    List<Integer> index_group = new ArrayList<>();

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
        //加载网页
//        myView.loadUrl("file:///android_asset/index.html");
        myView.setVerticalScrollBarEnabled(true);
        myView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myView.setHorizontalScrollBarEnabled(true);
        myView.getSettings().setJavaScriptEnabled(true);
        myView.addJavascriptInterface(this, "nativeMethod");

        String temp_res1 = MMKVUtils.getString("index_table_string", "");
        String[] index_info = temp_res1.split("<");
        List<String> index_raw = new ArrayList<>();
        index_raw.add("Weight");
        index_raw.add("Height");
        index_raw.add("Diastolic blood pressure");
        index_raw.add("Systolic blood pressure");
        index_raw.add("Heart rate");
        index_raw.add("Waist circumference");
        index_raw.add("Gender");
        index_raw.add("Age");
        index_raw.add("Disease status");
        index_raw.add("Exercise duration");
        index_raw.add("Sleep time");
        String new_index_name = "";

        for(int i = 0; i < index_info.length;i++ ){
            if(index_info[i].split(";").length > 1){
                String temp_index_name = index_info[i].split(";")[2];
                if(!index_raw.contains(temp_index_name)){
                    if(new_index_name.equals("")){
                        new_index_name = new_index_name + temp_index_name;
                    }else{
                        new_index_name = new_index_name + ";" + temp_index_name;
                    }
                }
            }

        }


        myView.loadUrl("file:///android_asset/index.html#/"+"?name="+new_index_name);
//        myView.loadUrl("http://10.10.114.202:8080/#/"+"?name="+new_index_name);
//        myView.loadUrl("file:///android_asset/index.html"+"&name=testA;testB");
        boolean isShowFood = MMKVUtils.getBoolean("IS_SHOW_FOOD", false);

        myView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);


    }




    @JavascriptInterface
    public void toActivity(String activityName) {
        //此处应该定义常量对应，同时提供给web页面编写者
//        if(TextUtils.equals(activityName, "Sleep time")){
//
//            openNewPage(BodyIndexFragment.class,"null","sleep");
//
//        }else if(TextUtils.equals(activityName, "Weight")){
//            openNewPage(BodyIndexFragment.class,"null","weight");
//        }else
        if(TextUtils.equals(activityName, "Height")){
            openNewPage(BodyIndexFragment.class,"null","height");
//            startActivity(new Intent(this,BActivity.class));
        }else if(TextUtils.equals(activityName, "Blood Pressure")){
            openNewPage(BodyIndexFragment.class,"null","blood");
        }else if(TextUtils.equals(activityName, "Heart Rate")){
            openNewPage(BodyIndexFragment.class,"null","heart");
        }else if(TextUtils.equals(activityName, "Waist Circumference")){
            openNewPage(BodyIndexFragment.class,"null","waist");
        }else if(TextUtils.equals(activityName, "Exercise Duration")){
            openNewPage(BodyIndexFragment.class,"null","exercise");
        }else if(TextUtils.equals(activityName, "Temperature")){
            openNewPage(BodyIndexFragment.class,"null","temperature");
        }else if(TextUtils.equals(activityName, "Medicine")){
            openNewPage(BodyIndexFragment.class,"null","medicine");
        }else if(TextUtils.equals(activityName, "Disease Status")){
            openNewPage(DiseaseFragment.class,"null","disease");
        }else{
            openNewPage(BodyIndexFragment.class,"null",activityName);
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

            String temp_res1 = MMKVUtils.getString("index_table_string", "");
            String[] index_info = temp_res1.split("<");
            List<String> index_raw = new ArrayList<>();
            index_raw.add("Weight");
            index_raw.add("Height");
            index_raw.add("Diastolic blood pressure");
            index_raw.add("Systolic blood pressure");
            index_raw.add("Heart rate");
            index_raw.add("Waist circumference");
            index_raw.add("Gender");
            index_raw.add("Age");
            index_raw.add("Disease");
            index_raw.add("Exercise duration");
            index_raw.add("Sleep time");
            String new_index_name = "";
            for(int i = 0; i < index_info.length;i++ ){
                String temp_index_name = index_info[i].split(";")[2];
                if(!index_raw.contains(temp_index_name)){
                    if(new_index_name.equals("")){
                        new_index_name = new_index_name + temp_index_name;
                    }else{
                        new_index_name = new_index_name + ";" + temp_index_name;
                    }
                }
            }
            myView.loadUrl("file:///android_asset/index.html#/"+"?name="+new_index_name);

            //myView.loadUrl("file:///android_asset/index.html");
            //myView.loadUrl("http://10.10.114.202:8080/#/");
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

                myView.loadUrl("file:///android_asset/index.html#/"+"?name="+new_index_name);



                //myView.loadUrl("file:///android_asset/index.html");
//                myView.loadUrl("http://10.10.114.202:8080/#/");
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