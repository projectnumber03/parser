package mei.testtask.parser.repository;

import mei.testtask.parser.model.db.ParsedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParsedDataRepository extends JpaRepository<ParsedData, UUID> {
}
