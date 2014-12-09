package com.easou.novelpush.dto;

public class UdidBaiduBean {
	private String os;
	private String udid;
	private String channelId;
	private String baiduUserId;
	private long updateTime;
	private String versionCode;//版本号
	private String address;//地域（省级单位）
	private String gsm;//运营商
	private String ch;//渠道
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
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	@Override
	public String toString() {
		return "UdidBaiduBean [os=" + os + ", udid=" + udid + ", channelId=" + channelId + ", baiduUserId=" + baiduUserId + ", updateTime=" + updateTime + ", versionCode=" + versionCode
				+ ", address=" + address + ", gsm=" + gsm + ", ch=" + ch + "]";
	}

	

}
