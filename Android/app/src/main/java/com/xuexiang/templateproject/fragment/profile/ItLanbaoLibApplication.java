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


import android.app.Application;

import com.xuexiang.xui.XUI;


public class ItLanbaoLibApplication extends Application{
	
	private static ItLanbaoLibApplication instance;
	 

	 
	
	@Override
	public void onCreate() {
		super.onCreate();
		XUI.init(this); //初始化UI框架
		XUI.debug(true);
		setInstance(this); 
      
	} 
	
	public static void setInstance(ItLanbaoLibApplication instance) {
		ItLanbaoLibApplication.instance = instance;
	}
	
	/** 
	 * 获取时间戳
	 * @return
	 */
	public String getTime(){
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 获取ItLanBaoApplication实例
	 * 
	 * @return
	 */
	public static ItLanbaoLibApplication getInstance()
	{
		return instance;
	}
 
}
