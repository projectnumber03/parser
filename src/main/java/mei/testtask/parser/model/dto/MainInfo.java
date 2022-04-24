package mei.testtask.parser.model.dto;

import lombok.Getter;
import lombok.Setter;
import mei.testtask.parser.annotation.ExcelCellName;
import mei.testtask.parser.annotation.ExcelCellRange;

import java.time.LocalDate;

@Getter
public class MainInfo {

    @ExcelCellName("id")
    private Integer id;

    @ExcelCellName("company")
    private String company;

    @ExcelCellRange("fact")
    private IndicationInfo fact;

    @ExcelCellRange("forecast")
    private IndicationInfo forecast;

    @Setter
    private LocalDate date;

}
