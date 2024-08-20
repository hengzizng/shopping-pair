package shopping.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Service
public class PurgoMalumService implements SwearWordFilterClient {
    @Autowired
    private PurgoMalumClient purgoMalumClient;

    @Override
    public boolean isCleanText(String text) {
        return !isNotCleanText(text);
    }

    @Override
    public boolean isNotCleanText(String text) {
        return purgoMalumClient.containsprofanity(text);
    }
}
