package shopping.api.controller;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import shopping.api.request.CreateProductRequest;
import shopping.api.response.ProductInfoResponse;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {
        "spring.cloud.openfeign.client.config.purgoMalumClient.url=http://localhost:${wiremock.server.port}"
})
public class ProductControllerAcceptanceTest {
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

        WireMock.stubFor(get(urlPathEqualTo("/containsprofanity"))
                .withQueryParam("text", equalTo("ㅅㅂ 아메리카노"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE)
                        .withBody("true")));

        WireMock.stubFor(get(urlPathEqualTo("/containsprofanity"))
                .withQueryParam("text", equalTo("아메리카노"))
                .willReturn(aResponse().withBody("false")));

        WireMock.stubFor(get(urlPathEqualTo("/containsprofanity"))
                .withQueryParam("text", equalTo("아메리카노 fuck"))
                .willReturn(aResponse().withBody("true")));
    }

    @Test
    @DisplayName("""
            Given 상품 이름이 "ㅅㅂ 아메리카노" 일 때
            When 상품을 생성하면
            Then 400 Bad Request를 응답한다
            And "올바르지 않은 상품 이름입니다."라고 응답한다.
            ```
            """)
    void swearWordCreateProductTest() {
        final String postUrl = "http://localhost:" + port + "/api/products";
        final CreateProductRequest request = new CreateProductRequest(
                "ㅅㅂ 아메리카노",
                1000,
                "https://www.naver.com/image.png"
        );

        assertThatRuntimeException().isThrownBy(() -> client.postForEntity(postUrl, request, String.class))
                .isInstanceOf(RestClientException.class);
    }

    @Test
    @DisplayName("""
            Given 기존 상품이 존재할 때
              And 변경하고자 하는 상품 이름이 "아메리카노 fuck" 일 때
            When 상품을 수정하면
            Then 400 Bad Request를 응답한다
            And "올바르지 않은 상품 이름입니다."라고 응답한다.
            ```
            """)
    void swearWordUpdateProductTest() {
        final ProductInfoResponse expected = 상품_생성(
                "아메리카노",
                1000,
                "https://www.naver.com/image.png"
        );
        final String url = "http://localhost:" + port + "/api/products/" + expected.getId();
        final CreateProductRequest request = new CreateProductRequest(
                "아메리카노 fuck",
                1000,
                "https://www.naver.com/image.png"
        );

        assertThatRuntimeException().isThrownBy(() -> client.postForEntity(url, request, String.class))
                .isInstanceOf(RestClientException.class);
    }

    private void 상품_수정(
            ProductInfoResponse expected,
            final String name,
            final int price,
            final String imageUrl
    ) {
        // when
        final String url = "http://localhost:" + port + "/api/products/" + expected.getId();
        final CreateProductRequest request = new CreateProductRequest(
                name,
                price,
                imageUrl
        );

        final ProductInfoResponse actual = client.postForEntity(url, request, ProductInfoResponse.class).getBody();

        // then
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getPrice()).isEqualTo(price);
        assertThat(actual.getImageUrl()).isEqualTo(imageUrl);
    }

    @Nullable
    private ProductInfoResponse 상품_생성(
            final String name,
            final int price,
            final String imageUrl
    ) {
        final String postUrl = "http://localhost:" + port + "/api/products";
        final CreateProductRequest request = new CreateProductRequest(
                name,
                price,
                imageUrl
        );
        final ProductInfoResponse actual = client.postForEntity(postUrl, request, ProductInfoResponse.class).getBody();

        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getPrice()).isEqualTo(price);
        assertThat(actual.getImageUrl()).isEqualTo(imageUrl);

        return actual;
    }

    private ProductInfoResponse 상품_조회(ProductInfoResponse expected) {
        // when
        final String url = "http://localhost:" + port + "/api/products";
        final ProductInfoResponse actual = client.exchange(
                        url,
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<ProductInfoResponse>>() {
                        }
                ).getBody()
                .stream()
                .filter(response -> response.getId().equals(expected.getId()))
                .findFirst()
                .orElseThrow();

        // then
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getPrice()).isEqualTo(expected.getPrice());
        assertThat(actual.getImageUrl()).isEqualTo(expected.getImageUrl());

        return actual;
    }

    private List<ProductInfoResponse> 상품_조회() {
        // when
        final String url = "http://localhost:" + port + "/api/products";
        final List<ProductInfoResponse> actual = client.exchange(
                        url,
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<ProductInfoResponse>>() {
                        }
                ).getBody();

        // then
        return actual;
    }
}
