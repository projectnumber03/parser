package mei.testtask.parser.component.processor;

import lombok.AllArgsConstructor;
import mei.testtask.parser.component.type.ICellValueType;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CellValueProcessor implements ICellValueProcessor {

    private final List<ICellValueType> cellValueTypes;

    @Override
    public Optional<Object> process(@Nullable final Cell cell) {
        if (Objects.isNull(cell)) {
            return Optional.empty();
        }
        for (final ICellValueType cellValueType : cellValueTypes) {
            if (cellValueType.check(cell)) {
                return cellValueType.getValue(cell);
            }
        }
        return Optional.empty();
    }

}
