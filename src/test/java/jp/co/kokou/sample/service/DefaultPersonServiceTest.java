package jp.co.kokou.sample.service;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;
import jp.co.kokou.sample.model.Result.Status;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author kozaki
 */
public class DefaultPersonServiceTest {

    @Test
    public void パラメータがまっとうな人間_問題ないとの結果() {
        DefaultPersonService sut = new DefaultPersonService();
        Person person = new Person() {
            {
                setName("yamada");
                setAge(20);
                setMarried(true);
            }
        };

        Result result = sut.getResult(person);

        assertThat(result.getStatus(), is(Status.OK));
        assertThat(result.getMessage(), is(containsString("Success")));

    }

    @Test
    public void パラメータの名前がnull_名前の指定がないとの結果() {
        DefaultPersonService sut = new DefaultPersonService();
        Person person = new Person() {
            {
                setName(null);
                setAge(20);
                setMarried(true);
            }
        };

        Result result = sut.getResult(person);

        assertThat(result.getStatus(), is(Status.NG));
        assertThat(result.getMessage(), is(containsString("No name")));
    }

    @Test
    public void パラメータの名前が空_名前が空との結果() {
        DefaultPersonService sut = new DefaultPersonService();
        Person person = new Person() {
            {
                setName("");
                setAge(20);
                setMarried(true);
            }
        };

        Result result = sut.getResult(person);

        assertThat(result.getStatus(), is(Status.NG));
        assertThat(result.getMessage(), is(containsString("empty")));
    }

    @Test
    public void 年齢がマイナス値_未来人であるとの結果() {
        DefaultPersonService sut = new DefaultPersonService();
        Person person = new Person() {
            {
                setName("yamada");
                setAge(-10);
                setMarried(true);
            }
        };

        Result result = sut.getResult(person);

        assertThat(result.getStatus(), is(Status.NG));
        assertThat(result.getMessage(), is(containsString("future")));
    }

    @Test
    public void 年齢が100才を越えている_古代人であるとの結果() {
        DefaultPersonService sut = new DefaultPersonService();
        Person person = new Person() {
            {
                setName("yamada");
                setAge(101);
                setMarried(true);
            }
        };

        Result result = sut.getResult(person);

        assertThat(result.getStatus(), is(Status.NG));
        assertThat(result.getMessage(), is(containsString("too past")));
    }
}
