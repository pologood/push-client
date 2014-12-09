package com.easou.novelpush.service;

import org.junit.Assert;
import org.junit.Test;

import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.impl.PushNovelUpdateServiceImpl;

public class PushNovelUpdateServiceTest {
	private static PushNovelUpdateService service;
	private static PushNovelUpdateService service1;
	static {
		try {
			service = new PushNovelUpdateServiceImpl();
			service1 = new PushNovelUpdateServiceImpl();
		} catch (PushNovelServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lpushNovelGid() throws PushNovelServiceException {
		long gid = 11;
		service.lpushNovelGid(gid);
		service1.lpushNovelGid(gid);
	}

	@Test
	public void rpopNovelGid() throws PushNovelServiceException {
		long gid = 31321;
		Assert.assertEquals(gid, service.rpopNovelGid());
	}
}
