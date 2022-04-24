package mei.testtask.parser.component.processor.total;

import lombok.AllArgsConstructor;
import mei.testtask.parser.model.dto.ITotal;
import mei.testtask.parser.model.dto.MainInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MainTotalProcessor {

    private final List<ITotalProcessor> processors;

    public <T extends MainInfo> List<ITotal> getTotals(final List<T> data) {
        return processors.stream()
                .map(processor -> processor.process(data))
                .collect(Collectors.toList());
    }

}
