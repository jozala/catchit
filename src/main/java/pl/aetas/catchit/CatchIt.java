package pl.aetas.catchit;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import pl.aetas.catchit.exception.InternalPhotomakingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class CatchIt {
    private final static String PROPERTIES_FILEPATH = "catchit.properties";
    private final PhotoMaker photoMaker;
    private final PhotoManager photoManager;

    public CatchIt(PhotoManager manager, PhotoMaker maker) {
        this.photoMaker = maker;
        this.photoManager = manager;
    }

    public static void main(String[] args) throws IOException, InternalPhotomakingException {
        ArgumentsParser parser = new ArgumentsParser();
        EventType eventType = null;
        if (parser.validateArgs(args)) {
            parser.parseArgs(args);
            eventType = parser.getEventType();
        }


        CatchIt catchIt = new CatchIt(new PhotoManager(), new PhotoMaker());
        File propertiesFile = catchIt.preparePropertiesFile(PROPERTIES_FILEPATH);

        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesFile));
        PropertiesReader propertiesReader = new PropertiesReader(properties);

        if (!isEventTypeEnabled(eventType, propertiesReader)) {
            return;
        }

        String photosLocation = propertiesReader.getPhotosLocation();

        IplImage photo = catchIt.makePhoto();
        catchIt.savePhoto(photo, photosLocation);
    }

    private static boolean isEventTypeEnabled(EventType eventType, PropertiesReader propertiesReader) {
        if (eventType == null) {
            return true;
        }

        if (eventType == EventType.BOOT) {
            return propertiesReader.isPhotosOnBoot();
        }

        if (eventType == EventType.WAKEUP) {
            return propertiesReader.isPhotosOnWakeup();
        }

        return true;
    }

    /**
     * Save given photo in a given directory using timestamp as a name of the file.
     *
     * @param photo             photo to save
     * @param photosLocationDir directory where photo file will be saved
     */
    public void savePhoto(IplImage photo, String photosLocationDir) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        long timestampValue = timestamp.getTime();
        String photoPath = photosLocationDir + timestampValue + PhotoManager.DEFAULT_PHOTO_EXTENSION;
        photoManager.savePhoto(photo, photoPath);
    }

    /**
     * Makes photo with default camera (0)
     *
     * @return
     * @throws InternalPhotomakingException thrown when external library was unable to make a photo
     */
    public IplImage makePhoto() throws InternalPhotomakingException {
        return photoMaker.makePhoto(0);
    }

    private File preparePropertiesFile(String propertiesFilepath) throws IOException {
        File propFile = new File(propertiesFilepath);
        if (!propFile.exists()) {
            if (!propFile.createNewFile()) {
                throw new FileNotFoundException("Cannot create properties file at location: " + propertiesFilepath);
            }
        }
        return propFile;

    }

}
