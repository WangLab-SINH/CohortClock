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

import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CrashHandler implements Thread.UncaughtExceptionHandler{
    //定义文件存放路径
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashInfo/";

    //定义文件后缀
    private static final String FILE_NAME_SUFFIX = ".txt";

    //系统默认的异常处理器
    private Thread.UncaughtExceptionHandler defaultCrashHandler;

    private static final String TAG = "CrashHandler";

    private static CrashHandler crashHandler = new CrashHandler();

    //私有化构造函数
    private CrashHandler() {
    }

    //获取实例
    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init() {
        defaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置系统的默认异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //记录异常信息到本地文本中
        dumpExceptionToSDCard(throwable);
        if (defaultCrashHandler != null) {
            //如果在自定义异常处理器之前，系统有自己的默认异常处理器的话，调用它来处理异常信息
            defaultCrashHandler.uncaughtException(thread, throwable);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    //记录异常信息到本地文本中
    private void dumpExceptionToSDCard(Throwable throwable) {
        //如果SD卡非正常挂载，则用Log输出异常信息
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.e(TAG, "SD卡出错");
            return;
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long currentTime = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTime));
        //建立记录Crash信息的文本
        File file = new File(PATH + time + FILE_NAME_SUFFIX);
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            printWriter.println(time);
            dumpPhoneInfo(printWriter);
            printWriter.println();
            throwable.printStackTrace(printWriter);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "记录Crash信息失败");
        }
    }

    //记录手机信息
    private void dumpPhoneInfo(PrintWriter printWriter) {
        //系统版本号
        printWriter.print("OS Version:");
        printWriter.print(Build.VERSION.RELEASE);
        printWriter.print("_");
        printWriter.println(Build.VERSION.SDK_INT);
        //硬件制造商
        printWriter.print("Vendor:");
        printWriter.println(Build.MANUFACTURER);
        //系统定制商
        printWriter.print("Brand:");
        printWriter.println(Build.BRAND);
    }
}
