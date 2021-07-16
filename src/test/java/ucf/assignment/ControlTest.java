package ucf.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class ControlTest {
    //make new controller
    //get current value
    //execute action
    //get new value
    //compare
    public TableView<Model> tableview; //table to display tasks
    public TableColumn<Model,String> colTask; //column defining and holding tasks
    public TableColumn<Model, String> dueDate; //column defining and holding the due date per task
    public TableColumn<Model, String> status; //checkmark to note done tasks
    //display

    //action
    public TextField inputTask; //textfied input to store the item added to the to-do list
    public Button addItem; //adding item to said list
    public Button delete; //initiating the button to delete a selected item from the list
    public DatePicker datePicker; //choosing date to submit to to-do list
    public Button ClearingList; //button to execute the action of deleting all items from list
    public TextField nameFile; //submitting title of text
    public Button savingList;

    ObservableList<Model> observableList = FXCollections.observableArrayList();


        @Test
    void addItemsDisplay_give_true_if_display_no_null_list() {

        Model model = new Model("Get milk", "2021-10-02");
        assertTrue(observableList.size()!=0);
    }

    @Test
    void removeItemDisplay() {
        //Model model = new Model("Get milk", "2021-10-02");
        //tableview.getItems().add(model);
        //assert(observableList.size()!=observableList.size());
    }

    @Test
    void onEditChange() {
    }

    @Test
    void clearListDisplay() {
        //Model model = new Model("Get milk", "2021-10-02");
        //tableview.getItems().add(model);
        //assertTrue( observableList.size()=0);
    }

    @Test
    void saveListDisplay() {
    }

    @Test
    void handleOpenDisplay() {
    }
}