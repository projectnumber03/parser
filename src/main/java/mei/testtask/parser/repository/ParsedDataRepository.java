package mei.testtask.parser.repository;

import mei.testtask.parser.model.db.IParsedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParsedDataRepository extends JpaRepository<IParsedData, UUID> {
}
