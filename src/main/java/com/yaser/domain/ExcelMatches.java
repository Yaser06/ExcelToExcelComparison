package com.yaser.domain;

import com.sun.tools.javac.util.Assert;
import com.yaser.model.FirstExcel;
import com.yaser.model.Report;
import com.yaser.model.SecondExcel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yaser
 */

public class ExcelMatches {
    final static Logger logger = Logger.getLogger(ExcelMatches.class);

    public static List<Report> findMatch(List<FirstExcel> excelList1, List<SecondExcel> excelList2) {
        logger.info("##############Starting Matches Excel ###############");
        List<Report> newExcel = new ArrayList<Report>();

        logger.info("############## Write New List Starting ###############");
        for (int i = 0; i < excelList1.size(); i++) {

            String matchStatus = "Dogru Eşleşme.";

            String nameExcel1 = excelList1.get(i).getName() != null ? excelList1.get(i).getName() : "";
            String nameExcel2 = excelList2.get(i).getName() != null ? excelList2.get(i).getName() : "";

            String lastNameExcel1 = excelList1.get(i).getSurname() != null ? excelList1.get(i).getSurname() : "";
            String lastNameExcel2 = excelList2.get(i).getSurname() != null ? excelList2.get(i).getSurname() : "";

            String telephoneExcel1 = excelList1.get(i).getPhone() != null ? excelList1.get(i).getPhone() : "";
            String telephoneExcel2 = excelList2.get(i).getPhone() != null ? excelList2.get(i).getPhone() : "";

            String mailExcel1 = excelList1.get(i).getEmail() != null ? excelList1.get(i).getEmail() : "";
            String mailExcel2 = excelList2.get(i).getEmail() != null ? excelList2.get(i).getEmail() : "";


            if ((nameExcel1.equals(nameExcel2)) && (lastNameExcel1.equals(lastNameExcel2))) {
                if ((mailExcel1.isEmpty() && telephoneExcel1.isEmpty())
                        || (mailExcel2.isEmpty() && telephoneExcel2.isEmpty())) {
                    matchStatus = "Mail ve telefon numaraları boş olduğu için kontrole alınmadı.";
                } else {
                    if (telephoneExcel1.equals(telephoneExcel2)) {
                        matchStatus += "Telefon numaraları eşleşiyor.";
                    } else
                        matchStatus = "Telefon numaraları eşleşmiyor.";
                    if (!mailExcel1.isEmpty() && !mailExcel2.isEmpty()) {
                        if (mailExcel1.equals(mailExcel2)) {
                            matchStatus += "Mailler eşleşiyor.";
                        } else
                            matchStatus = matchStatus + "Mailler eşleşmiyor.";
                    }
                }
            }



            Report newWriteExcel = new Report();
            newWriteExcel.setName(excelList1.get(i).getName());
            newWriteExcel.setSurname(excelList1.get(i).getSurname());
            newWriteExcel.setBirthDate(excelList1.get(i).getBirthDate());
            newWriteExcel.setBirthPlace(excelList2.get(i).getBirthPlace());
            newWriteExcel.setEmail(excelList1.get(i).getEmail());
            newWriteExcel.setPhone(excelList1.get(i).getPhone());
            newWriteExcel.setStatus(excelList1.get(i).getStatus());
            newWriteExcel.setWorkStatus(excelList2.get(i).getWorkStatus());
            newWriteExcel.setUniversity(excelList2.get(i).getUniversity());
            newWriteExcel.setMatchStatus(matchStatus);
            newExcel.add(newWriteExcel);
        }
        logger.info("############## Write New List Finished ###############");
        return newExcel;
    }

}
