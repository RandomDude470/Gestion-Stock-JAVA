package com.example.gestionstock;

import com.db.utils.Client;
import com.db.utils.ClientManager;
import com.db.utils.Produit;
import com.db.utils.ProduitManager;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public abstract class ModalUi {
    protected String Title;
    protected ArrayList<Field> Fields = new ArrayList<>();

    private Double X;
    private Double Y;
    private VBox fieldContainer ;


    public abstract boolean save();
    public abstract void cancel();
    public  void ShowModal(){
        Stage stage = new Stage();
        stage.setTitle("ISYY Inventory Management");
        stage.setWidth(628);
        stage.setHeight(650);
        stage.setResizable(false);

        VBox root = new VBox();
        VBox vBoxContainer = new VBox();
        HBox hBoxbuttons = new HBox();
        VBox vBoxfields = new VBox();
        fieldContainer = vBoxfields;
        Label labelTitle = new Label(Title);
        Button save = new Button("Save");
        Button cancel = new Button("Cancel");


        root.getChildren().addAll(vBoxContainer,hBoxbuttons);
        vBoxContainer.getChildren().add(vBoxfields);
        hBoxbuttons.getChildren().addAll(save,cancel);
        vBoxfields.getChildren().add(labelTitle);
        vBoxfields.getChildren().addAll(Fields.stream().map(Field::getTextField).toList());

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                save();
                stage.close();
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public  void ShowModal(ObservableList<Client> clientsList, ObservableList<Produit> produitsList){
        Stage stage = new Stage();
        stage.setTitle("ISYY Inventory Management");
        stage.setWidth(428);
        stage.setHeight(550);
        stage.setResizable(false);

        VBox root = new VBox();
        root.getStyleClass().add("root");
        VBox vBoxContainer = new VBox();
        vBoxContainer.getStyleClass().add("fieldTitleContainer");
        HBox hBoxbuttons = new HBox();
        hBoxbuttons.getStyleClass().add("buttonContainer");
        VBox vBoxfields = new VBox();
        vBoxfields.getStyleClass().add("fieldContainer");
        fieldContainer = vBoxfields;
        Label labelTitle = new Label(Title);
        labelTitle.getStyleClass().add("Title");
        Button save = new Button("Save");
        save.getStyleClass().add("save");
        Button cancel = new Button("Cancel");
        cancel.getStyleClass().add("cancel");


        VBox space = new VBox();
        VBox.setVgrow(space, Priority.ALWAYS);
        root.getChildren().addAll(vBoxContainer,space,hBoxbuttons);
        vBoxContainer.getChildren().add(vBoxfields);
        hBoxbuttons.getChildren().addAll(cancel, save);
        vBoxfields.getChildren().add(labelTitle);
        vBoxfields.setSpacing(10);
        labelTitle.setMaxWidth(Double.MAX_VALUE);
        vBoxfields.getChildren().addAll(Fields.stream().map(Field::getTextField).toList());

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                save();
                stage.close();
                //refresh lists
                clientsList.clear();
                produitsList.clear();
                clientsList.addAll(ClientManager.getClients());
                produitsList.addAll(ProduitManager.getProduits());
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add(GestionApp.class.getResource("modalStyle.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);

        //make movable
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Y = mouseEvent.getSceneY();
                X = mouseEvent.getSceneX();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX( mouseEvent.getScreenX() - X);
                stage.setY( mouseEvent.getScreenY() - Y);
            }
        });

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
