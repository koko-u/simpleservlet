package jp.co.kokou.sample.simpleservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.service.DefaultPersonService;
import jp.co.kokou.sample.service.PersonService;

/**
 * サービスを実行するサーブレット
 *
 * @author kozaki
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    private static final ObjectMapper mapper = new ObjectMapper();
    transient private PersonService service = new DefaultPersonService();

    /**
     * クライアントからのPOSTリクエストに応答する
     * クライアントからは Person を表わす JSON フォーマットでコンテンツが送られてくる
     *
     * <p>
     * リクエストのフォーマットが想定外である場合は ServletException 例外を投げる
     * 受け取ったリクエストから Personインスタンスを構築して、結果は PersonService サービスにおまかせ
     *
     * <p>戻ってきた Result を再び JSONフォーマットに直してレスポンスする
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (BufferedReader reader = request.getReader()) {
            Person person = mapper.readValue(reader, Person.class);

            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {

                out.write(service.getResult(person).toJson());
            }
        } catch (JsonProcessingException ex) {
            throw new ServletException("Invalid Json Request", ex);
        }
    }

    /**
     * PersonServiceを切り替える
     *
     * @param service サービスの実装
     */
    void setPersonService(PersonService service) {
        this.service = service;
    }
}
