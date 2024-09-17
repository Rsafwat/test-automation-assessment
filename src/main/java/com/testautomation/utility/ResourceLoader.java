package com.testautomation.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * ResourceLoader is a utility class used for reading files.
 */
public class ResourceLoader {

    /**
     * Logger for logging relevant messages
     */
    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    /**
     * Retrieves a resource as an InputStream by first checking the classpath and
     * then checking the file system if the resource is not found in the classpath.
     *
     * @param path the relative or absolute path to the resource
     * @return InputStream for the resource, or null if the resource is not found
     * @throws Exception if an error occurs while reading the resource
     */
    public static InputStream getResource(String path) throws Exception {
        log.info("Reading resource from location: {}", path);

        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(stream)) {
            return stream;
        }

        return Files.newInputStream(Path.of(path));
    }

}
