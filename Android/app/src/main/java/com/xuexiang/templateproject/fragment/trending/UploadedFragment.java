///*
// * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *       http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// */
//
//package com.xuexiang.templateproject.fragment.trending;
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Looper;
//import android.provider.Settings;
//import android.util.Log;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.umeng.analytics.MobclickAgent;
//import com.xuexiang.templateproject.R;
//import com.xuexiang.templateproject.activity.MainActivity;
//import com.xuexiang.templateproject.core.BaseActivity;
//import com.xuexiang.templateproject.core.BaseFragment;
//import com.xuexiang.xpage.annotation.Page;
//import com.xuexiang.xpage.enums.CoreAnim;
//import com.xuexiang.xrouter.annotation.AutoWired;
//import com.xuexiang.xui.widget.actionbar.TitleBar;
//import com.xuexiang.xui.widget.imageview.ImageLoader;
//
////@Page(anim = CoreAnim.none)
//@Page(name = "数据接收", anim = CoreAnim.none,params = {UploadedFragment.KEY_EVENT_DATA})
//public class UploadedFragment extends BaseFragment {
//    protected boolean isVisible;
//    static int flag1 = 0;
//    int temp_falg = 0;
//    boolean isChanged;
//    public final static String KEY_EVENT_DATA = "event_data";
//    @AutoWired(name = KEY_EVENT_DATA)
//    String eventData;
//    int first_init = 0;
//
////    @BindView(R.id.iv_content)
////    AppCompatImageView ivContent;
////    @BindView(R.id.iv_content1)
////    AppCompatImageView ivContent1;
//    /**
//     * @return 返回为 null意为不需要导航栏
//     */
//    @Override
//    protected TitleBar initTitle() {
//
//
//        //Log.e("-----------","---initTitle");
//        return null;
//    }
//
//    /**
//     * 布局的资源id
//     *
//     * @return
//     */
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_uploaded;
//    }
//
//    /**
//     * 初始化控件
//     */
//    @Override
//    protected void initViews() {
//        //Log.e("-----------","---reuploadedinitViews");
//        flag1 = 1;
//
//        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
//
//        String url_head="http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
//        String pic_name="dotplot.png";
//        String pic_name2="heatmap.png";
//        String PICTURE_URL = url_head + pic_name;
//        String PICTURE_URL1 = url_head + pic_name2;
//        ImageView imgView;
//        ImageView imgView2;
//        imgView = (ImageView) findViewById(R.id.imgView);
//        imgView2 = (ImageView) findViewById(R.id.imgView2);
//        Glide.with(this).load(PICTURE_URL)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(imgView);
//        Glide.with(this).load(PICTURE_URL1)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(imgView2);
//        Log.e("-----------","---reuploadedinitViews");
//
////        if(eventData != null) {
////            Log.e("-----------","---reuploadedeventData");
////            if (eventData.equals("事件")) {
////                final MainActivity mainActivity = (MainActivity) getActivity();
//////                mainActivity.onPageSelected(1);
////            }
////        }
////        if(getUserVisibleHint())
////        {
////            first_init = 0;
////            //final MainActivity mainActivity = (MainActivity) getActivity();
////            //mainActivity.onPageSelected(1);
////            ;
////        }
////        first_init = 1;
//
////        LoadOption option = LoadOption.of(DiskCacheStrategyEnum.NONE)
////                .skipMemoryCache(true)
////                .setPlaceholder(ResUtils.getDrawable(R.drawable.xui_ic_default_img))
////                .setSize(DensityUtils.dp2px(400), DensityUtils.dp2px(400));
////        ImageLoader.get().loadImage(ivContent, PICTURE_URL, option);
////        ImageLoader.get().loadImage(ivContent1, PICTURE_URL1, option);
////        LoadOption option = LoadOption.of(DiskCacheStrategyEnum.ALL)
////                .setPlaceholder(ResUtils.getDrawable(R.drawable.xui_ic_default_img))
////                .setSize(DensityUtils.dp2px(200), DensityUtils.dp2px(100));
////        ImageLoader.get().loadImage(ivContent1, PICTURE_URL1, option);
////        ImageLoader.get().loadImage(ivContent, PICTURE_URL, DiskCacheStrategyEnum.NONE, new ILoadListener() {
////            @Override
////            public void onLoadSuccess() {
////                getMessageLoader().dismiss();
////            }
////            @Override
////            public void onLoadFailed(Throwable error) {
////                getMessageLoader().dismiss();
////            }
////        });
////        ImageLoader.get().loadImage(ivContent1, PICTURE_URL1, DiskCacheStrategyEnum.NONE, new ILoadListener() {
////            @Override
////            public void onLoadSuccess() {
////                getMessageLoader().dismiss();
////            }
////            @Override
////            public void onLoadFailed(Throwable error) {
////                getMessageLoader().dismiss();
////            }
////        });
//////        LoadOption option = LoadOption.of(DiskCacheStrategyEnum.ALL)
////                .setSize(DensityUtils.dp2px(200), DensityUtils.dp2px(100));
////        ImageLoader.get().loadImage(ivContent1, PICTURE_URL1, option);
////
////        ImageLoader.get().loadImage(ivContent2, PICTURE_URL2, ResUtils.getDrawable(R.drawable.xui_ic_default_img), DiskCacheStrategyEnum.AUTOMATIC);
//
//    }
//    @Override
//    public void onResume() {
//        initViews();
//        super.onResume();
//        MobclickAgent.onPageStart(getPageName());
//    }
//
//    @Override
//    public void onPause() {
//        initViews();
//        super.onPause();
//        MobclickAgent.onPageEnd(getPageName());
//    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if (flag1 == 1){
//            //Log.e("-----------","---reuploadedrun");
//            super.setUserVisibleHint(isVisibleToUser);
//            if(getUserVisibleHint()) {
//                isVisible = true;
//                onVisible();
//            } else {
//                isVisible = false;
//                onInvisible();
//            }
//        }
//
//    }
//    protected void onVisible(){
//
//        flag1 = 1;
//        Log.e("-----------","---reuploadedonVisible");
//        lazyLoad();
//    }
//
//    protected void lazyLoad() {
//        Log.e("-----------","---reuploadedonlazyLoad");
////        final MainActivity mainActivity = (MainActivity) getActivity();
////        mainActivity.onPageSelected(1);
//        clearImageDiskCache(getContext());
//        clearImageMemoryCache(getContext());
//        String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.System.ANDROID_ID);
//        String url_head="http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
//        String pic_name="dotplot.png";
//        String pic_name2="heatmap.png";
//        String PICTURE_URL = url_head + pic_name;
//        String PICTURE_URL1 = url_head + pic_name2;
//        ImageView imgView;
//        ImageView imgView2;
//        imgView = (ImageView) findViewById(R.id.imgView);
//        imgView2 = (ImageView) findViewById(R.id.imgView2);
//        Glide.with(this).load(PICTURE_URL)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(imgView);
//        Glide.with(this).load(PICTURE_URL1)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(imgView2);
//
//        //ImageLoader.get().clearMemoryCache(getActivity().getApplicationContext());
//
////        //PICTURE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573298332486&di=57555a4ffbd9c2c09f12042f0f2b8ba6&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1212%2F10%2Fc1%2F16491245_1355126013759.jpg";
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////
////               ImageLoader.get().clearDiskCache(getActivity().getApplicationContext());
//////
////            }
////        }).start();
//
//
//    }
//
//    protected void onInvisible() {
//
//
//    }
//    public void clearImageDiskCache(final Context context) {
//        try {
//            if (Looper.myLooper() == Looper.getMainLooper()) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Glide.get(context).clearDiskCache();
//// BusUtil.getBus().post(new GlideCacheClearSuccessEvent());
//                    }
//                }).start();
//            } else {
//                Glide.get(context).clearDiskCache();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void clearImageMemoryCache(Context context) {
//        try {
//            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
//                Glide.get(context).clearMemory();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        isChanged = true;
//    }
//
//
//}
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
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.core.webview.XPageWebViewFragment;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import butterknife.OnClick;

public class UploadedFragment extends BaseFragment {
    protected boolean isVisible;

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

//    private void addLost(){
//        OkHttpClient okHttpClient = new OkHttpClient();
//        String url ="http://website/androidapi/create_lost_items.php?what="+title+"&when="+time+"&where="+place+"&detail="+describe+"&contact="+photo+"";
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//
//        try{
//            Response response = okHttpClient.newCall(request).execute();
//            res = response.body().string();
//            handler.sendEmptyMessage(1);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            handler.sendEmptyMessage(2);
//        }
//    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_uploaded;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        //XPageWebViewFragment.openUrl(this, temp_url);


    }

    @OnClick({R.id.imgViewall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgViewall:
                String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
                String pic_name = "heatmap.html";
                String pic_name2 = "heatmap.png";
                String temp_url = url_head + pic_name;
                if (!Patterns.WEB_URL.matcher(temp_url.toString()).matches()) {
                    XPageWebViewFragment.openUrl(this, temp_url);
                }
                else{
                    Log.e("not url","url");
                }
                break;
            default:
                break;
        }
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
        if(getActivity() != null) {
            String androidid = Settings.System.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            String url_head = "http://39.100.73.118/deeplearning_photo/result_photo/" + androidid + "/";
            String pic_name = "heatmap.html";
            String pic_name2 = "heatmap.png";
            String temp_url = url_head + pic_name;
            XPageWebViewFragment.openUrl(this, temp_url);
        }


    }

    protected void onInvisible() {


    }

}
