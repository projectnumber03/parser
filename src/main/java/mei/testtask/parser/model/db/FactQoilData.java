package mei.testtask.parser.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FactQoilData extends ParsedData {

    @Column
    private Integer data1;

    @Column
    private Integer data2;

    public FactQoilData(final Integer id, final String company, final Integer data1, final Integer data2) {
        super(id, company);
        this.data1 = data1;
        this.data2 = data2;
    }

}
