package com.example.gestionstock;

import com.db.utils.Produit;
import com.db.utils.ProduitManager;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PModalDelete extends ModalUi{

    public PModalDelete() {
        Title = "Delete Product";
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field("String", "Product Id..."));
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

        return pr.SupprimerProduit();
    }

    @Override
    public void cancel() {

    }


}
