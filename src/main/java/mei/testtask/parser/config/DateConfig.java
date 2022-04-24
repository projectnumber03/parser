package mei.testtask.parser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    @Bean
    public DateTimeFormatter randomDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy.MM.dd");
    }

}
