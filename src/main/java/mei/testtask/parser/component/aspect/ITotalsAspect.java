package mei.testtask.parser.component.aspect;

import mei.testtask.parser.model.dto.IParsedData;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

public interface ITotalsAspect {

    @Pointcut("execution(* mei.testtask.parser.service.XlsxParser.parse(..))")
    void callAtDataBaseAspectAfterReturning();

    @AfterReturning(pointcut = "callAtDataBaseAspectAfterReturning()", returning = "result")
    IParsedData afterReturningCallAt(IParsedData result);

}
