package com.db.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Produit {
    private int IDProduit;
    private String NomProduit;
    private double PrixUnitaire;
    private int QteStock;

    public Produit() {}

    public Produit(int IDProduit, String NomProduit, double PrixUnitaire, int QteStock) {
        this.IDProduit = IDProduit;
        this.NomProduit = NomProduit;
        this.PrixUnitaire = PrixUnitaire;
        this.QteStock = QteStock;
    }

    // Getters and setters

    public int getIDProduit() {
        return IDProduit;
    }

    public void setIDProduit(int IDProduit) {
        this.IDProduit = IDProduit;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public double getPrixUnitaire() {
        return PrixUnitaire;
    }

    public void setPrixUnitaire(double PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public int getQteStock() {
        return QteStock;
    }

    public void setQteStock(int QteStock) {
        this.QteStock = QteStock;
    }





    // return boolean
    public boolean ModifierProduit(String NomProduit  ,
                                   Double PrixUnitaire,
                                   Integer QteStock)
    {
        String query = "UPDATE produit SET NomProduit = ?, PrixUnitaire = ?, QteStock = ? WHERE IDProduit = ?";
        boolean flag = false;
        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, NomProduit);
                preparedStatement.setDouble(2, PrixUnitaire);
                preparedStatement.setInt(3, QteStock);
                preparedStatement.setInt(4, IDProduit);
                int lignesModifiees = preparedStatement.executeUpdate();
                flag = lignesModifiees > 0;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }



    //return boolean
    public boolean SupprimerProduit() {
        String query = "DELETE FROM produit WHERE IDProduit = ?";
        boolean flag = false;
        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, IDProduit);

                int lignesSupprimees = preparedStatement.executeUpdate();
                flag = lignesSupprimees > 0;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

