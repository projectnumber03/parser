package mei.testtask.parser.service;

import lombok.AllArgsConstructor;
import mei.testtask.parser.model.db.IParsedData;
import mei.testtask.parser.repository.ParsedDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParsedDataService implements IParsedDataService {

    private final ParsedDataRepository repository;

    @Override
    public void saveAll(final List<IParsedData> data) {
        repository.saveAll(data);
    }

}
