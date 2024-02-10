package com.example.gestionstock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import com.db.utils.ClientManager;
import com.db.utils.Produit;
import com.db.utils.ProduitManager;
import com.db.utils.Client;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Pane IndicatorClient;
    public Button ClientButton;
    public Pane IndicatorProduct;
    public Button ProductButton;
    public TableColumn<Client,Integer> IDClient;
    public TableColumn<Client,String> NomCLient;
    public TableColumn<Client,String> AdresseClient;
    public TableColumn<Client,String> EmailClient;
    public TableColumn<Client,String> TelClient;
    public TableView<Client>   ClientTableView;


    public TableView<Produit>   ProduitTableView;
    public TableColumn<Produit , Integer> IDProduit;
    public TableColumn<Produit,String> NomProduit;
    public TableColumn<Produit,Integer> QteProduit;
    public TableColumn<Produit,Integer> PrixUnitaire;
    public VBox Produits;
    public VBox Clients;
    public Button ManageClient;
    public Button ManageProduit;

    //variables
    private ObservableList<Client> clientsList = FXCollections.observableArrayList();
    private ObservableList<Produit>produitsList = FXCollections.observableArrayList();

    @FXML
    private ImageView imageview;


    @Override
    public void initialize(URL location, ResourceBundle rs){

        // Display logo
        Image im = new Image("C:/Users/Yassir/Projects/Scala/GestionStock/src/logo-jfx- big.png") ;
        imageview.setImage(im);

        // Setting widths of columns
        IDClient.setPrefWidth(30);
        NomCLient.setPrefWidth(120);
        AdresseClient.setPrefWidth(200);
        EmailClient.setPrefWidth(200);
        TelClient.setPrefWidth(150);


        IDProduit.setPrefWidth(64/1.25);
        NomProduit.setPrefWidth(561/1.25);
        QteProduit.setPrefWidth(120/1.25);
        PrixUnitaire.setPrefWidth(174/1.25);

        // Voodoo dark magic stuff

        IDClient.setCellValueFactory(new PropertyValueFactory<>("IDClient"));
        NomCLient.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        AdresseClient.setCellValueFactory(new PropertyValueFactory<>("AdresseClient"));
        EmailClient.setCellValueFactory(new PropertyValueFactory<>("EmailClient"));
        TelClient.setCellValueFactory(new PropertyValueFactory<>("TelClient"));

        IDProduit.setCellValueFactory(new PropertyValueFactory<>("IDProduit"));
        NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        QteProduit.setCellValueFactory(new PropertyValueFactory<>("QteStock"));
        PrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("PrixUnitaire"));


        //fill tables

        clientsList.addAll(ClientManager.getClients());
        produitsList.addAll(ProduitManager.getProduits());

        ProduitTableView.setItems(produitsList);
        ClientTableView.setItems(clientsList);

        // making client tab appear by default at startup
        Produits.setVisible(false);
        Produits.setManaged(false);
        Clients.setVisible(true);
        Clients.setManaged(true);
        ManageClient.setVisible(true);
        ManageClient.setManaged(true);
        ManageProduit.setVisible(false);
        ManageProduit.setManaged(false);
        IndicatorProduct.setPrefWidth(0);

        //context menu

        ContextMenu ClientCtx = new ContextMenu();
        MenuItem m1 = new MenuItem("Add Client");
        MenuItem m2 = new MenuItem("Edit Client");
        MenuItem m3 = new MenuItem("Delete Client");
        //MenuItem m4 = new MenuItem("Find Client");
        ClientCtx.getItems().addAll(m1,m2,m3);
        ClientCtx.getItems().forEach(menuItem -> {
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        ModalUi modal = ModalFactory.createModal(menuItem.getText());
                        modal.ShowModal(clientsList,produitsList);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
        ManageClient.setContextMenu(ClientCtx);


        ContextMenu ProduitCtx = new ContextMenu();
        MenuItem mp1 = new MenuItem("Add Product");
        MenuItem mp2 = new MenuItem("Edit Product");
        MenuItem mp3 = new MenuItem("Delete Product");
        //MenuItem mp4 = new MenuItem("Find Product");
        ProduitCtx.getItems().addAll(mp1,mp2,mp3);
        ProduitCtx.getItems().forEach(menuItem -> {
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        ModalUi modal = ModalFactory.createModal(menuItem.getText());
                        modal.ShowModal(clientsList,produitsList);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
        ManageProduit.setContextMenu(ProduitCtx);
    }

    @FXML
    protected  void NavClickedClient(){
        IndicatorClient.setPrefWidth(5);
        IndicatorProduct.setPrefWidth(0);
        Produits.setVisible(false);
        Produits.setManaged(false);
        Clients.setVisible(true);
        Clients.setManaged(true);
        ManageClient.setVisible(true);
        ManageClient.setManaged(true);
        ManageProduit.setVisible(false);
        ManageProduit.setManaged(false);
        refreshTables();
    }
    @FXML
    protected  void NavClickedProduct(){
        IndicatorClient.setPrefWidth(0);
        IndicatorProduct.setPrefWidth(5);
        Produits.setVisible(true);
        Produits.setManaged(true);
        Clients.setVisible(false);
        Clients.setManaged(false);
        ManageClient.setVisible(false);
        ManageClient.setManaged(false);
        ManageProduit.setVisible(true);
        ManageProduit.setManaged(true);
        refreshTables();
    }
    public void refreshTables(){
        clientsList.clear();
        produitsList.clear();
        clientsList.addAll(ClientManager.getClients());
        produitsList.addAll(ProduitManager.getProduits());
    }
    public void ManageClientClicked(MouseEvent actionEvent) {

        ManageClient.getContextMenu().show(ManageClient,actionEvent.getScreenX(),actionEvent.getScreenY());
    }

    public void ManageProduitClicked(MouseEvent actionEvent) {

        ManageProduit.getContextMenu().show(ManageProduit,actionEvent.getScreenX(),actionEvent.getScreenY());

    }


}