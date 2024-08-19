package shopping.util;

import shopping.domain.Product;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;

public class IdGenerator {
    public static <T extends Object> Long getNextId(final TreeMap<Long, T> treeMap) {
        try {
            return treeMap.lastKey() + 1;
        } catch(NoSuchElementException e) {
            return 0L;
        }
    }
}
