package com.easou.novelpush.service;

import java.util.List;

import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;

/**
 * 手机百度推送所需的用户信息
 * 
 * @author Administrator
 * 
 */
public interface BaiduPushBeanSaveServce {
	/**
	 * 系统初始化时，保存未登录用户的信息到mogo,是一个saveORupdate操作
	 * 
	 * @param udidBaiduBean
	 * @throws PushNovelServiceException
	 */
	public int saveUdidAndBaiDuUser(UdidBaiduBean udidBaiduBean) throws PushNovelServiceException;

	/**
	 * 用户登录时更新mongo里的userId，如果没有则添加
	 * 
	 * @param userBaiduBean
	 * @throws PushNovelServiceException
	 */
	public int saveOrUpdateUserAndBaiDuUser(UserBaiduBean userBaiduBean) throws PushNovelServiceException;

	/**
	 * 用户退出登录时删除mongo里的userId所关联信息
	 * 
	 * @param userBaiduBean
	 * @throws PushNovelServiceException
	 */
	public int deleteUserAndBaiDuUser(String sessionId) throws PushNovelServiceException;

	public List<UserBaiduBean> findBaiduUserByUserId(String userId) throws PushNovelServiceException;

}
