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
public class Base {

    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String phone;

}
