package com.easou.novelpush.dto;

/**
 * 用户消息类
 * 
 * @author xiaodong Date: 2014-8-5
 */
public class UserMessage {
	/** 用户id */
	private long userid;
	/** 消息内容 */
	private String content;

	public UserMessage() {
	}

	public UserMessage(long userid, String content) {
		this.userid = userid;
		this.content = content;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "UserMessage [userid=" + userid + ", content=" + content + "]";
	}

}
