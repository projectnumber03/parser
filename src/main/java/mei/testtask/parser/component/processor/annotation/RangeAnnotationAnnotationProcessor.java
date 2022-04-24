package mei.testtask.parser.component.processor.annotation;

import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.annotation.ExcelCellRange;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Order(2)
@Component
public class RangeAnnotationAnnotationProcessor implements IAnnotationProcessor {

    @Value("${model.package}")
    private String modelPackage;

    public boolean check(final Field field) {
        return field.isAnnotationPresent(ExcelCellRange.class);
    }

    public <T> void process(final T root, final Field field, final List<Cell> cells, final XSSFSheet sheet, final Function<Object, T> function) {
        try {
            final String className = String.format("%s.%s", modelPackage, StringUtils.capitalize(field.getAnnotation(ExcelCellRange.class).value()));
            final Object newRoot = Class.forName(className).getDeclaredConstructor().newInstance();
            field.set(root, newRoot);
            function.apply(newRoot);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            log.error("error creating new root", e);
        } catch (NoSuchMethodException e) {
            log.error("error getting constructor of new root", e);
        } catch (ClassNotFoundException e) {
            log.error("class not found for new root", e);
        }
    }

}
