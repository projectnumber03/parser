package mei.testtask.parser.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@AllArgsConstructor
public class FromInputArgsParser implements IFromInputArgsParser {

    private final IParser parser;

    @Override
    @PostConstruct
    public void init() {
        final String fileName = System.getProperty("fileName");
        if (StringUtils.isEmpty(fileName)) return;
        try (final InputStream is = new FileInputStream(fileName)) {
            log.info("start parsing file" + fileName);
            parser.parse(is);
            log.info("parsing finished");
        } catch (IOException e) {
            log.error("error parsing file " + fileName);
        }
    }

}
