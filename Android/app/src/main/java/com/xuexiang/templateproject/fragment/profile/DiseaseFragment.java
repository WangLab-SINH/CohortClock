package com.xuexiang.templateproject.fragment.profile;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
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

@Page(name = "Disease information",anim = CoreAnim.none)
public class DiseaseFragment extends BaseFragment{
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/user_info.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;

    @BindView(R.id.tcv_select1)
    TabControlView mTabControlView11;
    @BindView(R.id.tcv_select11)
    TabControlView mTabControlView1;
    @BindView(R.id.tcv_select12)
    TabControlView mTabControlView12;
    @BindView(R.id.tcv_select3)
    TabControlView mTabControlView3;
    @BindView(R.id.tcv_select4)
    TabControlView mTabControlView4;
    @BindView(R.id.tcv_select5)
    TabControlView mTabControlView5;
    @BindView(R.id.tcv_select6)
    TabControlView mTabControlView6;
    @BindView(R.id.tcv_select7)
    TabControlView mTabControlView7;
    @BindView(R.id.tcv_select8)
    TabControlView mTabControlView8;
    @BindView(R.id.tcv_select9)
    TabControlView mTabControlView9;
    @BindView(R.id.tcv_select10)
    TabControlView mTabControlView10;
    @BindView(R.id.tcv_select13)
    TabControlView mTabControlView13;
    @BindView(R.id.tcv_select14)
    TabControlView mTabControlView14;
    @BindView(R.id.tcv_select15)
    TabControlView mTabControlView15;
    @BindView(R.id.tcv_select16)
    TabControlView mTabControlView16;
    @BindView(R.id.tcv_select17)
    TabControlView mTabControlView17;
    @BindView(R.id.tcv_select18)
    TabControlView mTabControlView18;
    @BindView(R.id.tcv_select19)
    TabControlView mTabControlView19;
    @BindView(R.id.tcv_select20)
    TabControlView mTabControlView20;
    @BindView(R.id.tcv_select21)
    TabControlView mTabControlView21;
    @BindView(R.id.tcv_select22)
    TabControlView mTabControlView22;
    @BindView(R.id.tcv_select23)
    TabControlView mTabControlView23;
    @BindView(R.id.tcv_select24)
    TabControlView mTabControlView24;
    @BindView(R.id.tcv_select25)
    TabControlView mTabControlView25;
    @BindView(R.id.tcv_select26)
    TabControlView mTabControlView26;
    @BindView(R.id.tcv_select27)
    TabControlView mTabControlView27;
    @BindView(R.id.tcv_select28)
    TabControlView mTabControlView28;
    @BindView(R.id.tcv_select29)
    TabControlView mTabControlView29;
    @BindView(R.id.tcv_select30)
    TabControlView mTabControlView30;
    @BindView(R.id.tcv_select31)
    TabControlView mTabControlView31;
    @BindView(R.id.tcv_select32)
    TabControlView mTabControlView32;
    @BindView(R.id.tcv_select33)
    TabControlView mTabControlView33;
    @BindView(R.id.tcv_select34)
    TabControlView mTabControlView34;



//    @BindView(R.id.et_name)
//    EditText etDisease;
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
        return R.layout.fragment_disease;
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




            mTabControlView1.setItems(ResUtils.getStringArray(R.array.course_param_option1),ResUtils.getStringArray(R.array.course_param_value1));
            mTabControlView1.setDefaultSelection(1);
            mTabControlView11.setItems(ResUtils.getStringArray(R.array.course_param_option3),ResUtils.getStringArray(R.array.course_param_value3));
            mTabControlView11.setDefaultSelection(1);
            mTabControlView12.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView12.setDefaultSelection(1);
            mTabControlView3.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView3.setDefaultSelection(1);
            mTabControlView4.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView4.setDefaultSelection(1);

            mTabControlView5.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView5.setDefaultSelection(1);
            mTabControlView6.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView6.setDefaultSelection(1);
            mTabControlView7.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView7.setDefaultSelection(1);
            mTabControlView8.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView8.setDefaultSelection(1);
            mTabControlView9.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView9.setDefaultSelection(1);
            mTabControlView10.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView10.setDefaultSelection(1);
            mTabControlView11.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView11.setDefaultSelection(1);
            mTabControlView12.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView12.setDefaultSelection(1);
            mTabControlView13.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView13.setDefaultSelection(1);
            mTabControlView14.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView14.setDefaultSelection(1);
            mTabControlView15.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView15.setDefaultSelection(1);
            mTabControlView16.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView16.setDefaultSelection(1);
            mTabControlView17.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView17.setDefaultSelection(1);
            mTabControlView18.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView18.setDefaultSelection(1);
            mTabControlView19.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView19.setDefaultSelection(1);
            mTabControlView20.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView20.setDefaultSelection(1);
            mTabControlView21.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView21.setDefaultSelection(1);
            mTabControlView22.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView22.setDefaultSelection(1);
            mTabControlView23.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView23.setDefaultSelection(1);
            mTabControlView24.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView24.setDefaultSelection(1);
            mTabControlView25.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView25.setDefaultSelection(1);
            mTabControlView26.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView26.setDefaultSelection(1);
            mTabControlView27.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView27.setDefaultSelection(1);
            mTabControlView28.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView28.setDefaultSelection(1);
            mTabControlView29.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView29.setDefaultSelection(1);
            mTabControlView30.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView30.setDefaultSelection(1);
            mTabControlView31.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView31.setDefaultSelection(1);
            mTabControlView32.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView32.setDefaultSelection(1);
            mTabControlView33.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView33.setDefaultSelection(1);
            mTabControlView34.setItems(ResUtils.getStringArray(R.array.course_param_option4),ResUtils.getStringArray(R.array.course_param_value4));
            mTabControlView34.setDefaultSelection(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        mTabControlView1.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                diabete = value;
                //XToastUtils.toast("选中了：" + title + ", 选中的值为：" + value);
            }
        });

    }

    @OnClick({R.id.button_yes, R.id.button_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_yes:

//                if (!StringUtils.isEmpty(etDisease.getText().toString())) {
//                    disease = etDisease.getText().toString();
//                }

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
            case R.id.button_no:
                popToBack();
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
