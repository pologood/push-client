package com.easou.novelpush.service;

import com.easou.novelpush.dto.UdidBaiduBean;
import com.easou.novelpush.dto.UserBaiduBean;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.impl.BaiduPushBeanSaveRedisServceImpl;

public class BaiduPushBeanSaveRedisServceTest {

	private static BaiduPushBeanSaveRedisServce service;
	static {
		try {
			service = new BaiduPushBeanSaveRedisServceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存udid信息
	 * 
	 * @param udidBaiduBean
	 * @throws PushNovelServiceException
	 */
	public void lpushUdidBaiduBean() throws PushNovelServiceException {
		// for (int i = 0; i < 500; i++) {
		// UdidBaiduBean baiduBean = new UdidBaiduBean();
		// baiduBean.setBaiduUserId(String.valueOf(i));
		// baiduBean.setChannelId(String.valueOf(i));
		// baiduBean.setOs("ios");
		// baiduBean.setUdid(String.valueOf(i));
		// baiduBean.setUpdateTime(i);
		// service.lpushUdidBaiduBean(baiduBean);
		// }
		UdidBaiduBean baiduBean = new UdidBaiduBean();
		baiduBean.setBaiduUserId("798005841644852678");
		baiduBean.setChannelId("3980461911541281171");
		baiduBean.setOs("android");
		baiduBean.setUdid("5838999C6886EE09A97C500B8FCEEEF6");
		baiduBean.setUpdateTime(1111111111111111L);
		service.lpushUdidBaiduBean(baiduBean);
		System.out.println("lpushUdidBaiduBean    ===over");
	}

	/**
	 * 获取udid信息
	 * 
	 * @param UdidBaiduBean
	 * @throws PushNovelServiceException
	 */
	public void rpopUdidBaiduBean() throws PushNovelServiceException {
		UdidBaiduBean rpopUdidBaiduBean = service.rpopUdidBaiduBean();
		System.out.println(rpopUdidBaiduBean.toString());
	}

	/**
	 * 保存user信息
	 * 
	 * @param userBaiduBean
	 * @throws PushNovelServiceException
	 */
	public void lpushUserBaiduBean() throws PushNovelServiceException {
		// for (int i = 0; i < 500; i++) {
		// UserBaiduBean baiduBean = new UserBaiduBean();
		// baiduBean.setOs("ios");
		// baiduBean.setUdid(String.valueOf(i));
		// baiduBean.setUserId(String.valueOf(i));
		// baiduBean.setUpdateTime(i);
		// service.lpushUserBaiduBean(baiduBean);
		// }

		UserBaiduBean baiduBean = new UserBaiduBean();
		baiduBean.setOs("android");
		baiduBean.setUdid("314251C37837F0DDE6708ACF4FC30F81");
		baiduBean.setUserId("39");
		baiduBean.setUpdateTime(1111111111L);
		service.lpushUserBaiduBean(baiduBean);
		System.out.println("lpushUserBaiduBean    ===over");

	}

	/**
	 * 获取user信息
	 * 
	 * @param UserBaiduBean
	 * @throws PushNovelServiceException
	 */
	public void rpopUserBaiduBean() throws PushNovelServiceException {
		UserBaiduBean rpopUserBaiduBean = service.rpopUserBaiduBean();
		System.out.println(rpopUserBaiduBean.toString());
	}

	public static void main(String[] args) {
		BaiduPushBeanSaveRedisServceTest t = new BaiduPushBeanSaveRedisServceTest();
		try {
			t.lpushUdidBaiduBean();
			t.lpushUserBaiduBean();
		} catch (PushNovelServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
