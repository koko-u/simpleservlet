/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kozaki
 */
@AllArgsConstructor
public class Result {

    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String message;

}
