package com.imageviewer.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Contains methods that handle the file that contains the image URLs.
 *
 * @author Astha Tiwari
 * @version 1.0
 */
public class DataModel {
    /**
     * Path to the file containing the image URLs and titles.
     */
    private String filename = "./data/images.data";

    /**
     * Contains all the image objects.
     */
    private ArrayList<ImageObj> imageList = new ArrayList<>();

    /**
     * Stores image urls and titles from the images.data file into ArrayList imageList.
     */
    public void readInFile() {
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(fileInput.hasNext()) {
            String[] line = fileInput.nextLine().split(" ");
            String url = line[0];
            String title = "";
            for (int i = 1; i < line.length; i++) {
                title += line[i] + " ";
            }
            imageList.add(new ImageObj(url, title));
        }
    }

    /**
     * Adds an image to the imageList ArrayList.
     *
     * @param imageToAdd the image to be added to the list
     * @param desiredIndex the index where the image will be stored
     */
    public void addImageToImageList(ImageObj imageToAdd, int desiredIndex) {
        if (desiredIndex == imageList.size()) {
            imageList.add(imageToAdd);
        }
        else {
            imageList.add(desiredIndex, imageToAdd);
        }
    }

    /**
     * Removes an image from the imageList ArrayList.
     *
     * @param imageIndex the index that contains the image to be removed
     */
    public void removeImageFromImageList(int imageIndex) {
        imageList.remove(imageIndex);
    }

    /**
     * Updates the file with all existing urls and titles.
     */
    public void updateFile() {
        PrintWriter fileOutput = null;
        try {
            fileOutput = new PrintWriter(new FileOutputStream(filename));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        for (ImageObj i : imageList) {
            fileOutput.append(i.getUrl() + " " + i.getTitle() + "\n");
        }
        fileOutput.close();
    }

    /**
     * Gets the imageList ArrayList.
     *
     * @return the imageList ArrayList
     */
    public ArrayList<ImageObj> getImageList() {
        return imageList;
    }
}
