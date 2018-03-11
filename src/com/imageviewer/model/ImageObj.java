package com.imageviewer.model;

/**
 * Stores an image object
 *
 * @author Astha Tiwari
 * @version 1.0
 */
public class ImageObj {
    /**
     * Contains the URL of the image.
     */
    private String url;
    /**
     * Contains the title of the image.
     */
    private String title;

    /**
     * Constructor for a new image object.
     *
     * @param imageURL the URL for the new image
     * @param imageTitle the title for the new image
     */
    public ImageObj(String imageURL, String imageTitle) {
        url = imageURL;
        title = imageTitle;
    }

    /**
     * Gets the URL of the image.
     *
     * @return image URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the title of the image.
     *
     * @return image title
     */
    public String getTitle() {
        return title;
    }
}