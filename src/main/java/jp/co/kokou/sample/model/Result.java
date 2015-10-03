package jp.co.kokou.sample.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 結果クラス
 *
 * @author kozaki
 */
@AllArgsConstructor
public class Result {

    private final ObjectMapper mapper = new ObjectMapper();

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

    /**
     * 自身をJSON文字列に変換する
     *
     * @return 変換されたJSON文字列
     * @throws JsonProcessingException JSONへの変換に失敗した
     */
    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    public enum Status {

        OK, NG
    }

}
