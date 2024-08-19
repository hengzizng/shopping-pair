package shopping.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import shopping.api.request.CreateProductRequest;
import shopping.api.response.ProductInfoResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate client;

    @BeforeEach
    void setUp() {
        client = restTemplateBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Test
    @DisplayName("상품 생성할 땐 Name, Price, ImageUrl가 필요하다.")
    void test() {
        // given
        final String url = "http://localhost:" + port + "/api/products";
        final CreateProductRequest request = new CreateProductRequest(
                "name",
                1000,
                "https://www.naver.com/image.png"
        );

        // when
        final ProductInfoResponse actual = client.postForEntity(url, request, ProductInfoResponse.class).getBody();

        // then
        assertThat(actual.getName()).isEqualTo("name");
        assertThat(actual.getPrice()).isEqualTo(1000);
        assertThat(actual.getImageUrl()).isEqualTo("https://www.naver.com/image.png");
    }
}
