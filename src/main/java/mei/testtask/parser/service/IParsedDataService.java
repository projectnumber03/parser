package mei.testtask.parser.service;

import mei.testtask.parser.model.db.IParsedData;

import java.util.List;

public interface IParsedDataService {

    void saveAll(List<IParsedData> data);

}
