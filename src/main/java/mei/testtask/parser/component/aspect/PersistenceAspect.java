package mei.testtask.parser.component.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.component.processor.persistence.IMainPersistenceProcessor;
import mei.testtask.parser.model.db.IParsedData;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Order(1)
@Component
@AllArgsConstructor
public class PersistenceAspect implements IPersistenceAspect {

    private final IMainPersistenceProcessor mainPersistenceProcessor;

    @Override
    @Pointcut("execution(* mei.testtask.parser.service.XlsxParser.parse(..))")
    public void callAtDataBaseAspectAfterReturning() {
    }

    @Override
    @AfterReturning(pointcut = "callAtDataBaseAspectAfterReturning()", returning = "result")
    public List<IParsedData> afterReturningCallAt(final mei.testtask.parser.model.dto.IParsedData result) {
        return mainPersistenceProcessor.process(result);
    }

}
