package mei.testtask.parser.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(chain = true)
public class ParsedData implements IParsedData {

    private final List<?> mainInfos;

    @Setter
    private List<ITotal> totals;

    public ParsedData(List<?> mainInfos) {
        this.mainInfos = mainInfos;
    }

}
