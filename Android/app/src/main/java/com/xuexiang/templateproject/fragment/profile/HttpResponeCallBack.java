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
 * IT蓝豹 www.itlanbao.com
 * 回调接口
 */
public interface HttpResponeCallBack {
	public void onResponeStart(String apiName);

	/**
	 * 此回调只有调用download方法下载数据时才生效
	 * 
	 * @param apiName
	 * @param count
	 * @param current
	 */
	public void onLoading(String apiName, long count, long current);

	public void onSuccess(String apiName, Object object);

	public void onFailure(String apiName, Throwable t, int errorNo, String strMsg);
 
}
