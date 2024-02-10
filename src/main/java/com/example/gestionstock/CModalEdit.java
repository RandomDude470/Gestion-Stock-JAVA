package com.example.gestionstock;

import com.db.utils.Client;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CModalEdit extends ModalUi {
    public CModalEdit() {

        Title = "Edit Client";
        ArrayList<Field> fields = new ArrayList<>();


        fields.add(new Field("String", "Id..."));
        fields.add(new Field("String", "Name..." ));
        fields.add(new Field("String", "Adress..."));
        fields.add(new Field("String", "email..."));
        fields.add(new Field("String", "phone..."));
        Fields = fields;


    }

    @Override
    public boolean save() {

        List<TextField> textFields = Fields.stream().map(Field::getTextField).toList();
        ArrayList<String> values = new ArrayList<>() ;
        textFields.forEach(textField -> {
            if (Objects.equals(textField.getText(), "")) {
                throw new RuntimeException("All fields are required");
            }
            values.add(textField.getText());

        });
        Client client = new Client();
        client.setIDClient(Integer.parseInt(values.get(0)));
        client.modifierClient(values.get(1),values.get(2),values.get(3),values.get(4));
        return false;
    }

    @Override
    public void cancel() {

    }


}
