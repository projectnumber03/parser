package mei.testtask.parser.model.dto;

import lombok.Getter;
import mei.testtask.parser.annotation.ExcelCellName;

@Getter
public class Qliq implements Q {

    @ExcelCellName("data1")
    private Integer data1;

    @ExcelCellName("data2")
    private Integer data2;

}
