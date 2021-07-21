package com.yaser.domain;

import com.yaser.model.Report;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Yaser
 */
public class WriteToExcelResult implements Constants {

    final static Logger logger = Logger.getLogger(WriteToExcelResult.class);

    public static void writeFileExcel(List<Report> newWriteExcel) {

        Object object[] = new Object[]{AD, SOYAD, DOGUM_TARIHI, DOGUM_YERI, MAIL,
                TELEFON, DURUM, CALISMA_DURUMU, UNIVERSITE, ESLESME_DURUMU};

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Excel Result Data");

        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
        data.put(1, object);
        logger.info("############## Write New Excel Starting ###############");
        for (int i = 0; i < newWriteExcel.size(); i++) {
            data.put(i + 2, new Object[]{
                    newWriteExcel.get(i).getName(),
                    newWriteExcel.get(i).getSurname(),
                    newWriteExcel.get(i).getBirthDate(),
                    newWriteExcel.get(i).getBirthPlace(),
                    newWriteExcel.get(i).getEmail(),
                    newWriteExcel.get(i).getPhone(),
                    newWriteExcel.get(i).getStatus(),
                    newWriteExcel.get(i).getWorkStatus(),
                    newWriteExcel.get(i).getUniversity(),
                    newWriteExcel.get(i).getMatchStatus()});
        }
        Set<Integer> keyset = data.keySet();

        int rowNum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            final String CREATE_EXCEL_PATH = "/Users/Yaser/Desktop/ExceltoExcelComparison/src/main/resources/report/ExcelReport.xlsx";
            FileOutputStream outputStream = new FileOutputStream(CREATE_EXCEL_PATH);
            workbook.write(outputStream);
            logger.info("############## Write New Excel Finished ###############");
            workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
