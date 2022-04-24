package mei.testtask.parser.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PARSED_DATA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ParsedData implements IParsedData {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "PARSED_DATA_ID", length = 36)
    protected UUID parsedDataId;

    @Column
    protected Integer id;

    @Column
    protected String company;

    @Column(name = "CREATED_AT")
    protected LocalDateTime createdAt;

    public ParsedData(final Integer id, final String company) {
        this.parsedDataId = UUID.randomUUID();
        this.id = id;
        this.company = company;
        this.createdAt = LocalDateTime.now();
    }

}
