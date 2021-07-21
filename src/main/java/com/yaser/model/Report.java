package com.yaser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yaser
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report extends Base {

    private String birthPlace;
    private String workStatus;
    private String university;
    private String status;
    private String matchStatus;

}
