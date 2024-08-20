package shopping.domain.test.fixture;

import com.github.curiousoddman.rgxgen.RgxGen;
import com.navercorp.fixturemonkey.FixtureMonkey;

import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FailoverIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import shopping.domain.entity.Name;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ProductTestFixture {
    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

    private static final RgxGen nameRgxGen = RgxGen.parse(Name.regex);

    public static String getNameString() {
        return fixtureMonkey.giveMeBuilder(String.class)
                .set((Supplier<String>) nameRgxGen::generate)
                .sample();
    }

    public static List<String> getNameStrings(int size) {
        return fixtureMonkey.giveMeBuilder(String.class)
                .set((Supplier<String>) nameRgxGen::generate)
                .sampleList(size);
    }


    public static Name getName() {
        return fixtureMonkey.giveMeBuilder(Name.class)
                .set("value", (Supplier<String>) nameRgxGen::generate)
                .sample();
    }

    public static List<Name> getNames(int size) {
        return fixtureMonkey.giveMeBuilder(Name.class)
                .set("value", (Supplier<String>) nameRgxGen::generate)
                .sampleList(size);
    }
}
