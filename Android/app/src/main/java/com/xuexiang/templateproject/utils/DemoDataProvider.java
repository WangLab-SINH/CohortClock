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

package com.xuexiang.templateproject.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.reflect.TypeToken;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.adapter.entity.NewInfo;
import com.xuexiang.templateproject.fragment.trending.FoodTypeInfo;
import com.xuexiang.xaop.annotation.MemoryCache;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xutil.net.JsonUtil;
import com.xuexiang.xutil.resource.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示数据
 *
 * @author xuexiang
 * @since 2018/11/23 下午5:52
 */
public class DemoDataProvider {

    public static String[] titles = new String[]{

    };

    public static String[] urls = new String[]{//640*360 360/640=0.5625

    };

    @MemoryCache
    public static List<BannerItem> getBannerList() {
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            list.add(item);
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
//    @MemoryCache
//    public static List<NewInfo> getDemoNewInfos() {
//        List<NewInfo> list = new ArrayList<>();
//        list.add(new NewInfo("源码", "Android源码分析--Android系统启动")
//                .setSummary("其实Android系统的启动最主要的内容无非是init、Zygote、SystemServer这三个进程的启动，他们一起构成的铁三角是Android系统的基础。")
//                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("Android UI", "XUI 一个简洁而优雅的Android原生UI框架，解放你的双手")
//                .setSummary("涵盖绝大部分的UI组件：TextView、Button、EditText、ImageView、Spinner、Picker、Dialog、PopupWindow、ProgressBar、LoadingView、StateLayout、FlowLayout、Switch、Actionbar、TabBar、Banner、GuideView、BadgeView、MarqueeView、WebView、SearchView等一系列的组件和丰富多彩的样式主题。\n")
//                .setDetailUrl("https://juejin.im/post/5c3ed1dae51d4543805ea48d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/1/16/1685563ae5456408?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("面试", "写给即将面试的你")
//                .setSummary("最近由于公司业务发展，需要招聘技术方面的人才，由于我在技术方面比较熟悉，技术面的任务就交给我了。今天我要分享的就和面试有关，主要包含技术面的流程、经验和建议，避免大家在今后的面试过程中走一些弯路，帮助即将需要跳槽面试的人。")
//                .setDetailUrl("https://juejin.im/post/5ca4df966fb9a05e4e58320c")
//                .setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554629219186&di=6cdab5cfceaae1f7e6d78dbe79104c9f&imgtype=0&src=http%3A%2F%2Fimg.qinxue365.com%2Fuploads%2Fallimg%2F1902%2F4158-1Z22FZ64E00.jpg"));
//
//        list.add(new NewInfo("Android", "XUpdate 一个轻量级、高可用性的Android版本更新框架")
//                .setSummary("XUpdate 一个轻量级、高可用性的Android版本更新框架。本框架借鉴了AppUpdate中的部分思想和UI界面，将版本更新中的各部分环节抽离出来，形成了如下几个部分：")
//                .setDetailUrl("https://juejin.im/post/5b480b79e51d45190905ef44")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/7/13/16492d9b7877dc21?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("Android/HTTP", "XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp进行组装")
//                .setSummary("一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。还不赶紧点击使用说明文档，体验一下吧！")
//                .setDetailUrl("https://juejin.im/post/5b6b9b49e51d4576b828978d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/8/9/1651c568a7e30e02?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//        return list;
//    }
    @MemoryCache
    public static List<NewInfo> getDemoNewInfos() {
        List<NewInfo> list = new ArrayList<>();
        list.add(new NewInfo("相关推荐", "EARLY TIME-RESTRICTED FEEDING FOR THE PREVENTION OF DIABETES")
                .setSummary("Early Time-Restricted Feeding for the Prevention of Diabetes Author: Drew Duglan, PhD Intermittent fasting now seems to be a household term. It is being promoted by individuals in the health and wellness scene, as well as being\n" +
                        "\n")
                .setDetailUrl("https://blog.mycircadianclock.org/early-time-restricted-feeding-for-the-prevention-of-diabetes/")
                .setImageUrl("https://blog.mycircadianclock.org/wp-content/uploads/2018/05/image-1.png"));

        list.add(new NewInfo("相关推荐", "LIGHT THERAPEUTICS: HOW LIGHT (OR DARKNESS) AFFECTS OUR CIRCADIAN CLOCK, SLEEP, AND MOOD")
                .setSummary("Author: Satchin Panda, PhD When we travel from one time zone to another, our sleep-wake cycle slowly readjusts to the new time zone. It’s as if our sleep-wake cycle figures out when the sun rises and\n")
                .setDetailUrl("https://blog.mycircadianclock.org/light-therapeutics-how-light-or-darkness-affects-our-circadian-clock-sleep-and-mood/")
                .setImageUrl(""));

        list.add(new NewInfo("相关推荐", "ALL OF US MAY BE SHIFT WORKERS; WHICH SHIFT WORKER ARE YOU?")
                .setSummary("Author: Satchin Panda, PhD Graphic Design: Dora Wang All of us may be shift workers; which shift worker are you? For the thousands of years that humans have existed on this planet, our circadian rhythms have been perfectly")
                .setDetailUrl("https://blog.mycircadianclock.org/all-of-us-may-be-shift-workers-which-shift-worker-are-you/")
                .setImageUrl("https://blog.mycircadianclock.org/wp-content/uploads/2018/03/image.png"));

        list.add(new NewInfo("相关推荐", "HOW OUR CULTURE’S CHANGING DIETS COULD BE AFFECTING THE WAY WE SLEEP")
                .setSummary("Author: Sara Westgreen How Our Culture's Changing Diets Could Be Affecting the Way We Sleep What and how you eat can affect how well you sleep. Eat a big meal (or the wrong meal) before bed, and you could")
                .setDetailUrl("https://blog.mycircadianclock.org/how-our-cultures-changing-diets-could-be-affecting-the-way-we-sleep/")
                .setImageUrl("https://blog.mycircadianclock.org/wp-content/uploads/2017/09/fast-food-at-the-office.jpg"));

        list.add(new NewInfo("相关推荐", "WHAT ARE CIRCADIAN CLOCKS, WHERE ARE THEY, AND HOW CAN WE NURTURE THEM?")
                .setSummary("Author: Satchin Panda, PhD Circadian rhythms – such as daily rhythm in sleep-wake, heart rate, blood pressure etc. – are generated by circadian clocks. A few decades ago it was assumed that there was a master")
                .setDetailUrl("https://blog.mycircadianclock.org/what-are-circadian-clocks-where-are-they-and-how-can-we-nurture-them/")
                .setImageUrl(""));
        return list;
    }

    public static List<AdapterItem> getGridItems(Context context) {
        return getGridItems(context, R.array.grid_titles_entry, R.array.grid_icons_entry);
    }


    private static List<AdapterItem> getGridItems(Context context, int titleArrayId, int iconArrayId) {
        List<AdapterItem> list = new ArrayList<>();
        String[] titles = ResUtils.getStringArray(titleArrayId);
        Drawable[] icons = ResUtils.getDrawableArray(context, iconArrayId);
        for (int i = 0; i < titles.length; i++) {
            list.add(new AdapterItem(titles[i], icons[i]));
        }
        return list;
    }
    /**
     * 获取时间段
     *
     * @param interval 时间间隔（分钟）
     * @return
     */
    public static String[] getTimePeriod(int interval) {
        return getTimePeriod(24, interval);
    }

    /**
     * 获取时间段
     *
     * @param interval 时间间隔（分钟）
     * @return
     */
    public static String[] getTimePeriod(int totalHour, int interval) {
        String[] time = new String[totalHour * 60 / interval];
        int point, hour, min;
        for (int i = 0; i < time.length; i++) {
            point = i * interval;
            hour = point / 60;
            min = point - hour * 60;
            time[i] = (hour < 9 ? "0" + hour : "" + hour) + ":" + (min < 9 ? "0" + min : "" + min);
        }
        return time;
    }

    public static List<FoodTypeInfo> getProvinceInfos() {
        return JsonUtil.fromJson(ResourceUtils.readStringFromAssert("foodtype.json"), new TypeToken<List<FoodTypeInfo>>() {
        }.getType());
    }


}
