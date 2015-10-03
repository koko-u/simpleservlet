/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author kozaki
 */
@AllArgsConstructor
public class Result {

    @Getter
    private final int status;
    @Getter
    private final String message;

}
