package am.ik.sorry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

public class SorryHandlerTest {

    private WebTestClient testClient;

    @BeforeAll
    void setUp() throws Exception {
        this.testClient = WebTestClient.bindToRouterFunction(new SorryHandler().routes())
            .build();
    }

    @Test
    void testHello() throws Exception {
        this.testClient.get().uri("/") //
            .exchange() //
            .expectStatus().isOk() //
            .expectBody(String.class).isEqualTo("Server under maintenance, sorry for the inconveniences.");
    }

    @Test
    void postHello() throws Exception {
        this.testClient.post().uri("/") //
            .exchange() //
            .expectStatus().isOk() //
            .expectBody(String.class).isEqualTo("Server under maintenance, sorry for the inconveniences.");
    }

    @Test
    void testFoo() throws Exception {
        this.testClient.get().uri("/foo") //
            .exchange() //
            .expectStatus().isOk() //
            .expectBody(String.class).isEqualTo("Server under maintenance, sorry for the inconveniences.");
    }

    @Test
    void testHelloJa() throws Exception {
        this.testClient.get().uri("/") //
            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP")
            .exchange() //
            .expectStatus().isOk() //
            .expectBody(String.class).isEqualTo("現在メンテナンス中です。ご迷惑をおかけします。");
    }
}
