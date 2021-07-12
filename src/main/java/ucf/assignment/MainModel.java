package ucf.assignment;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class MainModel {

    private SimpleStringProperty listName;

    public MainModel(String listName) {
        this.listName = new SimpleStringProperty(listName);
    }

    public String getListName() {
        return listName.get();
    }

    public void setListName(String listName) {
        this.listName = new SimpleStringProperty(listName);
    }

}
