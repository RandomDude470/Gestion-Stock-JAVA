package com.db.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduitManager {

    public static  ArrayList<Produit> getProduits() {
        String query = "SELECT * FROM produit;";
        ArrayList<Produit> produits = new ArrayList<>();


        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Produit produit = new Produit();
                    produit.setIDProduit(  resultSet.getInt("IDProduit"));
                    produit.setNomProduit(  resultSet.getString("NomProduit"));
                    produit.setPrixUnitaire ( resultSet.getDouble("PrixUnitaire"));
                    produit.setQteStock(  resultSet.getInt("QteStock"));
                    produits.add(produit);
                }
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }
    public static  ArrayList<Produit> ChercherProduit(String name) {
        String query = "SELECT * FROM produit WHERE NomProduit= ?";
        ArrayList<Produit> produits = new ArrayList<>();


        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                Produit produit = new Produit();
                while (resultSet.next()) {
                    produit.setIDProduit(  resultSet.getInt("IDProduit"));
                    produit.setNomProduit(  resultSet.getString("NomProduit"));
                    produit.setPrixUnitaire ( resultSet.getDouble("PrixUnitaire"));
                    produit.setQteStock(  resultSet.getInt("QteStock"));
                    produits.add(produit);
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    
    public static boolean AjouterProduit(String NomProduit, Double PrixUnitaire, int QtéStock) {
        String query = "INSERT INTO produit (NomProduit, PrixUnitaire, QteStock) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, NomProduit);
                preparedStatement.setDouble(2, PrixUnitaire);
                preparedStatement.setInt(3, QtéStock);


                if (preparedStatement.executeUpdate() == 0) return false;

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }
}
