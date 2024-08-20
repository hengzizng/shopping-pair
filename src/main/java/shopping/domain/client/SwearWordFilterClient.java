package shopping.domain.client;

public interface SwearWordFilterClient {
    boolean isCleanText(String text);

    boolean isNotCleanText(String text);
}
