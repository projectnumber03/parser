package mei.testtask.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ParserApplication {

    public static void main(final String[] args) {
        Optional.ofNullable(args[0]).ifPresent(arg -> System.setProperty("fileName", arg));
        SpringApplication.run(ParserApplication.class, args);
    }

}
