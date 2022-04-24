package mei.testtask.parser.component.aspect;

import mei.testtask.parser.model.db.IParsedData;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;


public interface IDataBaseAspect {

    @Pointcut("execution(* mei.testtask.parser.component.processor.persistence.MainPersistenceProcessor.process(..))")
    void callAtMainPersistenceProcessorAfterReturning();

    @AfterReturning(pointcut = "callAtMainPersistenceProcessorAfterReturning()", returning = "result")
    void afterReturningCallAt(final List<IParsedData> result);

}
