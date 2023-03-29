package AutoTest;
import static org.junit.Assert.assertEquals;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import au.com.bytecode.opencsv.CSVReader;

@RunWith(Parameterized.class)  
public class testWork {
	
	private static WebDriver d;
    private String login;  
    private String password;   
	HashMap<String, String> params = new HashMap<String, String>();
	readCSVData td = new readCSVData("C:\\Users\\谢谢\\Desktop\\SearchData.csv"); // replace with your csv file location


	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\谢谢\\Desktop\\学习资料\\lab5\\selenium_example\\geckodriver.exe"); // replace with your geckodriver.exe csv file location
		d = new FirefoxDriver();
		params.putAll(td.readcsvData()); 
	}
	 @Parameters  
	    public static Collection perpareData() throws IOException { 
	    	String FILE_PATH="C:\\Users\\谢谢\\Desktop\\自动化登录测试用例.csv"; 
			CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
	    	String [] nextLine;
	    	String [][] strs=new String[95][2];
	    	int i=0;
			while ((nextLine = reader.readNext()) != null) {
	        String[] str =  {nextLine[0],nextLine[1]}; 
	        strs[i]=str;
	        i++;
			}
			reader.close();
	        return Arrays.asList(strs);  
	    }  
	      
	    public testWork(String login, String password){  
	    	super();
	        this.login = login;  
	        this.password = password;  
	    }  
	      
	    @Test 
	    public void testlogin() throws Exception{  
	    	   d.get(params.get("AppURL"));
	    	   d.findElement(By.name("username")).sendKeys(login);
	   		   d.findElement(By.name("upwd")).sendKeys(password);
	   		   d.findElement(By.className("btn")).click();
	   		   String a = d.findElement(By.xpath("/html/body/div/div/p")).getText();
	   		   assertEquals(a,"您现在可以："); 	   		   
	   		   d.quit();
	    } 
	@After
	public void tearDown() throws Exception {
		d.quit();
	}
}
