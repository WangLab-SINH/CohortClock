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

package com.xuexiang.templateproject.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/11/9.
 */

public class TanBean {
    
    private int position;
    private String url;
    private String name;

    public TanBean(int position, String url, String name) {
        this.position = position;
        this.url = url;
        this.name = name;
    }

    public static List<TanBean> initDatas() {
        List<TanBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new TanBean(i++, "http://imgs.ebrun.com/resources/2016_03/2016_03_25/201603259771458878793312_origin.jpg", "张"));
        datas.add(new TanBean(i++, "http://39.100.73.118/deeplearning_photo/uploads/kYUSDZQf200626.jpg", "旭童"));
        datas.add(new TanBean(i++, "http://news.k618.cn/tech/201604/W020160407281077548026.jpg", "多种type"));
        datas.add(new TanBean(i++, "http://www.kejik.com/image/1460343965520.jpg", "多种type"));
        datas.add(new TanBean(i++, "http://cn.chinadaily.com.cn/img/attachement/jpg/site1/20160318/eca86bd77be61855f1b81c.jpg", "多种type"));
        datas.add(new TanBean(i++, "http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg", "多种type"));
        datas.add(new TanBean(i++, "http://imgs.ebrun.com/resources/2016_04/2016_04_24/201604244971461460826484_origin.jpeg", "多种type"));
        datas.add(new TanBean(i++, "http://www.lnmoto.cn/bbs/data/attachment/forum/201408/12/074018gshshia3is1cw3sg.jpg", "多种type"));
        return datas;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
