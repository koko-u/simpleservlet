package jp.co.kokou.sample.service;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;

/**
 * 個人情報を加工するサービス
 *
 * @author kozaki
 */
public interface PersonService {

    /**
     * 人を受け取ってそのチェック結果を返す
     *
     * @param person チェック対象となる人
     * @return 結果
     */
    public Result getResult(Person person);
}
