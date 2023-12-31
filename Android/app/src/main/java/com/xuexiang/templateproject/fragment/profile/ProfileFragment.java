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

package com.xuexiang.templateproject.fragment.profile;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.fragment.AboutFragment;
import com.xuexiang.templateproject.fragment.SettingsFragment;
import com.xuexiang.templateproject.fragment.news.FeedbackFragment;
import com.xuexiang.templateproject.fragment.news.HowtouseFragment;
import com.xuexiang.templateproject.fragment.news.QuestionFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class ProfileFragment extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener {
//    @BindView(R.id.riv_head_pic)
//    RadiusImageView rivHeadPic;
    @BindView(R.id.menu_settings)
    SuperTextView menuSettings;
    @BindView(R.id.menu_about)
    SuperTextView menuAbout;
    @BindView(R.id.menu_user)
    SuperTextView menuUser;
    @BindView(R.id.menu_info)
    SuperTextView menuInfo;
    @BindView(R.id.menu_question)
    SuperTextView menuQuestion;
    @BindView(R.id.menu_news)
    SuperTextView menuNews;
    @BindView(R.id.menu_weight_sleep)
    SuperTextView menuWeightSleep;
    @BindView(R.id.menu_sleep)
    SuperTextView menuSleep;
    @BindView(R.id.menu_feedback)
    SuperTextView menuFeedback;
//    @BindView(R.id.tvAccount)
//    TextView textview111;


    /**
     * @return 返回为 null意为不需要导航栏
     */
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
        return R.layout.fragment_profile;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
//        String userAccount = MMKVUtils.getString("IS_USER_ACCOUNT", null);
//        if(userAccount == null)
//        {
//            textview111.setText("无登录账号");
//
//        }
//        else
//        {
//            textview111.setText("当前账号："+ userAccount);
//
//        }

    }

    @Override
    protected void initListeners() {
        menuSettings.setOnSuperTextViewClickListener(this);
        menuAbout.setOnSuperTextViewClickListener(this);
        menuUser.setOnSuperTextViewClickListener(this);
        menuInfo.setOnSuperTextViewClickListener(this);
        menuQuestion.setOnSuperTextViewClickListener(this);
        menuNews.setOnSuperTextViewClickListener(this);
        menuWeightSleep.setOnSuperTextViewClickListener(this);
        menuSleep.setOnSuperTextViewClickListener(this);
        menuFeedback.setOnSuperTextViewClickListener(this);

    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
        switch(view.getId()) {
            case R.id.menu_user:
                String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
                if(user_name.equals("Cohort Clock")){
                    openNewPage(LoginFragment.class);
                }else{
                    openNewPage(AlreadyLoginFragment.class);
                }

                break;
            case R.id.menu_feedback:
                openNewPage(FeedbackFragment.class);
                break;
            case R.id.menu_settings:
                openNewPage(SettingsFragment.class);
                break;
            case R.id.menu_about:
                openNewPage(AboutFragment.class);
                break;
            case R.id.menu_info:
                openNewPage(AgeInfoFragment.class);
                break;
            case R.id.menu_question:
                openNewPage(QuestionFragment.class);
                break;
            case R.id.menu_news:
                openNewPage(HowtouseFragment.class);
                break;
            case R.id.menu_weight_sleep:
                openNewPage(SexInfoFragment.class);
                break;

            case R.id.menu_sleep:
                openNewPage(WorkInfoFragment.class);
                break;


            default:
                break;
        }
    }
}
