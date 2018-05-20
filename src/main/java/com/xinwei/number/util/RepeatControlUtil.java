package com.xinwei.number.util;

import java.util.HashMap;
import java.util.Map;

public class RepeatControlUtil {
	private static Map<String, Integer> keyMap = new HashMap<>();

	/**
	 * 生成计数器统计次数key
	 */
	public static String getNumberKey(String userId, String amountId, String key) {
		StringBuilder sb = new StringBuilder(userId);
		return sb.append(":").append(amountId).append(":").append(key).toString();
	}

	/**
	 * 用户统计次数key 被统计一次
	 * @param numberKey
	 */
	public static void addCount(String numberKey) {
		keyMap.put(numberKey, 0);
	}

	/**
	 * 判断该用户统计次数key 是否还能被使用
	 * @param numberKey
	 * @return
	 */
	public static boolean canAddCount(String numberKey) {
		return !keyMap.containsKey(numberKey);
	}
}
