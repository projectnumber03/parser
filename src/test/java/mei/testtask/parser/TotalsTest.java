package mei.testtask.parser;

import mei.testtask.parser.component.factory.RandomDateFactory;
import mei.testtask.parser.model.dto.IParsedData;
import mei.testtask.parser.model.dto.ITotal;
import mei.testtask.parser.model.dto.MainInfo;
import mei.testtask.parser.repository.ParsedDataRepository;
import mei.testtask.parser.service.XlsxParser;
import org.apache.commons.codec.Resources;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TotalsTest {

    @Autowired
    private XlsxParser parser;

    @MockBean
    private ParsedDataRepository repository;

    @MockBean
    private RandomDateFactory randomDateFactory;

    private IParsedData data;

    @BeforeEach
    public void init() {
        final Deque<LocalDate> testDates = Stream.of(
                Collections.nCopies(5, LocalDate.of(2022, 4, 1)),
                Collections.nCopies(5, LocalDate.of(2022, 4, 5)),
                Collections.nCopies(5, LocalDate.of(2022, 4, 10)),
                Collections.nCopies(5, LocalDate.of(2022, 4, 20))
        ).flatMap(Collection::stream).collect(Collectors.toCollection(ArrayDeque::new));
        Mockito.when(randomDateFactory.produce()).thenAnswer((Answer<LocalDate>) invocation -> testDates.pollFirst());
        data = parser.parse(Resources.getInputStream("Задание.xlsx"));
    }

    @Test
    public void shouldCountFactQliqDataOneTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "FactQliqDataOne".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(60, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(85, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 110, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 135, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountFactQliqDataTwoTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "FactQliqDataTwo".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(110, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(135, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 160, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 185, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountFactQoilDataOneTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "FactQoilDataOne".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(160, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(185, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 210, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 235, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountFactQoilDataTwoTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "FactQoilDataTwo".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(210, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(235, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 260, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 285, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountForecastQliqDataOneTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "ForecastQliqDataOne".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(70, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(95, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 120, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 145, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountForecastQliqDataTwoTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "ForecastQliqDataTwo".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(120, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(145, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 170, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 195, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountForecastQoilDataOneTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "ForecastQoilDataOne".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(125, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(250, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 375, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 500, values.get(LocalDate.of(2022, 4, 20)));
    }

    @Test
    public void shouldCountForecastQoilDataTwoTotals() {
        final ITotal total = data.getTotals().stream().filter(t -> "ForecastQoilDataTwo".equals(t.getName())).findAny().get();
        final Map<LocalDate, Long> values = total.getValues();
        assertEquals(175, values.get(LocalDate.of(2022, 4, 1)));
        assertEquals(300, values.get(LocalDate.of(2022, 4, 5)));
        assertEquals( 425, values.get(LocalDate.of(2022, 4, 10)));
        assertEquals( 550, values.get(LocalDate.of(2022, 4, 20)));
    }

}
