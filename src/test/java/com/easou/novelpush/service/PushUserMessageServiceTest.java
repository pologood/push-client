package com.easou.novelpush.service;

import org.junit.Test;

import com.easou.novelpush.dto.UserMessage;
import com.easou.novelpush.exception.PushNovelServiceException;
import com.easou.novelpush.service.impl.PushUserMessageServiceImpl;

public class PushUserMessageServiceTest {
	private static PushUserMessageService service ;
    static{
        try {
        	service = new PushUserMessageServiceImpl();
        } catch (PushNovelServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void lpushUserrMessageTest(){
    	UserMessage userMessage = new UserMessage(123, "消息测试");
    	try {
			service.lpushUserMessage(userMessage);
		} catch (PushNovelServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Test
    public void rPopUserMessageTest(){
    	try {
			UserMessage userMessage = service.rPopUserMessage();
			System.out.println(userMessage);
		} catch (PushNovelServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
