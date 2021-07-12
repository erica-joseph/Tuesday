package ucf.assignment;

import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ControlTest {
    private Model task;
    private Model date;
    public TableView<Model> tableview;
    @Test
    void initialize() {
    }

    @Test
    void handleOpen() {
    }

    @Test
   void addItems_when_asked_by_button() {

        Model model = new Model("Get milk", "2021-10-14");

        }



    @Test
    void onEditChange() {
    }

    @Test
    void clearList() {
        Model beep = new Model("Get milk", "2021-12-10");

        //boolean actual = beep.reader("src/main/java/org/example/assignment3/ex41/test41A.txt");
        //boolean expected = true;
    }

    @Test
    void saveList() {
    }

    @Test
    void main() {
    }
}