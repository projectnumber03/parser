package mei.testtask.parser.component.processor.persistence;

import mei.testtask.parser.model.db.ForecastQliqData;
import mei.testtask.parser.model.db.IParsedData;
import mei.testtask.parser.model.dto.IndicationInfo;
import mei.testtask.parser.model.dto.MainInfo;
import mei.testtask.parser.model.dto.Q;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ForecastQliqDataPersistenceProcessor implements IPersistenceProcessor {

    @Override
    public Optional<IParsedData> process(final MainInfo mainInfo) {
        if (Objects.isNull(mainInfo)) return Optional.empty();
        final Integer id = mainInfo.getId();
        final String company = mainInfo.getCompany();
        final Integer data1 = Optional.ofNullable(mainInfo.getForecast())
                .map(IndicationInfo::getQliq)
                .map(Q::getData1)
                .orElse(null);
        final Integer data2 = Optional.ofNullable(mainInfo.getForecast())
                .map(IndicationInfo::getQliq)
                .map(Q::getData2)
                .orElse(null);
        return Optional.of(new ForecastQliqData(id, company, data1, data2));
    }

}
