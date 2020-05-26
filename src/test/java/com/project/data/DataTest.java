package com.project.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.project.pages.DataTestDTO;

public class DataTest {
	
	public  static HSSFWorkbook workbook;
	public  static HSSFSheet worksheet;
	public  static DataFormatter formatter = new DataFormatter();
	static int RowNu;
	

	public static Map<String, Integer> getHeaderSheetFromExcelFile(String SheetName, String file_location) throws IOException {
		//String file_location = "D:\\Study\\Selenium\\Data\\data4Test.xls";
		FileInputStream fileInputStream = new FileInputStream(file_location); // Excel sheet file location get mentioned
																				// here
		workbook = new HSSFWorkbook(fileInputStream); // get my workbook
		worksheet = workbook.getSheet(SheetName);// get my sheet from workbook
		HSSFRow firstRow = worksheet.getRow(0); // get my Row which start from 0
		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int maxColNum = firstRow.getLastCellNum(); // get last ColNum
		int minColNum = firstRow.getFirstCellNum(); // get first ColNum
		RowNu = RowNum;

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int colIndx = minColNum; colIndx < maxColNum; colIndx++) {
			HSSFCell cellHeader = firstRow.getCell(colIndx);
			map.put(cellHeader.getStringCellValue(), cellHeader.getColumnIndex());
		}

		return map;
	}

	
	public List<DataTestDTO> readExcelTC1 () throws IOException {
		  String SheetName = "testCase01";
		  DataTest data = new DataTest();
		  Map<String, Integer> headerSheet = data.getHeaderSheetFromExcelFile(SheetName,"D:\\Study\\Selenium\\Data\\data4Test.xls");
		  int RowNumLocal = data.RowNu;
		  
	      int invalidemailIndexColumn = headerSheet.get("Invalid Email");
	      List<DataTestDTO> listDataTestDTO = new ArrayList<DataTestDTO>();
			for (int iRow = 1; iRow < RowNumLocal; iRow++) // Loop work for Rows
			{
				HSSFRow row = worksheet.getRow(iRow);
				DataTestDTO dataTestDTO = new DataTestDTO();
				dataTestDTO.setEmail(formatter.formatCellValue(row.getCell(invalidemailIndexColumn)));
				listDataTestDTO.add(dataTestDTO);
//				System.out.println(listDataTestDTO.size());
			}
		return listDataTestDTO;
	}
	
	
	public static List<DataTestDTO> readExcelTC2 () throws IOException {
		
		String SheetName = "testCase02";																		
		DataTest data = new DataTest();
		Map<String, Integer> headerSheet = data.getHeaderSheetFromExcelFile(SheetName,"D:\\Study\\Selenium\\Data\\data4Test.xls");
		  int RowNumLocal = data.RowNu;
	      
		  int position_email = headerSheet.get("Email");
	      int position_pass = headerSheet.get("Pass");
	      int position_firstname = headerSheet.get("First Name");
	      int position_lastname = headerSheet.get("Last name");
	      int position_DAY_OF_BIRTH = headerSheet.get("DAY_OF_BIRTH");
	      int position_MONTH_OF_BIRTH = headerSheet.get("MONTH_OF_BIRTH");
	      int position_YEAR_OF_BIRTH = headerSheet.get("YEAR_OF_BIRTH");
	      int position_ADDRESS = headerSheet.get("ADDRESS");
	      int position_city = headerSheet.get("City");
	      int position_state = headerSheet.get("State");
	      int position_ZIPCODE = headerSheet.get("ZIPCODE");
	      int position_MOBILEPHONE = headerSheet.get("MOBILEPHONE");
	      int position_ALIAS = headerSheet.get("ALIAS");
      	
		List<DataTestDTO> listDataTestDTO = new ArrayList<DataTestDTO>();
		for (int iRow = 1; iRow < RowNumLocal; iRow++) // Loop work for Rows
		{
			HSSFRow row = worksheet.getRow(iRow);
			DataTestDTO dataTestDTO = new DataTestDTO();
			dataTestDTO.setEmail(formatter.formatCellValue(row.getCell(position_email)));
			dataTestDTO.setPass(formatter.formatCellValue(row.getCell(position_pass)));
			dataTestDTO.setFirst_Name(formatter.formatCellValue(row.getCell(position_firstname)));
			dataTestDTO.setLast_name(formatter.formatCellValue(row.getCell(position_lastname)));
			dataTestDTO.setDAY_OF_BIRTH(formatter.formatCellValue(row.getCell(position_DAY_OF_BIRTH)));
			dataTestDTO.setMONTH_OF_BIRTH(formatter.formatCellValue(row.getCell(position_MONTH_OF_BIRTH)));
			dataTestDTO.setYEAR_OF_BIRTH(formatter.formatCellValue(row.getCell(position_YEAR_OF_BIRTH)));
			dataTestDTO.setADDRESS(formatter.formatCellValue(row.getCell(position_ADDRESS)));
			dataTestDTO.setCity(formatter.formatCellValue(row.getCell(position_city)));
			dataTestDTO.setState(formatter.formatCellValue(row.getCell(position_state)));
			dataTestDTO.setZIPCODE(formatter.formatCellValue(row.getCell(position_ZIPCODE)));
			dataTestDTO.setMOBILEPHONE(formatter.formatCellValue(row.getCell(position_MOBILEPHONE)));
			dataTestDTO.setALIAS(formatter.formatCellValue(row.getCell(position_ALIAS)));
			listDataTestDTO.add(dataTestDTO);
		}
		return listDataTestDTO;
	}
	public static void main(String[] args) throws IOException {
		DataTest datatest = new DataTest();
		List<DataTestDTO> list = datatest.readExcelTC1();
		System.out.println(list);
	}
	
}
