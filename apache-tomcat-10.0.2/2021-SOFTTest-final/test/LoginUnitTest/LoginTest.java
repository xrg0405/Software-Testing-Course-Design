package LoginUnitTest;
import RegisterUnitTest.function;
import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class LoginTest {
    private static final String FILE_PATH="C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\登录测试用例.csv";
    private String uname;
    private String upwd;
    private String expect;

    @Parameterized.Parameters
    public static Collection perpareData() {
        List<String[]> temp = new ArrayList<String[]>();
        String[] nextLine;
        int i = 0;
        try {
            CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
            while((nextLine = reader.readNext()) != null) {
                if(i == 0){
                    nextLine[0] = nextLine[0].substring(1);
                }
                temp.add(nextLine);
                i++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
    }

    public LoginTest(String uname,String upwd, String expect) {
        this.uname = uname;
        this.upwd = upwd;
        this.expect = expect;
    }
    @Test
    public void test() throws Exception {
        function fun = new function();
        assertEquals(expect,fun.login(uname,upwd));
    }
}
