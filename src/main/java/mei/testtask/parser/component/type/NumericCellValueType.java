package mei.testtask.parser.component.type;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Order(1)
@Component
public class NumericCellValueType implements ICellValueType {

    @Override
    public boolean check(final Cell cell) {
        return CellType.NUMERIC.equals(cell.getCellType());
    }

    @Override
    public Optional<Object> getValue(final Cell cell) {
        try {
            return Optional.of((int) cell.getNumericCellValue());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
