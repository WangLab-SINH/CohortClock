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


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.MainActivity;
import com.xuexiang.templateproject.core.webview.BaseWebViewFragment;
import com.xuexiang.templateproject.utils.Utils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @since 2019/5/28 8:48
 */
//@Page(name = "ECharts\nAndroid原生调用")
@Page(anim = CoreAnim.none)
public class EchartInnerFragment extends BaseWebViewFragment {
    protected boolean isVisible;
    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
    private static final int MSG_FAILURE = 1;//获取图片失败的标识
    int this_flag = 1;
    String title = "";

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
//
    private ChartInterface mChartInterface;

    @Override
    protected TitleBar initTitle() {


        //Log.e("-----------","---initTitle");
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_echarts_android;
    }

    @Override
    protected void initViews() {

        int flag = 1;

        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
        String pic_name = "heatmap.html";
        //String pic_name2 = "heatmap.png";
        String temp_url = url_head + pic_name;
        if(this_flag == 1) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        //还是一样先从一个URL加载一个Document对象。
                        Document doc = Jsoup.connect(temp_url).get();
                        Elements links = doc.select("head");
                        Elements titlelinks = links.get(0).select("title");
                        title = titlelinks.get(0).text();
                        mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
                    } catch (Exception e) {
                        title = "";
                    }
                }
            }).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(this_flag == 2) {
            mAgentWeb = Utils.createAgentWeb(this, flContainer, "file:///android_asset/chart/src/template.html");

            //注入接口,供JS调用
            mAgentWeb.clearWebCache();
            //mAgentWeb.getJsInterfaceHolder().addJavaObject("Android", mChartInterface = new ChartInterface());

            mAgentWeb.getUrlLoader().loadUrl(temp_url);
        }


//        try {
//            //还是一样先从一个URL加载一个Document对象。
//            Document doc = Jsoup.connect(temp_url).get();
//            Elements links = doc.select("head");
//            Elements titlelinks=links.get(0).select("title");
//            title = titlelinks.get(0).text();
//        }catch(Exception e) {
//            title = "";
//        }
//        if(title == "")
//        {
//
//        }

//        URL url = null;
//        try {
//            url = new URL(temp_url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection conn = null;
//
//        try {
//            conn = (HttpURLConnection)url.openConnection();
//
//        } catch (IOException e) {
//            flag = 2;
//            e.printStackTrace();
//        }
//        System.out.println(conn.getResponseCode());
        //if (Patterns.WEB_URL.matcher(temp_url.toString()).matches()) {
//        if(flag == 1){
//            mAgentWeb = Utils.createAgentWeb(this, flContainer, "file:///android_asset/chart/src/template.html");
//
//            //注入接口,供JS调用
//            mAgentWeb.clearWebCache();
//            //mAgentWeb.getJsInterfaceHolder().addJavaObject("Android", mChartInterface = new ChartInterface());
//
//            mAgentWeb.getUrlLoader().loadUrl(temp_url);
//        }
//        else{
//            Log.e("not url","url");
////            mAgentWeb = Utils.createAgentWeb(this, flContainer, "file:///android_asset/chart/src/template.html");
////
////            //注入接口,供JS调用
////            mAgentWeb.clearWebCache();
////            //mAgentWeb.getJsInterfaceHolder().addJavaObject("Android", mChartInterface = new ChartInterface());
////
////            mAgentWeb.getUrlLoader().loadUrl(temp_url);
//        }
        //目前Echarts-Java只支持3.x



    }
    public static String getWebTitle(String url){
        try {
            //还是一样先从一个URL加载一个Document对象。
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("head");
            Elements titlelinks=links.get(0).select("title");
            return titlelinks.get(0).text();
        }catch(Exception e) {
            return "";
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage (Message msg) {//此方法在ui线程运行
            switch(msg.what) {
                case MSG_SUCCESS:
                    this_flag = 2;
                    initViews();



                    break;

                case MSG_FAILURE:

                    break;
            }
        }
    };

//    @SingleClick
//    @OnClick({R.id.btn_bar_chart, R.id.btn_line_chart, R.id.btn_pie_chart, R.id.btn_js})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_bar_chart:
//                initBarChart();
//                break;
//            case R.id.btn_line_chart:
//                initLineChart();
//                break;
//            case R.id.btn_pie_chart:
//                initPieChart();
//            case R.id.btn_js:
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//
//                        OkHttpClient okHttpClient = new OkHttpClient();
//                        String url = "http://39.100.73.118/deeplearning_photo/json_test.php";
//                        Request request = new Request.Builder()
//                                .url(url)
//                                .build();
//                        Call call = okHttpClient.newCall(request);
//                        try {
//                            Response response = call.execute();
//                            String res = response.body().string();
//                            if (res != null && !res.trim().equals("")){
//                                JSONObject jsonObject = new JSONObject(res);
//                                if (jsonObject.getInt("success") == 1){
//                                    JSONArray jsonArray = jsonObject.getJSONArray("items");
//                                    for (int i = jsonArray.length() - 1;i >= 0;i--){
//                                        JSONObject item = jsonArray.getJSONObject(i);
//                                        String what = null;
//                                        String week = null;
//                                        String weekend = null;
//                                        try {
//                                            week = item.getString("week");
//                                            weekend = item.getString("weekend");
//                                            week = week.replace("[", "");
//                                            week = week.replace("]","");
//                                            String[] new_data = week.split(" ");
//                                            String[] week_data = new String[96];
//                                            int count = 0;
//                                            for(i = 0; i < new_data.length; i++) {
//                                                if(new_data[i] == ""){
//                                                    week_data[count] = new_data[i];
//                                                    count += 1;
//                                                }
//
//                                            }
//
//                                            what = item.getString("what");
//
//                                        }catch (Exception e){
//
//                                        }
//                                        String when = null;
//                                        try{
//                                            when = item.getString("when");
//                                        }catch (Exception e){
//
//                                        }
//                                        String where = null;
//                                        try{
//                                            where = item.getString("where");
//                                        }catch (Exception e){
//
//                                        }
//                                        String detail = null;
//                                        try {
//                                            detail = item.getString("detail");
//                                        }catch (Exception e){
//
//                                        }
//                                        String contact = null;
//                                        try {
//                                            contact = item.getString("contact");
//                                        }catch (Exception e){
//
//                                        }
//
//                                    }
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//
//
//                        }
//
//                    }
//                }).start();
//
//                break;
//        }
//    }
//
//    private void initBarChart() {
//        mAgentWeb.getJsAccessEntrace().quickCallJs("loadChartView", "chart", mChartInterface.makeBarChartOptions());
//    }
//
//    private void initLineChart() {
//        mAgentWeb.getJsAccessEntrace().quickCallJs("loadChartView", "chart", mChartInterface.makeLineChartOptions1());
//
//    }
//
//    private void initPieChart() {
//        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
//        String url_head="http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
//        String pic_name="heatmap.html";
//        String pic_name2="heatmap.png";
//        String temp_url = url_head + pic_name;
//        //mAgentWeb.getJsAccessEntrace().quickCallJs("loadChartView", "chart", mChartInterface.makePieChartOptions());
//
//
//    }
//
//
//
//
//
//


    @OnClick({R.id.imgViewall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgViewall:
                Bundle bundle=getArguments();
                bundle.remove("open_camera");
                bundle.putString("open_camera","2");
                final MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onPageSelected(1);
            default:
                break;
        }
    }

    /**
     * 注入到JS里的对象接口
     */
    public class ChartInterface {

        @JavascriptInterface
        public String makeBarChartOptions() {
            GsonOption option = new GsonOption();
            option.setTitle(new Title().text("柱状图"));
            option.setLegend(new Legend().data("销量"));
            option.xAxis(new CategoryAxis().data("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"));
            option.yAxis();

            Bar bar = new Bar("销量");
            bar.data(5, 20, 36, 10, 10, 20);
            option.series(bar);

            return option.toString();
        }


        @JavascriptInterface
        public String makeLineChartOptions() {
            GsonOption option = new GsonOption();
            option.legend("高度(km)与气温(°C)变化关系");
            option.toolbox().show(false);
            option.calculable(true);
            option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");

            ValueAxis valueAxis = new ValueAxis();
            valueAxis.axisLabel().formatter("{value} °C");
            option.xAxis(valueAxis);

            CategoryAxis categoryAxis = new CategoryAxis();
            categoryAxis.axisLine().onZero(false);
            categoryAxis.axisLabel().formatter("{value} km");
            categoryAxis.boundaryGap(false);
            categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
            option.yAxis(categoryAxis);

            Line line = new Line();
            line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
            option.series(line);
            return option.toString();
        }

        @JavascriptInterface
        public String makePieChartOptions() {
            GsonOption option = new GsonOption();
            option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
            option.legend().data("直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎");

            Pie pie = new Pie("访问来源").data(
                    new PieData("直接访问", 335),
                    new PieData("邮件营销", 310),
                    new PieData("联盟广告", 274),
                    new PieData("视频广告", 235),
                    new PieData("搜索引擎", 400)
            ).center("50%", "45%").radius("50%");
            pie.label().normal().show(true).formatter("{b}{c}({d}%)");
            option.series(pie);
            return option.toString();
        }

        @JavascriptInterface
        public String makeLineChartOptions1() {
            GsonOption option = new GsonOption();
            option.title("折线图");
            option.legend("销量");
            option.tooltip().trigger(Trigger.axis);

            ValueAxis valueAxis = new ValueAxis();
            option.yAxis(valueAxis);

            CategoryAxis categorxAxis = new CategoryAxis();
            categorxAxis.axisLine().onZero(false);
            categorxAxis.boundaryGap(true);
            categorxAxis.data("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
            option.xAxis(categorxAxis);

            Line line = new Line();
            line.areaStyle();
            int[] a = {820, 932, 901, 934, 1290, 1330, 1320};
            List<Integer> b = new ArrayList<>();
            b.add(820);
            b.add(932);
            b.add(901);
            b.add(934);
            b.add(1290);
            b.add(1330);
            b.add(1320);
            String[] c = {"820", "932", "901", "934", "1290", "1330", "1320"};
            line.smooth(true).name("销量").data(b).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
            option.series(line);
            return option.toString();
        }


    }

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
        Bundle bundle=getArguments();
        String s=bundle.getString("one");
        if(s.equals("2")) {
            if (getActivity() != null) {
                String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
                String pic_name = "heatmap.html";
                String temp_url = url_head + pic_name;
                //目前Echarts-Java只支持3.x
                mAgentWeb = Utils.createAgentWeb(this, flContainer, "file:///android_asset/chart/src/template.html");

                //注入接口,供JS调用
                mAgentWeb.clearWebCache();
                //mAgentWeb.getJsInterfaceHolder().addJavaObject("Android", mChartInterface = new ChartInterface());

                mAgentWeb.getUrlLoader().loadUrl(temp_url);
            }
            bundle.putString("one","1");
        }


    }

    protected void onInvisible() {


    }


}











