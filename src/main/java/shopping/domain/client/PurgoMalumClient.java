package shopping.domain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "purgoMalumClient",
        configuration = PurgoMalumConfig.class
)
public interface PurgoMalumClient {
    @GetMapping("/containsprofanity")
    boolean containsprofanity(@RequestParam("text") String text);
}
