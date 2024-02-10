package com.example.gestionstock;

import com.db.utils.Client;
import com.db.utils.ClientManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CModalAdd extends ModalUi{

    //private VBox fieldContainer ;

    public CModalAdd() {
        Title = "Add Client";
        ArrayList<Field> fields = new ArrayList<>();



        fields.add(new Field("String", "Name..." ));
        fields.add(new Field("String", "Adress..."));
        fields.add(new Field("String", "email..."));
        fields.add(new Field("String", "phone..."));
        Fields = fields;
    }

    @Override
    public boolean save(){

        List<TextField> textFields = Fields.stream().map(Field::getTextField).toList();
        ArrayList<String> values = new ArrayList<>() ;
        textFields.forEach(textField -> {
            if (Objects.equals(textField.getText(), "")) {
                throw new RuntimeException("All fields are required");
            }
            values.add(textField.getText());

        });

        return ClientManager.AjouterClient(values.get(0),values.get(1),values.get(2),values.get(3));
    }

    @Override
    public void cancel() {

    }




}
