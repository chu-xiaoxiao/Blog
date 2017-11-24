package test.com.zyc.util;

import com.zyc.util.ExcleUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by YuChen Zhang on 17/10/10.
 */
public class excleTest {
    @Test
    public void testExcle() throws IOException, WriteException {
        ExcleUtil excleUtil = new ExcleUtil();
        Object[][] result = new Object[][]{{"123","123"},{"234","234"}};
    }
    @Test
    public void split(){
        System.out.println("."+"123.pdf".split("\\.")[1]);
    }
}
