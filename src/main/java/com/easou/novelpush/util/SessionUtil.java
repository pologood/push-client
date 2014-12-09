package com.easou.novelpush.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SessionUtil {
	// 本地进制
	private static final String LOCAL_SCALE = "0123456789-_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	// 词典映射
	private static final String DICTIONARY = "O3GQ2VEZ_ehzfInBH4jaDvcU9Y1lpX-mKqR6ACWL5xgT0iMrds8SNt7PkbyuowJF";
	private static final int FIX_WIDTH = 20;

	/**
	 * 生成sessions
	 * 
	 * @param uid
	 * @param expire
	 *            过期时间，单位ms，0表示永不过期
	 * @return
	 */
	public static SessionInfo create(long uid, long expire) {
		if (uid < 1 || expire < 0) {
			return null;
		}
		SessionInfo session = new SessionInfo();
		StringBuilder sessionId = new StringBuilder();
		long createTime = System.currentTimeMillis();
		String ctLocal = transToLocal(createTime);
		sessionId.append(genUnit(ctLocal));
		String expLocal = transToLocal(expire);
		sessionId.append(genUnit(expLocal));
		String uidLocal = transToLocal(uid);
		sessionId.append(genUnit(uidLocal));
		String random = additional(FIX_WIDTH - sessionId.length() - 2);
		sessionId.append(genUnit(random));
		String verifyLocal = transToLocal(getVerifyCode(sessionId.toString()));
		sessionId.append(verifyLocal);

		session.setUid(uid);
		session.setSessionId(encode(switchPos(sessionId.toString())));
		session.setCreateTime(createTime);
		session.setExpire(expire);
		return session;
	}

	private static String genUnit(String val) {
		char prefix = LOCAL_SCALE.charAt(val.length());
		return prefix + val;
	}

	private static String additional(int length) {
		StringBuilder add = new StringBuilder();
		Random random = new Random();
		while (length > 0) {
			int index = random.nextInt(LOCAL_SCALE.length());
			add.append(LOCAL_SCALE.charAt(index));
			length--;
		}
		return add.toString();
	}

	/**
	 * 通过sessionId获取Udid 必须udid !=0 && isValid=true
	 * 
	 * @param sessionId
	 * @return
	 */
	public static SessionInfo getUdidBySession(String sessionId) {
		SessionInfo sessionInfo = new SessionInfo();
		if (sessionId == null) {
			sessionInfo.setValid(false);
			return sessionInfo;
		}
		sessionId = sessionId.trim();
		int length = sessionId.length();
		if (length < FIX_WIDTH || !isLocalScale(sessionId)) {
			sessionInfo.setValid(false);
			return sessionInfo;
		}
		sessionInfo.setSessionId(sessionId);
		sessionId = switchPos(decode(sessionId));
		long verifyCode = transToLong(sessionId.substring(length - 1));
		if (verifyCode != getVerifyCode(sessionId.substring(0, length - 1))) {
			sessionInfo.setValid(false);
			return sessionInfo;
		}
		List<String> vals = split(sessionId);
		if (vals.isEmpty() || vals.size() < 3) {
			sessionInfo.setValid(false);
			return sessionInfo;
		}
		sessionInfo.setCreateTime(transToLong(vals.get(0)));
		sessionInfo.setExpire(transToLong(vals.get(1)));
		sessionInfo.setUid(transToLong(vals.get(2)));
		return sessionInfo;
	}

	private static List<String> split(String sessionId) {
		List<String> list = new ArrayList<String>();
		int startIdx = 0;
		int cnt = 0;
		while (cnt < 3) {
			int bitLen = LOCAL_SCALE.indexOf(sessionId.charAt(startIdx));
			if (bitLen == 0) {
				list.add("");
				startIdx++;
			} else {
				int endIdx = startIdx + bitLen + 1;
				if (endIdx > sessionId.length())
					return Collections.emptyList();
				String value = sessionId.substring(startIdx + 1, endIdx);
				list.add(value);
				startIdx = endIdx;
			}
			cnt++;
		}
		return list;
	}

	private static String encode(String val) {
		StringBuffer ret = new StringBuffer();
		int i = 0;
		for (int j = val.length(); i < j; i++) {
			int index = LOCAL_SCALE.indexOf(val.charAt(i));
			ret.append(DICTIONARY.charAt(index));
		}
		return ret.toString();
	}

	private static String decode(String val) {
		StringBuffer ret = new StringBuffer();
		int i = 0;
		for (int j = val.length(); i < j; i++) {
			int index = DICTIONARY.indexOf(val.charAt(i));
			ret.append(LOCAL_SCALE.charAt(index));
		}
		return ret.toString();
	}

	public static String transToLocal(long val) {
		int length = LOCAL_SCALE.length();
		StringBuffer num = new StringBuffer();
		do {
			long div = val / (long) length;
			int remain = (int) (val % (long) length);
			val = div;
			num.append(LOCAL_SCALE.charAt(remain));
		} while (val != 0L);
		return num.reverse().toString();
	}

	private static long transToLong(String val) {
		long num = 0L;
		int length = LOCAL_SCALE.length();
		for (int i = 0, j = val.length(); i < j; i++) {
			long base = (long) Math.pow(length, j - i - 1);
			int multiple = LOCAL_SCALE.indexOf(val.charAt(i));
			num += (long) multiple * base;
		}
		return num;
	}

	private static String switchPos(String val) {
		StringBuffer first = new StringBuffer();
		StringBuffer last = new StringBuffer();
		int index = val.length() / 2;
		first.append(val.substring(0, index));
		last.append(val.substring(index));
		return first.reverse().append(last.reverse()).toString();
	}

	private static boolean isLocalScale(String val) {
		if (val == null)
			return false;
		else
			return val.matches((new StringBuilder()).append("[").append(LOCAL_SCALE.replaceAll("\\-", "\\\\\\-"))
					.append("]*").toString());
	}

	private static long getVerifyCode(String val) {
		long code = 0L;
		for (int i = 0, len = val.length(); i < len; i++)
			code += transToLong(val.substring(i, i + 1)) * (long) i * 63L;

		return code % (long) LOCAL_SCALE.length();
	}

	public static void main(String[] args) {
		// long base = 281474976710655L;
		// System.out.println(Long.MAX_VALUE - base);
		// System.out.println(System.currentTimeMillis());
		// System.out.println(transToLocal(90000000));
		// System.out.println(genUnit("oo"));
		// System.out.println("additional:" + additional(4));
		// System.out.println("getVerifyCode:" +
		// getVerifyCode("ccznLC80ID7wdl3EH0Cvd") + ", " +
		// getVerifyCode("ccznLC80Ia7wdl3EH0Cvd"));
		System.out.println(SessionUtil.getUdidBySession("XE2_P9x-DZIs333Odj0U").getUid());
	}
}
