package jp.co.kokou.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 結果クラス
 *
 * @author kozaki
 */
@AllArgsConstructor
public class Result {

    /**
     * ステータス
     */
    @Getter
    private final Status status;

    /**
     * ステータスを補足するメッセージ
     */
    @Getter
    private final String message;

    public enum Status {

        OK, NG
    }

}
