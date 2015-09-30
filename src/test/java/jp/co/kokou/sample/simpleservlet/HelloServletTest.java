/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.kokou.sample.simpleservlet;

import java.io.IOException;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author kozaki
 */
public class HelloServletTest {
    private final static ServletTester tester = new ServletTester();

    private HttpTester request = new HttpTester();
    private HttpTester response = new HttpTester();

    @BeforeClass
    public static void startTester() throws Exception {
        tester.addServlet(HelloServlet.class, "/hello");
        tester.start();

    }

    @AfterClass
    public static void stopTester() throws Exception {
        tester.stop();
    }


    @Test
    public void サーブレットのテスト例() throws Exception {
        String json = "{ \"name\": \"yamada\", \"age\": 20, \"isMarried\": false}";
        String req = createRequest(json);

        String res = tester.getResponses(req);
        response.parse(res);

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContent(), is(containsString("Success!")));
    }

    private String createRequest(String content) throws IOException {
        request = new HttpTester();
        request.setMethod("POST");
        request.setHeader("Host", "tester");
        request.setURI("/hello");
        request.setVersion("HTTP/1.1");
        request.setContent(content);

        return request.generate();
    }

}
