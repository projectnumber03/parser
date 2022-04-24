package mei.testtask.parser.component.processor.total;

import mei.testtask.parser.model.dto.MainInfo;
import org.springframework.stereotype.Component;

import java.util.function.ToLongFunction;

@Component
public class FactQliqDataTwoTotalProcessor implements ITotalProcessor {

    @Override
    public ToLongFunction<? super MainInfo> getFunction() {
        return mainInfo -> mainInfo.getFact().getQliq().getData2();
    }

}
