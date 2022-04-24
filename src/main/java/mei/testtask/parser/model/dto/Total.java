package mei.testtask.parser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Total implements ITotal {

    private final String name;

    private final Map<LocalDate, Long> values;

}
