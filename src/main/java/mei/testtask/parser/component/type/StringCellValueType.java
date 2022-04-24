package mei.testtask.parser.component.type;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Order(2)
@Component
public class StringCellValueType implements ICellValueType {

    @Override
    public boolean check(final Cell cell) {
        return CellType.STRING.equals(cell.getCellType());
    }

    @Override
    public Optional<Object> getValue(final Cell cell) {
        try {
            return Optional.of(cell.getStringCellValue());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
