package shopping.domain.test.fixture;

import com.github.curiousoddman.rgxgen.RgxGen;
import com.navercorp.fixturemonkey.FixtureMonkey;

import shopping.domain.entity.Name;

import java.util.List;

public class ProductTestFixture {
    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.create();

    private static final RgxGen nameRgxGen = RgxGen.parse(Name.regex);

    public static Name getName() {
        System.out.println(nameRgxGen.generate());
        System.out.println(nameRgxGen.generate());
        return fixtureMonkey.giveMeBuilder(Name.class)
                .set("value", nameRgxGen.generate())
                .sample();
    }

    public static List<Name> getNames() {
        return fixtureMonkey.giveMeBuilder(Name.class)
                .set("value", nameRgxGen.generate())
                .sampleList(100);
    }
}
