package pl.aetas.catchit;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import pl.aetas.catchit.exception.InternalPhotomakingException;


public class PhotoMaker {

    private IplImage captureFrame(int cameraNumber) throws InternalPhotomakingException {
        // 0-default camera, 1 - next...so on
        final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(cameraNumber);
        try {
            grabber.start();
            IplImage img = grabber.grab();
            if (img != null) {
                return img;
            }
        } catch (Exception e) {
            throw new InternalPhotomakingException("Unexpected error when grabbing a photo", e);
        }
        return null;
    }

    /**
     * Make a photo using camera of given number (0 is default one - use it when you don't know what to use).
     *
     * @param cameraNumber number of camera to make a photo with (use 0 for default)
     * @return photo made
     * @throws InternalPhotomakingException thrown when photo cannot be made because of some internal problems of external library or camera is
     *                                      currently busy
     * @see {@link opencv_highgui#cvSaveImage(String, com.googlecode.javacv.cpp.opencv_core.CvArr)}
     */
    public IplImage makePhoto(int cameraNumber) throws InternalPhotomakingException {
        IplImage image = captureFrame(cameraNumber);
        if (image == null) {
            throw new InternalPhotomakingException("Photo has not been made. External library problem.");
        }
        return image;

    }
}
