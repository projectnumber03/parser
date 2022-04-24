package mei.testtask.parser.service;

import mei.testtask.parser.exception.ParsingErrorException;
import mei.testtask.parser.model.dto.IParsedData;
import mei.testtask.parser.model.dto.MainInfo;

import java.io.InputStream;

public interface IParser {

    <T extends MainInfo> IParsedData parse(InputStream is, Class<T> clazz) throws ParsingErrorException;

    <T extends MainInfo> IParsedData parse(InputStream is) throws ParsingErrorException;

}
