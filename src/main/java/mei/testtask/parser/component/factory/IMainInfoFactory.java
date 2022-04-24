package mei.testtask.parser.component.factory;

public interface IMainInfoFactory {

    <T> T produce(Class<T> clazz);

}
