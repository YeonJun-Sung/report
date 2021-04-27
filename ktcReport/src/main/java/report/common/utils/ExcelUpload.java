package report.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
public class ExcelUpload {
	public static List<Map<String, Object>> uploadExcelFile(MultipartFile excelFile, int sheetNum, int maxCol) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
			XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
			XSSFSheet sheet = workbook.getSheetAt(1);
			workbook.close();
			
			for(int i = 1; i < sheet.getLastRowNum() + 1; i++) {
				Map<String, Object> temp = new HashMap<String, Object>();
				XSSFRow row = sheet.getRow(i);

				if (null == row) {
					continue;
				}

				for(int j = 0;j < maxCol;j++) {
					XSSFCell cell = row.getCell(j);
					if (null != cell) {
						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if(DateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
								temp.put(Integer.toString(j), dateString);
							}
							else
								temp.put(Integer.toString(j), Float.toString((float) cell.getNumericCellValue()));
						} else {
							temp.put(Integer.toString(j), cell.getStringCellValue());
						}
					}
				}

				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}