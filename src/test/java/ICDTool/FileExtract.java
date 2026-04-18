package ICDTool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileExtract {
	
	public ArrayList<String> getData(String ChangesList) throws IOException
	{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\MounikaChadalavada\\eclipse-workspace\\Changes.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		int Sheets = wb.getNumberOfSheets();
		XSSFSheet FirstSheet= wb.getSheetAt(Sheets-1);
		
		Iterator<Row> iR = FirstSheet.iterator();
		
		ArrayList<String> columnValues = new ArrayList<>();
		
        while (iR.hasNext()) 
        {
          Row row = iR.next();

          Cell cell = row.getCell(0);
                if (cell != null)
                {
                    columnValues.add(cell.toString());
                }
         }     
		
		return columnValues;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		System.out.println("after creating branch1");
	}

}
