/*
Project: Name Picker
Name: Christian Zacher
Date: 9/22/20
Description takes a file of names and picks one at random
 */
package namepicker;

import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

public class NamePicker extends Application {
    
    // A list of names
    private static ArrayList<String> list = new ArrayList<>();
    
    // Name displayed
    private Text name = new Text();
    
    // Text field where you enter the file path
    TextField tfFileName = new TextField();
    
    // Random button
    Button randBtn = new Button("Choose Name");
    
    @Override
    public void start(Stage primaryStage){
        
        // Setting the names font
        name.setFont(new Font(20));
        
        // The text at the top of the screen
        Text topText = new Text("The Name Picked Is");
        topText.setFont(new Font(35));
        StackPane banner = new StackPane();
        banner.getChildren().add(topText);
        
        // Button push method
        randBtn.setOnAction(e ->{
            chooseRandomName();
        });
        
        // Centers the button
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(randBtn);
        
        // GUI
        BorderPane root = new BorderPane();
        root.setTop(banner);
        root.setCenter(name);
        root.setBottom(stackPane);
        
        // Scene
        Scene scene = new Scene(root, 400, 250);
        
        
        // Main Stage
        primaryStage.setTitle("Random Name Drawing");
        primaryStage.setScene(scene);
        
        // File select stage
        
        // Text feild 
        Label fileSelector = new Label("Enter File Path:", tfFileName);
        fileSelector.setContentDisplay(ContentDisplay.RIGHT);
        
        // Pane
        BorderPane pane = new BorderPane();
        pane.setCenter(fileSelector);
        
        // Scene
        Scene fileScene = new Scene(pane, 400, 150);
        Stage stage = new Stage();
        stage.setTitle("File Select");
        stage.setScene(fileScene);
        stage.show();
        
        tfFileName.requestFocus();
                
        tfFileName.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER){
                stage.close();
                fileSelector();
                primaryStage.show();
            }
        });
    }
    
    // Starts the program
    public static void main(String[] args) {
        
       // Launches the application
       Application.launch(args);
    }
    
    // Name randomizer
    public void chooseRandomName(){
        String temp;
        // Random number generator
        Random rand = new Random(); 
        
        temp = list.get(rand.nextInt(list.size()));
        
        // Gets random name from the list
        name.setText(temp);
    }
    
    // Gets the list of names from the file
    public void fileSelector(){
        try{
           File file = new File(tfFileName.getText());
           String temp;
           String[] array;
           Scanner input = new Scanner(file); 
           while(input.hasNextLine()){
               temp = input.nextLine();
               array = temp.split("\t");
               temp = array[0] + " " + array[1];
               list.add(temp);
           }
        }
        catch(FileNotFoundException fe){
            name.setText("File Not Found");
            randBtn.setOnAction(e -> {});
        }
        
    }
}
