package mei.testtask.parser.component.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainInfoFactory implements IMainInfoFactory {

    @Nullable
    @Override
    public <T> T produce(final Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("error while producing MainInfo", e);
            return null;
        }
    }

}
