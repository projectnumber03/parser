package mei.testtask.parser.component.processor.persistence;

import mei.testtask.parser.model.db.IParsedData;
import mei.testtask.parser.model.dto.MainInfo;

import java.util.Optional;

public interface IPersistenceProcessor {

    Optional<IParsedData> process(final MainInfo mainInfo);

}
