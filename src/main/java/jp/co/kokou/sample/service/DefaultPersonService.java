package jp.co.kokou.sample.service;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;
import jp.co.kokou.sample.model.Result.Status;

/**
 * デフォルトのパーソン ⇒ 結果 サービス
 *
 * <p>
 * 名前が空である場合、null である場合は文句を言う
 * 年齢がマイナスの場合、100才を越える場合も文句を言う
 *
 * @author kozaki
 */
public class DefaultPersonService implements PersonService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Result getResult(Person person) {
        if (person.getName() == null) {
            return new Result(Status.NG, "No name indicated.");
        }
        if (person.getName().isEmpty()) {
            return new Result(Status.NG, "Name is empty");
        }
        if (person.getAge() < 0) {
            return new Result(Status.NG, "You are future person, are you?");
        }
        if (person.getAge() > 100) {
            return new Result(Status.NG, "You are too past man. Sorry...");
        }

        return new Result(Status.OK, "Success!");
    }

}
