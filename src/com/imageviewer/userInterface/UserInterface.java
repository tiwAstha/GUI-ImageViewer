package com.imageviewer.userInterface;

import com.imageviewer.controller.Controller;
import com.imageviewer.model.ImageObj;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Manages the view/user interface. Uses the Controller and DataModel classes to handle data.
 *
 * @author Astha Tiwari
 * @version 1.0
 */
public class UserInterface extends Application {
    /**
     * Instance of the Controller class.
     */
    private static Controller controller = new Controller();
    /**
     * EventHandler object to be used when handling button clicks.
     */
    private EventHandler<ActionEvent> handler;
    /**
     * Contains the primaryStage from the start method.
     */
    private Stage primaryStage;

    /**
     * Sets up the primaryStage with the scene containing the main pane and then displays it.
     *
     * @param image the ImageObj to be displayed.
     */
    private void showPane(ImageObj image) {
        primaryStage.setResizable(false);
        Pane pane1 = new Pane();
        Scene scene1 = new Scene(pane1, 750, 750);
        primaryStage.setScene(scene1);

        primaryStage.getScene().getWindow().setOnCloseRequest(event -> {
            controller.quit();
        });

        ImageView view1 =  setImage(image);

        Text title1 = setTitle(image);

        Button previous = setPrevious();
        Button add = setAdd();
        Button delete = setDelete();
        Button next = setNext();

        pane1.getChildren().addAll(title1, view1, previous, add, delete, next);

        primaryStage.show();
    }

    /**
     * Sets up the display for the image of the ImageObj.
     *
     * @param image the ImageObj to be displayed
     * @return the ImageView to be added to the pane.
     */
    private ImageView setImage(ImageObj image) {
        Image image1 = new Image(image.getUrl());
        ImageView view1 = new ImageView();
        view1.setPreserveRatio(true);
        view1.setFitWidth(500);
        view1.setSmooth(true);
        view1.setImage(image1);
        view1.setLayoutX((750/2) - (500/2));
        double imageWidth = view1.getImage().getWidth();
        double ratio = imageWidth/500;
        double imageHeight = view1.getImage().getHeight();
        view1.setLayoutY((750/2) - (imageHeight/ratio)/2);
        return view1;
    }

    /**
     * Sets up the display for the title of the ImageObj.
     *
     * @param image the ImageObj to be displayed
     * @return the Text to be added to the pane.
     */
    private Text setTitle(ImageObj image) {
        Text title1 = new Text();
        title1.setText(image.getTitle());
        int titleLength = image.getTitle().length();
        title1.setLayoutX((750/2) - (titleLength * 11.75));
        title1.setLayoutY(100);
        title1.setFont(new Font(50));
        return title1;
    }

    /**
     * Sets up the display for the "Previous" button.
     * If the button is pressed, then the controller's prevImage method is called.
     *
     * @return the Button to be added to the pane.
     */
    private Button setPrevious() {
        Button previous = new Button("Previous");
        previous.setPrefWidth(100);
        previous.setLayoutX((750 / 9) * 1 - 15);
        previous.setLayoutY(650);
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPane(controller.prevImage());
            }
        };
        previous.setOnAction(handler);
        if (controller.hasNoImages()) {
            previous.setDisable(true);
        }
        return previous;
    }

    /**
     * Sets up the display for the "Add" button.
     * If the button is pressed, then the user is prompted for a URL and title.
     * The controller's addImage method is called and passed the new URL and title.
     *
     * @return the Button to be added to the pane.
     */
    private Button setAdd() {
        Button add = new Button("Add");
        add.setPrefWidth(100);
        add.setLayoutX((750 / 9) * 3 - 15);
        add.setLayoutY(650);
        add.setOnAction(event -> {
            Optional<String> url = inputDialog("Please enter the URL of your image");
            if (url.isPresent()) {
                Optional<String> title = inputDialog("Please enter the title of your image");
                if (title.isPresent()) {
                    showPane(controller.addImage(url.get(), title.get()));
                }
            }
        });
        return add;
    }

    /**
     * Sets up the display for the "Delete" button.
     * If the button is pressed, then the controller's delImage method is called.
     *
     * @return the Button to be added to the pane.
     */
    private Button setDelete() {
        Button delete = new Button("Delete");
        delete.setPrefWidth(100);
        delete.setLayoutX((750 / 9) * 5 - 15);
        delete.setLayoutY(650);
        delete.setOnAction(event -> {
            showPane(controller.delImage());
        });
        if (controller.hasNoImages()) {
            delete.setDisable(true);
        }
        return delete;
    }

    /**
     * Sets up the display for the "Next" button.
     * If the button is pressed, then the controller's nextImage method is called.
     *
     * @return the Button to be added to the pane.
     */
    private Button setNext() {
        Button next = new Button("Next");
        next.setPrefWidth(100);
        next.setLayoutX((750 / 9) * 7 - 15);
        next.setLayoutY(650);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPane(controller.nextImage());
            }
        });
        if (controller.hasNoImages()) {
            next.setDisable(true);
        }
        return next;
    }

    /**
     * An input dialog box used to get input from the user.
     *
     * @param message the header message to be displayed to the user
     * @return the result of the user's action in the dialog box
     */
    private Optional<String> inputDialog(String message) {
        TextInputDialog input = new TextInputDialog();
        input.setHeaderText(message);
        input.getDialogPane().setPrefSize(600,300);
        input.setGraphic(null);

        Optional<String> result = input.showAndWait();
        return result;
    }

    /**
     * Sets the value of the primaryStage member variable to the value of the primaryStageMain parameter.
     *
     * @param primaryStageMain the primary stage
     */
    @Override
    public void start(Stage primaryStageMain) {
        primaryStage = primaryStageMain;

        controller.getModel().readInFile();

        //initial call, then from inside the buttons, showPane is called and passed a new image
        showPane(controller.currentImage());
    }
}
