package shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TreeMap;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        Integer nexKey = treeMap.lastKey() + 1;

        SpringApplication.run(Application.class, args);
    }
}
