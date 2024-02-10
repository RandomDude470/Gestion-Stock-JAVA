package com.example.gestionstock;

import com.db.utils.Produit;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PModalEdit extends ModalUi {

    public PModalEdit() {
        Title = "Edit Product";
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field("String", "Product Id..."));
        fields.add(new Field("String", "Product Name..."));
        fields.add(new Field("String", "Unit Price..."));
        fields.add(new Field("String", "Qte.."));
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

        Produit pr = new Produit();
        pr.setIDProduit(Integer.parseInt(values.get(0)));
        return pr.ModifierProduit(values.get(1),Double.parseDouble(values.get(2)),Integer.parseInt(values.get(3)));
    }

    @Override
    public void cancel() {

    }


}
