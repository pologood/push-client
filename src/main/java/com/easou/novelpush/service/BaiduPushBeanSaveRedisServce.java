package com.easou.novelpush.service;

import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;

public interface BaiduPushBeanSaveRedisServce {
	/**
	 * 保存udid信息
	 * 
	 * @param udidBaiduBean
	 * @throws PushNovelServiceException
	 */
	public void lpushUdidBaiduBean(UdidBaiduBean udidBaiduBean) throws PushNovelServiceException;

	/**
	 * 获取udid信息
	 * 
	 * @param UdidBaiduBean
	 * @throws PushNovelServiceException
	 */
	public UdidBaiduBean rpopUdidBaiduBean() throws PushNovelServiceException;

	/**
	 * 保存user信息
	 * 
	 * @param userBaiduBean
	 * @throws PushNovelServiceException
	 */
	public void lpushUserBaiduBean(UserBaiduBean userBaiduBean) throws PushNovelServiceException;

	/**
	 * 获取user信息
	 * 
	 * @param UserBaiduBean
	 * @throws PushNovelServiceException
	 */
	public UserBaiduBean rpopUserBaiduBean() throws PushNovelServiceException;
}
