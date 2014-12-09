package com.easou.novelpush;

import com.easou.novelpush.util.SessionInfo;
import com.easou.novelpush.util.SessionUtil;

public class TestSessionId {
	public static void main(String[] args) {
		String sessionId = "XEONqyk-DZJr3e3Odj0U";
		SessionInfo udidBySession = SessionUtil.getUdidBySession(sessionId);
		long uid = udidBySession.getUid();
		System.out.println(uid);
	}
}
