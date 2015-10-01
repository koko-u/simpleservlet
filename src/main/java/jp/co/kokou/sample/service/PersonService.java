package jp.co.kokou.sample.service;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;

/**
 * 個人情報を加工するサービス
 *
 * @author kozaki
 */
public interface PersonService {
    public Result getResult(Person person);
}
