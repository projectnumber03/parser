package mei.testtask.parser.component.processor.total;

import mei.testtask.parser.model.dto.ITotal;
import mei.testtask.parser.model.dto.MainInfo;
import mei.testtask.parser.model.dto.Total;

import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

public interface ITotalProcessor {

    String TOTAL_PROCESSOR = "TotalProcessor";

    default <T extends MainInfo> ITotal process(final List<T> data) {
        return new Total(getName(), data.stream().collect(Collectors.groupingBy(MainInfo::getDate, Collectors.summingLong(getFunction()))));
    }

    default String getName() {
        return this.getClass().getSimpleName().replaceAll(TOTAL_PROCESSOR, "");
    }

    ToLongFunction<? super MainInfo> getFunction();

}
