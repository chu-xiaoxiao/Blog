package com.zyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by YuChen Zhang on 17/10/23.
 */
@Controller("mapController")
@RequestMapping("/Houtai/map")
public class MapController {
    @RequestMapping("markMap.do")
    public ModelAndView markMap(ModelAndView modelAndView){
        modelAndView.setViewName("/Houtai/map");
        return modelAndView;
    }
}
