package com.testautomation.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Config class manages the loading of configuration properties
 * for the test automation framework. It supports loading properties
 * from a default properties file and allows overriding properties
 * with system properties at runtime.
 */
public class Config {

    /**
     * Logger for logging relevant messages
     */
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    /**
     * configuration properties file location
     */
    private static final String CONFIG_PROPERTIES = "config/config.properties";

    /**
     * Properties object to store the loaded configuration
     */
    private static Properties properties;

    /**
     * Initializes the configuration by loading properties from the configuration
     * properties file and applying any system property overrides.
     */
    public static void initialize() {
        properties = loadProperties();

        // Override properties with system properties if they exist
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        log.info("Test Properties");
        log.info("-----------------");
        for (String key : properties.stringPropertyNames()) {
            log.info("{}={}", key, properties.getProperty(key));
        }
        log.info("-----------------");
    }

    /**
     * Retrieves the value of a property based on the provided key.
     *
     * @param key the property key
     * @return the value associated with the given key, or null if the key does not exist
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Loads the properties from the configuration properties file.
     *
     * @return Properties object containing the loaded configuration
     */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = ResourceLoader.getResource(CONFIG_PROPERTIES)) {
            properties.load(stream);
        } catch (Exception e) {
            log.error("Unable to read the property file {}", CONFIG_PROPERTIES, e);
        }
        return properties;
    }

}
