package com.zyc.controller;

import com.zyc.util.DownloadRecord;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;

/**
 * Created by YuChen Zhang on 17/10/30.
 */
@Controller
public class DownloadController {
    /**
     * 下载保存在服务器虚拟路径的文件
     *
     * @param pathbiaoti
     * @param path
     * @param request
     * @param filename
     * @return
     * @throws IOExceptioncreateCriteria
     */
    @RequestMapping(value = "/download/home/imgs/file/{pathbiaoti}/{path}.*")
    public void download(HttpServletResponse response, @PathVariable("pathbiaoti") String pathbiaoti,
                         @PathVariable("path") String path, HttpServletRequest request, @RequestParam("filename") String filename)
            throws IOException {
        File file2 = new File("/home/imgs/file/" + pathbiaoti + "/" + path);
        String dfileName = new String(filename.getBytes("gb2312"), "iso8859-1");
        DownloadRecord downloadRecord = new DownloadRecord(file2.getName(), file2.getAbsolutePath(), request);
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + file2.getName());
        response.setHeader("Content-Length", String.valueOf(file2.length()));
        //用于记录以完成的下载的数据量，单位是byte
        long downloadedLength = 0l;
        try {
            //打开本地文件流
            InputStream inputStream = new FileInputStream(file2.getAbsoluteFile());
            //激活下载操作
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                downloadedLength += b.length;
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (Exception e){
            //System.out.println(e.getMessage());
            downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
            // throw e;
        }
        downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
        downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
        downloadRecord.setLength(downloadedLength);
        //存储记录
    }

}
