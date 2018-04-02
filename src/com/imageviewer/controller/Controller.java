package com.imageviewer.controller;

import com.imageviewer.model.ImageObj;
import com.imageviewer.model.DataModel;

/**
 * Contains methods called by the UserInterface class.
 * It uses the DataModel class to manage the image objects.
 * The Controller gives the UserInterface class the image and title to display.
 * @version 1.0
 */
public class Controller {
    /**
     * Instance of the DataModel class.
     */
    private DataModel model = new DataModel();
    /**
     * Contains the position of the image object.
     */
    private int position = 0;

    /**
     * Says if there are any images in the model's imageList ArrayList.
     */
    private boolean noImages = false;

    /**
     * Updates the current position to that of the next image in the model's imageList ArrayList.
     * Uses model's getImageList method to get the image at the desired position.
     *
     * @return the image to be displayed
     */
    public ImageObj nextImage() {
        if (position == model.getImageList().size() - 1) {
            position = 0;
        }
        else {
            position++;
        }
        return model.getImageList().get(position);
    }

    /**
     * Updates the current position to that of the previous image in the model's imageList ArrayList.
     * Uses model's getImageList method to get the image at the desired position.
     *
     * @return the image to be displayed
     */
    public ImageObj prevImage() {
        if (position != 0) {
            position--;
        }
        else {
            position = model.getImageList().size() - 1;
        }

        return model.getImageList().get(position);
    }

    /**
     * Creates a new ImageObj from the given URL and title.
     * Updates the current position to that of the added image.
     * Passes the new ImageObj to model's addImageToImageList method.
     *
     * @param url the URL of the new image
     * @param title the title of the new image
     * @return the image to be displayed
     */
    public ImageObj addImage(String url, String title) {
        ImageObj newImage = new ImageObj(url, title);
        if(!noImages) {
            position++;
        }
        model.addImageToImageList(newImage, position);
        noImages = false;
        return model.getImageList().get(position);
    }

    /**
     * Updates the current position to that of the image before the image to be deleted.
     * Calls model's removeImageFromImageList method.
     *
     * @return the image to be displayed
     */
    public ImageObj delImage() {
        model.removeImageFromImageList(position);
        if (model.getImageList().size() != 0) {
            if (position == model.getImageList().size()) {
                position = 0;
            }
            return model.getImageList().get(position);
        }
        else {
            position = 0;
            noImages = true;
            return new ImageObj("http://www.xn--flawiler-fachgeschfte-n2b.ch/wp-content/uploads/2016/09/sample-image.jpg", "No Image");
        }
    }

    /**
     * Gets the ImageObj at the current position of the model's imageList.
     *
     * @return the image to be displayed
     */
    public ImageObj currentImage() {
        if (model.getImageList().size() == 0) {
            noImages = true;
            return new ImageObj("http://www.xn--flawiler-fachgeschfte-n2b.ch/wp-content/uploads/2016/09/sample-image.jpg", "No Image");
        }
        return model.getImageList().get(position);
    }

    /**
     * Calls the model's updateFile method and then the program finishes execution.
     */
    public void quit() {
        model.updateFile();
    }

    /**
     * Returns the value of the position member variable.
     *
     * @return value of position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the instance of the DataModel.
     *
     * @return the instance of the DataModel.
     */
    public DataModel getModel() {
        return model;
    }

    /**
     * Returns the value of the noImages member variable.
     *
     * @return value of noImages member variable.
     */
    public boolean hasNoImages() {
        return noImages;
    }
}
