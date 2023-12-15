package com.xuexiang.templateproject.fragment.trending;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.webview.BaseWebViewFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuexiang
 * @since 2019/5/28 8:48
 */
//@Page(name = "ECharts\nAndroid原生调用")
@Page(name = "Sleep", anim = CoreAnim.none)
public class EchartSleepFragment extends BaseWebViewFragment {
    protected boolean isVisible;
    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
    private static final int MSG_FAILURE = 1;//获取图片失败的标识
    int this_flag = 1;
    String title = "";

    //    @BindView(R.id.fl_container)
//    FrameLayout flContainer;
    //
//    private ChartInterface mChartInterface;
    private WebView myView;

//    @Override
//    protected TitleBar initTitle() {
//
//
//        //Log.e("-----------","---initTitle");
//        return null;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_local_sleep_weight;
    }

    @Override
    protected void initViews() {

        Log.i("cbs","isGranted == "+"here");
        myView = (WebView) findViewById(R.id.myView);
        myView.getSettings().setAllowFileAccess(true);
        myView.getSettings().setJavaScriptEnabled(true);
        //android 是我们在Js中调用当前方法的名字
        myView.addJavascriptInterface(this, "android");
        //这是让WebView 自己处理网页加载请求
        myView.setWebChromeClient(new WebChromeClient());
        //加载网页
        myView.loadUrl("file:///android_asset/sleep_new.html");
//        String result = getT5Data(1);
//
//        int flag = 1;
//
//        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
//        String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
//        String pic_name = "sleep_weight.html";
//        //String pic_name2 = "heatmap.png";
//        String temp_url = url_head + pic_name;
//
//        if(this_flag == 1) {
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    try {
//                        //还是一样先从一个URL加载一个Document对象。
//                        Document doc = Jsoup.connect(temp_url).get();
//                        Elements links = doc.select("head");
//                        Elements titlelinks = links.get(0).select("title");
//                        title = titlelinks.get(0).text();
//                        mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
//                    } catch (Exception e) {
//                        title = "";
//                    }
//                }
//            }).start();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(this_flag == 2) {
//            mAgentWeb = Utils.createAgentWeb(this, flContainer, "file:///android_asset/chart/src/template.html");
//
//            //注入接口,供JS调用
//            mAgentWeb.clearWebCache();
//            //mAgentWeb.getJsInterfaceHolder().addJavaObject("Android", mChartInterface = new ChartInterface());
//
//            mAgentWeb.getUrlLoader().loadUrl(temp_url);
//        }


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




    @JavascriptInterface
    public String getWeight(int isx)
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
        cursor.close();
        db.close();
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
                start_input_data.add(start_dict.get(sleep_time_list.get(i)));
            }
            else{
                start_input_data.add(null);
            }
            if(end_dict.containsKey(sleep_time_list.get(i))){
                end_input_data.add(end_dict.get(sleep_time_list.get(i)));
            }
            else{
                end_input_data.add(null);
            }

        }

        String x_axis = "[";
        String y_axis = "[";

        for(int i =0; i < weight_time_list.size(); i++){
            x_axis += "'" + weight_time_list.get(i) + "',";
        }
        x_axis += "]";

        for(int i =0; i < weight_input_data.size(); i++){
            y_axis += weight_input_data.get(i) + ",";
        }
        y_axis += "]";
        if(isx == 1){
            return x_axis;
        }
        else{
            return y_axis;
        }




    }

    @JavascriptInterface
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

//        List<Double> weight_input_data = new ArrayList<Double>();
//        for(int i =0; i < weight_time_list.size(); i ++){
//            weight_input_data.add(weight_dict.get(weight_time_list.get(i)));
//        }

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

        String x_axis = "[";
        String y_axis1 = "[";
        String y_axis2 = "[";

        for(int i =0; i < day_30.size(); i++){
            x_axis += "'" + day_30.get(i) + "',";
        }
        x_axis += "]";

        for(int i =0; i < day_30.size(); i++){
            y_axis1 += start_input_data.get(i) + ",";
        }
        y_axis1 += "]";

        for(int i =0; i < day_30.size(); i++){
            y_axis2 += end_input_data.get(i) + ",";
        }
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


    @JavascriptInterface
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

        String x_axis = "['0:00', '0:30','1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30'," +
                "'12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30']";
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









//        for(int i =0; i < sleep_time_list.size(); i++){
//            x_axis += "'" + sleep_time_list.get(i) + "',";
//        }
//        x_axis += "]";
//
//        for(int i =0; i < sleep_time_list.size(); i++){
//            y_axis1 += start_input_data.get(i) + ",";
//        }
//        y_axis1 += "]";
//
//        for(int i =0; i < sleep_time_list.size(); i++){
//            y_axis2 += end_input_data.get(i) + ",";
//        }
//        y_axis2 += "]";
//        if(isx == 1){
//            return x_axis;
//        }
//        else if(isx == 2){
//            return y_axis1;
//        }
//        else{
//            return y_axis2;
//        }




    }








    @JavascriptInterface
    public String getWeight1(int isx)
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
        cursor.close();
        db.close();
        Collections.sort(weight_time_list);
        Collections.sort(sleep_time_list);

        List<Integer> weight_input_data = new ArrayList<Integer>();
        for(int i =0; i < weight_time_list.size(); i ++){
            weight_input_data.add(weight_dict.get(weight_time_list.get(i)).intValue());
        }

        int current_weight_min = Collections.min(weight_input_data) - 5;
        int current_weight_max = Collections.max(weight_input_data) + 5;

        String x_axis = "[";
        String y_axis = "[";
        for(int i = current_weight_min; i < current_weight_max; i++){
            x_axis += "'" + i + "',";
            int temp_y = 0;
            for(int j = 0; j < weight_input_data.size(); j++){
                if(weight_input_data.get(j) == i){
                    temp_y += 1;
                }
            }
            y_axis += temp_y + "," ;
        }

        x_axis += "]";
        y_axis += "]";

        if(isx == 1){
            return x_axis;
        }
        else{
            return y_axis;
        }




    }










//    public static String getWebTitle(String url){
//        try {
//            //还是一样先从一个URL加载一个Document对象。
//            Document doc = Jsoup.connect(url).get();
//            Elements links = doc.select("head");
//            Elements titlelinks=links.get(0).select("title");
//            return titlelinks.get(0).text();
//        }catch(Exception e) {
//            return "";
//        }
//    }
//
//    private Handler mHandler = new Handler() {
//        public void handleMessage (Message msg) {//此方法在ui线程运行
//            switch(msg.what) {
//                case MSG_SUCCESS:
//                    this_flag = 2;
//                    initViews();
//
//
//
//                    break;
//
//                case MSG_FAILURE:
//
//                    break;
//            }
//        }
//    };

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


//    @OnClick({R.id.imgViewall})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.imgViewall:
//                openNewPage(WeightSleepFragment.class);
//            default:
//                break;
//        }
//    }

    /**
     * 注入到JS里的对象接口
     */
//    public class ChartInterface {
//
//        @JavascriptInterface
//        public String makeBarChartOptions() {
//            GsonOption option = new GsonOption();
//            option.setTitle(new Title().text("柱状图"));
//            option.setLegend(new Legend().data("销量"));
//            option.xAxis(new CategoryAxis().data("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"));
//            option.yAxis();
//
//            Bar bar = new Bar("销量");
//            bar.data(5, 20, 36, 10, 10, 20);
//            option.series(bar);
//
//            return option.toString();
//        }
//
//
//        @JavascriptInterface
//        public String makeLineChartOptions() {
//            GsonOption option = new GsonOption();
//            option.legend("高度(km)与气温(°C)变化关系");
//            option.toolbox().show(false);
//            option.calculable(true);
//            option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");
//
//            ValueAxis valueAxis = new ValueAxis();
//            valueAxis.axisLabel().formatter("{value} °C");
//            option.xAxis(valueAxis);
//
//            CategoryAxis categoryAxis = new CategoryAxis();
//            categoryAxis.axisLine().onZero(false);
//            categoryAxis.axisLabel().formatter("{value} km");
//            categoryAxis.boundaryGap(false);
//            categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
//            option.yAxis(categoryAxis);
//
//            Line line = new Line();
//            line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
//            option.series(line);
//            return option.toString();
//        }
//
//        @JavascriptInterface
//        public String makePieChartOptions() {
//            GsonOption option = new GsonOption();
//            option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
//            option.legend().data("直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎");
//
//            Pie pie = new Pie("访问来源").data(
//                    new PieData("直接访问", 335),
//                    new PieData("邮件营销", 310),
//                    new PieData("联盟广告", 274),
//                    new PieData("视频广告", 235),
//                    new PieData("搜索引擎", 400)
//            ).center("50%", "45%").radius("50%");
//            pie.label().normal().show(true).formatter("{b}{c}({d}%)");
//            option.series(pie);
//            return option.toString();
//        }
//
//        @JavascriptInterface
//        public String makeLineChartOptions1() {
//            GsonOption option = new GsonOption();
//            option.title("折线图");
//            option.legend("销量");
//            option.tooltip().trigger(Trigger.axis);
//
//            ValueAxis valueAxis = new ValueAxis();
//            option.yAxis(valueAxis);
//
//            CategoryAxis categorxAxis = new CategoryAxis();
//            categorxAxis.axisLine().onZero(false);
//            categorxAxis.boundaryGap(true);
//            categorxAxis.data("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
//            option.xAxis(categorxAxis);
//
//            Line line = new Line();
//            line.areaStyle();
//            int[] a = {820, 932, 901, 934, 1290, 1330, 1320};
//            List<Integer> b = new ArrayList<>();
//            b.add(820);
//            b.add(932);
//            b.add(901);
//            b.add(934);
//            b.add(1290);
//            b.add(1330);
//            b.add(1320);
//            String[] c = {"820", "932", "901", "934", "1290", "1330", "1320"};
//            line.smooth(true).name("销量").data(b).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
//            option.series(line);
//            return option.toString();
//        }
//
//
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



    }

    protected void onInvisible() {


    }


}











