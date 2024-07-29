package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightreservation.model.FlightReservationTestData;
import com.vendorportal.vendorportaltestdata.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
    private static final Logger log= LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestdata(String path,Class<T> type) throws IOException {
        try (InputStream stream = ResourseLoader.getResourceAsStream(path)) {
            return mapper.readValue(stream, type);
        } catch (Exception ex) {
            log.error("unable to read file: " + path, ex);
        }
        return null;
    }

}
