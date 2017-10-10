package com.zyc.util;

import com.zyc.model.Juzi;
import com.zyc.model.JuziTypeKey;
import jxl.Workbook;
import jxl.write.*;

import java.io.File;
import java.io.IOException;
import java.lang.Boolean;
import java.util.List;

/**
 * 创建一个xls文件并写入数据
 * 当flag为true时覆盖文件
 * Created by YuChen Zhang on 17/10/10.
 */
public class ExcleUtil {
    public void writetoExcle(String url, String wirtableSheetName,Object[][] result, Boolean flag, String... rolName) throws IOException, WriteException {
        File file = new File(url);
        if(file.exists()){
            if(flag==true){
                file.delete();
            }
        }else{
            if(!file.getParentFile().exists()) {
               file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
        WritableSheet writableSheet = writableWorkbook.createSheet(wirtableSheetName,0);
        WritableCellFormat wc = new WritableCellFormat();
        for(int i=0;i<rolName.length;i++){
            writableSheet.addCell(new Label(i, 0, rolName[i]));
        }
        for(int i=1;i<=result.length;i++){
            for(int j=0;j<result[i-1].length;j++){
                writableSheet.addCell(new Label(j, i, (String) result[i - 1][j]));
            }
        }
        writableWorkbook.write();
        writableWorkbook.close();
    }
}
