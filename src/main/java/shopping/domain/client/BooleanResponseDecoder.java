package shopping.domain.client;

import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class BooleanResponseDecoder implements Decoder {

    private final Decoder delegate;

    public BooleanResponseDecoder(final Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(
            final Response response,
            final Type type
    ) throws IOException {
        if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            final String body = (String) delegate.decode(response, String.class);
            return Boolean.parseBoolean(body);
        }
        return delegate.decode(response, type);
    }
}