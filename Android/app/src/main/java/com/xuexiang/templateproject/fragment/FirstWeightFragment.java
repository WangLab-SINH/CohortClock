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

package com.xuexiang.templateproject.fragment;

import android.view.View;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.activity.MainActivity;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.fragment.profile.HttpResponeCallBack;
import com.xuexiang.templateproject.utils.MMKVUtils;
import com.xuexiang.templateproject.utils.Utils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.picker.RulerView;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xutil.app.ActivityUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

@Page(anim = CoreAnim.none)
public class FirstWeightFragment extends BaseFragment {
    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/user_info.php";
    private HttpResponeCallBack mCallBack = null;
    private static URL url;
    @BindView(R.id.tcv_select)
    TabControlView mTabControlView;
    private CountDownButtonHelper mCountDownHelper;



    @BindView(R.id.rulerView)
    RulerView rulerView;
    @BindView(R.id.rulerView1)
    RulerView rulerView1;

    String sex = "null";
    String weight = "null";
    String height = "null";
    String diabete = "null";
    String workdate = "null";
    String disease = "null";
    String press_min = "null";
    String press_max = "null";

    @Override
    protected TitleBar initTitle() {
        return null;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_first;
    }

    @Override
    protected void initViews() {
        Utils.showPrivacyDialog(getContext(), (dialog, which) -> {
            dialog.dismiss();
            MMKVUtils.put("key_agree_privacy", true);
            //ActivityUtils.startActivity(MainActivity.class);
            //finish();
        });
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
            mTabControlView.setDefaultSelection(1);



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

    }
    @OnClick({R.id.button_yes, R.id.button_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_yes:
                weight = String.valueOf(rulerView.getCurrentValue());
                height = String.valueOf(rulerView1.getCurrentValue());
                //ActivityUtils.startActivity(MainActivity.class);
                MMKVUtils.put("weight", weight);
                MMKVUtils.put("height", height);
                MMKVUtils.put("sex", sex);
                ActivityUtils.startActivity(MainActivity.class);

//                Utils.showPrivacyDialog(getActivity(), (dialog, which) -> {
//                    dialog.dismiss();
//                    MMKVUtils.put("key_agree_privacy", true);
//                    ActivityUtils.startActivity(MainActivity.class);
//                });

                //RequestApiData.getInstance().getLoginData(account, password, UserBaseInfo.class, LoginActivity.this);

//                if (!StringUtils.isEmpty(etWeight.getText().toString())) {
//                    rulerView.setCurrentValue(StringUtils.toFloat(etWeight.getText().toString(), 0));
//                } else {
//                    XToastUtils.toast("请输入体重值！");
//                }
                //openNewPage(ProfileFragment.class);

                break;

            case R.id.button_skip:
                ActivityUtils.startActivity(MainActivity.class);
                break;

            default:
                break;
        }
    }






    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }
}
