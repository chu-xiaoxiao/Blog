package com.zyc.test;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zyc.model.User;
import org.junit.Test;


import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;


public class TestAdd {
	@Test
	public void testadd() throws UnsupportedEncodingException{
		User user = new User();
		try {
			user.setUserpassword(new BASE64Encoder().encode("Zhangyuchen1016".getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		user.setUsername("zyc");
	}
	@Test
	public void testJson(){
		String string = "{'address':'CN|\u8fbd\u5b81|\u6c88\u9633|None|UNICOM|0|0','content':{'address':'\u8fbd\u5b81\u7701\u6c88\u9633\u5e02','address_detail':{'city':'\u6c88\u9633\u5e02','city_code':58,'district':'','province':'\u8fbd\u5b81\u7701','street':'','street_number':''},'point':{'x':'123.43279092','y':'41.80864478'}},'status':0}";
		JSONObject jsonObject = new JSONObject();
		
	}
    @Test
    public void testThread(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<100000;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    while(true){
                        System.out.println("6");
                    }
                }
            });
        }
    }
}