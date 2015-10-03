/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.co.kokou.sample.model.Result.Status;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 *
 * @author kozaki
 */
public class ResultTest {

    @Test
    public void 正常な場合_ステータスが0としてJSON文字列化される() throws JsonProcessingException {
        Result sut = new Result(Status.OK, "normal result");

        assertThat(sut.toJson(), both(containsString("0")).and(containsString("normal result")));
    }

    @Test
    public void 異常な場合_ステータス1としてJSON文字列化される() throws JsonProcessingException {
        Result sut = new Result(Status.NG, "abnormal one");

        assertThat(sut.toJson(), both(containsString("1")).and(containsString("abnormal one")));
    }

}
