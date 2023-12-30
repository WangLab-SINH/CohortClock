/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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

package com.xuexiang.templateproject.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.widget.CompoundButton;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseHelper;
import com.xuexiang.templateproject.fragment.profile.LoginFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.Utils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author xuexiang
 * @since 2019-10-15 22:38
 */
@Page(name = "Settings")
public class SettingsFragment extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener {

//    @BindView(R.id.menu_common)
//    SuperTextView menuCommon;
    @BindView(R.id.menu_privacy)
    SuperTextView menuPrivacy;
    @BindView(R.id.super_switch_tv)
    SuperTextView switch_tv;
//    @BindView(R.id.super_switch_tv1)
//    SuperTextView switch_tv1;
    @BindView(R.id.super_switch_tv2)
    SuperTextView switch_tv2;

//    @BindView(R.id.menu_helper)
//    SuperTextView menuHelper;
    @BindView(R.id.menu_change_account)
    SuperTextView menuChangeAccount;
    @BindView(R.id.menu_logout)
    SuperTextView menuLogout;
    @BindView(R.id.menu_syn)
    SuperTextView menuSyn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initViews() {
//        menuCommon.setOnSuperTextViewClickListener(this);
        menuPrivacy.setOnSuperTextViewClickListener(this);
        switch_tv.setOnSuperTextViewClickListener(this);
//        switch_tv1.setOnSuperTextViewClickListener(this);
        switch_tv2.setOnSuperTextViewClickListener(this);


        boolean isShowFood = MMKVUtils.getBoolean("IS_SHOW_FOOD", false);
        boolean isShowHand = MMKVUtils.getBoolean("IS_SHOW_HAND", false);
        boolean isShowClock = MMKVUtils.getBoolean("IS_SHOW_CLOCK", false);

        boolean isShowEatingTime = MMKVUtils.getBoolean("IS_SHOW_EatingTime", true);
        switch_tv.setSwitchIsChecked(isShowEatingTime);
//        switch_tv1.setSwitchIsChecked(isShowHand);
        switch_tv2.setSwitchIsChecked(isShowClock);
//        menuHelper.setOnSuperTextViewClickListener(this);
        menuChangeAccount.setOnSuperTextViewClickListener(this);
        menuLogout.setOnSuperTextViewClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(SuperTextView superTextView) {
        switch(superTextView.getId()) {
//            case R.id.menu_common:
//                openNewPage(DatabaseSleepWeightFragment.class);
//                break;
            case R.id.menu_privacy:
                Utils.showPrivacyDialog(getContext(), (dialog, which) -> {
                    dialog.dismiss();
                    MMKVUtils.put("key_agree_privacy", true);
                    //ActivityUtils.startActivity(MainActivity.class);
                    //finish();
                });
                break;
//            case R.id.super_switch_tv:
//                MMKVUtils.put("IS_SHOW_FOOD", account);
//                break;
//            case R.id.menu_helper:
//                openNewPage(HowtouseFragment.class);
                //XToastUtils.toast(superTextView.getLeftString());

//                break;
            case R.id.menu_change_account:
                openNewPage(LoginFragment.class);
                break;


            case R.id.menu_syn:
                DatabaseHelper databaseHelper1 = new DatabaseHelper(getContext(),"photo_path_new",null,1);
                SQLiteDatabase db1 = databaseHelper1.getWritableDatabase();
                Cursor cursor2 = db1.query("photo_path_new", new String[]{"id","user_name","user_time","photo_type","photo_url","photo_cal","photo_kind"}, null, null, null, null, null);
                String result = getSynData();
                List<String> user_time_list = new ArrayList<String>();

                while(cursor2.moveToNext()){
                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
                    String user_name = cursor2.getString(cursor2.getColumnIndex("user_name"));
                    String user_time = cursor2.getString(cursor2.getColumnIndex("user_time"));
                    String photo_type = cursor2.getString(cursor2.getColumnIndex("photo_type"));
                    user_time_list.add(user_time);
                }
                String [] each_data = result.split("<");
                for(int i  = 0; i < each_data.length; i ++){
                    String [] temp_data = each_data[i].split(";");
                    if(temp_data.length < 7){
                        continue;
                    }
                    String user_name = temp_data[0];
                    String user_time = temp_data[1];
                    String photo_url = temp_data[2];
                    String food_type = temp_data[3];
                    String photo_cal = temp_data[4];
                    String photo_kind = temp_data[5];
                    String workday = temp_data[6];
                    String weekend = temp_data[7];

                    String temp_date = user_time.split("-")[0];
                    String temp_time = user_time.split("-")[1];
                    String temp_hour = temp_time.split(":")[0];
                    String temp_minute = temp_time.split(":")[1];
                    String temp_second = temp_time.split(":")[2];
                    if(Integer.valueOf(temp_hour) < 10){
                        temp_hour = "0" + temp_hour;
                    }
                    if(Integer.valueOf(temp_minute) < 10){
                        temp_minute = "0" + temp_minute;
                    }
                    if(Integer.valueOf(temp_second) < 10){
                        temp_second = "0" + temp_second;
                    }
                    String new_user_time = temp_date + "-" + temp_hour + ":" + temp_minute + ":" + temp_second;
                    if(!user_time_list.contains(new_user_time)){
                        ContentValues values = new ContentValues();
                        values.put("user_name", user_name);
                        values.put("user_time", new_user_time);
                        values.put("photo_url", photo_url);
                        values.put("photo_type",food_type);
                        values.put("photo_cal",photo_cal);
                        values.put("photo_kind",photo_kind);
                        values.put("workday",workday);
                        values.put("weekend",weekend);
                        db1.insert("photo_path_new", null, values);

                    }else{
                        ContentValues values = new ContentValues();
                        values.put("photo_type",food_type);
                        values.put("photo_cal",photo_cal);
                        values.put("photo_kind",photo_kind);
                        values.put("workday",workday);
                        values.put("weekend",weekend);
                        db1.update("photo_path_new", values, "user_time = ?", new String[]{new_user_time});
                    }


                }
                cursor2.close();
                db1.close();
                XToastUtils.toast("同步成功");
                break;
            case R.id.menu_logout:
                XToastUtils.toast(superTextView.getCenterString());
                MMKVUtils.put("IS_USER_ID", "Cohort Clock");
                MMKVUtils.put("IS_USER_ACCOUNT", "Cohort Clock");
                MMKVUtils.put("IS_USER_PASSWORD", null);
                popToBack();
                break;
            default:
                break;
        }
    }

    public String getSynData()
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
        String url1 ="http://39.100.73.118/deeplearning_photo/syn_database.php?androidid="+androidid+"";
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
    protected void initListeners() {
        switch_tv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false);
            }
        }).setSwitchCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MMKVUtils.put("IS_SHOW_EatingTime", isChecked);
                if(!isChecked){
                    XToastUtils.toast("Choose eating time manually");
                }
                else{
                    XToastUtils.toast("Choose eating time by eating model");
                }
//                XToastUtils.toast(" : " + isChecked);
            }
        });


//        switch_tv1.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
//            @Override
//            public void onClick(SuperTextView superTextView) {
//                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false);
//            }
//        }).setSwitchCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                MMKVUtils.put("IS_SHOW_HAND", isChecked);
//                if(!isChecked){
//                    XToastUtils.toast("不显示手动上传按钮");
//                }
//                else{
//                    XToastUtils.toast("显示手动上传按钮");
//                }
////                XToastUtils.toast(" : " + isChecked);
//            }
//        });


        switch_tv2.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false);
            }
        }).setSwitchCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MMKVUtils.put("IS_SHOW_CLOCK", isChecked);
                if(!isChecked){
//                    XToastUtils.toast("调用");
                }
                else{
//                    XToastUtils.toast("调用系统时钟");
                }
            }
        });













    }
}
