package com.xuexiang.templateproject.fragment.profile;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.picker.RulerView;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xutil.common.StringUtils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "Base information",anim = CoreAnim.none)
public class InfoFragment extends BaseFragment{
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/user_info.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;
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
    String sex = "null";
    String weight = "null";
    String height = "null";
    String diabete = "null";
    String workdate = "null";
    String disease = "null";
    String press_min = "null";
    String press_max = "null";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
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
    try {
        mTabControlView.setItems(ResUtils.getStringArray(R.array.course_param_option),ResUtils.getStringArray(R.array.course_param_value));
        String mmkv_sex = MMKVUtils.getString("sex", "male");
        if(mmkv_sex.equals("male")){
            mTabControlView.setDefaultSelection(0);
        }else{
            mTabControlView.setDefaultSelection(1);
        }
        float mmkv_weight = Float.valueOf(MMKVUtils.getString("latest_weight", "66.2"));
        float mmkv_height = Float.valueOf(MMKVUtils.getString("height", "167.1"));
        rulerView.setFirstScale(mmkv_weight);
        rulerView1.setFirstScale(mmkv_height);


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

    @OnClick({R.id.button_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_yes:
                weight = String.valueOf(rulerView.getCurrentValue());
                height = String.valueOf(rulerView1.getCurrentValue());
                MMKVUtils.put("latest_weight", weight);
                MMKVUtils.put("height", height);
                if (!StringUtils.isEmpty(etDisease.getText().toString())) {
                    disease = etDisease.getText().toString();
                }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = "none";
                            //creating new thread to handle Http Operations
                            Looper.prepare();
                            result = getRegistData( sex, weight, height,  diabete,  workdate,  disease);

                            if(result.equals("no"))
                            {
                                Toast.makeText(getActivity(), "User name or password error", Toast.LENGTH_SHORT).show();
                            }
                            else if(result.equals("yes"))
                            {
                                Toast.makeText(getActivity(), "Login successfully", Toast.LENGTH_SHORT).show();

                            }
                            else if(result.equals("none"))
                            {
                                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getActivity(), "Submit successfully", Toast.LENGTH_SHORT).show();
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
                    //RequestApiData.getInstance().getLoginData(account, password, UserBaseInfo.class, LoginActivity.this);

//                if (!StringUtils.isEmpty(etWeight.getText().toString())) {
//                    rulerView.setCurrentValue(StringUtils.toFloat(etWeight.getText().toString(), 0));
//                } else {
//                    XToastUtils.toast("请输入体重值！");
//                }

                //openNewPage(ProfileFragment.class);
                break;

            default:
                break;
        }
    }
    public String getRegistData(String sex,String weight,String height, String diabete, String workdate, String disease)
    {
        String string = "none";
        int serverResponseCode = 0;
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        //start

//        try {
//
////            URL url = new URL(SERVER_URL);
////            connection = (HttpURLConnection) url.openConnection();
////            connection.setDoInput(true);//Allow Inputs
////            connection.setDoOutput(true);//Allow Outputs
////            connection.setUseCaches(false);//Don't use a cached Copy
////            connection.setRequestMethod("POST");
////            connection.setRequestProperty("Connection", "Keep-Alive");
////            connection.setRequestProperty("ENCTYPE", "multipart/form-data");
////            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
////            //connection.setRequestProperty("uploaded_file", nickname);
////
////            //creating new dataoutputstream
////            dataOutputStream = new DataOutputStream(connection.getOutputStream());
////
////            //writing bytes to data outputstream
////            dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
////            String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
////            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"user_name\";filename=\""
////                    + sex + "_" + weight + "_" + height + "_" + diabete + "_" + workdate + "_" + disease + "_" + androidid + "\"" + lineEnd);
////            dataOutputStream.writeBytes(lineEnd);
//
//
//
////
////			dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
////            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"passward\";filename=\""
////                    + password + "\"" + lineEnd);
////            dataOutputStream.writeBytes(lineEnd);
////            dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
////            serverResponseCode = connection.getResponseCode();
////            String serverResponseMessage = connection.getResponseMessage();
////            //Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);
////
////            //response code of 200 indicates the server status OK
////            dataOutputStream.flush();
////            dataOutputStream.close();
////            InputStreamReader reader =null;
////            BufferedReader reader2 =null;
////
////            if (serverResponseCode == 200) {
////                reader = new InputStreamReader(connection.getInputStream(),"utf-8");
////                reader2 = new BufferedReader(reader);
////                string = reader2.readLine();
////                if(string.equals("no"))
////                {
////                    System.out.println("服务端响应的结果"+string);
////                }
////                if(string.equals("yes"))
////                {
////                    System.out.println("服务端响应的结果"+string);
////                }
////                System.out.println("服务端响应的结果"+string);
////
////                //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();
////            }
//
//
//        }  catch (MalformedURLException e) {
//            e.printStackTrace();
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/user_info1.php?sex="+sex+"&weight="+weight+"&height="+height+"&diabete="+diabete+"&workdate="+workdate+"&disease="+disease+"&androidid="+androidid+"";
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
