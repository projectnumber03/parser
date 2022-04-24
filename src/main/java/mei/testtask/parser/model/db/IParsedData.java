package mei.testtask.parser.model.db;

public interface IParsedData {

    Integer getId();

    String getCompany();

    void setId(final Integer id);

    void setCompany(final String company);

    boolean equals(final Object o);

    int hashCode();

    String toString();

}
