package jp.co.kokou.sample.simpleservlet;

import java.io.IOException;

import jp.co.kokou.sample.model.Person;
import jp.co.kokou.sample.model.Result;
import jp.co.kokou.sample.model.Result.Status;
import jp.co.kokou.sample.service.PersonService;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author kozaki
 */
public class HelloServletTest {
    private static final ServletTester tester = new ServletTester();
    private static final PersonService service = mock(PersonService.class);

    private static final String goodRequestJson = "{ \"name\": \"yamada\", \"age\": 20, \"married\": false}";
    private static final Person goodPerson = new Person() {
        {
            setName("yamada");
            setAge(20);
            setMarried(false);
        }
    };
    private static final String badRequestJson = "{ \"name\": \"\", \"age\": 20, \"married\": false}";
    private static final Person badPerson = new Person() {
        {
            setName("");
            setAge(20);
            setMarried(false);
        }
    };


    @BeforeClass
    public static void startTester() throws Exception {
        ServletHolder servletHolder = tester.addServlet(HelloServlet.class, "/hello");
        tester.start();
        HelloServlet servlet = (HelloServlet) servletHolder.getServlet();
        servlet.setPersonService(service);
        when(service.getResult(goodPerson)).thenReturn(new Result(Status.OK, "ok"));
        when(service.getResult(badPerson)).thenReturn(new Result(Status.NG, "problematic"));
    }

    @AfterClass
    public static void stopTester() throws Exception {
        tester.stop();
    }


    @Test
    public void 正常なリクエストを受け付けた_HTTPステータスは200() throws Exception {
        String req = createRequest(goodRequestJson);

        HttpTester response = getResponse(req);

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void 正常なリクエストを受け付けた_PersonServiceに応じたレスポンスを得る() throws Exception {
        String req = createRequest(goodRequestJson);

        HttpTester response = getResponse(req);

        assertThat(response.getContent(), both(containsString("0")).and(containsString("ok")));
    }

    @Test
    public void 問題のあるリクエストを受け付けた_HTTPステータスは200() throws Exception {
        String req = createRequest(badRequestJson);

        HttpTester response = getResponse(req);

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void 問題のあるリクエストを受け付けた_PersonServiceに応じたレスポンスを得る() throws Exception {
        String req = createRequest(badRequestJson);

        HttpTester response = getResponse(req);

        assertThat(response.getContent(), both(containsString("1")).and(containsString("problematic")));
    }

    @Test
    public void 予定外のキーを持つ異常なリクエストを受け付けた_HTTPステータスは500となる() throws Exception {
        String abnormalJsonString = "{ \"name\": \"yamada\",\"age\": 20, \"married\": false、 \"hobby\": \"jogging\"}";
        String req = createRequest(abnormalJsonString);

        HttpTester response = getResponse(req);

        assertThat(response.getStatus(), is(500));
    }

    private String createRequest(String content) throws IOException {
        HttpTester request = new HttpTester();
        request.setMethod("POST");
        request.setHeader("Host", "tester");
        request.setURI("/hello");
        request.setVersion("HTTP/1.1");
        request.setContent(content);

        return request.generate();
    }

    private HttpTester getResponse(String request) throws Exception {
        String res = tester.getResponses(request);
        HttpTester response = new HttpTester();
        response.parse(res);
        return response;
    }

}
