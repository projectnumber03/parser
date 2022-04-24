package mei.testtask.parser.component.aspect;

import mei.testtask.parser.model.db.IParsedData;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

public interface IPersistenceAspect {

    @Pointcut("execution(* mei.testtask.parser.service.XlsxParser.parse(..))")
    void callAtDataBaseAspectAfterReturning();

    @AfterReturning(pointcut = "callAtDataBaseAspectAfterReturning()", returning = "result")
    List<IParsedData> afterReturningCallAt(mei.testtask.parser.model.dto.IParsedData result);

}
