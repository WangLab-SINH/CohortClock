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

package com.xuexiang.templateproject.fragment.news;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Page(name = "Feedback")
public class FeedbackFragment extends BaseFragment {
    @BindView(R.id.fee_back_edit)
    EditText mVersionTextView;

    @BindView(R.id.et_name)
    MaterialEditText etname;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected void initViews() {
    }

//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }

    @OnClick({R.id.feed_back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.feed_back_btn:
                final String feedback_text = mVersionTextView.getText().toString();
                final String account = etname.getText().toString();
                if (!TextUtils.isEmpty(feedback_text)) {
                    if(!TextUtils.isEmpty(account)){
                        boolean isEmailFlag = isEmail(account);
                        if(!isEmailFlag){
                            Toast.makeText(getActivity(), "您输入的邮箱格式不正确", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    getRegistData_new(feedback_text, account);



//                            final MainActivity mainActivity = (MainActivity) getActivity();
//                            mainActivity.onPageSelected(3);

                                }
                            }).start();
                            popToBack();
                        }
                    }
                    else{
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                getRegistData_new(feedback_text, account);



//                            final MainActivity mainActivity = (MainActivity) getActivity();
//                            mainActivity.onPageSelected(3);

                            }
                        }).start();
                        popToBack();
                    }

                } else {
                    Toast.makeText(getActivity(), "请输入反馈内容", Toast.LENGTH_SHORT).show();
                }
                break;



            default:
                break;
        }
    }


    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    public String getRegistData_new(String feedback_text, String email)
    {
        if(email == null){
            email = "NULL";
        }
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


        String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
        OkHttpClient okHttpClient = new OkHttpClient();
        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String url1 ="http://39.100.73.118/deeplearning_photo/get_feedback.php?android="+androidid+"&user_name="+user_name+"&feedback="+feedback_text+"&email="+email+"";
        //http://39.100.73.118/deeplearning_photo/user_info1.php?sex=null&weight1=null&height=100&diabete=null&workdate=null&disease=null&androidid=11
        Request request = new Request.Builder()
                .url(url1)
                .build();
        String res = "";
        try{
            Response response = okHttpClient.newCall(request).execute();
            res = response.body().string();
            Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();

            //handler.sendEmptyMessage(1);
        }catch (Exception e)
        {
            e.printStackTrace();
            //handler.sendEmptyMessage(2);
        }
        return (res);
    }
}
