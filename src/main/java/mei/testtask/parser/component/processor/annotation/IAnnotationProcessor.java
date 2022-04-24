package mei.testtask.parser.component.processor.annotation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

public interface IAnnotationProcessor {

    boolean check(final Field field);

    <T> void process(final T root, final Field field, final List<Cell> cells, final XSSFSheet sheet, final Function<Object, T> supplier);

}
