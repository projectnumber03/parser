package mei.testtask.parser.model.dto;

import java.util.List;

public interface IParsedData {

    IParsedData setTotals(List<ITotal> totals);

    List<?> getMainInfos();

    List<ITotal> getTotals();

}
