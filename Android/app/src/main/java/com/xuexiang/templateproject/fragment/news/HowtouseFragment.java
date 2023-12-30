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

package com.xuexiang.templateproject.fragment.news;

import android.text.Html;
import android.widget.TextView;

import com.kunminx.linkage.bean.DefaultGroupedItem;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.adapter.base.delegate.SimpleDelegateAdapter;
import com.xuexiang.templateproject.adapter.entity.NewInfo;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 首页动态
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Page(name = "Help",anim = CoreAnim.none)
public class HowtouseFragment extends BaseFragment {
    @BindView(R.id.textView1)
    ExpandableTextView mExpandableTextView1;

    @BindView(R.id.textView2)
    ExpandableTextView mExpandableTextView2;
    @BindView(R.id.textView3)
    ExpandableTextView mExpandableTextView3;
    @BindView(R.id.textView4)
    ExpandableTextView mExpandableTextView4;
    @BindView(R.id.textView5)
    ExpandableTextView mExpandableTextView5;
    @BindView(R.id.textView6)
    ExpandableTextView mExpandableTextView6;
//    @BindView(R.id.photoView1)
//    ImageView photoview1;
//
//    @BindView(R.id.photoView2)
//    ImageView photoview2;
//
//    @BindView(R.id.photoView3)
//    ImageView photoview3;






    private SimpleDelegateAdapter<NewInfo> mNewsAdapter;

    /**
     * @return 返回为 null意为不需要导航栏
     */
//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_howtouse;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        String str1 = "<p><B>1. How do I use this app?</B><br/>" +
                "After opening the app, agree to the user agreement and set your gender, height, and age. Select the food upload interface, then click the button to take a photo or select an image, granting the necessary permissions for storage and camera use as prompted. Use the app to take a photo of the food, or choose a food image from your phone, then click the save button. At the same time, enter your sleep, weight, and disease information. The app will upload the current image to the server for analysis to determine whether it's food, and record the user's sleep patterns and other lifestyle habits.</p>";

        String  str2 = "<p><B>2. What features does this app have?</B><br/>" +
                "This app primarily characterizes users' dietary, exercise and sleep patterns through uploaded food images and sleep times. Additionally, the app can record users' basic information such as height, weight, disease history, and other body metrics. These data can be used for cohort level scientific research. <br/>" +
                "To upload sleep information: Select the Main tab, click on the Weight/Sleep page, and choose your sleep and wake-up times.<br/>" +
                "To upload weight information: Select the Main tab, click on the Weight/Sleep page, and enter your weight.<br/>"+
                "To upload height information: Select the Body Metrics tab, click the Height button, and enter your height for data upload.<br/>"+
                "To upload blood pressure data: Select the Body Metrics tab, click the Blood Pressure button, and enter your measured systolic and diastolic pressure data.<br/>"+
                "To upload heart rate data: Select the Body Metrics tab, click the Heart Rate button, and enter your measured heart rate.<br/>"+
                "To upload waist circumference data: Select the Body Metrics tab, click the Waist Circumference button, and enter your measured waist circumference.<br/>"+
                "To upload exercise data: Select the Body Metrics tab, click the Exercise Duration button, and choose the start and end times of your exercise.<br/>"+
                "To upload age data: Select the My information tab, click the Age button, and enter your age.<br/>"+
                "To upload gender data: Select the My information tab, click the Gender button, and choose your gender.<br/>"+
                "To upload body temperature data: Select the Body Metrics tab, click the Temperature button, and enter your measured body temperature.<br/>"+
                "To upload medication data: Select the Body Metrics tab, click the Medicine button, and enter the medication you are taking.<br/>"+
                "To upload disease data: Select the Body Metrics tab, click the Disease button, and select the diseases you want to record.<br/>"+
                "</p>";
        String str3 = "<p><B>3. What should I do if I forget to upload food, sleep, weight, exercise, and other information on time?</B><br/>" +
                "If you forget to upload data, you can click the update button to go to the data re-upload page. Select the food picture, then choose the time of eating for that day. Please note that you can only re-upload pictures of food that you forgot to upload on the same day. After selecting the picture, click save to complete the re-upload. The operations for sleep, weight, and exercise information are similar." +
                "</p>";
        String str4 = "<p><B>4. Can I upload non-food pictures?</B><br/>" +
                "This app uses deep learning algorithms to determine whether the uploaded image is food. If a non-food image is uploaded, it will not be recorded in the user's dietary habits." +
                "</p>";
        String str5 = "<p><B>5. What should I do if I want to modify the body metrics data?</B><br/>" +
                "If you need to modify data due to an upload error, you can click on the Main tab and then the Modification button. This page will display all the data you uploaded today, allowing you to edit and delete as necessary." +
                "</p>";
        String str6 = "<p><B>6. Is registering an account useful?</B><br/>" +
                "After registering an account, if you change your phone, you can synchronize the dietary habits recorded on your previous phone. This facilitates the cohort research." +
                "</p>";


//        photoview1.setVisibility(View.GONE);
//        String str1 = "<p><B>1.我该如何使用这个App？</B><br/>" +
//                "打开APP后，同意使用协议，选择传食物界面，点击拍照或者选择图片按钮，根据提示赋予所需的手机存储与使用相机权限。使用APP对食物进行拍照，或选择手机内的食物图片后点击上传按钮。APP会将当前图片上传到服务器进行分析判断是否是食物，并返回近三个月内的用户饮食规律和当前时间之后的24小时内用户的饮食时间建议。</p>";
//
//        String  str2 = "<p><B>2.这个App有哪些功能？</B><br/>" +
//                "本应用目前主要是通过用户上传的食物图片，推断出用户的饮食规律，并给出合理的饮食建议，比如当前时间之后的24小时内用户的饮食时间建议。" +
//                "同时本应用还可以记录用户的基本信息如身高、体重、睡眠时间等数据。</p>";
//        String str3 = "<p><B>3.我忘记按时上传食物图片了怎么办？</B><br/>" +
//                "如果忘记上传数据，可以点击更新按钮，进入补传数据页面，选择食物的图片，然后选择当天进食的时间点，注意是只能补传当天忘记的食物的图片，然后选中图片，点击上传，那么补传就完成了。" +
//                "</p>";
//        String str4 = "<p><B>4.我没有上传食物图片可以么？</B><br/>" +
//                "本应用会使用深度学习算法判断上传的图片是否是食物，如果上传非食物图片并不会被记录到用户的饮食规律中。本应用也可以选择手动上传功能，可以不上传图片" +
//                "</p>";
//        String str5 = "<p><B>5.注册账号有什么用处？</B><br/>" +
//                "注册账号后在您更换手机后可以同步之前手机所记录的饮食规律，这样有利于给出您更合理的饮食建议。" +
//                "</p>";

        mExpandableTextView1.setText(Html.fromHtml(str1));
        mExpandableTextView1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                if(isExpanded) {
//                    photoview1.setVisibility(View.VISIBLE);
//                    photoview2.setVisibility(View.VISIBLE);

                }


//                if(isExpanded){
//                    photoview1.setVisibility(View.VISIBLE);
//                }
                else{

//                    photoview1.setVisibility(View.GONE);
//                    photoview2.setVisibility(View.GONE);


                }

                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView2.setText(Html.fromHtml(str2));
        mExpandableTextView2.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView3.setText(Html.fromHtml(str3));
        mExpandableTextView3.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                if(isExpanded) {
//                    photoview3.setVisibility(View.VISIBLE);

                }


//                if(isExpanded){
//                    photoview1.setVisibility(View.VISIBLE);
//                }
                else{

//                    photoview3.setVisibility(View.GONE);


                }
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView4.setText(Html.fromHtml(str4));
        mExpandableTextView4.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView5.setText(Html.fromHtml(str5));
        mExpandableTextView5.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });
        mExpandableTextView6.setText(Html.fromHtml(str6));
        mExpandableTextView6.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });


    }

    @Override
    protected void initListeners() {


    }



    public static List<DefaultGroupedItem> getGroupItems() {
        List<DefaultGroupedItem> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                items.add(new DefaultGroupedItem(true, "菜单" + i / 10));
            } else {
                items.add(new DefaultGroupedItem(new DefaultGroupedItem.ItemInfo("这是标题" + i, "菜单" + i / 10, "这是内容" + i)));
            }
        }
        return items;
    }
}
