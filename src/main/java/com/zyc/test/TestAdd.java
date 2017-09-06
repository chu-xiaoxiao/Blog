package com.zyc.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.zyc.model.User;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;


public class TestAdd {
	@Test
	public void testadd() throws UnsupportedEncodingException{
		User user = new User();
		try {
			user.setPssword(new BASE64Encoder().encode("Zhangyuchen1016".getBytes("UTF-8")));
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
}
