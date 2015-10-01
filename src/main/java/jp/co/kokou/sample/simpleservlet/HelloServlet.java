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
import lombok.AllArgsConstructor;

/**
 *
 * @author kozaki
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Handles the HTTP <code>POST</code> method.
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

                if (person.name == null) {
                    mapper.writeValue(out, new Result(1, "No name indicated."));
                    return;
                } else if (person.name.isEmpty()) {
                    mapper.writeValue(out, new Result(1, "Name is empty"));
                    return;
                }

                mapper.writeValue(out, new Result(0, "Success!"));
            }
        } catch (JsonProcessingException ex) {
            throw new ServletException("Invalid Json Request", ex);
        }
    }

    private static class Person {

        public String name;
        public int age;
        public boolean isMarried;
    }

    @AllArgsConstructor
    private static class Result {

        public int status;
        public String message;
    }

}
