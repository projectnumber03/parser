package mei.testtask.parser.component.processor.persistence;

import mei.testtask.parser.model.db.IParsedData;

import java.util.List;

public interface IMainPersistenceProcessor {

    List<IParsedData> process(final mei.testtask.parser.model.dto.IParsedData result);

}
