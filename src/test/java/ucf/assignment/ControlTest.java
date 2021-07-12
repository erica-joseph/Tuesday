package ucf.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlTest {

    @Test
    void initialize() {
    }

    @Test
    void handleOpen() {
    }

    @Test
    void addItems() {
        ObservableList<Model> observableList = FXCollections.observableArrayList();
        String inputTask = "get milk";
        String date = "2021-12-10";
        Model model = new Model(inputTask, date);
        assert observableList.size()!=0;

    }

    @Test
    void onEditChange() {
        if(!model.equals(model.setTaskName(modelStringCellEditEvent.getNewValue()){
            assertTrue(true);
        }
    }

    @Test
    void clearList() {
        if(observableList.size() = 0){
            assertTrue(true);
        }
    }



    @Test
    void saveList() {

    }

    @Test
    void main() {
    }
}