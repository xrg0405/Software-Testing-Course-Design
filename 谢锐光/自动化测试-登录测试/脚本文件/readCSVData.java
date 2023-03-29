package AutoTest;
import java.io.FileReader;
import java.io.IOException;
import au.com.bytecode.opencsv.*;
import java.util.*;
public class readCSVData {
	private static String FILE_PATH;

	HashMap<String, String> parametervals = new HashMap<String, String>();

	public readCSVData(String filepath){
		FILE_PATH=filepath;
	}

	public String getFilePath(){
		return FILE_PATH;
	}

	public HashMap<String, String> readcsvData()throws IOException {
		CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			parametervals.put(nextLine[0], nextLine[1]);
		}
		reader.close();
		return parametervals;
	}

}
