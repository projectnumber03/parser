package mei.testtask.parser.component.factory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomDateFactory implements IRandomDateFactory {

    @NonNull
    private final DateTimeFormatter randomDateTimeFormatter;

    @Value("${date.pattern}")
    private String datePatterm;

    @Override
    public LocalDate produce() {
        return LocalDate.parse(datePatterm, randomDateTimeFormatter)
                .plusDays(new Random().ints(fromInclusive(), toInclusive()).findFirst().orElse(0));
    }

    @Override
    public int fromInclusive() {
        return 1;
    }

    @Override
    public int toInclusive() {
        return 27;
    }

}
