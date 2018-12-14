package com.pricingPortal.helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;

	
	private static String testDataPath = Xls_Reader.class.getResource("/TestData/Testdata.xlsx").getPath();
	
	
	public static Xls_Reader forTestData(){
		
		
		return new Xls_Reader(System.getProperty("user.dir")+"/src/test/resources/TestData/Testdata.xlsx");
		}

	
	
	public Xls_Reader(String path) 
	{
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName)
	{
		  int index = workbook.getSheetIndex(sheetName);
		  if(index==-1)
		   return 0;
		  else{
		  sheet = workbook.getSheetAt(index);
		  int number=sheet.getLastRowNum()+1;
		  return number;
		  }
		  
	}
	// returns number of columns in a sheet	
			public int getColumnCount(String sheetName){

				
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				
				if(row==null)
					return -1;
				
				return row.getLastCellNum();
				
				
				
			}
	
			
			public int getColumnCount(String sheetName,int rowNumber){

				
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(rowNumber);
				
				if(row==null)
					return -1;
				
				return row.getLastCellNum();
				
			}
			
			public String getCellData(String sheetnam,int rownum,int colnum){
				String cellval = "";
				try{
					if(rownum<=0)
						return "";
					
					int index=workbook.getSheetIndex(sheetnam);
					if(index==-1)
						return "";
					sheet=workbook.getSheetAt(index);
					row=sheet.getRow(rownum-1);
					if(row==null)
						return "";
					
					cell=row.getCell(colnum);
					if(cell==null)
						return "";
					else
						try {
							cellval =  cell.getStringCellValue();
						}catch(Exception e)
						{
							try {
							double number = cell.getNumericCellValue();
							cellval =  String.valueOf(number);
							}catch(Exception e1)
							{
								Date dat = cell.getDateCellValue();
								cellval =  String.valueOf(dat);
							}
						}
					
				}catch(Exception e){
					
				}
				return cellval;
				
				
			}
	
			
			public boolean setCellData(String sheetName,int rowNum,int colNum, String data){
				  try{
				  fis = new FileInputStream(path); 
				  workbook = new XSSFWorkbook(fis);

				   if(rowNum<=0)
				   return false;
				  
				  int index = workbook.getSheetIndex(sheetName);
				 
				  if(index==-1)
				   return false;
				  
				  
				  sheet = workbook.getSheetAt(index);
				  

				   sheet.autoSizeColumn(colNum); 
				  row = sheet.getRow(rowNum-1);
				  if (row == null)
				   row = sheet.createRow(rowNum-1);
				  
				  cell = row.getCell(colNum); 
				  if (cell == null)
				         cell = row.createCell(colNum);
				  
				     cell.setCellValue(data);

				      fileOut = new FileOutputStream(path);

				   workbook.write(fileOut);

				      fileOut.close(); 

				   }
				  catch(Exception e){
				   e.printStackTrace();
				   return false;
				  }
				  return true;
				 }



}
