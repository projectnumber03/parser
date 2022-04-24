package mei.testtask.parser;

import mei.testtask.parser.component.processor.persistence.FactQliqDataPersistenceProcessor;
import mei.testtask.parser.component.processor.persistence.FactQoilDataPersistenceProcessor;
import mei.testtask.parser.component.processor.persistence.ForecastQliqDataPersistenceProcessor;
import mei.testtask.parser.component.processor.persistence.ForecastQoilDataPersistenceProcessor;
import mei.testtask.parser.model.dto.IParsedData;
import mei.testtask.parser.model.dto.MainInfo;
import mei.testtask.parser.repository.ParsedDataRepository;
import mei.testtask.parser.service.ParsedDataService;
import mei.testtask.parser.service.XlsxParser;
import org.apache.commons.codec.Resources;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
class ParserTest {

    @Autowired
    private XlsxParser parser;

    @MockBean
    private ParsedDataRepository repository;

    @MockBean
    private ParsedDataService parsedDataService;

    @MockBean
    private FactQliqDataPersistenceProcessor factQliqDataPersistenceProcessor;

    @MockBean
    private FactQoilDataPersistenceProcessor factQoilDataPersistenceProcessor;

    @MockBean
    private ForecastQliqDataPersistenceProcessor forecastQliqDataPersistenceProcessor;

    @MockBean
    private ForecastQoilDataPersistenceProcessor forecastQoilDataPersistenceProcessor;

    @Test
    void shouldParseAndSaveToDB() {
        final String[][] testDataMatrix =
                {
                        {"1", "company1", "10", "20", "30", "40", "12", "22", "15", "25"}
                        , {"2", "company2", "11", "21", "31", "41", "13", "23", "20", "30"}
                        , {"3", "company1", "12", "22", "32", "42", "14", "24", "25", "35"}
                        , {"4", "company2", "13", "23", "33", "43", "15", "25", "30", "40"}
                        , {"5", "company1", "14", "24", "34", "44", "16", "26", "35", "45"}
                        , {"6", "company2", "15", "25", "35", "45", "17", "27", "40", "50"}
                        , {"7", "company1", "16", "26", "36", "46", "18", "28", "45", "55"}
                        , {"8", "company2", "17", "27", "37", "47", "19", "29", "50", "60"}
                        , {"9", "company1", "18", "28", "38", "48", "20", "30", "55", "65"}
                        , {"10", "company2", "19", "29", "39", "49", "21", "31", "60", "70"}
                        , {"11", "company1", "20", "30", "40", "50", "22", "32", "65", "75"}
                        , {"12", "company2", "21", "31", "41", "51", "23", "33", "70", "80"}
                        , {"13", "company1", "22", "32", "42", "52", "24", "34", "75", "85"}
                        , {"14", "company2", "23", "33", "43", "53", "25", "35", "80", "90"}
                        , {"15", "company1", "24", "34", "44", "54", "26", "36", "85", "95"}
                        , {"16", "company2", "25", "35", "45", "55", "27", "37", "90", "100"}
                        , {"17", "company1", "26", "36", "46", "56", "28", "38", "95", "105"}
                        , {"18", "company2", "27", "37", "47", "57", "29", "39", "100", "110"}
                        , {"19", "company1", "28", "38", "48", "58", "30", "40", "105", "115"}
                        , {"20", "company2", "29", "39", "49", "59", "31", "41", "110", "120"}

                };
        final IParsedData data = parser.parse(Resources.getInputStream("Задание.xlsx"));
        final List<Object[]> list = data.getMainInfos().stream().map(mi -> (MainInfo) mi).map(this::mapToList).map(List::toArray).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            assertArrayEquals(testDataMatrix[i], list.get(i));
        }
        Mockito.verify(parsedDataService, Mockito.times(1)).saveAll(Mockito.anyList());
        Mockito.verify(factQliqDataPersistenceProcessor, Mockito.times(testDataMatrix.length)).process(Mockito.any(MainInfo.class));
        Mockito.verify(factQoilDataPersistenceProcessor, Mockito.times(testDataMatrix.length)).process(Mockito.any(MainInfo.class));
        Mockito.verify(forecastQliqDataPersistenceProcessor, Mockito.times(testDataMatrix.length)).process(Mockito.any(MainInfo.class));
        Mockito.verify(forecastQoilDataPersistenceProcessor, Mockito.times(testDataMatrix.length)).process(Mockito.any(MainInfo.class));
    }

    private List<?> mapToList(final MainInfo mi) {
        return Stream.of(
                mi.getId(),
                mi.getCompany(),
                mi.getFact().getQliq().getData1(),
                mi.getFact().getQliq().getData2(),
                mi.getFact().getQoil().getData1(),
                mi.getFact().getQoil().getData2(),
                mi.getForecast().getQliq().getData1(),
                mi.getForecast().getQliq().getData2(),
                mi.getForecast().getQoil().getData1(),
                mi.getForecast().getQoil().getData2()
        ).map(String::valueOf).collect(Collectors.toList());
    }

}
