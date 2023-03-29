package RegisterUnitTest;

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
public class EmailTest {
    private static final String FILE_PATH="C:\\Users\\HP\\Desktop\\大二下\\软件质量和检测\\软件测试大作业\\注册邮箱测试用例.csv";
    private String email;
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

    public EmailTest(String email, String expect) {
        this.email = email;
        this.expect = expect;
    }
    @Test
    public void test() throws Exception {
        function fun = new function();
        assertEquals(expect,fun.isEmail(email));
    }

}
