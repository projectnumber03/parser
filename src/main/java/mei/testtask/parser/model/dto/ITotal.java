package mei.testtask.parser.model.dto;

import java.time.LocalDate;
import java.util.Map;

public interface ITotal {

    String getName();

    Map<LocalDate, Long> getValues();

}
