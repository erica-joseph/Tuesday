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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.scene.control.cell.CheckBoxTableCell;

import java.io.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import static javafx.scene.control.cell.CheckBoxTableCell.*;

public class Control implements Initializable{

    //storage
    public TableView<Model> tableview; //table to display tasks
    public TableColumn<Model,String> colTask; //column defining and holding tasks
    public TableColumn<Model, String> dueDate; //column defining and holding the due date per task
    public TableColumn<Model,Boolean> status = new TableColumn<>("Status");

    //display

    //action
    public TextField inputTask; //textfied input to store the item added to the to-do list
    public Button addItem; //adding item to said list
    public Button delete; //initiating the button to delete a selected item from the list
    public DatePicker datePicker; //choosing date to submit to to-do list
    public Button ClearingList; //button to execute the action of deleting all items from list
    public TextField nameFile; //submitting title of text
    public Button savingList;

    FileChooser fileChooser = new FileChooser(); //instance of the file chooser

    @Override
    public void initialize(URL location, ResourceBundle resources){ //loading the initialized statements
        colTask.setCellValueFactory(new PropertyValueFactory<>("taskName")); //initializing taksks
        colTask.setCellFactory(TextFieldTableCell.forTableColumn()); //setting tasks to accept the text
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate")); //initializing due dates
        tableview.setItems(observableList); //printle items of the table
        tableview.setEditable(true); //determining them editable
        status.setCellFactory(column -> new CheckBoxTableCell());
        tableview.setPlaceholder(new Label(" "));//displays no contents in table before items are added to the list
//      displayItems.getItems().removeAll(displayItems.getItems());
//      displayItems.getItems().addAll("Display all", "Display completed", "Display incomplete");
    }


    ObservableList<Model> observableList = FXCollections.observableArrayList(
            new Model("Buy some candy", "2021-12-15", false)
    );


    //Actions performed on the list
    @FXML
    public void addItems(ActionEvent event){ //function to add items to list
        try{
        addItemsDisplay();
        refresh();}
        catch (Exception exception){
            inputTask.setPromptText("Input a valid task.");}
        }


    public void addItemsDisplay(){
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //pull in format for the date
        Model model = new Model(inputTask.getText(), date, true);//run the inputted text through the model to designate which values land where
        tableview.getItems().add(model);//display said items on the table
    }

    @FXML
    private void removeItem(ActionEvent event) {//remove item from the to-do list
        try {
            removeItemDisplay();
        }
        catch(Exception e){

        }
    }

    public void removeItemDisplay(){
        int selectedItem = tableview.getSelectionModel().getSelectedIndex(); //select an item
        observableList.remove(selectedItem); //remove the selected item from the list
    }

    public void onEditChange(TableColumn.CellEditEvent<Model, String> modelStringCellEditEvent) {//make list columns editable
    Model model = tableview.getSelectionModel().getSelectedItem();//grabbing an item
    model.setTaskName(modelStringCellEditEvent.getNewValue()); //replacing said item
    }

    @FXML
    public void clearList(ActionEvent event){//clearing list
        clearListDisplay(); //grabbing all items to clear
    }

    public void clearListDisplay(){
        tableview.getItems().clear();
    }


    //refresh to reset the items in the list
    private void refresh(){
        datePicker.setValue(LocalDate.now()); //reset date picker to the current date
        inputTask.setText(null); //reeset textfield to be empty before the item is introduced
    }




    //Show all items

    //Show incomplete items

    //Show complete items


    public void CompleteTask(){

    }


    //Storage of contents
    @FXML
    public void saveList (ActionEvent event){ //saving list
        saveListDisplay ();
    }

    public void saveListDisplay (){
        Window stage = savingList.getScene().getWindow(); //displaying and opening
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


    @FXML
    public void handleOpen(){ //using the file chooser to determine the path to open a file if needed
        handleOpenDisplay();
    }

    public void handleOpenDisplay(){
        Window stage = savingList.getScene().getWindow(); //displaying and opening
        fileChooser.setTitle("Save"); //title
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter( "text file", "*.txt")); //filtering to only have relevant files
        try {
            File file = fileChooser.showOpenDialog((stage)); //launcing dialogue for saving
            fileChooser.setInitialDirectory((file.getParentFile()));
            Scanner buff = new Scanner(new File(String.valueOf(file))); //writing path

            ArrayList<String> listOfLines = new ArrayList<String>(); //storing the opened file into an arraylist

            while (buff.hasNextLine()) //looping to asses each line
                listOfLines.add(buff.nextLine()); //adding said lines to new list

            for (String lines : listOfLines) { //for each with lines and lineOfLines
                ArrayList<String> finishTest = new ArrayList<>(); //new list
                String[] words = lines.split(","); //creating a partition for the file to separate at the commas
                //pulling each
                finishTest.addAll(Arrays.asList(words));
                String retrieveTask = "";
                String retrieveDate = "";
                String retrieveCheck = "";


                for( int i=0; i<=finishTest.size()-1; i++ ){
                    if(i==0){
                        retrieveTask = finishTest.get(i) + "\n"; //splitting at commas and prinign as lines
                    }
                    else if(i==1){
                        retrieveDate = finishTest.get(i) + "\n"; //splitting at commas and prinign as lines
                    }
                    else if(i==2){
                        retrieveCheck = finishTest.get(i) + "\n"; //splitting at commas and prinign as lines
                    }

                }

                Model modelTest = new Model(retrieveTask, retrieveDate, Boolean.parseBoolean(retrieveCheck));
                tableview.getItems().add(modelTest);
            }
        }
        catch(Exception e){}
    }


    //Changing Scenes
    public void main(ActionEvent event) throws Exception {
        Stage stage;//set stage
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ucf/assignment/TableViewMain.fxml")));//load the main view of fxml
        Scene scene = new Scene(root); // attach scene graph to scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("To-do List"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        scene.getStylesheets().add("ucf/assignment/toDoList.css");//load the custom cascading sheed
        stage.show(); // display the stage
    }
}