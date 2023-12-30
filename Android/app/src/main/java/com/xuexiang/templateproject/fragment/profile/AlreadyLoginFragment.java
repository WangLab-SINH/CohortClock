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

import android.widget.TextView;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

@Page(name = "已登录",anim = CoreAnim.none)
public class AlreadyLoginFragment extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener{

    @BindView(R.id.menu_logout)
    SuperTextView menuLogout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_already_login;
    }

    @Override
    protected void initViews() {
        menuLogout.setOnSuperTextViewClickListener(this);
        String user_name = MMKVUtils.getString("IS_USER_ACCOUNT", "Cohort Clock");
        final TextView btn = findViewById(R.id.user_name);
        user_name = "账号：" + user_name;
        btn.setText(user_name);


    }

    @Override
    protected void initListeners() {

    }



    @SingleClick
    @Override
    public void onClick(SuperTextView superTextView) {

                XToastUtils.toast(superTextView.getCenterString());
                MMKVUtils.put("IS_USER_ID", "Cohort Clock");
                MMKVUtils.put("IS_USER_ACCOUNT", "Cohort Clock");
                MMKVUtils.put("IS_USER_PASSWORD", null);

               popToBack();


    }






}
