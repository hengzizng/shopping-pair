package shopping.util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;

public class IdGenerator {
    public static Long getNextId(final TreeMap<Long, Object> treeMap) {
        try {
            return treeMap.lastKey() + 1;
        } catch(NoSuchElementException e) {
            return 0L;
        }
    }
}
