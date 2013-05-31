package pl.aetas.catchit;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

public class PhotoManager {


    public static final String DEFAULT_PHOTO_EXTENSION = ".jpg";

    public void savePhoto(IplImage photo, String path) {
        cvSaveImage(path, photo);
    }

}
