package com.project.api.app;

public class Property {

    private static final String PATH = "resources/properties/";
    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String CONFIGURATION_PROPERTIES = "config.properties";
    private static final String TRANSLATIONS_PROPERTIES = "translations.properties";

    public enum PropertyPath {
        APPLICATION,
        CONFIGURATION,
        TRANSLATION;
    }

    private String pathFile;

    public Property(String pathFile) {
        this.pathFile = pathFile;
    }

    public static Property getPropertyFile(PropertyPath propertyPath) {
        switch (propertyPath) {
            case APPLICATION:
                return new Property(PATH + APPLICATION_PROPERTIES);
            case CONFIGURATION:
                return new Property(PATH + CONFIGURATION_PROPERTIES);
            case TRANSLATION:
            	return new Property(PATH + TRANSLATIONS_PROPERTIES);
        }
        return null;
    }

    public String getPathFile() {
        return pathFile;
    }

}
