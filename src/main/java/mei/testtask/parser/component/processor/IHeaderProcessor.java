package mei.testtask.parser.component.processor;

import mei.testtask.parser.model.dto.IHeader;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

public interface IHeaderProcessor {
    List<IHeader> process(XSSFSheet sheet);
}
