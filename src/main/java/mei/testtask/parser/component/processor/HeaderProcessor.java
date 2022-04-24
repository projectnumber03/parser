package mei.testtask.parser.component.processor;

import mei.testtask.parser.model.dto.Header;
import mei.testtask.parser.model.dto.IHeader;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class HeaderProcessor implements IHeaderProcessor {

    @Value("${header.level}")
    private int level;

    @Override
    public List<IHeader> process(final XSSFSheet sheet) {
        final List<IHeader> IHeaders = new ArrayList<>();
        final AtomicInteger counter = new AtomicInteger();
        for (final Row row : sheet) {
            if (counter.intValue() == level) break;
            final List<Cell> cells = StreamSupport.stream(row.spliterator(), false)
                    .filter(c -> !StringUtils.isEmpty(c.toString()))
                    .collect(Collectors.toList());
            for (int i = 0; i < cells.size(); i++) {
                final int toInclusive = i == cells.size() - 1 ? row.getLastCellNum() - 1 : (cells.get(i + 1).getColumnIndex() - 1);
                final String name = cells.get(i).toString();
                final Range<Integer> range = Range.between(cells.get(i).getColumnIndex(), toInclusive);
                final IHeader parent = IHeaders.stream().filter(h -> h.getRange().containsRange(range) && (h.getLevel() == (counter.intValue() - 1))).findAny().orElse(null);
                IHeaders.add(new Header(counter.intValue(), name, range, parent));
            }
            counter.incrementAndGet();
        }
        return IHeaders;
    }

}
