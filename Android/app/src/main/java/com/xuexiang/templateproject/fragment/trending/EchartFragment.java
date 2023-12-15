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

package com.xuexiang.templateproject.fragment.trending;

import android.provider.Settings;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.core.webview.XPageWebViewFragment;
import com.xuexiang.xui.widget.actionbar.TitleBar;

public class EchartFragment extends BaseFragment {
    protected boolean isVisible;
    String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    String url_head="http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
    String pic_name="heatmap.html";
    String pic_name2="heatmap.png";
    String temp_url = url_head + pic_name;
    @Override
    protected TitleBar initTitle() {


        //Log.e("-----------","---initTitle");
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_uploaded;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        XPageWebViewFragment.openUrl(this, temp_url);


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

            //Log.e("-----------","---reuploadedrun");
            super.setUserVisibleHint(isVisibleToUser);
            if(getUserVisibleHint()) {
                isVisible = true;
                onVisible();
            } else {
                isVisible = false;
                onInvisible();
            }


    }
    protected void onVisible(){

        lazyLoad();
    }

    protected void lazyLoad() {

        XPageWebViewFragment.openUrl(this, temp_url);


    }

    protected void onInvisible() {


    }

}
