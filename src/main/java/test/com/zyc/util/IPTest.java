package test.com.zyc.util;

import com.zyc.mapper.IpMapper;
import com.zyc.model.Ip;
import com.zyc.model.IpExample;
import com.zyc.service.IPService;
import com.zyc.util.IPUtils;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YuChen Zhang on 17/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
@Transactional
public class IPTest {
    @Autowired
    IPService ipService;

    @Autowired
    IpMapper ipMapper;

    @Test
    public void GetIp() throws IOException {
        String ip = "123.206.8.180";
        System.out.println(IPUtils.getLocationByGode(ip));
    }
    @Test
    @Rollback(false)
    public void addIp() throws IOException {
        IpExample ipExample = new IpExample();
        ipExample.getOredCriteria().add(ipExample.createCriteria().andXIsNull());
        ipExample.setLimit(100);
        ipExample.setOffset(900);
        List<Ip> ips = ipMapper.selectByExample(ipExample);
        for(Ip temp : ips){
            if(temp.getIp().contains("127")||temp.getIp().contains("0:0:0:0:0:0:0:1")){
                continue;
            }
            JSONObject jsonObject = JSONObject.fromObject(IPUtils.getLocationByGode(temp.getIp()));
            System.out.print(temp.getIp());
            if(jsonObject.get("rectangle").toString().contains("[")){
                continue;
            }
            temp.setX(jsonObject.get("rectangle").toString().split(";")[0]);
            temp.setY(jsonObject.get("rectangle").toString().split(";")[1]);
            System.out.println("\tx:"+temp.getX()+"y:"+temp.getY());
            ipService.updateIPByKey(temp);
        }
    }
    @Test
    public void testgetIPThread(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Integer count = 1000;
        Integer buchang = 100;
        Stack<Integer> counts = new Stack<>();
        for(int i=count-buchang;i>0;i-=100){
            counts.push(i);
        }
       for(Integer temp : counts)
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    IpExample ipExample = new IpExample();
                    ipExample.getOredCriteria().add(ipExample.createCriteria().andXIsNull());
                    ipExample.setLimit(buchang);
                    ipExample.setOffset(temp);
                    List<Ip> ips = null;
                    ips = ipMapper.selectByExample(ipExample);
                    for (Ip temp : ips) {
                        if (temp.getIp().contains("127") || temp.getIp().contains("0:0:0:0:0:0:0:1")) {
                            continue;
                        }
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = JSONObject.fromObject(IPUtils.getLocationByGode(temp.getIp()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.print(temp.getIp());
                        if (jsonObject.get("rectangle").toString().contains("[")) {
                            continue;
                        }
                        temp.setX(jsonObject.get("rectangle").toString().split(";")[0]);
                        temp.setY(jsonObject.get("rectangle").toString().split(";")[1]);
                        System.out.println("\tx:" + temp.getX() + "y:" + temp.getY());
                        ipService.updateIPByKey(temp);
                    }
                }
            });
        }
    }
