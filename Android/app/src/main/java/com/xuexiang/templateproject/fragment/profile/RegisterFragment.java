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

package com.xuexiang.templateproject.fragment.profile;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "注册",anim = CoreAnim.none)
public class RegisterFragment extends BaseFragment implements HttpResponeCallBack{
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/register.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;
    @BindView(R.id.et_name)
    MaterialEditText etname;

    @BindView(R.id.et_passwd)
    MaterialEditText etpasswd;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initListeners() {

    }

//    private void initValidationEt() {
//        mEtCheck.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
//        mEtAutoCheck.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
//        mEtAutoCheck.addValidator(new RegexpValidator(getString(R.string.tip_number_only_error_message), getString(R.string.regexp_number_only)));
//    }

    @OnClick({R.id.Button_register_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Button_register_1:
                final String nick = etname.getText().toString();
                final String passwordStr = etpasswd.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = "none";
                        //creating new thread to handle Http Operations
                        Looper.prepare();
                        result = getRegistData_new(nick, passwordStr);

                        if(result.equals("no"))
                        {
                            Toast.makeText(getActivity(), "用户名已注册", Toast.LENGTH_SHORT).show();
                        }
                        else if(result.equals("yes"))
                        {
                            Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                            popToBack();
                        }
                        else if(result.equals("none"))
                        {
                            Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                }).start();
                break;

            default:
                break;
        }
    }
    @Override
    public void onResponeStart(String apiName) {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "正在请求数据...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(String apiName, long count, long current) {
        Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String apiName, Object object) {
        // TODO Auto-generated method stub
        //注册接口
        if (UrlConstance.KEY_REGIST_INFO.equals(apiName)) {
            if (object != null && object instanceof AnalyticalRegistInfo) {
                AnalyticalRegistInfo info = (AnalyticalRegistInfo) object;
                String successCode = info.getRet();
                //请求成功
                if (successCode.equals(Constant.KEY_SUCCESS)) {



//                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                    RegisterActivity.this.startActivity(intent);
//                    Toast.makeText(RegisterActivity.this, "注册成功...", Toast.LENGTH_SHORT).show();
//                    RegisterActivity.this.finish();

                } else {
                    Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onFailure(String apiName, Throwable t, int errorNo, String strMsg) {
        Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
    }


    public String getRegistData(String nickname,String password)
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

        try {

            URL url = new URL(SERVER_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);//Allow Inputs
            connection.setDoOutput(true);//Allow Outputs
            connection.setUseCaches(false);//Don't use a cached Copy
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("ENCTYPE", "multipart/form-data");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            //connection.setRequestProperty("uploaded_file", nickname);

            //creating new dataoutputstream
            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            //writing bytes to data outputstream
            dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
            String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"user_name\";filename=\""
                    + nickname + "_" + password + "_" + androidid + "\"" + lineEnd);
            dataOutputStream.writeBytes(lineEnd);




//
//			dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
//            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"passward\";filename=\""
//                    + password + "\"" + lineEnd);
//            dataOutputStream.writeBytes(lineEnd);
            dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();
            //Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

            //response code of 200 indicates the server status OK
            dataOutputStream.flush();
            dataOutputStream.close();
            InputStreamReader reader =null;
            BufferedReader reader2 =null;

            if (serverResponseCode == 200) {
                reader = new InputStreamReader(connection.getInputStream(),"utf-8");
                reader2 = new BufferedReader(reader);
                string = reader2.readLine();
                if(string.equals("no"))
                {
                    System.out.println("服务端响应的结果"+string);
                }
                if(string.equals("yes"))
                {
                    System.out.println("服务端响应的结果"+string);
                }
                System.out.println("服务端响应的结果"+string);

                //Toast.makeText(RegisterActivity.this, "成功111", Toast.LENGTH_SHORT).show();
            }

            //closing the input and output streams



        }  catch (MalformedURLException e) {
            e.printStackTrace();
            //Toast.makeText(RegisterActivity.this, "URL error!", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (string);
    }



    public String getRegistData_new(String nickname,String password)
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
        String url1 ="http://39.100.73.118/deeplearning_photo/register_new.php?nickname="+nickname+"&password="+password+"&androidid="+androidid+"";
        //http://39.100.73.118/deeplearning_photo/user_info1.php?sex=null&weight1=null&height=100&diabete=null&workdate=null&disease=null&androidid=11
        Request request = new Request.Builder()
                .url(url1)
                .build();
        String res = "";
        try{
            Response response = okHttpClient.newCall(request).execute();
            res = response.body().string();
            if(res.equals("no"))
            {
                System.out.println("服务端响应的结果"+res);
            }
            if(res.equals("yes"))
            {
                System.out.println("服务端响应的结果"+res);
            }
            System.out.println("服务端响应的结果"+res);

            //handler.sendEmptyMessage(1);
        }catch (Exception e)
        {
            e.printStackTrace();
            //handler.sendEmptyMessage(2);
        }
        return (res);
    }

}
