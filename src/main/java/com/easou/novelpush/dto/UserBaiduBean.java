package com.easou.novelpush.dto;

public class UserBaiduBean {

	private String os;
	private String udid;
	private String channelId;
	private String baiduUserId;
	private String userId;
	private long updateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getBaiduUserId() {
		return baiduUserId;
	}

	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserBaiduBean [os=" + os + ", udid=" + udid + ", channelId=" + channelId + ", baiduUserId="
				+ baiduUserId + ", userId=" + userId + ", updateTime=" + updateTime + "]";
	}

}
