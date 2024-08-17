package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	public static HashMap<String, String> storeValues = new HashMap();

	public static List<HashMap<String, String>> data(String filepath, String sheetName) throws IOException //data method takes excel file path and sheetname.It returns excel data in the form of list collection.
	//
	 {
		
		List<HashMap<String, String>> mydata = new ArrayList<>();//list collectioncontains data in the form of hashmap
		
			FileInputStream file = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRows=sheet.getLastRowNum();
				
			XSSFRow headerRow=sheet.getRow(0);//Header row.These are  used as keys.
			
			
			for (int i = 1; i <= totalRows; i++) //1st row
				{
				XSSFRow currentRow = sheet.getRow(i);//here 1st row is current row
				
				HashMap<String, String> currentHash = new HashMap<String, String>();//hashmap creation
				
				for (int j = 0; j < currentRow.getLastCellNum(); j++) //first cell
					{
					XSSFCell currentCell = currentRow.getCell(j); //fetch firstcall data and store in currentcell
					currentHash.put(headerRow.getCell(j).toString(), currentCell.toString());//headerRow.getCell(j).toString()=fetch header row first cell data,convert to string,store in hashmap.
					//currentCell.toString()=fetch firstcell of first row ,convert to string,store in hashmap.
				 }
				mydata.add(currentHash);//push hashmap to list collection.Repeats and creates mutliple hashmaps.
				}
			file.close();
			
		return mydata;
	}
}
