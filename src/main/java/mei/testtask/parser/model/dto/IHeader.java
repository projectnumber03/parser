package mei.testtask.parser.model.dto;

public interface IHeader {
    int getLevel();

    String getName();

    org.apache.commons.lang3.Range<Integer> getRange();

    IHeader getParent();

    String toString();
}
