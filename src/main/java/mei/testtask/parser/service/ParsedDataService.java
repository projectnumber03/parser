package mei.testtask.parser.service;

import lombok.AllArgsConstructor;
import mei.testtask.parser.model.db.IParsedData;
import mei.testtask.parser.model.db.ParsedData;
import mei.testtask.parser.repository.ParsedDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParsedDataService implements IParsedDataService {

    private final ParsedDataRepository repository;

    @Override
    public void saveAll(final List<IParsedData> data) {
        if (data.stream().noneMatch(c -> c instanceof ParsedData)) {
            return;
        }
        repository.saveAll(data.stream().map(pd -> (ParsedData) pd).collect(Collectors.toList()));
    }

}
