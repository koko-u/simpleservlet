package jp.co.kokou.sample.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 人クラス
 *
 * @author kozaki
 */
@NoArgsConstructor
@EqualsAndHashCode
public class Person {

    /**
     * 名前
     */
    @Getter
    @Setter
    private String name;

    /**
     * 年齢
     */
    @Getter
    @Setter
    private int age;

    /**
     * 既婚／未婚フラグ
     */
    @Getter
    @Setter
    private boolean married;

}
