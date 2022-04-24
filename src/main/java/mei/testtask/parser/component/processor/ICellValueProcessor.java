package mei.testtask.parser.component.processor;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface ICellValueProcessor {
    Optional<Object> process(@Nullable Cell cell);
}
