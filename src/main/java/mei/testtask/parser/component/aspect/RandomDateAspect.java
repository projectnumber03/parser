package mei.testtask.parser.component.aspect;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.component.factory.IRandomDateFactory;
import mei.testtask.parser.model.dto.IParsedData;
import mei.testtask.parser.model.dto.MainInfo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Order(3)
@Component
@AllArgsConstructor
public class RandomDateAspect implements IRandomDateAspect {

    @NonNull
    private final IRandomDateFactory randomDateFactory;

    @Override
    @Pointcut("execution(* mei.testtask.parser.service.XlsxParser.parse(..))")
    public void callAtDataBaseAspectAfterReturning() {
    }

    @Override
    @AfterReturning(pointcut = "callAtDataBaseAspectAfterReturning()", returning = "result")
    public IParsedData afterReturningCallAt(final IParsedData result) {
        result.getMainInfos().stream()
                .map(mi -> (MainInfo) mi)
                .forEach(mi -> mi.setDate(randomDateFactory.produce()));
        return result;
    }

}
