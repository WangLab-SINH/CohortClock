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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import smile.stat.distribution.KernelDensity;


public class PlotFunction {
    public static int isLUN(int year){
        if(year % 100 == 0){
            if(year % 400 == 0){
                return 1;
            }
        }
        else{
            if(year % 4 == 0){
                return 1;
            }
        }
        return 0;
    }

    public static int isDay(int YEAR, int Month, int day){
        int ret = 0;
        int [] ping = new int []{31,28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int [] lun = new int []{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(isLUN(YEAR) == 1){
            for(int i = 0; i < Month - 1; i++){
                ret = ret + lun[i];
            }
        }
        else{
            for(int i = 0; i < Month - 1; i++){
                ret = ret + ping[i];
            }
        }
        return ret + day;


    }


    public static int isWeekEnd(int YEAR, int Month, int day){
        double S = ((YEAR + Math.ceil((YEAR - 1) / 4) - Math.ceil((YEAR - 1) / 100) +Math.ceil((YEAR - 1) / 400)) % 7);
        double days = (isDay(YEAR, Month, day) + S - 1) % 7;
        if(days == 0.00 || days == 6.00){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static Map<String, List<Integer>> getData(List<String> new_date_list){
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len -1);
        String[] temp = temp_current_time.split("-");
        String current_time = temp[0];
        List<Integer> work_plpt_data_list = new ArrayList<Integer>();
        Map<String, List<Integer>> work_plot_data_dic = new HashMap<String,List<Integer>>();
        List<Integer> weekend_plpt_data_list = new ArrayList<Integer>();
        Map<String, List<Integer>> weekend_plot_data_dic = new HashMap<String,List<Integer>>();
        int i;
        Date last_time = null;
        for(i = 0; i < new_date_list.size(); i ++){
            String date_str = new_date_list.get(i).split("-")[0];
            if(!date_str.equals(current_time)){
                int year = Integer.parseInt(date_str.split("\\.")[0]);
                int month = Integer.parseInt(date_str.split("\\.")[1]);
                int day = Integer.parseInt(date_str.split("\\.")[2]);
                if(isWeekEnd(year, month, day) == 0){
                    if(i > 0){
                        String temp_string = new_date_list.get(i).split("-")[0];
                        Set<String> keySet = work_plot_data_dic.keySet();
                        int inKeyFlag = 0;
                        for(String key : keySet) {
                            if(key == temp_string){
                                inKeyFlag = 1;
                                DateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
                                try {
                                    Date temp_time = df.parse(new_date_list.get(i));
                                    long temp_msscond = temp_time.getTime() - last_time.getTime();
                                    long days = temp_msscond / (1000 * 60 * 60 * 24);
                                    long hours = (temp_msscond-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                                    long minutes = (temp_msscond-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                                    long minus_time = days * 24 * 60 + hours * 60 + minutes;
                                    if(minus_time > 30){
                                        String[] temp_str = new_date_list.get(i).split("-");
                                        int temp_str1 = (int) Math.round((Double.parseDouble(temp_str[1].split(":")[0]) * 60 + Double.parseDouble(temp_str[1].split(":")[1])) / 30);
                                        work_plpt_data_list.add(temp_str1);
                                        work_plot_data_dic.get(temp_string).add(temp_str1);
                                        last_time = df.parse(new_date_list.get(i));
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        if(inKeyFlag == 0){
                            DateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
                            try {
                                Date temp_time = df.parse(new_date_list.get(i));
                                long temp_msscond = temp_time.getTime() - last_time.getTime();
                                long days = temp_msscond / (1000 * 60 * 60 * 24);
                                long hours = (temp_msscond-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                                long minutes = (temp_msscond-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                                long minus_time = days * 24 * 60 + hours * 60 + minutes;
                                if(minus_time > 30){
                                    String[] temp_str = new_date_list.get(i).split("-");
                                    int temp_str1 = (int) Math.round((Double.parseDouble(temp_str[1].split(":")[0]) * 60 + Double.parseDouble(temp_str[1].split(":")[1])) / 30);
                                    work_plpt_data_list.add(temp_str1);
                                    List<Integer> temp_arraylist=new ArrayList<Integer>();
                                    work_plot_data_dic.put(temp_string, temp_arraylist);
                                    work_plot_data_dic.get(temp_string).add(temp_str1);
                                    last_time = df.parse(new_date_list.get(i));
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    else{
                        String temp_string = new_date_list.get(i).split("-")[0];
                        DateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
                        try {
                            last_time = df.parse(new_date_list.get(i));
                            String[] temp_str = new_date_list.get(i).split("-");
                            int temp_str1 = (int) Math.round((Double.parseDouble(temp_str[1].split(":")[0]) * 60 + Double.parseDouble(temp_str[1].split(":")[1])) / 30);
                            work_plpt_data_list.add(temp_str1);
                            List<Integer> temp_arraylist=new ArrayList<Integer>();
                            work_plot_data_dic.put(temp_string, temp_arraylist);
                            work_plot_data_dic.get(temp_string).add(temp_str1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }
                else{
                    if(i > 0){
                        String temp_string = new_date_list.get(i).split("-")[0];
                        Set<String> keySet = weekend_plot_data_dic.keySet();
                        int inKeyFlag = 0;
                        for(String key : keySet) {
                            if(key == temp_string){
                                inKeyFlag = 1;
                                DateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
                                try {
                                    Date temp_time = df.parse(new_date_list.get(i));
                                    long temp_msscond = temp_time.getTime() - last_time.getTime();
                                    long days = temp_msscond / (1000 * 60 * 60 * 24);
                                    long hours = (temp_msscond-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                                    long minutes = (temp_msscond-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                                    long minus_time = days * 24 * 60 + hours * 60 + minutes;
                                    if(minus_time > 30){
                                        String[] temp_str = new_date_list.get(i).split("-");
                                        int temp_str1 = (int) Math.round((Double.parseDouble(temp_str[1].split(":")[0]) * 60 + Double.parseDouble(temp_str[1].split(":")[1])) / 30);
                                        weekend_plpt_data_list.add(temp_str1);
                                        weekend_plot_data_dic.get(temp_string).add(temp_str1);
                                        last_time = df.parse(new_date_list.get(i));
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        if(inKeyFlag == 0){
                            DateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
                            try {
                                Date temp_time = df.parse(new_date_list.get(i));
                                long temp_msscond = temp_time.getTime() - last_time.getTime();
                                long days = temp_msscond / (1000 * 60 * 60 * 24);
                                long hours = (temp_msscond-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                                long minutes = (temp_msscond-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                                long minus_time = days * 24 * 60 + hours * 60 + minutes;
                                if(minus_time > 30){
                                    String[] temp_str = new_date_list.get(i).split("-");
                                    int temp_str1 = (int) Math.round((Double.parseDouble(temp_str[1].split(":")[0]) * 60 + Double.parseDouble(temp_str[1].split(":")[1])) / 30);
                                    weekend_plpt_data_list.add(temp_str1);
                                    List<Integer> temp_arraylist=new ArrayList<Integer>();
                                    weekend_plot_data_dic.put(temp_string, temp_arraylist);
                                    weekend_plot_data_dic.get(temp_string).add(temp_str1);
                                    last_time = df.parse(new_date_list.get(i));
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    else{
                        String temp_string = new_date_list.get(i).split("-")[0];
                        DateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
                        try {
                            last_time = df.parse(new_date_list.get(i));
                            String[] temp_str = new_date_list.get(i).split("-");
                            int temp_str1 = (int) Math.round((Double.parseDouble(temp_str[1].split(":")[0]) * 60 + Double.parseDouble(temp_str[1].split(":")[1])) / 30);
                            weekend_plpt_data_list.add(temp_str1);
                            List<Integer> temp_arraylist=new ArrayList<Integer>();
                            weekend_plot_data_dic.put(temp_string, temp_arraylist);
                            weekend_plot_data_dic.get(temp_string).add(temp_str1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }




            }
        }
        Map<Integer, Double> work_plot_data_list_new = new HashMap<Integer,Double>();
        Set<String> keySet = work_plot_data_dic.keySet();
        for(String key : keySet) {
            int temp_len = work_plot_data_dic.get(key).size();
            for(int j= 0 ; j < temp_len; j++){
                if(work_plot_data_list_new.containsKey(work_plot_data_dic.get(key).get(j))){
                    work_plot_data_list_new.put(work_plot_data_dic.get(key).get(j), work_plot_data_list_new.get(work_plot_data_dic.get(key).get(j)) + Double.valueOf(1/ temp_len));

                }
                else{
                    work_plot_data_list_new.put(work_plot_data_dic.get(key).get(j), Double.valueOf(1/ temp_len));
                }

            }


        }
        List<Integer> pd_y = new ArrayList<Integer>();
        List<Integer> work_plot_data = new ArrayList<Integer>(Collections.<Integer>nCopies(48,0));
        for(i = 0; i < 48; i++){
            if(work_plot_data_list_new.containsKey(i)){
                work_plot_data.set(i, (int) Math.round(work_plot_data_list_new.get(i) * 100));
                Double temp_100 = work_plot_data_list_new.get(i) * 100;
                for(int j = 0; j < Math.round(temp_100); j++){
                    pd_y.add(i);
                }

            }
        }

        Map<Integer, Double> weekend_plot_data_list_new = new HashMap<Integer,Double>();
        Set<String> keySet_wwekend = weekend_plot_data_dic.keySet();
        for(String key : keySet_wwekend) {
            int temp_len = weekend_plot_data_dic.get(key).size();
            for(int j= 0 ; j < temp_len; j++){
                if(weekend_plot_data_list_new.containsKey(weekend_plot_data_dic.get(key).get(j))){
                    weekend_plot_data_list_new.put(weekend_plot_data_dic.get(key).get(j), weekend_plot_data_list_new.get(weekend_plot_data_dic.get(key).get(j)) + Double.valueOf(1/ temp_len));

                }
                else{
                    weekend_plot_data_list_new.put(weekend_plot_data_dic.get(key).get(j), Double.valueOf(1/ temp_len));
                }

            }


        }
        List<Integer> pd_weekend = new ArrayList<Integer>();
        List<Integer> weekend_plot_data = new ArrayList<Integer>(Collections.<Integer>nCopies(48,0));
        for(i = 0; i < 48; i++){
            if(weekend_plot_data_list_new.containsKey(i)){
                weekend_plot_data.set(i, (int) Math.round(weekend_plot_data_list_new.get(i) * 100));
                Double temp_100 = weekend_plot_data_list_new.get(i) * 100;
                for(int j = 0; j < Math.round(temp_100); j++){
                    pd_weekend.add(i);
                }

            }
        }
        List<Integer> new_pd_y = new ArrayList<Integer>();
        double [] sample = new double[pd_y.size()];
        for(i =0; i < pd_y.size(); i++){
            sample[i] = pd_y.get(i);
        }

        if(sample.length <= 2){
            for(int k = 0; k < 48; k++){
                new_pd_y.add(0);
            }
        }

        KernelDensity kd = new KernelDensity(sample,1);

        for(int k =0; k < 48; k++){
            new_pd_y.add((int)Math.ceil(kd.p(k)*100));
        }
        //System.out.println(kd.cdf(1));

        double [] sample_weekend = new double[pd_weekend.size()];
        for(i =0; i < pd_weekend.size(); i++){
            sample_weekend[i] = pd_weekend.get(i);
        }
        List<Integer> new_pd_weekend = new ArrayList<Integer>();
        if(sample_weekend.length <= 2){
            for(int k = 0; k < 48; k++){
                new_pd_weekend.add(0);
            }
        }
        else{
            KernelDensity kd_weekend = new KernelDensity(sample_weekend,1);
            //List<Integer> new_pd_weekend = new ArrayList<Integer>();
            for(int k =0; k < 48; k++){
                new_pd_weekend.add((int)Math.ceil(kd_weekend.p(k)*100));
            }
        }


//        List<Integer> new_pd_y1 = new ArrayList<Integer>();
//        for(int k= 0; k < work_plot_data.size(); k++){
//            new_pd_y1.add(work_plot_data.get(k));
//        }



        Map<String, List<Integer>> result_map = new HashMap<String,List<Integer>>();
        result_map.put("workday", new_pd_y);
        result_map.put("weekend", new_pd_weekend);

        return result_map;
    }

    public static String getDataHeatmap(List<String> new_date_list) {
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len -1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
        if(Integer.valueOf(hour_list) < 3){
            current_day_list.add(current_day);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date temp_time = df.parse(current_day);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(temp_time);
                calendar.add(Calendar.DATE, 1);
                String temp_day = df.format(calendar.getTime());
                current_day_list.add(temp_day);


            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        else{
            current_day_list.add(current_day);
        }
        List<Double> time_list = new ArrayList<Double>();
        for(int i = 0; i < new_date_list.size(); i++){
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if(current_day_list.size() > 1){
                if(temp_current_day.equals(current_day_list.get(1))){
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
                else if(temp_current_day.equals(current_day_list.get(0))){
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            }
            else{
                if(temp_current_day.equals(current_day_list.get(0))){
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        int start_hour = (int)Math.floor(input_current_time);
        if(input_current_time % 1 >= 0.5){
            input_current_time = Math.floor(input_current_time) + 0.5;
        }
        else{
            input_current_time = Math.floor(input_current_time);
            start_flag = 1;
        }

        Double limit_no12;
        Double limit_8;
        Double limit_10;
        Double limit_12;
        if(input_current_time >= 17){
            if(first_time > 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= first_time + 12){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else{
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                limit_no12 = input_current_time + 12;
                if(limit_12 >= limit_no12){
                    limit_no12 = limit_12;
                }
            }
        }
        else{
            if(first_time > 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= first_time + 12){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else{
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                limit_no12 = Math.min(limit_12 + 12, 22 + 12);
            }


        }
        limit_8 -= 1;
        limit_10 -= 1;
        limit_12 -= 1;
        limit_no12 -= 1;
        for(int i=0; i < 48; i++){
            Double temp_input_current_time = Math.ceil(input_current_time) + i / 2;
            if(temp_input_current_time < limit_8 && temp_input_current_time < 22){
                time_level.add(4);
            }
            else if(temp_input_current_time < limit_10 && temp_input_current_time < 22){
                time_level.add(3);
            }
            else if(temp_input_current_time < limit_12 && temp_input_current_time < 22){
                time_level.add(2);
            }
            else if(temp_input_current_time < limit_no12){
                time_level.add(1);
            }
            else if(temp_input_current_time >= limit_no12){
                if(temp_input_current_time < limit_8){
                    time_level.add(4);
                }
                else if(temp_input_current_time < limit_10){
                    time_level.add(3);
                }
                else if(temp_input_current_time < limit_12){
                    time_level.add(2);
                }
                else if(temp_input_current_time < limit_no12 + 8){
                    time_level.add(4);
                }
                else if(temp_input_current_time < limit_no12 + 10){
                    time_level.add(3);
                }
                else if(temp_input_current_time < limit_no12 + 12){
                    time_level.add(2);
                }
                else if(temp_input_current_time < limit_no12 + 24){
                    time_level.add(1);
                }
            }
            else{
                time_level.add(1);
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();
        String out_string = "";
        for(int i = 0; i < 48; i++){
            if(time_level.get(i) == 1){
                out_string += "[" + "0" + "," + String.valueOf(i) +   "," + "1" + "]" + ",";
            }
            else if(time_level.get(i) == 2){
                out_string += "[" + "1" + "," + String.valueOf(i)   + "," + "2" + "]" + ",";
                out_string += "["+ "0"  + "," + String.valueOf(i) + ","  + "2" + "]" + ",";
            }
            else if(time_level.get(i) == 3){
                out_string += "[" + "2"  + "," + String.valueOf(i) + ","  + "3" + "]" + ",";
                out_string += "[" + "1"  + "," + String.valueOf(i) + ","  + "3" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + ","   + "3" + "]" + ",";
            }
            else if(time_level.get(i) == 4){
                out_string += "[" + "3" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
            }
        }
        out_string += "&";
        if(start_flag == 1){
            for(int i =0; i < 25; i++){
                int temp_time = start_hour + i - 1;
                int temp_time1 = start_hour + i;
                if(temp_time >= 24){
                    temp_time -= 24;
                }
                if(temp_time1 >= 24){
                    temp_time1 -= 24;
                }
                if(i == 0){
                    out_string += "'现在'" + ",";
                }
                else{
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                }
            }
        }
        else{
            for(int i =0; i < 25; i++){
                int temp_time = start_hour + i;
                if(temp_time >= 24){
                    temp_time -= 24;
                }
                if(i == 0){
                    out_string += "'现在',";
                }
                else{
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                }
            }
        }

        return out_string;

    }


    public static String getCircledata(List<String> new_date_list) {
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len -1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
        if(Integer.valueOf(hour_list) < 3){
            current_day_list.add(current_day);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date temp_time = df.parse(current_day);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(temp_time);
                calendar.add(Calendar.DATE, 1);
                String temp_day = df.format(calendar.getTime());
                current_day_list.add(temp_day);


            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        else{
            current_day_list.add(current_day);
        }
        List<Double> time_list = new ArrayList<Double>();
        for(int i = 0; i < new_date_list.size(); i++){
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if(current_day_list.size() > 1){
                if(temp_current_day.equals(current_day_list.get(1))){
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
                else if(temp_current_day.equals(current_day_list.get(0))){
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            }
            else{
                if(temp_current_day.equals(current_day_list.get(0))){
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        int start_hour = (int)Math.floor(input_current_time);
        if(input_current_time % 1 >= 0.5){
            input_current_time = Math.floor(input_current_time) + 0.5;
        }
        else{
            input_current_time = Math.floor(input_current_time);
            start_flag = 1;
        }

        Double limit_no12;
        Double limit_8;
        Double limit_10;
        Double limit_12;
        if(input_current_time >= 17){
            if(first_time > 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= first_time + 12){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else{
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                limit_no12 = input_current_time + 12;
                if(limit_12 >= limit_no12){
                    limit_no12 = limit_12;
                }
            }
        }
        else{
            if(first_time > 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= 22){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else if(input_current_time >= first_time + 12){
                limit_no12 = input_current_time + 12;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }
            else{
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                limit_no12 = Math.min(limit_12 + 12, 22 + 12);
            }


        }

        List<String> color_list = new ArrayList<String>();

        for(int i=0; i < 48; i++){
            Double temp_input_current_time = Math.ceil(input_current_time) + i / 2;
            if(temp_input_current_time < limit_8 && temp_input_current_time < 22){
                time_level.add(4);
                color_list.add("'#C9451E'");
            }
            else if(temp_input_current_time < limit_10 && temp_input_current_time < 22){
                time_level.add(3);
                color_list.add("'#FFB99B'");
            }
            else if(temp_input_current_time < limit_12 && temp_input_current_time < 22){
                time_level.add(2);
                color_list.add("'#D4A68D'");
            }
            else if(temp_input_current_time < limit_no12){
                time_level.add(1);
                color_list.add("'#3F4342'");
            }
            else if(temp_input_current_time >= limit_no12){
                if(temp_input_current_time < limit_8){
                    time_level.add(4);
                    color_list.add("'#C9451E'");
                }
                else if(temp_input_current_time < limit_10){
                    time_level.add(3);
                    color_list.add("'#FFB99B'");
                }
                else if(temp_input_current_time < limit_12){
                    time_level.add(2);
                    color_list.add("'#D4A68D'");
                }
                else if(temp_input_current_time < limit_no12 + 8){
                    time_level.add(4);
                    color_list.add("'#C9451E'");
                }
                else if(temp_input_current_time < limit_no12 + 10){
                    time_level.add(3);
                    color_list.add("'#FFB99B'");
                }
                else if(temp_input_current_time < limit_no12 + 12){
                    time_level.add(2);
                    color_list.add("'#D4A68D'");
                }
                else if(temp_input_current_time < limit_no12 + 24){
                    time_level.add(1);
                    color_list.add("'#3F4342'");
                }
            }
            else{
                time_level.add(1);
                color_list.add("'#3F4342'");
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();
        String out_string = "";
//        for(int i = 0; i < 48; i++){
//            if(time_level.get(i) == 1){
//                out_string += "[" + "0" + "," + String.valueOf(i) +   "," + "1" + "]" + ",";
//            }
//            else if(time_level.get(i) == 2){
//                out_string += "[" + "1" + "," + String.valueOf(i)   + "," + "2" + "]" + ",";
//                out_string += "["+ "0"  + "," + String.valueOf(i) + ","  + "2" + "]" + ",";
//            }
//            else if(time_level.get(i) == 3){
//                out_string += "[" + "2"  + "," + String.valueOf(i) + ","  + "3" + "]" + ",";
//                out_string += "[" + "1"  + "," + String.valueOf(i) + ","  + "3" + "]" + ",";
//                out_string += "[" + "0" + "," + String.valueOf(i) + ","   + "3" + "]" + ",";
//            }
//            else if(time_level.get(i) == 4){
//                out_string += "[" + "3" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
//                out_string += "[" + "2" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
//                out_string += "[" + "1" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
//                out_string += "[" + "0" + "," + String.valueOf(i) + ","   + "4" + "]" + ",";
//            }
//        }
        List<String> time_name = new ArrayList<String>();

        out_string += "&";
        if(start_flag == 1){
            for(int i =0; i < 25; i++){
                int temp_time = start_hour + i - 1;
                int temp_time1 = start_hour + i;
                if(temp_time >= 24){
                    temp_time -= 24;
                }
                if(temp_time1 >= 24){
                    temp_time1 -= 24;
                }
                if(i == 0){
                    out_string += "'现在'" + ",";
                    time_name.add("'现在'");
                }
                else{
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                    time_name.add("'" + String.valueOf(temp_time) + ":" + "30'");
                    time_name.add("'" + String.valueOf(temp_time1) + ":" + "00'");
                }
            }
        }
        else{
            for(int i =0; i < 25; i++){
                int temp_time = start_hour + i;
                if(temp_time >= 24){
                    temp_time -= 24;
                }
                if(i == 0){
                    out_string += "'现在',";
                    time_name.add("'现在'");
                }
                else{
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    time_name.add("'" + String.valueOf(temp_time) + ":" + "00'");
                    time_name.add("'" + String.valueOf(temp_time) + ":" + "30'");
                }
            }
        }

        String new_out_string = "[";
        for(int i = 0; i < 47; i++){
            new_out_string += "{" + "value:" + String.valueOf(time_level.get(i)) + "," +
                    "name:" + time_name.get(i) + "," + "itemStyle:{normal:{color:" + color_list.get(i) + ",}}},";
        }
        new_out_string += "]";


        return new_out_string;

    }















//    public static String getDataHeatmapType(List<String> new_date_list, String diet_type, String first_diet_time, int is_now_flag, int is_next_flag, String current_time) {
//        diet_type = "no";
//        String[] diet_6_8_10_12 = {"A", "no"};
//        String[] diet_12_12 = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
//        String[] diet_warrior = {"Z"};
//        String[] diet_spontaneous = {"A1", "A2"};
//        List<String> diet_6_8_10_12_list = Arrays.asList(diet_6_8_10_12);
//        List<String> diet_12_12_list = Arrays.asList(diet_12_12);
//        List<String> diet_warrior_list = Arrays.asList(diet_warrior);
//        List<String> diet_spontaneous_list = Arrays.asList(diet_spontaneous);
//        String out_string = "";
//        String first_input_current_time = new_date_list.get(new_date_list.size() - 1);
//        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
//        Date fromDate2 = null;
//        Date toDate2 = null;
//
//
//
//
//
////        String[] temp21 = first_input_current_time.split("-")[1].split(":");
////        Double input_hour1 = Double.valueOf(temp21[0]) + (Double.valueOf(temp21[1]) / 60);
////        if (input_hour1 % 1 >= 0.5) {
////            input_hour1 = Math.floor(input_hour1) + 0.5;
////        } else {
////            input_hour1 = Math.floor(input_hour1);
////        }
////
////        String[] temp22 = current_time.split("-")[1].split(":");
////        Double input_hour2 = Double.valueOf(temp22[0]) + (Double.valueOf(temp22[1]) / 60);
////        if (input_hour2 % 1 >= 0.5) {
////            input_hour2 = Math.floor(input_hour2) + 0.5;
////        } else {
////            input_hour2 = Math.floor(input_hour2);
////        }
//
////        int start_index = (int)((input_hour2 - input_hour1) * 2);
////        while(start_index >=48){
////            start_index -= 48;
////        }
//
//        String[] temp21 = first_input_current_time.split("-")[1].split(":");
//        String temp_hour = temp21[0];
//        String temp_minute = temp21[1];
//        if(Integer.valueOf(temp_minute) < 30){
//            temp_minute = "00";
//        }else{
//            temp_minute = "30";
//        }
//        String new_first_input_current_time = first_input_current_time.split("-")[0] + "-" + temp_hour + ":" + temp_minute + ":" + "00";
//
//        String[] temp22 = current_time.split("-")[1].split(":");
//        String temp_hour1 = temp22[0];
//        String temp_minute1 = temp22[1];
//        if(Integer.valueOf(temp_minute1) < 30){
//            temp_minute1 = "00";
//        }else{
//            temp_minute1 = "30";
//        }
//        String new_current_time = current_time.split("-")[0] + "-" + temp_hour1 + ":" + temp_minute1 + ":" + "00";
//
//
//
//
//        try {
//            fromDate2 = simpleFormat.parse(new_first_input_current_time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            toDate2 = simpleFormat.parse(new_current_time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long from2 = fromDate2.getTime();
//        long to2 = toDate2.getTime();
//        int from_to_minutes = (int) ((to2 - from2) / (1000 * 60));
//        double to_from_hour = Double.valueOf(from_to_minutes) / 60;
//        int start_index = (int) (to_from_hour * 2);
//        while(start_index >=48){
//            start_index -= 48;
//        }
//
//
//
//
//
//
//
//
//
////        if(to_from_hour % 1 > 0.5){
////            to_from_hour = Math.floor(to_from_hour) + 0.5;
////        }else{
////            to_from_hour = Math.floor(to_from_hour);
////        }
//
//
//        if (diet_6_8_10_12_list.contains(diet_type)) {
//            int len = new_date_list.size();
//            String temp_current_time = new_date_list.get(len - 1);
//            String[] temp = temp_current_time.split("-");
//            String current_day = temp[0];
//            String current_hour = temp[1];
//            temp = current_hour.split(":");
//            String hour_list = temp[0];
//            String minute_list = temp[1];
//            String past_time_minute = current_hour.split(":")[1];
//            String past_time_hour = current_hour.split(":")[0];
//            List<String> current_day_list = new ArrayList<String>();
//            if (Integer.valueOf(hour_list) < 3) {
//                current_day_list.add(current_day);
//                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//                try {
//                    Date temp_time = df.parse(current_day);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(temp_time);
//                    calendar.add(Calendar.DATE, -1);
//                    String temp_day = df.format(calendar.getTime());
//                    current_day_list.add(temp_day);
//
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                current_day_list.add(current_day);
//            }
//            List<Double> time_list = new ArrayList<Double>();
//            for (int i = 0; i < new_date_list.size(); i++) {
//                String temp_current_day = new_date_list.get(i).split("-")[0];
//                String temp_current_hour = new_date_list.get(i).split("-")[1];
//                if (current_day_list.size() > 1) {
//                    if (temp_current_day.equals(current_day_list.get(1))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    } else if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
//                        time_list.add(input_hour);
//                    }
//
//                } else {
//                    if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    }
//                }
//            }
//
//            List<Integer> time_level = new ArrayList<Integer>();
//            Double first_time = 0.0;
//            while(first_time < 3){
//                first_time = time_list.get(0);
//                if(first_time < 3){
//                    time_list.remove(0);
//                }
//            }
//
//            Double input_current_time = time_list.get(time_list.size() - 1);
//            int start_flag = 0;
//            int start_hour = (int) Math.floor(input_current_time);
//            if (input_current_time % 1 >= 0.5) {
//                input_current_time = Math.floor(input_current_time) + 0.5;
//            } else {
//                input_current_time = Math.floor(input_current_time);
//                start_flag = 1;
//            }
//
//            if(start_index % 2 == 1){
//                if(start_flag == 1){
//                    start_flag = 0;
//                }else{
//                    start_flag = 1;
//                }
//            }
//
//            if (first_time % 1 >= 0.5) {
//                first_time = Math.floor(first_time) + 0.5;
//            } else {
//                first_time = Math.floor(first_time);
//            }
//
//            Double limit_no12;
//            Double limit_6;
//            Double limit_8;
//            Double limit_10;
//            Double limit_12;
//            if (input_current_time >= 17) {
//                if (first_time > 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else {
//                    limit_6 = first_time + 6;
//                    limit_8 = first_time + 8;
//                    limit_10 = first_time + 10;
//                    limit_12 = first_time + 12;
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = input_current_time + 12;
//                    if (limit_12 >= limit_no12) {
//                        limit_no12 = limit_12;
//                    }
//                }
//            } else {
//                if (first_time > 22) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                }else {
//                    limit_6 = first_time + 6;
//                    limit_8 = first_time + 8;
//                    limit_10 = first_time + 10;
//                    limit_12 = first_time + 12;
//                    Double temp_limit_no12 = Math.min(limit_12 + 12, 22 + 12);
//                    limit_no12 = temp_limit_no12;
//                }
//
//
//            }
//
//            for (int i = 0; i < 96; i++) {
//                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
//                if(limit_no12 < limit_6 && temp_input_current_time < limit_no12){
//                    time_level.add(1);
//                }
//                else if (temp_input_current_time < limit_6 && temp_input_current_time < 22) {
//                    time_level.add(5);
//                }
//                else if (temp_input_current_time < limit_8 && temp_input_current_time < 22) {
//                    time_level.add(4);
//                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 22) {
//                    time_level.add(3);
//                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
//                    time_level.add(2);
//                } else if (temp_input_current_time < limit_no12) {
//                    time_level.add(1);
//                } else if (temp_input_current_time >= limit_no12) {
//                    if (temp_input_current_time < limit_6 && temp_input_current_time < 22 + 24) {
//                        time_level.add(5);
//                    }
//                    else if (temp_input_current_time < limit_8 && temp_input_current_time < 22 + 24) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_10 && temp_input_current_time < 22 + 24) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_12 && temp_input_current_time < 22 + 24) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < limit_no12 + 6 && temp_input_current_time < 22 + 24) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 8 && temp_input_current_time < 22 + 24) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_no12 + 10 && temp_input_current_time < 22 + 24) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_no12 + 12 && temp_input_current_time < 22 + 24) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 22 + 24 + 12)) {
//                        time_level.add(1);
//                    }else if(temp_input_current_time < Math.min(limit_no12 + 24, 22 + 24 + 12) + 6 ){
//                        time_level.add(5);
//                    }else if (temp_input_current_time < Math.min(limit_no12 + 24, 22 + 24 + 12) + 8 ) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 22 + 24 + 12) + 10 ) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 22 + 24 + 12) + 12 ) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 22 + 24 + 12) + 24) {
//                        time_level.add(1);
//                    }
//
//                } else {
//                    time_level.add(1);
//                }
//            }
//
//
//            List<Integer> test_data = new ArrayList<Integer>();
//
//            for (int i = start_index; i < start_index + 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i- start_index) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                }
//            }
//            out_string += "&";
//            if (start_flag == 1) {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2) - 1;
//                    int temp_time1 = start_hour + i + (int)Math.ceil((double)start_index / 2);
//                    while(temp_time >= 24){
//                        temp_time -= 24;
//                    }
//                    while(temp_time1 >= 24){
//                        temp_time1 -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在'" + ",";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
//                    }
//                }
//            } else {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i + (int)Math.floor((double)start_index / 2);
//                    while(temp_time >= 24){
//                        temp_time -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在',";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                    }
//                }
//            }
//
//
//        }
//
//
//
//
////        if (diet_6_8_10_12_list.contains(diet_type)) {
////            int len = new_date_list.size();
////            String temp_current_time = new_date_list.get(len - 1);
////            String[] temp = temp_current_time.split("-");
////            String current_day = temp[0];
////            String current_hour = temp[1];
////            temp = current_hour.split(":");
////            String hour_list = temp[0];
////            String minute_list = temp[1];
////            String past_time_minute = current_hour.split(":")[1];
////            String past_time_hour = current_hour.split(":")[0];
////            List<String> current_day_list = new ArrayList<String>();
////            if (Integer.valueOf(hour_list) < 3) {
////                current_day_list.add(current_day);
////                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
////                try {
////                    Date temp_time = df.parse(current_day);
////                    Calendar calendar = Calendar.getInstance();
////                    calendar.setTime(temp_time);
////                    calendar.add(Calendar.DATE, 1);
////                    String temp_day = df.format(calendar.getTime());
////                    current_day_list.add(temp_day);
////
////
////                } catch (ParseException e) {
////                    e.printStackTrace();
////                }
////
////
////            } else {
////                current_day_list.add(current_day);
////            }
////            List<Double> time_list = new ArrayList<Double>();
////            for (int i = 0; i < new_date_list.size(); i++) {
////                String temp_current_day = new_date_list.get(i).split("-")[0];
////                String temp_current_hour = new_date_list.get(i).split("-")[1];
////                if (current_day_list.size() > 1) {
////                    if (temp_current_day.equals(current_day_list.get(1))) {
////                        String[] temp2 = temp_current_hour.split(":");
////                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
////                        time_list.add(input_hour);
////                    } else if (temp_current_day.equals(current_day_list.get(0))) {
////                        String[] temp2 = temp_current_hour.split(":");
////                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
////                        time_list.add(input_hour);
////                    }
////
////                } else {
////                    if (temp_current_day.equals(current_day_list.get(0))) {
////                        String[] temp2 = temp_current_hour.split(":");
////                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
////                        time_list.add(input_hour);
////                    }
////                }
////            }
////
////            List<Integer> time_level = new ArrayList<Integer>();
////            Double first_time = time_list.get(0);
////            Double input_current_time = time_list.get(time_list.size() - 1);
////            int start_flag = 0;
////            int start_hour = (int) Math.floor(input_current_time);
////            if (input_current_time % 1 >= 0.5) {
////                input_current_time = Math.floor(input_current_time) + 0.5;
////            } else {
////                input_current_time = Math.floor(input_current_time);
////                start_flag = 1;
////            }
////
////            if(start_index % 2 == 1){
////                if(start_flag == 1){
////                    start_flag = 0;
////                }else{
////                    start_flag = 1;
////                }
////            }
////
////            if (first_time % 1 >= 0.5) {
////                first_time = Math.floor(first_time) + 0.5;
////            } else {
////                first_time = Math.floor(first_time);
////            }
////
////            Double limit_no12;
////            Double limit_6;
////            Double limit_8;
////            Double limit_10;
////            Double limit_12;
////            if (input_current_time >= 17) {
////                if (first_time > 22) {
////                    //limit_no12 = input_current_time + 12;
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    limit_6 = limit_no12 + 6;
////                    limit_8 = limit_no12 + 8;
////                    limit_10 = limit_no12 + 10;
////                    limit_12 = limit_no12 + 12;
////                } else if (input_current_time >= 22) {
////                    //limit_no12 = input_current_time + 12;
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    limit_6 = limit_no12 + 6;
////                    limit_8 = limit_no12 + 8;
////                    limit_10 = limit_no12 + 10;
////                    limit_12 = limit_no12 + 12;
////                } else if (input_current_time >= first_time + 12) {
////                    //limit_no12 = input_current_time + 12;
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    limit_6 = limit_no12 + 6;
////                    limit_8 = limit_no12 + 8;
////                    limit_10 = limit_no12 + 10;
////                    limit_12 = limit_no12 + 12;
////                } else {
////                    limit_6 = first_time + 6;
////                    limit_8 = first_time + 8;
////                    limit_10 = first_time + 10;
////                    limit_12 = first_time + 12;
////                    //limit_no12 = input_current_time + 12;
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    if (limit_12 >= limit_no12) {
////                        limit_no12 = limit_12;
////                    }
////                }
////            } else {
////                if (first_time > 22) {
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    limit_6 = limit_no12 + 6;
////                    limit_8 = limit_no12 + 8;
////                    limit_10 = limit_no12 + 10;
////                    limit_12 = limit_no12 + 12;
////                } else if (input_current_time >= 22) {
////                    //limit_no12 = input_current_time + 12;
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    limit_6 = limit_no12 + 6;
////                    limit_8 = limit_no12 + 8;
////                    limit_10 = limit_no12 + 10;
////                    limit_12 = limit_no12 + 12;
////                } else if (input_current_time >= first_time + 12) {
////                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
////                    limit_6 = limit_no12 + 6;
////                    limit_8 = limit_no12 + 8;
////                    limit_10 = limit_no12 + 10;
////                    limit_12 = limit_no12 + 12;
////                } else {
////                    limit_6 = first_time + 6;
////                    limit_8 = first_time + 8;
////                    limit_10 = first_time + 10;
////                    limit_12 = first_time + 12;
////                    Double temp_limit_no12 = Math.min(limit_12 + 12, 22 + 12);
////                    limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
////                }
////
////
////            }
////
////            for (int i = 0; i < 96; i++) {
////                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
////                if(limit_no12 < limit_6 && temp_input_current_time < limit_no12){
////                        time_level.add(1);
////                }
////                else if (temp_input_current_time < limit_6 && temp_input_current_time < 22) {
////                    time_level.add(5);
////                }
////                else if (temp_input_current_time < limit_8 && temp_input_current_time < 22) {
////                    time_level.add(4);
////                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 22) {
////                    time_level.add(3);
////                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
////                    time_level.add(2);
////                } else if (temp_input_current_time < limit_no12) {
////                    time_level.add(1);
////                } else if (temp_input_current_time >= limit_no12) {
////                    if (temp_input_current_time < limit_6) {
////                        time_level.add(5);
////                    }
////                    else if (temp_input_current_time < limit_8) {
////                        time_level.add(4);
////                    } else if (temp_input_current_time < limit_10) {
////                        time_level.add(3);
////                    } else if (temp_input_current_time < limit_12) {
////                        time_level.add(2);
////                    } else if (temp_input_current_time < limit_no12 + 6 && temp_input_current_time < 22 + 48) {
////                        time_level.add(5);
////                    } else if (temp_input_current_time < limit_no12 + 8 && temp_input_current_time < 22 + 48) {
////                        time_level.add(4);
////                    } else if (temp_input_current_time < limit_no12 + 10 && temp_input_current_time < 22 + 48) {
////                        time_level.add(3);
////                    } else if (temp_input_current_time < limit_no12 + 12 && temp_input_current_time < 22 + 48) {
////                        time_level.add(2);
////                    } else if (temp_input_current_time < limit_no12 + 24) {
////                        time_level.add(1);
////                    }else if(temp_input_current_time < limit_no12 + 24 + 6 ){
////                        time_level.add(5);
////                    }else if (temp_input_current_time < limit_no12 + 24 + 8 ) {
////                        time_level.add(4);
////                    } else if (temp_input_current_time < limit_no12 + 24 + 10 ) {
////                        time_level.add(3);
////                    } else if (temp_input_current_time < limit_no12 + 24 + 12 ) {
////                        time_level.add(2);
////                    } else if (temp_input_current_time < limit_no12 + 24 + 24) {
////                        time_level.add(1);
////                    }
////
////                } else {
////                    time_level.add(1);
////                }
////            }
////
////
////            List<Integer> test_data = new ArrayList<Integer>();
////
////            for (int i = start_index; i < start_index + 48; i++) {
////                if (time_level.get(i) == 1) {
////                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "1" + "]" + ",";
////                } else if (time_level.get(i) == 2) {
////                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
////                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
////                } else if (time_level.get(i) == 3) {
////                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
////                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
////                    out_string += "[" + "0" + "," + String.valueOf(i- start_index) + "," + "3" + "]" + ",";
////                } else if (time_level.get(i) == 4) {
////                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
////                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
////                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
////                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
////                } else if (time_level.get(i) == 5) {
////                    out_string += "[" + "4" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
////                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
////                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
////                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
////                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
////                }
////            }
////            out_string += "&";
////            if (start_flag == 1) {
////                for (int i = 0; i < 25; i++) {
////                    int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2) - 1;
////                    int temp_time1 = start_hour + i + (int)Math.ceil((double)start_index / 2);
////                    while(temp_time >= 24){
////                        temp_time -= 24;
////                    }
////                    while(temp_time1 >= 24){
////                        temp_time1 -= 24;
////                    }
////                    if (i == 0) {
////                        out_string += "'现在'" + ",";
////                    } else {
////                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
////                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
////                    }
////                }
////            } else {
////                for (int i = 0; i < 25; i++) {
////                    int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2);
////                    while(temp_time >= 24){
////                        temp_time -= 24;
////                    }
////                    if (i == 0) {
////                        out_string += "'现在',";
////                    } else {
////                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
////                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
////                    }
////                }
////            }
////
////
////        }
//        else if (diet_12_12_list.contains(diet_type)) {
//            int len = new_date_list.size();
//            String temp_current_time = new_date_list.get(len - 1);
//            String[] temp = temp_current_time.split("-");
//            String current_day = temp[0];
//            String current_hour = temp[1];
//            temp = current_hour.split(":");
//            String hour_list = temp[0];
//            String minute_list = temp[1];
//            String past_time_minute = current_hour.split(":")[1];
//            String past_time_hour = current_hour.split(":")[0];
//            List<String> current_day_list = new ArrayList<String>();
//            if (Integer.valueOf(hour_list) < 3) {
//                current_day_list.add(current_day);
//                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//                try {
//                    Date temp_time = df.parse(current_day);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(temp_time);
//                    calendar.add(Calendar.DATE, 1);
//                    String temp_day = df.format(calendar.getTime());
//                    current_day_list.add(temp_day);
//
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                current_day_list.add(current_day);
//            }
//            List<Double> time_list = new ArrayList<Double>();
//            for (int i = 0; i < new_date_list.size(); i++) {
//                String temp_current_day = new_date_list.get(i).split("-")[0];
//                String temp_current_hour = new_date_list.get(i).split("-")[1];
//                if (current_day_list.size() > 1) {
//                    if (temp_current_day.equals(current_day_list.get(1))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    } else if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
//                        time_list.add(input_hour);
//                    }
//
//                } else {
//                    if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    }
//                }
//            }
//
//            List<Integer> time_level = new ArrayList<Integer>();
//            Double first_time = time_list.get(0);
//            Double input_current_time = time_list.get(time_list.size() - 1);
//            int start_flag = 0;
//            int start_hour = (int) Math.floor(input_current_time);
//            if (input_current_time % 1 >= 0.5) {
//                input_current_time = Math.floor(input_current_time) + 0.5;
//            } else {
//                input_current_time = Math.floor(input_current_time);
//                start_flag = 1;
//            }
//            if(start_index % 2 == 1){
//                if(start_flag == 1){
//                    start_flag = 0;
//                }else{
//                    start_flag = 1;
//                }
//            }
//
//            if (first_time % 1 >= 0.5) {
//                first_time = Math.floor(first_time) + 0.5;
//            } else {
//                first_time = Math.floor(first_time);
//            }
//
//            Double limit_no12;
//            Double limit_6;
//            Double limit_8;
//            Double limit_10;
//            Double limit_12;
//            if (input_current_time >= 17) {
//                if (first_time > 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//
//                    limit_12 = limit_no12 + 12;
//                } else {
//
//                    limit_12 = first_time + 12;
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    if (limit_12 >= limit_no12) {
//                        limit_no12 = limit_12;
//                    }
//                }
//            } else {
//                if (first_time > 22) {
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//
//                    limit_12 = limit_no12 + 12;
//                } else {
//
//                    limit_12 = first_time + 12;
//                    Double temp_limit_no12 = Math.min(limit_12 + 12, 22 + 12);
//                    limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
//                }
//
//
//            }
//
//            for (int i = 0; i < 48; i++) {
//                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
//                if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
//                    time_level.add(5);
//                } else if (temp_input_current_time < limit_no12) {
//                    time_level.add(1);
//                } else if (temp_input_current_time >= limit_no12) {
//                    if (temp_input_current_time < limit_12) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 12) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 24) {
//                        time_level.add(1);
//                    }
//                } else {
//                    time_level.add(1);
//                }
//            }
//
//
//            List<Integer> test_data = new ArrayList<Integer>();
//
//            for (int i = 0; i < 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                }
//            }
//            out_string += "&";
//            if (start_flag == 1) {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i - 1;
//                    int temp_time1 = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (temp_time1 >= 24) {
//                        temp_time1 -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在'" + ",";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
//                    }
//                }
//            } else {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在',";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                    }
//                }
//            }
//
//        }
//        else if(diet_warrior_list.contains(diet_type)){
//            int len = new_date_list.size();
//            String temp_current_time = new_date_list.get(len - 1);
//            String[] temp = temp_current_time.split("-");
//            String current_day = temp[0];
//            String current_hour = temp[1];
//            temp = current_hour.split(":");
//            String hour_list = temp[0];
//            String minute_list = temp[1];
//            String past_time_minute = current_hour.split(":")[1];
//            String past_time_hour = current_hour.split(":")[0];
//            List<String> current_day_list = new ArrayList<String>();
//            if (Integer.valueOf(hour_list) < 3) {
//                current_day_list.add(current_day);
//                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//                try {
//                    Date temp_time = df.parse(current_day);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(temp_time);
//                    calendar.add(Calendar.DATE, 1);
//                    String temp_day = df.format(calendar.getTime());
//                    current_day_list.add(temp_day);
//
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                current_day_list.add(current_day);
//            }
//            List<Double> time_list = new ArrayList<Double>();
//            for (int i = 0; i < new_date_list.size(); i++) {
//                String temp_current_day = new_date_list.get(i).split("-")[0];
//                String temp_current_hour = new_date_list.get(i).split("-")[1];
//                if (current_day_list.size() > 1) {
//                    if (temp_current_day.equals(current_day_list.get(1))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    } else if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
//                        time_list.add(input_hour);
//                    }
//
//                } else {
//                    if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    }
//                }
//            }
//
//            List<Integer> time_level = new ArrayList<Integer>();
//            Double first_time = time_list.get(0);
//            Double input_current_time = time_list.get(time_list.size() - 1);
//            int start_flag = 0;
//            int start_hour = (int) Math.floor(input_current_time);
//            if (input_current_time % 1 >= 0.5) {
//                input_current_time = Math.floor(input_current_time) + 0.5;
//            } else {
//                input_current_time = Math.floor(input_current_time);
//                start_flag = 1;
//            }
//
//            if (first_time % 1 >= 0.5) {
//                first_time = Math.floor(first_time) + 0.5;
//            } else {
//                first_time = Math.floor(first_time);
//            }
//
//            Double limit_no12;
//            Double limit_12;
//
//            if (first_time > 22) {
//                limit_no12 = Math.max(input_current_time + 20, Double.valueOf(first_diet_time));
//                //limit_no12 = input_current_time + 4;
//
//                limit_12 = limit_no12 + 4;
//            } else if (input_current_time >= 22) {
//                //limit_no12 = input_current_time + 20;
//                limit_no12 = Math.max(input_current_time + 20, Double.valueOf(first_diet_time));
//
//                limit_12 = limit_no12 + 4;
//            } else if (input_current_time >= first_time + 4) {
//                //limit_no12 = input_current_time + 20;
//                limit_no12 = Math.max(input_current_time + 20, Double.valueOf(first_diet_time));
//
//                limit_12 = limit_no12 + 4;
//            } else {
//
//                limit_12 = first_time + 4;
//                //limit_no12 = Math.min(limit_12 + 20, 22 + 20);
//                Double temp_limit_no12 = Math.min(limit_12 + 20, 22 + 20);
//                limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
//            }
//
//
//
//
//            for (int i = 0; i < 48; i++) {
//                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
//                if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
//                    time_level.add(5);
//                } else if (temp_input_current_time < limit_no12) {
//                    time_level.add(1);
//                } else if (temp_input_current_time >= limit_no12) {
//                    if (temp_input_current_time < limit_12) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 4) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 24) {
//                        time_level.add(1);
//                    }
//                } else {
//                    time_level.add(1);
//                }
//            }
//
//
//            List<Integer> test_data = new ArrayList<Integer>();
//            for (int i = 0; i < 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                }
//            }
//            out_string += "&";
//            if (start_flag == 1) {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i - 1;
//                    int temp_time1 = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (temp_time1 >= 24) {
//                        temp_time1 -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在'" + ",";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
//                    }
//                }
//            } else {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在',";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                    }
//                }
//            }
//        }
//        else if(diet_spontaneous_list.contains(diet_type)){
//            int len = new_date_list.size();
//            String temp_current_time = new_date_list.get(len - 1);
//            String[] temp = temp_current_time.split("-");
//            String current_day = temp[0];
//            String current_hour = temp[1];
//            temp = current_hour.split(":");
//            String hour_list = temp[0];
//            String minute_list = temp[1];
//            String past_time_minute = current_hour.split(":")[1];
//            String past_time_hour = current_hour.split(":")[0];
//            List<String> current_day_list = new ArrayList<String>();
//            if (Integer.valueOf(hour_list) < 3) {
//                current_day_list.add(current_day);
//                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//                try {
//                    Date temp_time = df.parse(current_day);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(temp_time);
//                    calendar.add(Calendar.DATE, 1);
//                    String temp_day = df.format(calendar.getTime());
//                    current_day_list.add(temp_day);
//
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                current_day_list.add(current_day);
//            }
//            List<Double> time_list = new ArrayList<Double>();
//            for (int i = 0; i < new_date_list.size(); i++) {
//                String temp_current_day = new_date_list.get(i).split("-")[0];
//                String temp_current_hour = new_date_list.get(i).split("-")[1];
//                if (current_day_list.size() > 1) {
//                    if (temp_current_day.equals(current_day_list.get(1))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    } else if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
//                        time_list.add(input_hour);
//                    }
//
//                } else {
//                    if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    }
//                }
//            }
//
//            List<Integer> time_level = new ArrayList<Integer>();
//            Double first_time = time_list.get(0);
//            Double input_current_time = time_list.get(time_list.size() - 1);
//            int start_flag = 0;
//            int start_hour = (int) Math.floor(input_current_time);
//            if (input_current_time % 1 >= 0.5) {
//                input_current_time = Math.floor(input_current_time) + 0.5;
//            } else {
//                input_current_time = Math.floor(input_current_time);
//                start_flag = 1;
//            }
//
//            if (first_time % 1 >= 0.5) {
//                first_time = Math.floor(first_time) + 0.5;
//            } else {
//                first_time = Math.floor(first_time);
//            }
//
//            Double limit_no12;
//            Double limit_12;
//
//            if (first_time > 22) {
//                limit_no12 = Math.max(input_current_time + 18, Double.valueOf(first_diet_time));
//                //limit_no12 = input_current_time + 4;
//
//                limit_12 = limit_no12 + 6;
//            } else if (input_current_time >= 22) {
//                //limit_no12 = input_current_time + 20;
//                limit_no12 = Math.max(input_current_time + 18, Double.valueOf(first_diet_time));
//
//                limit_12 = limit_no12 + 6;
//            } else if (input_current_time >= first_time + 6) {
//                //limit_no12 = input_current_time + 20;
//                limit_no12 = Math.max(input_current_time + 18, Double.valueOf(first_diet_time));
//
//                limit_12 = limit_no12 + 6;
//            } else {
//
//                limit_12 = first_time + 6;
//                //limit_no12 = Math.min(limit_12 + 20, 22 + 20);
//                Double temp_limit_no12 = Math.min(limit_12 + 18, 22 + 18);
//                limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
//            }
//
//
//
//
//            for (int i = 0; i < 48; i++) {
//                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
//                if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
//                    time_level.add(5);
//                } else if (temp_input_current_time < limit_no12) {
//                    time_level.add(1);
//                } else if (temp_input_current_time >= limit_no12) {
//                    if (temp_input_current_time < limit_12) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 6) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 24) {
//                        time_level.add(1);
//                    }
//                } else {
//                    time_level.add(1);
//                }
//            }
//
//
//            List<Integer> test_data = new ArrayList<Integer>();
//            for (int i = 0; i < 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                }
//            }
//            out_string += "&";
//            if (start_flag == 1) {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i - 1;
//                    int temp_time1 = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (temp_time1 >= 24) {
//                        temp_time1 -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在'" + ",";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
//                    }
//                }
//            } else {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在',";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                    }
//                }
//            }
//        }
//        else{
//            int len = new_date_list.size();
//            String temp_current_time = new_date_list.get(len - 1);
//            String[] temp = temp_current_time.split("-");
//            String current_day = temp[0];
//            String current_hour = temp[1];
//            temp = current_hour.split(":");
//            String hour_list = temp[0];
//            String minute_list = temp[1];
//            String past_time_minute = current_hour.split(":")[1];
//            String past_time_hour = current_hour.split(":")[0];
//            List<String> current_day_list = new ArrayList<String>();
//            if (Integer.valueOf(hour_list) < 3) {
//                current_day_list.add(current_day);
//                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//                try {
//                    Date temp_time = df.parse(current_day);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(temp_time);
//                    calendar.add(Calendar.DATE, 1);
//                    String temp_day = df.format(calendar.getTime());
//                    current_day_list.add(temp_day);
//
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                current_day_list.add(current_day);
//            }
//            List<Double> time_list = new ArrayList<Double>();
//            for (int i = 0; i < new_date_list.size(); i++) {
//                String temp_current_day = new_date_list.get(i).split("-")[0];
//                String temp_current_hour = new_date_list.get(i).split("-")[1];
//                if (current_day_list.size() > 1) {
//                    if (temp_current_day.equals(current_day_list.get(1))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    } else if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
//                        time_list.add(input_hour);
//                    }
//
//                } else {
//                    if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    }
//                }
//            }
//
//            List<Integer> time_level = new ArrayList<Integer>();
//            Double first_time = time_list.get(0);
//            Double input_current_time = time_list.get(time_list.size() - 1);
//            int start_flag = 0;
//            int start_hour = (int) Math.floor(input_current_time);
//            if (input_current_time % 1 >= 0.5) {
//                input_current_time = Math.floor(input_current_time) + 0.5;
//            } else {
//                input_current_time = Math.floor(input_current_time);
//                start_flag = 1;
//            }
//
//            if (first_time % 1 >= 0.5) {
//                first_time = Math.floor(first_time) + 0.5;
//            } else {
//                first_time = Math.floor(first_time);
//            }
//
//            Double limit_no12;
//            Double limit_6;
//            Double limit_8;
//            Double limit_10;
//            Double limit_12;
//            if (input_current_time >= 17) {
//                if (first_time > 22) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else {
//                    limit_6 = first_time + 6;
//                    limit_8 = first_time + 8;
//                    limit_10 = first_time + 10;
//                    limit_12 = first_time + 12;
//                    limit_no12 = input_current_time + 12;
//                    if (limit_12 >= limit_no12) {
//                        limit_no12 = limit_12;
//                    }
//                }
//            } else {
//                if (first_time > 22) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    limit_no12 = input_current_time + 12;
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else {
//                    limit_6 = first_time + 6;
//                    limit_8 = first_time + 8;
//                    limit_10 = first_time + 10;
//                    limit_12 = first_time + 12;
//                    limit_no12 = Math.min(limit_12 + 12, 22 + 12);
//                }
//
//
//            }
//
//            for (int i = 0; i < 48; i++) {
//                //Double temp_input_current_time = Math.ceil(input_current_time) + i / 2;
//                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
//                if (temp_input_current_time < limit_6 && temp_input_current_time < 22) {
//                    time_level.add(5);
//                }
//                else if (temp_input_current_time < limit_8 && temp_input_current_time < 22) {
//                    time_level.add(4);
//                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 22) {
//                    time_level.add(3);
//                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
//                    time_level.add(2);
//                } else if (temp_input_current_time < limit_no12) {
//                    time_level.add(1);
//                } else if (temp_input_current_time >= limit_no12) {
//                    if (temp_input_current_time < limit_6) {
//                        time_level.add(5);
//                    }
//                    if (temp_input_current_time < limit_8) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_10) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_12) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < limit_no12 + 6) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 8) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_no12 + 10) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_no12 + 12) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < limit_no12 + 24) {
//                        time_level.add(1);
//                    }
//                } else {
//                    time_level.add(1);
//                }
//            }
//
//
//            List<Integer> test_data = new ArrayList<Integer>();
//
//            for (int i = 0; i < 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
//                }
//            }
//            out_string += "&";
//            if (start_flag == 1) {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i - 1;
//                    int temp_time1 = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (temp_time1 >= 24) {
//                        temp_time1 -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在'" + ",";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
//                    }
//                }
//            } else {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i;
//                    if (temp_time >= 24) {
//                        temp_time -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在',";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                    }
//                }
//            }
//        }
//        return out_string;
//    }
public static String getDataHeatmapType(List<String> new_date_list, String diet_type, String first_diet_time, int is_now_flag, int is_next_flag, String current_time) {
    diet_type = "no";
    List<String>new_date_list1 = new ArrayList<>();
    DateFormat df1 = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");






    for(int h = 0; h < new_date_list.size(); h++){
        String temp_h = new_date_list.get(h);
        Date temp_time111 = null;
        try {
            temp_time111 = df1.parse(temp_h);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(temp_time111);
        calendar1.add(Calendar.HOUR_OF_DAY, -3);
        String temp_day1 = df1.format(calendar1.getTime());
        new_date_list1.add(temp_day1);
    }
    new_date_list = new_date_list1;

    Date temp_time112 = null;
    try {
        temp_time112 = df1.parse(current_time);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTime(temp_time112);
    calendar1.add(Calendar.HOUR_OF_DAY, -3);
    String temp_day1 = df1.format(calendar1.getTime());
    current_time = temp_day1;



    String[] diet_6_8_10_12 = {"A", "no"};
    String[] diet_12_12 = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
    String[] diet_warrior = {"Z"};
    String[] diet_spontaneous = {"A1", "A2"};
    List<String> diet_6_8_10_12_list = Arrays.asList(diet_6_8_10_12);
    List<String> diet_12_12_list = Arrays.asList(diet_12_12);
    List<String> diet_warrior_list = Arrays.asList(diet_warrior);
    List<String> diet_spontaneous_list = Arrays.asList(diet_spontaneous);
    String out_string = "";
    String first_input_current_time = new_date_list.get(new_date_list.size() - 1);
    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
    Date fromDate2 = null;
    Date toDate2 = null;





//        String[] temp21 = first_input_current_time.split("-")[1].split(":");
//        Double input_hour1 = Double.valueOf(temp21[0]) + (Double.valueOf(temp21[1]) / 60);
//        if (input_hour1 % 1 >= 0.5) {
//            input_hour1 = Math.floor(input_hour1) + 0.5;
//        } else {
//            input_hour1 = Math.floor(input_hour1);
//        }
//
//        String[] temp22 = current_time.split("-")[1].split(":");
//        Double input_hour2 = Double.valueOf(temp22[0]) + (Double.valueOf(temp22[1]) / 60);
//        if (input_hour2 % 1 >= 0.5) {
//            input_hour2 = Math.floor(input_hour2) + 0.5;
//        } else {
//            input_hour2 = Math.floor(input_hour2);
//        }

//        int start_index = (int)((input_hour2 - input_hour1) * 2);
//        while(start_index >=48){
//            start_index -= 48;
//        }

    String[] temp21 = first_input_current_time.split("-")[1].split(":");
    String temp_hour = temp21[0];
    String temp_minute = temp21[1];
    int first_time_redu = 0;
    if(Integer.valueOf(temp_minute) < 30){
        first_time_redu = Integer.valueOf(temp_minute)*60+Integer.valueOf(temp21[2]);
        temp_minute = "00";

    }else{
        first_time_redu = (Integer.valueOf(temp_minute)-30)*60+Integer.valueOf(temp21[2]);
        temp_minute = "30";

    }
    String new_first_input_current_time = first_input_current_time.split("-")[0] + "-" + temp_hour + ":" + temp_minute + ":" + "00";

    String[] temp22 = current_time.split("-")[1].split(":");
    String temp_hour1 = temp22[0];
    String temp_minute1 = temp22[1];
    if(Integer.valueOf(temp_minute1) < 30){
        temp_minute1 = "00";
    }else{
        temp_minute1 = "30";
    }
    String new_current_time = current_time.split("-")[0] + "-" + temp_hour1 + ":" + temp_minute1 + ":" + "00";




    try {
        fromDate2 = simpleFormat.parse(new_first_input_current_time);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    try {
        toDate2 = simpleFormat.parse(new_current_time);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    long from2 = fromDate2.getTime();
    long to2 = toDate2.getTime();
    int from_to_minutes = (int) ((to2 - from2) / (1000 * 60));
    double to_from_hour = Double.valueOf(from_to_minutes) / 60;
    int start_index = (int) (to_from_hour * 2);
    while(start_index >=96){
        start_index -= 48;
    }
    if(Integer.valueOf(temp_hour) >= 14){
        if(Integer.valueOf(temp_hour1) >= 14){

        }else{
            start_index -= 1;
        }
    }









//        if(to_from_hour % 1 > 0.5){
//            to_from_hour = Math.floor(to_from_hour) + 0.5;
//        }else{
//            to_from_hour = Math.floor(to_from_hour);
//        }


    if (diet_6_8_10_12_list.contains(diet_type)) {





        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len - 1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
//        if (Integer.valueOf(hour_list) < 3) {
//            current_day_list.add(current_day);
//            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//            try {
//                Date temp_time = df.parse(current_day);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(temp_time);
//                calendar.add(Calendar.DATE, -1);
//                String temp_day = df.format(calendar.getTime());
//                current_day_list.add(temp_day);
//
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
        current_day_list.add(current_day);
       // }
        List<Double> time_list = new ArrayList<Double>();
        for (int i = 0; i < new_date_list.size(); i++) {
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if (current_day_list.size() > 1) {
                if (temp_current_day.equals(current_day_list.get(1))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                } else if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            } else {
                if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        //Double first_time = 0.0;
//        while(first_time < 3){
//            first_time = time_list.get(0);
//            if(first_time < 3){
//                time_list.remove(0);
//            }
//        }



        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        if(input_current_time >= 14){
            if (input_current_time % 1 >= 0.5) {
                input_current_time = Math.ceil(input_current_time);
                start_flag = 1;
            } else {
                input_current_time = Math.floor(input_current_time) + 0.5;


            }
        }else{
            if (input_current_time % 1 >= 0.5) {
                input_current_time = Math.floor(input_current_time) + 0.5;
            } else {
                input_current_time = Math.floor(input_current_time);
                start_flag = 1;
            }
        }










        int start_hour = (int) Math.floor(input_current_time);
        if(start_hour < 21){
            start_hour += 3;
        }else{
            start_hour = start_hour + 3 - 24;
        }






//        if (input_current_time % 1 >= 0.5) {
//            input_current_time = Math.floor(input_current_time) + 0.5;
//        } else {
//            input_current_time = Math.floor(input_current_time);
//            start_flag = 1;
//        }






        if(start_index % 2 == 1){
            if(start_flag == 1){
                start_flag = 0;
            }else{
                start_flag = 1;
            }
        }

//        if (first_time % 1 >= 0.5) {
//            first_time = Math.floor(first_time) + 0.5;
//        } else {
//            first_time = Math.floor(first_time);
//        }


        if(first_time >= 14){
            if (first_time % 1 >= 0.5) {
                first_time = Math.ceil(first_time);
            } else {
                first_time = Math.floor(first_time) + 0.5;
            }
        }else{
            if (first_time % 1 >= 0.5) {
                first_time = Math.floor(first_time) + 0.5;
            } else {
                first_time = Math.floor(first_time);
            }
        }


        Double limit_no12;
        Double limit_6;
        Double limit_8;
        Double limit_10;
        Double limit_12;
        if (input_current_time >= 14) {
            if (first_time > 19) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= 19) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= first_time + 12) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else {
                limit_6 = first_time + 6;
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                //limit_no12 = input_current_time + 12;
                limit_no12 = input_current_time + 12;
                if (limit_12 >= limit_no12) {
                    limit_no12 = limit_12;
                }
            }
        } else {
            if (first_time > 19) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= 19) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= first_time + 12) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            }else {
                limit_6 = first_time + 6;
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                Double temp_limit_no12 = Math.min(limit_12 + 12, 19 + 12);
                limit_no12 = temp_limit_no12;
            }


        }

        for (int i = 0; i < 144; i++) {
            Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
            if(limit_no12 < limit_6 && temp_input_current_time < limit_no12){
                time_level.add(1);
            }
            else if (temp_input_current_time < limit_6 && temp_input_current_time < 19) {
                time_level.add(5);
            }
            else if (temp_input_current_time < limit_8 && temp_input_current_time < 19) {
                time_level.add(4);
            } else if (temp_input_current_time < limit_10 && temp_input_current_time < 19) {
                time_level.add(3);
            } else if (temp_input_current_time < limit_12 && temp_input_current_time < 19) {
                time_level.add(2);
            } else if (temp_input_current_time < limit_no12) {
                time_level.add(1);
            } else if (temp_input_current_time >= limit_no12) {
                if (temp_input_current_time < limit_6 && temp_input_current_time < 19 + 24) {
                    time_level.add(5);
                }
                else if (temp_input_current_time < limit_8 && temp_input_current_time < 19 + 24) {
                    time_level.add(4);
                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 19 + 24) {
                    time_level.add(3);
                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 19 + 24) {
                    time_level.add(2);
                } else if (temp_input_current_time < limit_no12 + 6 && temp_input_current_time < 19 + 24) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 8 && temp_input_current_time < 19 + 24) {
                    time_level.add(4);
                } else if (temp_input_current_time < limit_no12 + 10 && temp_input_current_time < 19 + 24) {
                    time_level.add(3);
                } else if (temp_input_current_time < limit_no12 + 12 && temp_input_current_time < 19 + 24) {
                    time_level.add(2);
                } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12)) {
                    time_level.add(1);
                }else if(temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 6 ){
                    time_level.add(5);
                }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 8 ) {
                    time_level.add(4);
                } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 10 ) {
                    time_level.add(3);
                } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 12 ) {
                    time_level.add(2);
                } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24) {
                    time_level.add(1);
                }




                else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 6) {
                    time_level.add(5);
                }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 8) {
                    time_level.add(4);
                }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 10) {
                    time_level.add(3);
                }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 12) {
                    time_level.add(2);
                }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 24) {
                    time_level.add(1);
                }












            } else {
                time_level.add(1);
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();

        for (int i = start_index; i < start_index + 48; i++) {
            if (time_level.get(i) == 1) {
                out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "1" + "]" + ",";
            } else if (time_level.get(i) == 2) {
                out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
            } else if (time_level.get(i) == 3) {
                out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i- start_index) + "," + "3" + "]" + ",";
            } else if (time_level.get(i) == 4) {
                out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
            } else if (time_level.get(i) == 5) {
                out_string += "[" + "4" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
                out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
            }
        }
        out_string += "&";
        if (start_flag == 1) {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2) - 1;
                int temp_time1 = start_hour + i + (int)Math.ceil((double)start_index / 2);
                while(temp_time >= 24){
                    temp_time -= 24;
                }
                while(temp_time1 >= 24){
                    temp_time1 -= 24;
                }
                if (i == 0) {
                    out_string += "'现在'" + ",";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                }
            }
        } else {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i + (int)Math.floor((double)start_index / 2);
                while(temp_time >= 24){
                    temp_time -= 24;
                }
                if (i == 0) {
                    out_string += "'现在',";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                }
            }
        }


        String time_lelvel_string = "";
        for (int i = start_index; i < start_index + 48; i++)  {
            if (time_level.get(i) == 1) {
                time_lelvel_string += "1,";
            } else if (time_level.get(i) == 2) {
                time_lelvel_string += "2,";

            } else if (time_level.get(i) == 3) {
                time_lelvel_string += "3,";
            } else if (time_level.get(i) == 4) {
                time_lelvel_string += "4,";
            } else if (time_level.get(i) == 5) {
                time_lelvel_string += "5,";
            }
        }

//
//        String time_lelvel_string2 = "";
//        for (int i = 0; i <  48; i++)  {
//            if (time_level.get(i) == 1) {
//                time_lelvel_string2 += "1,";
//            } else if (time_level.get(i) == 2) {
//                time_lelvel_string2 += "2,";
//
//            } else if (time_level.get(i) == 3) {
//                time_lelvel_string2 += "3,";
//            } else if (time_level.get(i) == 4) {
//                time_lelvel_string2 += "4,";
//            } else if (time_level.get(i) == 5) {
//                time_lelvel_string2 += "5,";
//            }
//        }

        out_string = out_string + "&" + time_lelvel_string;


    }




//        if (diet_6_8_10_12_list.contains(diet_type)) {
//            int len = new_date_list.size();
//            String temp_current_time = new_date_list.get(len - 1);
//            String[] temp = temp_current_time.split("-");
//            String current_day = temp[0];
//            String current_hour = temp[1];
//            temp = current_hour.split(":");
//            String hour_list = temp[0];
//            String minute_list = temp[1];
//            String past_time_minute = current_hour.split(":")[1];
//            String past_time_hour = current_hour.split(":")[0];
//            List<String> current_day_list = new ArrayList<String>();
//            if (Integer.valueOf(hour_list) < 3) {
//                current_day_list.add(current_day);
//                DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//                try {
//                    Date temp_time = df.parse(current_day);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(temp_time);
//                    calendar.add(Calendar.DATE, 1);
//                    String temp_day = df.format(calendar.getTime());
//                    current_day_list.add(temp_day);
//
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                current_day_list.add(current_day);
//            }
//            List<Double> time_list = new ArrayList<Double>();
//            for (int i = 0; i < new_date_list.size(); i++) {
//                String temp_current_day = new_date_list.get(i).split("-")[0];
//                String temp_current_hour = new_date_list.get(i).split("-")[1];
//                if (current_day_list.size() > 1) {
//                    if (temp_current_day.equals(current_day_list.get(1))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    } else if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
//                        time_list.add(input_hour);
//                    }
//
//                } else {
//                    if (temp_current_day.equals(current_day_list.get(0))) {
//                        String[] temp2 = temp_current_hour.split(":");
//                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
//                        time_list.add(input_hour);
//                    }
//                }
//            }
//
//            List<Integer> time_level = new ArrayList<Integer>();
//            Double first_time = time_list.get(0);
//            Double input_current_time = time_list.get(time_list.size() - 1);
//            int start_flag = 0;
//            int start_hour = (int) Math.floor(input_current_time);
//            if (input_current_time % 1 >= 0.5) {
//                input_current_time = Math.floor(input_current_time) + 0.5;
//            } else {
//                input_current_time = Math.floor(input_current_time);
//                start_flag = 1;
//            }
//
//            if(start_index % 2 == 1){
//                if(start_flag == 1){
//                    start_flag = 0;
//                }else{
//                    start_flag = 1;
//                }
//            }
//
//            if (first_time % 1 >= 0.5) {
//                first_time = Math.floor(first_time) + 0.5;
//            } else {
//                first_time = Math.floor(first_time);
//            }
//
//            Double limit_no12;
//            Double limit_6;
//            Double limit_8;
//            Double limit_10;
//            Double limit_12;
//            if (input_current_time >= 17) {
//                if (first_time > 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else {
//                    limit_6 = first_time + 6;
//                    limit_8 = first_time + 8;
//                    limit_10 = first_time + 10;
//                    limit_12 = first_time + 12;
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    if (limit_12 >= limit_no12) {
//                        limit_no12 = limit_12;
//                    }
//                }
//            } else {
//                if (first_time > 22) {
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= 22) {
//                    //limit_no12 = input_current_time + 12;
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else if (input_current_time >= first_time + 12) {
//                    limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
//                    limit_6 = limit_no12 + 6;
//                    limit_8 = limit_no12 + 8;
//                    limit_10 = limit_no12 + 10;
//                    limit_12 = limit_no12 + 12;
//                } else {
//                    limit_6 = first_time + 6;
//                    limit_8 = first_time + 8;
//                    limit_10 = first_time + 10;
//                    limit_12 = first_time + 12;
//                    Double temp_limit_no12 = Math.min(limit_12 + 12, 22 + 12);
//                    limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
//                }
//
//
//            }
//
//            for (int i = 0; i < 96; i++) {
//                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
//                if(limit_no12 < limit_6 && temp_input_current_time < limit_no12){
//                        time_level.add(1);
//                }
//                else if (temp_input_current_time < limit_6 && temp_input_current_time < 22) {
//                    time_level.add(5);
//                }
//                else if (temp_input_current_time < limit_8 && temp_input_current_time < 22) {
//                    time_level.add(4);
//                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 22) {
//                    time_level.add(3);
//                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
//                    time_level.add(2);
//                } else if (temp_input_current_time < limit_no12) {
//                    time_level.add(1);
//                } else if (temp_input_current_time >= limit_no12) {
//                    if (temp_input_current_time < limit_6) {
//                        time_level.add(5);
//                    }
//                    else if (temp_input_current_time < limit_8) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_10) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_12) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < limit_no12 + 6 && temp_input_current_time < 22 + 48) {
//                        time_level.add(5);
//                    } else if (temp_input_current_time < limit_no12 + 8 && temp_input_current_time < 22 + 48) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_no12 + 10 && temp_input_current_time < 22 + 48) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_no12 + 12 && temp_input_current_time < 22 + 48) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < limit_no12 + 24) {
//                        time_level.add(1);
//                    }else if(temp_input_current_time < limit_no12 + 24 + 6 ){
//                        time_level.add(5);
//                    }else if (temp_input_current_time < limit_no12 + 24 + 8 ) {
//                        time_level.add(4);
//                    } else if (temp_input_current_time < limit_no12 + 24 + 10 ) {
//                        time_level.add(3);
//                    } else if (temp_input_current_time < limit_no12 + 24 + 12 ) {
//                        time_level.add(2);
//                    } else if (temp_input_current_time < limit_no12 + 24 + 24) {
//                        time_level.add(1);
//                    }
//
//                } else {
//                    time_level.add(1);
//                }
//            }
//
//
//            List<Integer> test_data = new ArrayList<Integer>();
//
//            for (int i = start_index; i < start_index + 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i- start_index) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                }
//            }
//            out_string += "&";
//            if (start_flag == 1) {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2) - 1;
//                    int temp_time1 = start_hour + i + (int)Math.ceil((double)start_index / 2);
//                    while(temp_time >= 24){
//                        temp_time -= 24;
//                    }
//                    while(temp_time1 >= 24){
//                        temp_time1 -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在'" + ",";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
//                    }
//                }
//            } else {
//                for (int i = 0; i < 25; i++) {
//                    int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2);
//                    while(temp_time >= 24){
//                        temp_time -= 24;
//                    }
//                    if (i == 0) {
//                        out_string += "'现在',";
//                    } else {
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
//                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
//                    }
//                }
//            }
//
//
//        }
    else if (diet_12_12_list.contains(diet_type)) {
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len - 1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
        if (Integer.valueOf(hour_list) < 3) {
            current_day_list.add(current_day);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date temp_time = df.parse(current_day);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(temp_time);
                calendar.add(Calendar.DATE, 1);
                String temp_day = df.format(calendar.getTime());
                current_day_list.add(temp_day);


            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {
            current_day_list.add(current_day);
        }
        List<Double> time_list = new ArrayList<Double>();
        for (int i = 0; i < new_date_list.size(); i++) {
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if (current_day_list.size() > 1) {
                if (temp_current_day.equals(current_day_list.get(1))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                } else if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            } else {
                if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        int start_hour = (int) Math.floor(input_current_time);
        if (input_current_time % 1 >= 0.5) {
            input_current_time = Math.floor(input_current_time) + 0.5;
        } else {
            input_current_time = Math.floor(input_current_time);
            start_flag = 1;
        }
        if(start_index % 2 == 1){
            if(start_flag == 1){
                start_flag = 0;
            }else{
                start_flag = 1;
            }
        }

        if (first_time % 1 >= 0.5) {
            first_time = Math.floor(first_time) + 0.5;
        } else {
            first_time = Math.floor(first_time);
        }

        Double limit_no12;
        Double limit_6;
        Double limit_8;
        Double limit_10;
        Double limit_12;
        if (input_current_time >= 17) {
            if (first_time > 22) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));

                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= 22) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));

                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= first_time + 12) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));

                limit_12 = limit_no12 + 12;
            } else {

                limit_12 = first_time + 12;
                //limit_no12 = input_current_time + 12;
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));
                if (limit_12 >= limit_no12) {
                    limit_no12 = limit_12;
                }
            }
        } else {
            if (first_time > 22) {
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));

                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= 22) {
                //limit_no12 = input_current_time + 12;
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));

                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= first_time + 12) {
                limit_no12 = Math.max(input_current_time + 12, Double.valueOf(first_diet_time));

                limit_12 = limit_no12 + 12;
            } else {

                limit_12 = first_time + 12;
                Double temp_limit_no12 = Math.min(limit_12 + 12, 22 + 12);
                limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
            }


        }

        for (int i = 0; i < 48; i++) {
            Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
            if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
                time_level.add(5);
            } else if (temp_input_current_time < limit_no12) {
                time_level.add(1);
            } else if (temp_input_current_time >= limit_no12) {
                if (temp_input_current_time < limit_12) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 12) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 24) {
                    time_level.add(1);
                }
            } else {
                time_level.add(1);
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();

        for (int i = 0; i < 48; i++) {
            if (time_level.get(i) == 1) {
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
            } else if (time_level.get(i) == 2) {
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
            } else if (time_level.get(i) == 3) {
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
            } else if (time_level.get(i) == 4) {
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
            } else if (time_level.get(i) == 5) {
                out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
            }
        }
        out_string += "&";
        if (start_flag == 1) {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i - 1;
                int temp_time1 = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (temp_time1 >= 24) {
                    temp_time1 -= 24;
                }
                if (i == 0) {
                    out_string += "'现在'" + ",";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                }
            }
        } else {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (i == 0) {
                    out_string += "'现在',";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                }
            }
        }

    }
    else if(diet_warrior_list.contains(diet_type)){
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len - 1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
        if (Integer.valueOf(hour_list) < 3) {
            current_day_list.add(current_day);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date temp_time = df.parse(current_day);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(temp_time);
                calendar.add(Calendar.DATE, 1);
                String temp_day = df.format(calendar.getTime());
                current_day_list.add(temp_day);


            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {
            current_day_list.add(current_day);
        }
        List<Double> time_list = new ArrayList<Double>();
        for (int i = 0; i < new_date_list.size(); i++) {
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if (current_day_list.size() > 1) {
                if (temp_current_day.equals(current_day_list.get(1))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                } else if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            } else {
                if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        int start_hour = (int) Math.floor(input_current_time);
        if (input_current_time % 1 >= 0.5) {
            input_current_time = Math.floor(input_current_time) + 0.5;
        } else {
            input_current_time = Math.floor(input_current_time);
            start_flag = 1;
        }

        if (first_time % 1 >= 0.5) {
            first_time = Math.floor(first_time) + 0.5;
        } else {
            first_time = Math.floor(first_time);
        }

        Double limit_no12;
        Double limit_12;

        if (first_time > 22) {
            limit_no12 = Math.max(input_current_time + 20, Double.valueOf(first_diet_time));
            //limit_no12 = input_current_time + 4;

            limit_12 = limit_no12 + 4;
        } else if (input_current_time >= 22) {
            //limit_no12 = input_current_time + 20;
            limit_no12 = Math.max(input_current_time + 20, Double.valueOf(first_diet_time));

            limit_12 = limit_no12 + 4;
        } else if (input_current_time >= first_time + 4) {
            //limit_no12 = input_current_time + 20;
            limit_no12 = Math.max(input_current_time + 20, Double.valueOf(first_diet_time));

            limit_12 = limit_no12 + 4;
        } else {

            limit_12 = first_time + 4;
            //limit_no12 = Math.min(limit_12 + 20, 22 + 20);
            Double temp_limit_no12 = Math.min(limit_12 + 20, 22 + 20);
            limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
        }




        for (int i = 0; i < 48; i++) {
            Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
            if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
                time_level.add(5);
            } else if (temp_input_current_time < limit_no12) {
                time_level.add(1);
            } else if (temp_input_current_time >= limit_no12) {
                if (temp_input_current_time < limit_12) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 4) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 24) {
                    time_level.add(1);
                }
            } else {
                time_level.add(1);
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();
        for (int i = 0; i < 48; i++) {
            if (time_level.get(i) == 1) {
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
            } else if (time_level.get(i) == 2) {
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
            } else if (time_level.get(i) == 3) {
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
            } else if (time_level.get(i) == 4) {
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
            } else if (time_level.get(i) == 5) {
                out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
            }
        }
        out_string += "&";
        if (start_flag == 1) {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i - 1;
                int temp_time1 = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (temp_time1 >= 24) {
                    temp_time1 -= 24;
                }
                if (i == 0) {
                    out_string += "'现在'" + ",";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                }
            }
        } else {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (i == 0) {
                    out_string += "'现在',";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                }
            }
        }
    }
    else if(diet_spontaneous_list.contains(diet_type)){
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len - 1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
        if (Integer.valueOf(hour_list) < 3) {
            current_day_list.add(current_day);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date temp_time = df.parse(current_day);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(temp_time);
                calendar.add(Calendar.DATE, 1);
                String temp_day = df.format(calendar.getTime());
                current_day_list.add(temp_day);


            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {
            current_day_list.add(current_day);
        }
        List<Double> time_list = new ArrayList<Double>();
        for (int i = 0; i < new_date_list.size(); i++) {
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if (current_day_list.size() > 1) {
                if (temp_current_day.equals(current_day_list.get(1))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                } else if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            } else {
                if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        int start_hour = (int) Math.floor(input_current_time);
        if (input_current_time % 1 >= 0.5) {
            input_current_time = Math.floor(input_current_time) + 0.5;
        } else {
            input_current_time = Math.floor(input_current_time);
            start_flag = 1;
        }

        if (first_time % 1 >= 0.5) {
            first_time = Math.floor(first_time) + 0.5;
        } else {
            first_time = Math.floor(first_time);
        }

        Double limit_no12;
        Double limit_12;

        if (first_time > 22) {
            limit_no12 = Math.max(input_current_time + 18, Double.valueOf(first_diet_time));
            //limit_no12 = input_current_time + 4;

            limit_12 = limit_no12 + 6;
        } else if (input_current_time >= 22) {
            //limit_no12 = input_current_time + 20;
            limit_no12 = Math.max(input_current_time + 18, Double.valueOf(first_diet_time));

            limit_12 = limit_no12 + 6;
        } else if (input_current_time >= first_time + 6) {
            //limit_no12 = input_current_time + 20;
            limit_no12 = Math.max(input_current_time + 18, Double.valueOf(first_diet_time));

            limit_12 = limit_no12 + 6;
        } else {

            limit_12 = first_time + 6;
            //limit_no12 = Math.min(limit_12 + 20, 22 + 20);
            Double temp_limit_no12 = Math.min(limit_12 + 18, 22 + 18);
            limit_no12 = Math.max(temp_limit_no12, Double.valueOf(first_diet_time));
        }




        for (int i = 0; i < 48; i++) {
            Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
            if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
                time_level.add(5);
            } else if (temp_input_current_time < limit_no12) {
                time_level.add(1);
            } else if (temp_input_current_time >= limit_no12) {
                if (temp_input_current_time < limit_12) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 6) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 24) {
                    time_level.add(1);
                }
            } else {
                time_level.add(1);
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();
        for (int i = 0; i < 48; i++) {
            if (time_level.get(i) == 1) {
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
            } else if (time_level.get(i) == 2) {
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
            } else if (time_level.get(i) == 3) {
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
            } else if (time_level.get(i) == 4) {
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
            } else if (time_level.get(i) == 5) {
                out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
            }
        }
        out_string += "&";
        if (start_flag == 1) {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i - 1;
                int temp_time1 = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (temp_time1 >= 24) {
                    temp_time1 -= 24;
                }
                if (i == 0) {
                    out_string += "'现在'" + ",";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                }
            }
        } else {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (i == 0) {
                    out_string += "'现在',";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                }
            }
        }
    }
    else{
        int len = new_date_list.size();
        String temp_current_time = new_date_list.get(len - 1);
        String[] temp = temp_current_time.split("-");
        String current_day = temp[0];
        String current_hour = temp[1];
        temp = current_hour.split(":");
        String hour_list = temp[0];
        String minute_list = temp[1];
        String past_time_minute = current_hour.split(":")[1];
        String past_time_hour = current_hour.split(":")[0];
        List<String> current_day_list = new ArrayList<String>();
        if (Integer.valueOf(hour_list) < 3) {
            current_day_list.add(current_day);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date temp_time = df.parse(current_day);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(temp_time);
                calendar.add(Calendar.DATE, 1);
                String temp_day = df.format(calendar.getTime());
                current_day_list.add(temp_day);


            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {
            current_day_list.add(current_day);
        }
        List<Double> time_list = new ArrayList<Double>();
        for (int i = 0; i < new_date_list.size(); i++) {
            String temp_current_day = new_date_list.get(i).split("-")[0];
            String temp_current_hour = new_date_list.get(i).split("-")[1];
            if (current_day_list.size() > 1) {
                if (temp_current_day.equals(current_day_list.get(1))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                } else if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                    time_list.add(input_hour);
                }

            } else {
                if (temp_current_day.equals(current_day_list.get(0))) {
                    String[] temp2 = temp_current_hour.split(":");
                    Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                    time_list.add(input_hour);
                }
            }
        }

        List<Integer> time_level = new ArrayList<Integer>();
        Double first_time = time_list.get(0);
        Double input_current_time = time_list.get(time_list.size() - 1);
        int start_flag = 0;
        int start_hour = (int) Math.floor(input_current_time);
        if (input_current_time % 1 >= 0.5) {
            input_current_time = Math.floor(input_current_time) + 0.5;
        } else {
            input_current_time = Math.floor(input_current_time);
            start_flag = 1;
        }

        if (first_time % 1 >= 0.5) {
            first_time = Math.floor(first_time) + 0.5;
        } else {
            first_time = Math.floor(first_time);
        }

        Double limit_no12;
        Double limit_6;
        Double limit_8;
        Double limit_10;
        Double limit_12;
        if (input_current_time >= 17) {
            if (first_time > 22) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= 22) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= first_time + 12) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else {
                limit_6 = first_time + 6;
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                limit_no12 = input_current_time + 12;
                if (limit_12 >= limit_no12) {
                    limit_no12 = limit_12;
                }
            }
        } else {
            if (first_time > 22) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= 22) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else if (input_current_time >= first_time + 12) {
                limit_no12 = input_current_time + 12;
                limit_6 = limit_no12 + 6;
                limit_8 = limit_no12 + 8;
                limit_10 = limit_no12 + 10;
                limit_12 = limit_no12 + 12;
            } else {
                limit_6 = first_time + 6;
                limit_8 = first_time + 8;
                limit_10 = first_time + 10;
                limit_12 = first_time + 12;
                limit_no12 = Math.min(limit_12 + 12, 22 + 12);
            }


        }

        for (int i = 0; i < 48; i++) {
            //Double temp_input_current_time = Math.ceil(input_current_time) + i / 2;
            Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
            if (temp_input_current_time < limit_6 && temp_input_current_time < 22) {
                time_level.add(5);
            }
            else if (temp_input_current_time < limit_8 && temp_input_current_time < 22) {
                time_level.add(4);
            } else if (temp_input_current_time < limit_10 && temp_input_current_time < 22) {
                time_level.add(3);
            } else if (temp_input_current_time < limit_12 && temp_input_current_time < 22) {
                time_level.add(2);
            } else if (temp_input_current_time < limit_no12) {
                time_level.add(1);
            } else if (temp_input_current_time >= limit_no12) {
                if (temp_input_current_time < limit_6) {
                    time_level.add(5);
                }
                if (temp_input_current_time < limit_8) {
                    time_level.add(4);
                } else if (temp_input_current_time < limit_10) {
                    time_level.add(3);
                } else if (temp_input_current_time < limit_12) {
                    time_level.add(2);
                } else if (temp_input_current_time < limit_no12 + 6) {
                    time_level.add(5);
                } else if (temp_input_current_time < limit_no12 + 8) {
                    time_level.add(4);
                } else if (temp_input_current_time < limit_no12 + 10) {
                    time_level.add(3);
                } else if (temp_input_current_time < limit_no12 + 12) {
                    time_level.add(2);
                } else if (temp_input_current_time < limit_no12 + 24) {
                    time_level.add(1);
                }
            } else {
                time_level.add(1);
            }
        }


        List<Integer> test_data = new ArrayList<Integer>();

        for (int i = 0; i < 48; i++) {
            if (time_level.get(i) == 1) {
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "1" + "]" + ",";
            } else if (time_level.get(i) == 2) {
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "2" + "]" + ",";
            } else if (time_level.get(i) == 3) {
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "3" + "]" + ",";
            } else if (time_level.get(i) == 4) {
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "4" + "]" + ",";
            } else if (time_level.get(i) == 5) {
                out_string += "[" + "4" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "3" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "2" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "1" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
                out_string += "[" + "0" + "," + String.valueOf(i) + "," + "5" + "]" + ",";
            }
        }
        out_string += "&";
        if (start_flag == 1) {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i - 1;
                int temp_time1 = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (temp_time1 >= 24) {
                    temp_time1 -= 24;
                }
                if (i == 0) {
                    out_string += "'现在'" + ",";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                }
            }
        } else {
            for (int i = 0; i < 25; i++) {
                int temp_time = start_hour + i;
                if (temp_time >= 24) {
                    temp_time -= 24;
                }
                if (i == 0) {
                    out_string += "'现在',";
                } else {
                    out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                    out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                }
            }
        }
    }

    out_string = out_string + "&" + String.valueOf(first_time_redu);

    return out_string;
}

public static String getDataHeatmapType(List<String> new_date_list, String current_time) {

        List<String>new_date_list1 = new ArrayList<>();
        DateFormat df1 = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");






        for(int h = 0; h < new_date_list.size(); h++){
            String temp_h = new_date_list.get(h);
            Date temp_time111 = null;
            try {
                temp_time111 = df1.parse(temp_h);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(temp_time111);
            calendar1.add(Calendar.HOUR_OF_DAY, -3);
            String temp_day1 = df1.format(calendar1.getTime());
            new_date_list1.add(temp_day1);
        }
        new_date_list = new_date_list1;

        Date temp_time112 = null;
        try {
            temp_time112 = df1.parse(current_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(temp_time112);
        calendar1.add(Calendar.HOUR_OF_DAY, -3);
        String temp_day1 = df1.format(calendar1.getTime());
        current_time = temp_day1;



        String[] diet_6_8_10_12 = {"A", "no"};
        String[] diet_12_12 = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
        String[] diet_warrior = {"Z"};
        String[] diet_spontaneous = {"A1", "A2"};
        List<String> diet_6_8_10_12_list = Arrays.asList(diet_6_8_10_12);
        List<String> diet_12_12_list = Arrays.asList(diet_12_12);
        List<String> diet_warrior_list = Arrays.asList(diet_warrior);
        List<String> diet_spontaneous_list = Arrays.asList(diet_spontaneous);
        String out_string = "";
        String first_input_current_time = new_date_list.get(new_date_list.size() - 1);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        Date fromDate2 = null;
        Date toDate2 = null;





//        String[] temp21 = first_input_current_time.split("-")[1].split(":");
//        Double input_hour1 = Double.valueOf(temp21[0]) + (Double.valueOf(temp21[1]) / 60);
//        if (input_hour1 % 1 >= 0.5) {
//            input_hour1 = Math.floor(input_hour1) + 0.5;
//        } else {
//            input_hour1 = Math.floor(input_hour1);
//        }
//
//        String[] temp22 = current_time.split("-")[1].split(":");
//        Double input_hour2 = Double.valueOf(temp22[0]) + (Double.valueOf(temp22[1]) / 60);
//        if (input_hour2 % 1 >= 0.5) {
//            input_hour2 = Math.floor(input_hour2) + 0.5;
//        } else {
//            input_hour2 = Math.floor(input_hour2);
//        }

//        int start_index = (int)((input_hour2 - input_hour1) * 2);
//        while(start_index >=48){
//            start_index -= 48;
//        }

        String[] temp21 = first_input_current_time.split("-")[1].split(":");
        String temp_hour = temp21[0];
        String temp_minute = temp21[1];
        if(Integer.valueOf(temp_minute) < 30){
            temp_minute = "00";
        }else{
            temp_minute = "30";
        }
        String new_first_input_current_time = first_input_current_time.split("-")[0] + "-" + temp_hour + ":" + temp_minute + ":" + "00";

        String[] temp22 = current_time.split("-")[1].split(":");
        String temp_hour1 = temp22[0];
        String temp_minute1 = temp22[1];
        if(Integer.valueOf(temp_minute1) < 30){
            temp_minute1 = "00";
        }else{
            temp_minute1 = "30";
        }
        String new_current_time = current_time.split("-")[0] + "-" + temp_hour1 + ":" + temp_minute1 + ":" + "00";




        try {
            fromDate2 = simpleFormat.parse(new_first_input_current_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            toDate2 = simpleFormat.parse(new_current_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        int from_to_minutes = (int) ((to2 - from2) / (1000 * 60));
        double to_from_hour = Double.valueOf(from_to_minutes) / 60;
        int start_index = (int) (to_from_hour * 2);
        while(start_index >=96){
            start_index -= 48;
        }









//        if(to_from_hour % 1 > 0.5){
//            to_from_hour = Math.floor(to_from_hour) + 0.5;
//        }else{
//            to_from_hour = Math.floor(to_from_hour);
//        }
    {





            int len = new_date_list.size();
            String temp_current_time = new_date_list.get(len - 1);
            String[] temp = temp_current_time.split("-");
            String current_day = temp[0];
            String current_hour = temp[1];
            temp = current_hour.split(":");
            String hour_list = temp[0];
            String minute_list = temp[1];
            String past_time_minute = current_hour.split(":")[1];
            String past_time_hour = current_hour.split(":")[0];
            List<String> current_day_list = new ArrayList<String>();
//        if (Integer.valueOf(hour_list) < 3) {
//            current_day_list.add(current_day);
//            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//            try {
//                Date temp_time = df.parse(current_day);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(temp_time);
//                calendar.add(Calendar.DATE, -1);
//                String temp_day = df.format(calendar.getTime());
//                current_day_list.add(temp_day);
//
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
            current_day_list.add(current_day);
            // }
            List<Double> time_list = new ArrayList<Double>();
            for (int i = 0; i < new_date_list.size(); i++) {
                String temp_current_day = new_date_list.get(i).split("-")[0];
                String temp_current_hour = new_date_list.get(i).split("-")[1];
                if (current_day_list.size() > 1) {
                    if (temp_current_day.equals(current_day_list.get(1))) {
                        String[] temp2 = temp_current_hour.split(":");
                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                        time_list.add(input_hour);
                    } else if (temp_current_day.equals(current_day_list.get(0))) {
                        String[] temp2 = temp_current_hour.split(":");
                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60) + 24;
                        time_list.add(input_hour);
                    }

                } else {
                    if (temp_current_day.equals(current_day_list.get(0))) {
                        String[] temp2 = temp_current_hour.split(":");
                        Double input_hour = Double.valueOf(temp2[0]) + (Double.valueOf(temp2[1]) / 60);
                        time_list.add(input_hour);
                    }
                }
            }

            List<Integer> time_level = new ArrayList<Integer>();
            Double first_time = time_list.get(0);
            //Double first_time = 0.0;
//        while(first_time < 3){
//            first_time = time_list.get(0);
//            if(first_time < 3){
//                time_list.remove(0);
//            }
//        }

            Double input_current_time = time_list.get(time_list.size() - 1);
            int start_flag = 0;
            int start_hour = (int) Math.floor(input_current_time);
            if(start_hour < 21){
                start_hour += 3;
            }else{
                start_hour = start_hour + 3 - 24;
            }
            if (input_current_time % 1 >= 0.5) {
                input_current_time = Math.floor(input_current_time) + 0.5;
            } else {
                input_current_time = Math.floor(input_current_time);
                start_flag = 1;
            }

            if(start_index % 2 == 1){
                if(start_flag == 1){
                    start_flag = 0;
                }else{
                    start_flag = 1;
                }
            }

            if (first_time % 1 >= 0.5) {
                first_time = Math.floor(first_time) + 0.5;
            } else {
                first_time = Math.floor(first_time);
            }

            Double limit_no12;
            Double limit_6;
            Double limit_8;
            Double limit_10;
            Double limit_12;
            if (input_current_time >= 14) {
                if (first_time > 19) {
                    //limit_no12 = input_current_time + 12;
                    limit_no12 = input_current_time + 12;
                    limit_6 = limit_no12 + 6;
                    limit_8 = limit_no12 + 8;
                    limit_10 = limit_no12 + 10;
                    limit_12 = limit_no12 + 12;
                } else if (input_current_time >= 19) {
                    //limit_no12 = input_current_time + 12;
                    limit_no12 = input_current_time + 12;
                    limit_6 = limit_no12 + 6;
                    limit_8 = limit_no12 + 8;
                    limit_10 = limit_no12 + 10;
                    limit_12 = limit_no12 + 12;
                } else if (input_current_time >= first_time + 12) {
                    //limit_no12 = input_current_time + 12;
                    limit_no12 = input_current_time + 12;
                    limit_6 = limit_no12 + 6;
                    limit_8 = limit_no12 + 8;
                    limit_10 = limit_no12 + 10;
                    limit_12 = limit_no12 + 12;
                } else {
                    limit_6 = first_time + 6;
                    limit_8 = first_time + 8;
                    limit_10 = first_time + 10;
                    limit_12 = first_time + 12;
                    //limit_no12 = input_current_time + 12;
                    limit_no12 = input_current_time + 12;
                    if (limit_12 >= limit_no12) {
                        limit_no12 = limit_12;
                    }
                }
            } else {
                if (first_time > 19) {
                    limit_no12 = input_current_time + 12;
                    limit_6 = limit_no12 + 6;
                    limit_8 = limit_no12 + 8;
                    limit_10 = limit_no12 + 10;
                    limit_12 = limit_no12 + 12;
                } else if (input_current_time >= 19) {
                    //limit_no12 = input_current_time + 12;
                    limit_no12 = input_current_time + 12;
                    limit_6 = limit_no12 + 6;
                    limit_8 = limit_no12 + 8;
                    limit_10 = limit_no12 + 10;
                    limit_12 = limit_no12 + 12;
                } else if (input_current_time >= first_time + 12) {
                    limit_no12 = input_current_time + 12;
                    limit_6 = limit_no12 + 6;
                    limit_8 = limit_no12 + 8;
                    limit_10 = limit_no12 + 10;
                    limit_12 = limit_no12 + 12;
                }else {
                    limit_6 = first_time + 6;
                    limit_8 = first_time + 8;
                    limit_10 = first_time + 10;
                    limit_12 = first_time + 12;
                    Double temp_limit_no12 = Math.min(limit_12 + 12, 19 + 12);
                    limit_no12 = temp_limit_no12;
                }


            }

            for (int i = 0; i < 144; i++) {
                Double temp_input_current_time = (input_current_time + Double.valueOf(i) / 2);
                if(limit_no12 < limit_6 && temp_input_current_time < limit_no12){
                    time_level.add(1);
                }
                else if (temp_input_current_time < limit_6 && temp_input_current_time < 19) {
                    time_level.add(5);
                }
                else if (temp_input_current_time < limit_8 && temp_input_current_time < 19) {
                    time_level.add(4);
                } else if (temp_input_current_time < limit_10 && temp_input_current_time < 19) {
                    time_level.add(3);
                } else if (temp_input_current_time < limit_12 && temp_input_current_time < 19) {
                    time_level.add(2);
                } else if (temp_input_current_time < limit_no12) {
                    time_level.add(1);
                } else if (temp_input_current_time >= limit_no12) {
                    if (temp_input_current_time < limit_6 && temp_input_current_time < 19 + 24) {
                        time_level.add(5);
                    }
                    else if (temp_input_current_time < limit_8 && temp_input_current_time < 19 + 24) {
                        time_level.add(4);
                    } else if (temp_input_current_time < limit_10 && temp_input_current_time < 19 + 24) {
                        time_level.add(3);
                    } else if (temp_input_current_time < limit_12 && temp_input_current_time < 19 + 24) {
                        time_level.add(2);
                    } else if (temp_input_current_time < limit_no12 + 6 && temp_input_current_time < 19 + 24) {
                        time_level.add(5);
                    } else if (temp_input_current_time < limit_no12 + 8 && temp_input_current_time < 19 + 24) {
                        time_level.add(4);
                    } else if (temp_input_current_time < limit_no12 + 10 && temp_input_current_time < 19 + 24) {
                        time_level.add(3);
                    } else if (temp_input_current_time < limit_no12 + 12 && temp_input_current_time < 19 + 24) {
                        time_level.add(2);
                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12)) {
                        time_level.add(1);
                    }else if(temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 6 ){
                        time_level.add(5);
                    }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 8 ) {
                        time_level.add(4);
                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 10 ) {
                        time_level.add(3);
                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 12 ) {
                        time_level.add(2);
                    } else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24) {
                        time_level.add(1);
                    }



                    else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 6) {
                        time_level.add(5);
                    }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 8) {
                        time_level.add(4);
                    }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 10) {
                        time_level.add(3);
                    }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 12) {
                        time_level.add(2);
                    }else if (temp_input_current_time < Math.min(limit_no12 + 24, 19 + 24 + 12) + 24 + 24) {
                        time_level.add(1);
                    }

                } else {
                    time_level.add(1);
                }
            }


            List<Integer> test_data = new ArrayList<Integer>();

        for (int i = start_index; i < start_index + 48; i++) {
            if (time_level.get(i) == 1) {
                out_string += "1" + ",";
            } else if (time_level.get(i) == 2) {
                out_string += "2" + ",";
            } else if (time_level.get(i) == 3) {
                out_string += "3" + ",";
            } else if (time_level.get(i) == 4) {
                out_string += "4" + ",";
            } else if (time_level.get(i) == 5) {
                out_string += "5" + ",";
            }
        }

//            for (int i = start_index; i < start_index + 48; i++) {
//                if (time_level.get(i) == 1) {
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "1" + "]" + ",";
//                } else if (time_level.get(i) == 2) {
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "2" + "]" + ",";
//                } else if (time_level.get(i) == 3) {
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "3" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i- start_index) + "," + "3" + "]" + ",";
//                } else if (time_level.get(i) == 4) {
//                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "4" + "]" + ",";
//                } else if (time_level.get(i) == 5) {
//                    out_string += "[" + "4" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "3" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "2" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "1" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                    out_string += "[" + "0" + "," + String.valueOf(i - start_index) + "," + "5" + "]" + ",";
//                }
//            }
            out_string += "&";
            if (start_flag == 1) {
                for (int i = 0; i < 25; i++) {
                    int temp_time = start_hour + i + (int)Math.ceil((double)start_index / 2) - 1;
                    int temp_time1 = start_hour + i + (int)Math.ceil((double)start_index / 2);
                    while(temp_time >= 24){
                        temp_time -= 24;
                    }
                    while(temp_time1 >= 24){
                        temp_time1 -= 24;
                    }
                    if (i == 0) {
                        out_string += "'现在'" + ",";
                    } else {
                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                        out_string += "'" + String.valueOf(temp_time1) + ":" + "00" + "',";
                    }
                }
            } else {
                for (int i = 0; i < 25; i++) {
                    int temp_time = start_hour + i + (int)Math.floor((double)start_index / 2);
                    while(temp_time >= 24){
                        temp_time -= 24;
                    }
                    if (i == 0) {
                        out_string += "'现在',";
                    } else {
                        out_string += "'" + String.valueOf(temp_time) + ":" + "00" + "',";
                        out_string += "'" + String.valueOf(temp_time) + ":" + "30" + "',";
                    }
                }
            }


        }



        return out_string;
    }


}
