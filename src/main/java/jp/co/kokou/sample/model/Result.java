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
     * ステータス 0: 正常、1:異常
     */
    @Getter
    private final int status;

    /**
     * ステータスを補足するメッセージ
     */
    @Getter
    private final String message;

}
