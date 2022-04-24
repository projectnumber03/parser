package mei.testtask.parser.component.processor.annotation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class MainAnnotationProcessor implements IMainAnnotationProcessor {

    private final List<IAnnotationProcessor> annotationProcessors;

    @Override
    public <T> T process(@Nullable final T root, final List<Cell> cells, final XSSFSheet sheet) {
        for (final Field field : Optional.ofNullable(root).map(T::getClass).map(Class::getDeclaredFields).orElse(new Field[0])) {
            field.setAccessible(true);
            for (final IAnnotationProcessor processor : annotationProcessors) {
                if (processor.check(field)) {
                    processor.process(root, field, cells, sheet, (newRoot) -> process(newRoot, cells, sheet));
                }
            }
            field.setAccessible(false);
        }
        return root;
    }

}
