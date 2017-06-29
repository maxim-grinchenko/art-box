package com.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoaderImpl implements PropertiesLoader{
	
	private static Properties properties;
	private static final String PROPERTIES_FILE_PATH = "src/main/resources/validations.properties";

	public String getProperty(String propertyName) {
		if (properties == null) {
            synchronized (PropertiesLoaderImpl.class) {
                if (properties == null) {
                    properties = new Properties();
                    try (FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
                        properties.load(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException("Error while loading Properties from InputStream!", e);
                    }
                }
            }
        }
        return properties.getProperty(propertyName);
	}

}
