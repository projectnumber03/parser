package mei.testtask.parser.component.processor.persistence;

import lombok.AllArgsConstructor;
import mei.testtask.parser.model.db.IParsedData;
import mei.testtask.parser.model.dto.MainInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MainPersistenceProcessor implements IMainPersistenceProcessor {

    private final List<IPersistenceProcessor> persistenceProcessors;

    @Override
    public List<IParsedData> process(final mei.testtask.parser.model.dto.IParsedData result) {
        return result.getMainInfos().stream()
                .map(o -> (MainInfo) o)
                .flatMap(mi -> persistenceProcessors.stream().map(p -> p.process(mi)).filter(Optional::isPresent).map(Optional::get))
                .collect(Collectors.toList());
    }

}
