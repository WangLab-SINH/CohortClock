package com.xuexiang.templateproject.fragment.profile;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.tabbar.TabControlView;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "Gender",anim = CoreAnim.none)
public class SexInfoFragment extends BaseFragment{
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/user_info.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;
    @BindView(R.id.tcv_select)
    TabControlView mTabControlView;

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
        return R.layout.fragment_info_sex;
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
            mTabControlView.setItems(ResUtils.getStringArray(R.array.course_param_option_sex),ResUtils.getStringArray(R.array.course_param_option_sex));
            String mmkv_sex = MMKVUtils.getString("sex", "male");
            if(mmkv_sex.equals("male")){
                mTabControlView.setDefaultSelection(0);
            }else if (mmkv_sex.equals("female")){
                mTabControlView.setDefaultSelection(1);
            }else{
                mTabControlView.setDefaultSelection(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        mTabControlView.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {

                sex = value;

                //XToastUtils.toast("选中了：" + title + ", 选中的值为：" + value);
            }
        });

    }

    @OnClick({R.id.button_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_yes:


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = "none";
                        //creating new thread to handle Http Operations
                        Looper.prepare();
                        result = getRegistData( sex, weight, height,  diabete,  workdate,  disease);

                        if(result.equals("no"))
                        {
                            Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                        else if(result.equals("yes"))
                        {
                            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();

                        }
                        else if(result.equals("none"))
                        {
                            Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
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
