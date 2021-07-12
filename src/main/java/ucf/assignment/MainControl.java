package ucf.assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.input.*;
import java.io.File;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainControl implements Initializable{

    public Button newFile;
    public Button loadFile;
    public Button deleteFile;
    public TextField fileInput;
    public MenuBar menuBar;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        fileChooser.setInitialDirectory(new File("C:\\Users\\erica\\Documents\\OOP\\TestingAgain\\src\\main\\resources\\ucf\\assignment\\todoListFiles"));
    }
    public void handleSave (){
        Window stage = menuBar.getScene().getWindow();
        fileChooser.setTitle("Save Dialoge");
        fileChooser.setInitialFileName("mysave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter( "text file", ".txt"));
        try {
            File file = fileChooser.showSaveDialog((stage));
            fileChooser.setInitialDirectory(file.getParentFile());
        }
        catch(Exception e){}
    }
    ObservableList<MainModel> observableList1 = FXCollections.observableArrayList(
            new MainModel("Erica"));

    public void addNewFile (ActionEvent action) {

        }
    public void todolist(ActionEvent event) throws Exception {
        Stage stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ucf/assignment/TableViewLoad.fxml")));
        Scene scene = new Scene(root); // attach scene graph to scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("To-do List"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        scene.getStylesheets().add("ucf/assignment/toDoList.css");
        stage.show(); // display the stage
    }

    FileChooser fileChooser = new FileChooser();


    }

