package mei.testtask.parser.component.processor.annotation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.annotation.ExcelCellName;
import mei.testtask.parser.component.processor.ICellValueProcessor;
import mei.testtask.parser.component.processor.IHeaderProcessor;
import mei.testtask.parser.model.dto.IHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Order(1)
@Component
@AllArgsConstructor
public class NameAnnotationAnnotationProcessor implements IAnnotationProcessor {

    private final IHeaderProcessor headerProcessor;

    private final ICellValueProcessor cellValueProcessor;

    public boolean check(final Field field) {
        return field.isAnnotationPresent(ExcelCellName.class);
    }

    public <T> void process(final T root, final Field field, final List<Cell> cells, final XSSFSheet sheet, final Function<Object, T> function) {
        try {
            final String name = field.getAnnotation(ExcelCellName.class).value();
            final List<IHeader> headersByName = headerProcessor.process(sheet).stream()
                    .filter(h -> h.getName().equals(name))
                    .collect(Collectors.toList());
            final Cell cell = cells.stream()
                    .filter(c -> headersByName.stream().map(IHeader::getRange).anyMatch(r -> r.contains(c.getColumnIndex())))
                    .findAny()
                    .orElse(null);
            final Optional<Object> value = cellValueProcessor.process(cell);
            if (value.isPresent()) {
                field.set(root, value.get());
            }
            cells.remove(cell);
        } catch (Exception e) {
            log.error("error creating new root", e);
        }
    }

}
