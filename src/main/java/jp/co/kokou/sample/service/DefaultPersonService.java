/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.service;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;

/**
 * デフォルトのパーソン ⇒ 結果 サービス
 *
 * 名前が空である場合、null である場合は文句を言う
 * 年齢がマイナスの場合、100才を越える場合も文句を言う
 *
 * @author kozaki
 */
public class DefaultPersonService implements PersonService {

    @Override
    public Result getResult(Person person) {
        if (person.getName() == null) {
            return new Result(1, "No name indicated.");
        }
        if (person.getName().isEmpty()) {
            return new Result(1, "Name is empty");
        }
        if (person.getAge() < 0) {
            return new Result(1, "You are future person, are you?");
        }
        if (person.getAge() > 100) {
            return new Result(1, "You are too past man. Sorry...");
        }

        return new Result(0, "Success!");
    }

}
