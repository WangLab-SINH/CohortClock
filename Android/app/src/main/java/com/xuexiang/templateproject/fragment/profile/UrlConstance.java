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
 * @author itlanbao
 * 处理网络的参数常量类
 */
public class UrlConstance {
	 
	 public static final String ACCESSTOKEN_KEY ="accesstoken";
	 
	   
   //签约公钥，即客户端与服务器协商订的一个公钥
   public static final String PUBLIC_KEY ="*.itlanbao.com";
   public static final String APP_URL = "http://www.itlanbao.com/api/app/";
   
   //4.6注册用户接口
   public static final String KEY_REGIST_INFO ="users/user_register_Handler.ashx";
   
   //4.8登录用户接口
   public static final String KEY_LOGIN_INFO ="users/user_login_handler.ashx";

   //4.9获取用户基本信息
   public static final String KEY_USER_BASE_INFO ="users/user_Info_handler.ashx";
   
}
