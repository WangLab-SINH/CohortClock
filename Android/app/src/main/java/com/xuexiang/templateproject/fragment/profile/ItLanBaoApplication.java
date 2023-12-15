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

/**
 * @author wjl IT蓝豹 ItLanBaoApplication主要作用是处理一些app全局变量，
 */
public class ItLanBaoApplication extends ItLanbaoLibApplication {

	private UserBaseInfo baseUser;//用户基本信息

	private RequestApiData requestApi;
	private static ItLanBaoApplication instance;

	// 渠道号
	private String fid = "";
	// 版本号
	private String version = "";

	@Override
	public void onCreate() {
		super.onCreate();
		setInstance(this);
		requestApi = RequestApiData.getInstance();
	}

	public static void setInstance(ItLanBaoApplication instance) {
		ItLanBaoApplication.instance = instance;
	}


	/**
	 * 设置用户基本信息
	 * @param baseUser
	 */
	public void setBaseUser(UserBaseInfo baseUser) {
		this.baseUser = baseUser;
	}



	/**
	 * 获取ItLanBaoApplication实例
	 *
	 * @return
	 */
	public static ItLanBaoApplication getInstance() {
		return instance;
	}

}
