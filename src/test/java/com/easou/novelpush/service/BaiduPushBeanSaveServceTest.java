package com.easou.novelpush.service;

import java.util.List;

import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.impl.BaiduPushBeanSaveServceImpl;

public class BaiduPushBeanSaveServceTest {
	private String bai_userId = "0123456789";
	private String userId = "0123456789";
	private String channelId = "0123456789";
	private String udid = "0123456789";
	private String os = "0123456789";
	private long updateTime = 11111111111L;

	private static BaiduPushBeanSaveServce service;
	static {
		try {
			service = new BaiduPushBeanSaveServceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws PushNovelServiceException {
		String userId = "26550132";
		List<UserBaiduBean> findBaiduUserByUserId = service.findBaiduUserByUserId(userId);
		
	}

	/**
	 * 系统初始化时，保存未登录用户的信息到mogo,是一个saveORupdate操作
	 * 
	 * @param udidBaiduBean
	 * @throws PushNovelServiceException
	 */
	public int saveUdidAndBaiDuUser() throws PushNovelServiceException {
		UdidBaiduBean baiduBean = new UdidBaiduBean();
		baiduBean.setBaiduUserId(bai_userId);
		baiduBean.setChannelId(channelId);
		baiduBean.setOs(os);
		baiduBean.setUdid(udid);
		baiduBean.setUpdateTime(updateTime);
		int saveUdidAndBaiDuUser = service.saveUdidAndBaiDuUser(baiduBean);
		return saveUdidAndBaiDuUser;
	}

	/**
	 * 用户登录时更新mongo里的userId，如果没有则添加
	 * 
	 * @param userBaiduBean
	 * @throws PushNovelServiceException
	 */
	public int saveOrUpdateUserAndBaiDuUser() throws PushNovelServiceException {
		UserBaiduBean baiduBean = new UserBaiduBean();
		baiduBean.setOs(os);
		baiduBean.setUdid(udid);
		baiduBean.setUserId(userId);
		baiduBean.setUpdateTime(updateTime);
		int saveOrUpdateUserAndBaiDuUser = service.saveOrUpdateUserAndBaiDuUser(baiduBean);
		return saveOrUpdateUserAndBaiDuUser;
	}

	/**
	 * 用户退出登录时删除mongo里的userId所关联信息
	 * 
	 * @param userBaiduBean
	 * @throws PushNovelServiceException
	 */
	public int deleteUserAndBaiDuUser() throws PushNovelServiceException {
		return 0;
	}

	public List<UserBaiduBean> findBaiduUserByUserId() throws PushNovelServiceException {
		List<UserBaiduBean> findBaiduUserByUserId = service.findBaiduUserByUserId(userId);
		return findBaiduUserByUserId;
	}

}
