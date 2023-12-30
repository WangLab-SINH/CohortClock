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

@Keep
public class UserInfo_WeightSleep {
    private String id;
    private String time;
    private String weight;
    private String start_time;
    private String end_time;
    private Boolean operation;

    public UserInfo_WeightSleep(String id, String time,  String start_time,String end_time,boolean operation) {
    //public UserInfo_WeightSleep(String time) {
        this.id = id;
        this.time = time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.operation = operation;

    }



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getStart_time() {
        return start_time;
    }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Boolean getOperation() {
        return operation;
    }
    public void setOperation(Boolean operation) {
        this.operation = operation;
    }
}

