package pl.aetas.catchit;

import java.io.File;
import java.util.Properties;

public class PropertiesReader {
    private static final String PHOTOS_LOCATION = "photos.location";
    private static final String PHOTOS_ON_WAKEUP = "photos.onWakeup";
    private static final String PHOTOS_ON_BOOT = "photos.onBoot";

    //    private static final String ENABLED = "enabled";
    private static final String DISABLED = "disabled";


    private static final String DEFAULT_APPLICATION_DIR = ".catchit";

    private final Properties properties;

    public PropertiesReader(final Properties properties) {
        this.properties = properties;
    }

    public String getPhotosLocation() {
        String photosLocation = properties.getProperty(PHOTOS_LOCATION);
        if (photosLocation != null) {
            return correctPath(photosLocation);
        }

        return getDefaultPhotosLocation();
    }

    public boolean isPhotosOnBoot() {
        String photosOnBoot = properties.getProperty(PHOTOS_ON_BOOT);
        if (photosOnBoot == null) {
            return true;
        }
        if (photosOnBoot.toLowerCase().equals(DISABLED)) {
            return false;
        }
        return true;
    }

    public boolean isPhotosOnWakeup() {
        String photosOnWakeup = properties.getProperty(PHOTOS_ON_WAKEUP);
        if (photosOnWakeup == null) {
            return true;
        }
        if (photosOnWakeup.toLowerCase().equals(DISABLED)) {
            return false;
        }
        return true;
    }

    private String getDefaultPhotosLocation() {
        String userHomePath = System.getProperty("user.home");
        return userHomePath + File.separatorChar + DEFAULT_APPLICATION_DIR + File.separatorChar + "photos"
                + File.separatorChar;
    }

    /**
     * Prepares path by adding trailing separators to directory
     *
     * @return
     */
    private String correctPath(String path) {
        if (!path.endsWith(File.separator)) {
            return path + File.separator;
        }
        return path;
    }

}
