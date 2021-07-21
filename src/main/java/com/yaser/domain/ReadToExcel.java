package com.yaser.domain;

import com.yaser.model.FirstExcel;
import com.yaser.model.SecondExcel;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yaser
 */
public class ReadToExcel {

    final static Logger logger = Logger.getLogger(ReadToExcel.class);

    public static List<FirstExcel> readExcelFirst() throws IOException {

        logger.info("##############Starting Reading First Excel ###############");

        final String FIRST_EXCEL_PATH = "src/main/resources/inputData/Excel1.xlsx";

        List<FirstExcel> excelList1 = new ArrayList<FirstExcel>();
        FileInputStream inputStream = new FileInputStream(new File(FIRST_EXCEL_PATH));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();

            if (nextRow.getRowNum() == 0) {
                continue;
            }

            FirstExcel firstExcel = new FirstExcel();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();

                String cellValue = getCellValue(cell);
                calculateColumnIndex(firstExcel, columnIndex, cellValue);
            }
            excelList1.add(firstExcel);
        }
        Closed(inputStream, workbook);
        return excelList1;
    }


    public static List<SecondExcel> readExcelSecond() throws IOException {

        logger.info("##############Starting Reading Second Excel ###############");

        final String SECOND_EXCEL_PATH = "src/main/resources/inputData/Excel2.xlsx";
        List<SecondExcel> excelList2 = new ArrayList<SecondExcel>();
        SecondExcel secondExcel = new SecondExcel();

        FileInputStream inputStream = new FileInputStream(new File(SECOND_EXCEL_PATH));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();

            if (nextRow.getRowNum() == 0) {
                continue;
            }

            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();

                String cellValue = getCellValue(cell);

                calculateColumnIndex(secondExcel, columnIndex, cellValue);
            }
            excelList2.add(secondExcel);
        }
        Closed(inputStream, workbook);
        return excelList2;
    }


    private static String getCellValue(Cell cell) {

        if (cell != null) {
            switch (cell.getCellType()) {
                case BOOLEAN:
                    return cell.getBooleanCellValue() + "";
                case NUMERIC:
                    return NumberToTextConverter.toText(cell.getNumericCellValue());
                case STRING:
                    return cell.getStringCellValue();
                case BLANK:
                    return cell.getStringCellValue();
                case ERROR:
                    return cell.getErrorCellValue() + "";
            }
        }
        return "";
    }

    //Only just used debuging (CalculateColumn)
    private static FirstExcel calculateColumnIndex(FirstExcel firstExcel, int columnIndex, String cellValue) {

        switch (columnIndex + 1) {
            case 1:
                firstExcel.setName(cellValue);
                logger.debug("Reading First Excel Name :" + cellValue);
                break;
            case 2:
                firstExcel.setSurname(cellValue);
                logger.debug("Reading First Excel Surname :" + cellValue);
                break;
            case 3:
                firstExcel.setBirthDate(cellValue);
                logger.debug("Reading First Excel Birth Date :" + cellValue);
                break;
            case 4:
                firstExcel.setEmail(cellValue);
                logger.debug("Reading First Excel Email :" + cellValue);
                break;
            case 5:
                firstExcel.setPhone(cellValue);
                logger.debug("Reading First Excel Phone :" + cellValue);
                break;
            case 6:
                firstExcel.setStatus(cellValue);
                logger.debug("Reading First Excel Status :" + cellValue);
                break;
        }
        return firstExcel;

    }

    //Only just used debuging (CalculateColumn)
    private static SecondExcel calculateColumnIndex(SecondExcel secondExcel, int columnIndex, String cellValue) {

        switch (columnIndex + 1) {
            case 1:
                secondExcel.setName(cellValue);
                logger.debug("Reading Second Excel Name :" + cellValue);
                break;
            case 2:
                secondExcel.setSurname(cellValue);
                logger.debug("Reading Second Excel Surname :" + cellValue);
                break;
            case 3:
                secondExcel.setBirthDate(cellValue);
                logger.debug("Reading Second Excel Birth Date :" + cellValue);
                break;
            case 4:
                secondExcel.setBirthPlace(cellValue);
                logger.debug("Reading Second Excel Birth Place :" + cellValue);
                break;
            case 5:
                secondExcel.setEmail(cellValue);
                logger.debug("Reading Second Excel Email :" + cellValue);
                break;
            case 6:
                secondExcel.setPhone(cellValue);
                logger.debug("Reading Second Excel Phone :" + cellValue);
                break;
            case 7:
                secondExcel.setWorkStatus(cellValue);
                logger.debug("Reading Second Excel Work Status :" + cellValue);
                break;
            case 8:
                secondExcel.setUniversity(cellValue);
                logger.debug("Reading Second Excel University :" + cellValue);
                break;
        }
        return secondExcel;

    }

    private static void Closed(FileInputStream inputStream, Workbook workbook) throws IOException {
        workbook.close();
        inputStream.close();
        logger.info("Workbook and Input Stream closed.");
    }
}
