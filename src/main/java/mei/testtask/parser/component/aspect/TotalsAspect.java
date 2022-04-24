package mei.testtask.parser.component.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.component.processor.total.MainTotalProcessor;
import mei.testtask.parser.model.dto.IParsedData;
import mei.testtask.parser.model.dto.ITotal;
import mei.testtask.parser.model.dto.MainInfo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Order(2)
@Component
@AllArgsConstructor
public class TotalsAspect implements ITotalsAspect {

    private final MainTotalProcessor mainTotalProcessor;

    @Override
    @Pointcut("execution(* mei.testtask.parser.service.XlsxParser.parse(..))")
    public void callAtDataBaseAspectAfterReturning() {
    }

    @Override
    @AfterReturning(pointcut = "callAtDataBaseAspectAfterReturning()", returning = "result")
    public IParsedData afterReturningCallAt(final IParsedData result) {
        final List<ITotal> ITotals = mainTotalProcessor.getTotals(result.getMainInfos().stream()
                .map(mi -> (MainInfo) mi)
                .collect(Collectors.toList()));
        return result.setTotals(ITotals);
    }

}
