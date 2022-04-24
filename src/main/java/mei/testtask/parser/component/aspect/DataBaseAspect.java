package mei.testtask.parser.component.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.model.db.IParsedData;
import mei.testtask.parser.service.IParsedDataService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class DataBaseAspect implements IDataBaseAspect {

    private final IParsedDataService parsedDataService;

    @Override
    @Pointcut("execution(* mei.testtask.parser.component.processor.persistence.MainPersistenceProcessor.process(..))")
    public void callAtMainPersistenceProcessorAfterReturning() {
    }

    @Override
    @AfterReturning(pointcut = "callAtMainPersistenceProcessorAfterReturning()", returning = "result")
    public void afterReturningCallAt(final List<IParsedData> result) {
        log.debug(String.format("saving %d records", result.size()));
        parsedDataService.saveAll(result);
        log.debug("saving complete");
    }

}
