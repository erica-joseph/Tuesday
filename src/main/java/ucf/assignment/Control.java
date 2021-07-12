package ucf.assignment;

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
    public TableView<Model> tableview;

    public TableColumn<Model,String> colTask;
    public TableColumn<Model, String> dueDate;
    public TableColumn status;

    public TextField inputTask;
    public Button addItem;
    public Button delete;
    public DatePicker datePicker;
    public Button ClearingList;
    public Button saving;
    public MenuBar menuBarLoad;
    public TextField nameFile;

    FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        colTask.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        tableview.setItems(observableList);
        tableview.setEditable(true);
        colTask.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellValueFactory(new PropertyValueFactory<>("status"));


        try {
            catchFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void handleOpen(){
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter( "text file", "*.txt"));
        try {
            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            //
            Scanner buff = new Scanner(new File(file.getPath()));
            ArrayList<String> listOfLines = new ArrayList<String>();
            while (buff.hasNextLine())
                listOfLines.add(buff.nextLine());
            ArrayList<String> finishTest = new ArrayList<>();
            for (String lines : listOfLines) { //for each with lines and lineOfLines
                String[] words = lines.split(","); //creating a partition for the file to separate at the commas
                for (String s : words) {
                    finishTest.add(s);
                }
                String output = "";
                for( int i=0; i<=finishTest.size()-1; i++ ){
                    output = finishTest.get(i) + "\n";

                }
                Model modelTest = new Model(output, output);
                tableview.getItems().add(modelTest);


            }
        }
        catch(Exception e){}
    }
    ObservableList<Model> observableList = FXCollections.observableArrayList();
    @FXML
    public void catchFile() throws FileNotFoundException {
        Scanner buff = new Scanner(new File("src/main/resources/ucf/assignment/todoListFiles/NewList.txt"));
        ArrayList<String> listOfLines = new ArrayList<String>();
        while (buff.hasNextLine())
            listOfLines.add(buff.nextLine());
        ArrayList<String> finishTest = new ArrayList<>();
        for (String lines : listOfLines) { //for each with lines and lineOfLines
            String[] words = lines.split(","); //creating a partition for the file to separate at the commas
            finishTest.addAll(Arrays.asList(words));
            String output = "";
            for( int i=0; i<=finishTest.size()-1; i++ ){
                output = finishTest.get(i) + "\n";

            }
            Model modelTest = new Model(output, output);
            tableview.getItems().add(modelTest);


        }
    }

    public void addNPQ (ActionEvent event){
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Model model = new Model(inputTask.getText(), date);
        tableview.getItems().add(model);
    }

    @FXML
    private void dragandDrop(DragEvent event){
        event.acceptTransferModes(TransferMode.ANY);
    }

    public void completeTask(ActionEvent event){
        int selectedItem = tableview.getSelectionModel().getSelectedIndex();
        System.out.print(selectedItem);
    }

    @FXML
    private void removeItem(ActionEvent event) {
        int selectedItem = tableview.getSelectionModel().getSelectedIndex();
        observableList.remove(selectedItem);
    }

    public void onEditChange(TableColumn.CellEditEvent<Model, String> modelStringCellEditEvent) {
    Model model = tableview.getSelectionModel().getSelectedItem();
    model.setTaskName(modelStringCellEditEvent.getNewValue());
    }

    public void clearList(ActionEvent event){
        tableview.getItems().clear();
    }

    public void saveList (ActionEvent event){

        BufferedWriter bw = null;
        try {
            String str = "File Handling in Java using "+
                    " FileWriter and FileReader";
            String fileName = nameFile.getText();
            fileName = fileName.replaceAll("\\s+", "");
            System.out.print(fileName);
            FileWriter fw=new FileWriter("src/main/resources/ucf/assignment/todoListFiles/"+fileName+".txt");
            bw = new BufferedWriter(fw);

            for(Model string: observableList) {
                fw.write(string.getTaskName() + "," + string.getDueDate() + "\n");
            }
            bw.close();
            System.out.println("Writing successful");
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