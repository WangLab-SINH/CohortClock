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

package com.xuexiang.templateproject.activity;

import androidx.annotation.Keep;

import com.bin.david.form.annotation.SmartTable;

/**
 * Created by huang on 2017/11/1.
 */
@Keep
@SmartTable(name="合并信息列表",count = true)
public class MergeInfo {

    private String time;

    public MergeInfo(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
//    @SmartColumn(id =1,name = "姓名",autoCount = true,autoMerge = true,width = 100)
//    private String name;
//    @SmartColumn(id=2,name="年龄",autoCount = true,autoMerge = true)
//    private int age;
//    @SmartColumn(id =3,name="更新时间")
//    private long time;
//    @SmartColumn(type= ColumnType.Child)
//    private ChildData childData;
//    @SmartColumn(id =4,name="选中")
//    private boolean isCheck;
//    private String url;
//
//    public MergeInfo(String name, int age, long time, boolean isCheck, ChildData childData) {
//        this.name = name;
//        this.age = age;
//        this.time = time;
//        this.childData = childData;
//        this.isCheck = isCheck;
//    }
//
//    public boolean isCheck() {
//        return isCheck;
//    }
//
//    public void setCheck(boolean check) {
//        isCheck = check;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public long getTime() {
//        return time;
//    }
//
//    public void setTime(long time) {
//        this.time = time;
//    }
//
//    public ChildData getChildData() {
//        return childData;
//    }
//
//    public void setChildData(ChildData childData) {
//        this.childData = childData;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
}
