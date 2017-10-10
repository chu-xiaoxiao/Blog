package com.zyc.controller;

import com.zyc.util.HttpclientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by YuChen Zhang on 17/10/10.
 */
@Controller
@RequestMapping("temp")
public class testcontroller {
    @RequestMapping("/getj.do")
    public void getGV(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpclientUtil httpclientUtil = new HttpclientUtil();
        String result = httpclientUtil.getDocumentFromUriGet(request.getParameter("url"));
        PrintWriter printWriter = response.getWriter();
        printWriter.print(result);
        printWriter.close();
    }
}
