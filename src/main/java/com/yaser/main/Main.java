package com.yaser.main;

import com.yaser.domain.ExcelMatches;
import com.yaser.domain.ReadToExcel;
import com.yaser.domain.WriteToExcelResult;
import com.yaser.model.FirstExcel;
import com.yaser.model.Report;
import com.yaser.model.SecondExcel;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @author Yaser
 */
public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        List<FirstExcel> excelList1 = ReadToExcel.readExcelFirst();
        List<SecondExcel> excelList2 = ReadToExcel.readExcelSecond();
        List<Report> findRecords = ExcelMatches.findMatch(excelList1, excelList2);
        WriteToExcelResult.writeFileExcel(findRecords);
        logger.info("############## Finish Matches project ###############");
        logger.info("############## The output is written. ###############");

    }
}
