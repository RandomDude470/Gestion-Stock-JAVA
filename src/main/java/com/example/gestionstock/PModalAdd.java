package com.example.gestionstock;

import com.db.utils.ClientManager;
import com.db.utils.ProduitManager;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PModalAdd extends ModalUi{

    public PModalAdd() {
        Title = "Add Product";
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field("String", "Product Name..."));
        fields.add(new Field("String", "Unit Price..."));
        fields.add(new Field("String", "Qte.."));
        Fields = fields;
    }

    public boolean save(){
        List<TextField> textFields = Fields.stream().map(Field::getTextField).toList();
        ArrayList<String> values = new ArrayList<>() ;
        textFields.forEach(textField -> {
            if (Objects.equals(textField.getText(), "")) {
                throw new RuntimeException("All fields are required");
            }
            values.add(textField.getText());

        });

        return ProduitManager.AjouterProduit(values.get(0),Double.parseDouble(values.get(1)),Integer.parseInt(values.get(2)));

    }

    @Override
    public void cancel() {

    }



}
