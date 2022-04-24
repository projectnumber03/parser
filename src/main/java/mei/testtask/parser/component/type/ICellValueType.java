package mei.testtask.parser.component.type;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Optional;

public interface ICellValueType {

    boolean check(final Cell cell);

    Optional<Object> getValue(final Cell cell);

}
