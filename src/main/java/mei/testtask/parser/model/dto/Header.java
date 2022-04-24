package mei.testtask.parser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.Range;

@Getter
@ToString
@AllArgsConstructor
public class Header implements IHeader {

    private final int level;

    private final String name;

    private final Range<Integer> range;

    private final IHeader parent;

}