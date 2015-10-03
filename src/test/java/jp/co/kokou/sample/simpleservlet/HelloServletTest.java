package jp.co.kokou.sample.simpleservlet;

import java.io.IOException;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;
import jp.co.kokou.sample.service.PersonService;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author kozaki
 */
public class HelloServletTest {
    private static final ServletTester tester = new ServletTester();
    private static final PersonService service = mock(PersonService.class);

    private HttpTester request = new HttpTester();
    private HttpTester response = new HttpTester();

    @BeforeClass
    public static void startTester() throws Exception {
        ServletHolder servletHolder = tester.addServlet(HelloServlet.class, "/hello");
        tester.start();
        HelloServlet servlet = (HelloServlet) servletHolder.getServlet();
        servlet.setPersonService(service);

    }

    @AfterClass
    public static void stopTester() throws Exception {
        tester.stop();
    }


    @Test
    public void 正常なリクエストを受け付けた_HTTPステータスは200() throws Exception {
        when(service.getResult(any(Person.class))).thenReturn(new Result(0, "ok"));
        String json = "{ \"name\": \"yamada\", \"age\": 20, \"married\": false}";
        String req = createRequest(json);

        String res = tester.getResponses(req);
        response.parse(res);

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void 正常なリクエストを受け付けた_PersonServiceに応じたレスポンスを得る() throws Exception {
        when(service.getResult(any(Person.class))).thenReturn(new Result(0, "ok"));
        String json = "{ \"name\": \"yamada\", \"age\": 20, \"married\": false}";
        String req = createRequest(json);

        String res = tester.getResponses(req);
        response.parse(res);

        assertThat(response.getContent(), is(containsString("ok")));
    }

    @Test
    public void 予定外のキーを持つリクエストを受け付けた_ステータスは500となる() throws Exception {
        when(service.getResult(any(Person.class))).thenReturn(new Result(0, "ok"));
        String json = "{ \"name\": \"yamada\",\"age\": 20, \"married\": false、 \"hobby\": \"jogging\"}";
        String req = createRequest(json);

        String res = tester.getResponses(req);
        response.parse(res);

        assertThat(response.getStatus(), is(500));
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
