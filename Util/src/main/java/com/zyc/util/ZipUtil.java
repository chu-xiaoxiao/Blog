package com.zyc.util;

import java.io.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by YuChen Zhang on 17/11/14.
 */
public class ZipUtil {
    public void Zip() throws IOException {
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(new FileOutputStream(new File("e:\\1.zip")),new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);

        zipOutputStream.setComment("zip test");
        ZipEntry zipEntry = new ZipEntry("1.xls");
        zipEntry.setComment("test 1.xls");
        zipOutputStream.putNextEntry(zipEntry);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("e:\\1.xls")));
        byte[] b = new byte[1024];
        while(bufferedInputStream.read(b)!=-1){
            bufferedOutputStream.write(b);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}