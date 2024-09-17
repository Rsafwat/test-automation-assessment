package com.testautomation.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Utility class for handling JSON operations related to test data.
 * Provides methods to load and map JSON test data into Java objects using Jackson's ObjectMapper.
 */
public class JsonUtil {

    /** Logger instance for logging relevant information and errors */
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

     /** ObjectMapper instance used for converting JSON to Java objects and vice versa */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads JSON test data from a specified path and converts it into the specified Java object type.
     *
     * @param path the path to the JSON file, relative to the resource location
     * @param type the class type to map the JSON data to
     * @param <T> the type of the object the JSON will be mapped to
     * @return an instance of the specified type, populated with data from the JSON file, or null if there is an error
     */
    public static <T> T getTestData(String path, Class<T> type) {
        try (InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream, type);
        } catch (Exception e) {
            log.error("Unable to read test data from path: {}", path, e);
        }
        return null;
    }

}
