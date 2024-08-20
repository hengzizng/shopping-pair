package shopping.api.controller;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import shopping.api.request.CreateProductRequest;
import shopping.api.request.UpdateProductRequest;
import shopping.api.response.ProductInfoResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

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
    void createTest() {
        상품_생성("name", 1000, "https://www.naver.com/image.png");
    }


    @Test
    @DisplayName("상품 조회 테스트")
    void getTest() {
        final ProductInfoResponse expected = 상품_생성("name", 1000, "https://www.naver.com/image.png");

        상품_조회(expected);
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteTest() {
        final ProductInfoResponse actual = 상품_생성("name", 1000, "https://www.naver.com/image.png");

        final String url = "http://localhost:" + port + "/api/products/" + actual.getId();
        assertThatNoException().isThrownBy(() -> client.delete(url));
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void updateTest() {
        // given
        final ProductInfoResponse expected = 상품_생성("name", 1000, "https://www.naver.com/image.png");

        상품_수정(expected, "수정", 1000, "https://www.naver.com/image.png");
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
