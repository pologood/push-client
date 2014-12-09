package com.easou.novelpush.constants;

public class Constants {
	/** 推送小说目录更新 缓存里面的key value是所有要更新小说的gid */
	public static final String PUSH_NOVEL_UPDATE = "push:novel:update";
	/** 用户个人消息推送key， value值是用户的userid */
	public static final String PUSH_USER_MESSAGE = "push:user:message";
	/** 保存推送用户udid和百度信息 value UdidBaiduBean */
	public static final String PUSH_UDID_BAIDU = "push:udid:baidu";
	/** 保存推送用户user和百度信息 value UserBaiduBean */
	public static final String PUSH_USER_BAIDU = "push:user:baidu";
}
