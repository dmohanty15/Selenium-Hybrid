package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtilReader {
    private static Logger log= LoggerFactory.getLogger(ConfigUtilReader.class);
    private static Properties prop;
    private static final String DEFAULT_CONFIG_FILE = "Config//Default.properties";

    public static void init() throws IOException {
        prop=getProperty();
        for(String key:prop.stringPropertyNames()) {
            if(System.getProperties().contains(key))
            {
              System.setProperty(key, prop.getProperty(key));
            }
        }

        for(String key1:prop.stringPropertyNames()) {
            log.info("{}={}",key1,prop.getProperty(key1));
        }
    }
    private static Properties getProperty() throws IOException {
        prop=new Properties();
        try(InputStream stream=ResourseLoader.getResourceAsStream(DEFAULT_CONFIG_FILE ))
        {
          prop.load(stream);
        }
        catch (Exception e)
        {
            log.error("unable to find resourse file {}",e.getMessage());
        }
        return prop;
    }

    public static String get(String key)
    {
      return  System.getProperty(key);
    }
}
