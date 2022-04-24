package mei.testtask.parser;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParserApplication {

    public static void main(final String[] args) {
        if (!ArrayUtils.isEmpty(args)) {
            System.setProperty("fileName", args[0]);
        }
        SpringApplication.run(ParserApplication.class, args);
    }

}
