package mei.testtask.parser.component.processor.annotation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

public interface IMainAnnotationProcessor {

    <T> T process(final T root, final List<Cell> cells, final XSSFSheet sheet);

}
