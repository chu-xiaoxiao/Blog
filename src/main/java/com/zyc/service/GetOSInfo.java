package com.zyc.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 获取操作信息和jvm信息的服务接口
 * Created by YuChen Zhang on 17/09/27.
 */
@Service("getOSInfo")
public interface GetOSInfo {
    JSONObject getJVM();
    JSONObject getOS();
}
