package com.xiaokang.util;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @Description: jsonp解决跨域访问工具类
 * @author 小康
 * @version V1.0.0 2019年11月24日 下午5:16:20
 */
public abstract class JsonpUtil {
	/**
	 * 
	 * @Description: 根据前端传入的jsonp的callback响应指定的json字符串回去
	 * @return String
	 * @param callback
	 * @param json
	 * @return
	 */
	public static String getResponseText(String callback, String json) {
		return String.valueOf(callback + "(" + json + ")");
	}

	/**
	 * 
	 * @Description: 根据前端传入的jsonp的callback响应指定的json字符串（传入的是Object）回去
	 * @return String
	 * @param callback
	 * @param object
	 * @return
	 */
	public static String getResponseText(String callback, Object object) {
		return getResponseText(callback, JSON.toJSONString(object));
	}
}
