package mei.testtask.parser.model.dto;

import lombok.Getter;
import mei.testtask.parser.annotation.ExcelCellRange;

@Getter
public class Fact implements IndicationInfo {

    @ExcelCellRange("Qliq")
    private Q qliq;

    @ExcelCellRange("Qoil")
    private Q qoil;

}
