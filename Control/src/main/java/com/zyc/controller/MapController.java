package com.zyc.controller;

import com.zyc.service.IPService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by YuChen Zhang on 17/10/23.
 */
@Controller("mapController")
@RequestMapping("/Houtai/map")
public class MapController {
    @Autowired
    @Qualifier("iPServiceImplements")
    IPService ipService;

    @RequestMapping("Map.do")
    public ModelAndView markMap(ModelAndView modelAndView){
        modelAndView.setViewName("/Houtai/map");
        return modelAndView;
    }
    @RequestMapping("markIp.do")
    public void markIp(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CountIpByCountry",ipService.selectCountByContry());
        out.print(jsonObject);
        out.close();
    }
}
