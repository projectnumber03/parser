package mei.testtask.parser.component.factory;

import java.time.LocalDate;

public interface IRandomDateFactory {

    LocalDate produce();

    int fromInclusive();

    int toInclusive();

}
