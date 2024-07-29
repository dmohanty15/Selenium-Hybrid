package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourseLoader {
    private static final Logger log= LoggerFactory.getLogger(ResourseLoader.class);

    public static InputStream getResourceAsStream(String resourceName) throws IOException {
        log.info("Loading resource: " + resourceName);
        InputStream stream=ResourseLoader.class.getClassLoader().getResourceAsStream(resourceName);
        if(Objects.nonNull(stream))
        {
            return stream;
        }
        return Files.newInputStream(Path.of(resourceName));
    }
}
