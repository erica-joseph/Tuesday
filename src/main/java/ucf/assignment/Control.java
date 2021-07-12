package ucf.assignment;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Erica Joseph
 */
import java.net.URL;

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
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.scene.input.*;
import java.io.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Control implements Initializable{
    public TableView<Model> tableview; //table to display tasks

    public TableColumn<Model,String> colTask; //column defining and holding tasks
    public TableColumn<Model, String> dueDate; //column defining and holding the due date per task
    public TableColumn status; //checkmark to note done tasks

    public TextField inputTask; //textfied input to store the item added to the to-do list
    public Button addItem; //adding item to said list
    public Button delete; //initiating the button to delete a selected item from the list
    public DatePicker datePicker; //choosing date to submit to to-do list
    public Button ClearingList; //button to execute the action of deleting all items from list
    public Button saving;
    public MenuBar menuBarLoad; //initiatin the menu
    public TextField nameFile; //submitting title of text
    public MenuItem saveList;
    public MenuItem save; //act of saving and writing the tasks from the list

    FileChooser fileChooser = new FileChooser(); //instance of the file chooser

    @Override
    public void initialize(URL location, ResourceBundle resources){ //loading the initialized statements
        colTask.setCellValueFactory(new PropertyValueFactory<>("taskName")); //initializing taksks
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate")); //initializing due dates
        tableview.setItems(observableList); //printle items of the table
        tableview.setEditable(true); //determining them editable
        colTask.setCellFactory(TextFieldTableCell.forTableColumn()); //setting tasks to accept the text
        status.setCellValueFactory(new PropertyValueFactory<>("status")); //adding checkbox to mark finished tasks

    }

    public void handleOpen(){ //using the file chooser to determine the path to open a file if needed
        fileChooser.setTitle("Open"); //setting the title as open
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter( "text file", "*.txt")); //filtering so only text files will be asked for
        try {
            Window stage = null; //intializing stage
            File file = fileChooser.showOpenDialog(stage); //calling and defining the file chooser
            fileChooser.setInitialDirectory(file.getParentFile()); //set initial place to look as previous
            Scanner buff = new Scanner(new File(file.getPath())); //writing path
            ArrayList<String> listOfLines = new ArrayList<String>(); //storing the opened file into an arraylist
            while (buff.hasNextLine()) //looping to asses each line
                listOfLines.add(buff.nextLine()); //adding said lines to new list
            ArrayList<String> finishTest = new ArrayList<>(); //new list
            for (String lines : listOfLines) { //for each with lines and lineOfLines
                String[] words = lines.split(","); //creating a partition for the file to separate at the commas
                for (String s : words) { //pulling each
                    finishTest.add(s);
                }
                String output = "";
                for( int i=0; i<=finishTest.size()-1; i++ ){
                    output = finishTest.get(i) + "\n"; //splitting at commas and prinign as lines
                }
                Model modelTest = new Model(output, output);
                tableview.getItems().add(modelTest);
            }
        }
        catch(Exception e){}
    }
    ObservableList<Model> observableList = FXCollections.observableArrayList();

    public void addItems(ActionEvent event){ //function to add items to list
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //pull in format for the date
        Model model = new Model(inputTask.getText(), date);//run the inputted text through the model to designate which values land where
        tableview.getItems().add(model);//display said items on the table
    }

    @FXML
    private void removeItem(ActionEvent event) {//remove item from the to-do list
        int selectedItem = tableview.getSelectionModel().getSelectedIndex(); //select an item
        observableList.remove(selectedItem); //remove the selected item from the list
    }

    public void onEditChange(TableColumn.CellEditEvent<Model, String> modelStringCellEditEvent) {//make list columns editable
    Model model = tableview.getSelectionModel().getSelectedItem();//grabbing an item
    model.setTaskName(modelStringCellEditEvent.getNewValue()); //replacing said item
    }

    public void clearList(ActionEvent event){//clearing list
        tableview.getItems().clear(); //grabbing all items to clear
    }

    public void saveList (ActionEvent event){ //saving list
        Window stage = menuBarLoad.getScene().getWindow(); //displaying and opening
        fileChooser.setTitle("Save"); //title
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter( "text file", "*.txt")); //filtering to only have relevant files
        BufferedWriter bw = null; //buffered writer to make easier
        try {
            File file = fileChooser.showSaveDialog((stage)); //launcing dialogue for saving
            fileChooser.setInitialDirectory((file.getParentFile()));
            FileWriter fw=new FileWriter(file);//writing file
            bw = new BufferedWriter(fw);

            for(Model string: observableList) {
                fw.write(string.getTaskName() + "," + string.getDueDate() + "\n"); //splitting the columns to store separately
            }
            bw.close(); //closing bufered writer
            fw.close();
        }
        catch(Exception e){

        }
    }


    public void main(ActionEvent event) throws Exception {
        Stage stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ucf/assignment/TableViewMain.fxml")));
        Scene scene = new Scene(root); // attach scene graph to scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("To-do List"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        scene.getStylesheets().add("ucf/assignment/toDoList.css");
        stage.show(); // display the stage
    }

}