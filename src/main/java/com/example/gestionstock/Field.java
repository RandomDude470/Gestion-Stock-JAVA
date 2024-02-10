package com.example.gestionstock;

import javafx.scene.control.TextField;

public class Field {
    private String Type;
    private String Text;
    private TextField TextField;

    public TextField getTextField() {
        TextField.setFocusTraversable(false);
        TextField.getStyleClass().add("Textfield");
        return TextField;
    }

    public void setTextField(javafx.scene.control.TextField textField) {
        TextField = textField;
    }

    public Field(String type, String text, TextField txtF) {
        Type = type;
        Text = text;
        TextField = txtF;
    }
    public Field(String type, String text) {
        Type = type;
        Text = text;
        TextField txf = new TextField();
        txf.setPromptText(text);
        TextField = txf;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }
}
