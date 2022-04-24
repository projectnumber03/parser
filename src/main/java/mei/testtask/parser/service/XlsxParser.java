package mei.testtask.parser.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import mei.testtask.parser.component.factory.IMainInfoFactory;
import mei.testtask.parser.component.processor.annotation.IMainAnnotationProcessor;
import mei.testtask.parser.exception.ParsingErrorException;
import mei.testtask.parser.model.dto.IParsedData;
import mei.testtask.parser.model.dto.MainInfo;
import mei.testtask.parser.model.dto.ParsedData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XlsxParser implements IParser {

    @NonNull
    final IMainInfoFactory IMainInfoFactory;

    @NonNull
    final IMainAnnotationProcessor annotationProcessor;

    @Value("${header.level}")
    int level;

    @Value("${sheet.number}")
    int sheetNumber;

    @Override
    public <T extends MainInfo> IParsedData parse(final InputStream is) throws ParsingErrorException {
        return parse(is, MainInfo.class);
    }

    private <T extends MainInfo> IParsedData parse(final InputStream is, final Class<T> clazz) throws ParsingErrorException {
        try (is; final XSSFWorkbook wb = new XSSFWorkbook(is)) {
            final XSSFSheet sheet = wb.getSheetAt(sheetNumber);
            final List<T> mainInfos = StreamSupport.stream(sheet.spliterator(), false)
                    .skip(level)
                    .map(row -> StreamSupport.stream(row.spliterator(), false))
                    .map(cells -> cells.collect(Collectors.toList()))
                    .map(cells -> annotationProcessor.process(IMainInfoFactory.produce(clazz), cells, sheet))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            return new ParsedData(mainInfos);
        } catch (IOException e) {
            log.error("parsing error", e);
            throw new ParsingErrorException();
        }
    }

}
