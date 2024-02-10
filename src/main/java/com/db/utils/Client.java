package com.db.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
    private  int IDClient;
    private String NomClient;
    private String AdresseClient;
    private String EmailClient;
    private String TelClient;

   

    // Getters and setters
    public Client() {}
    public Client(int IDClient, String NomClient, String AdresseClient, String EmailClient, String TelClient) {
        this.IDClient = IDClient;
        this.NomClient = NomClient;
        this.AdresseClient = AdresseClient;
        this.EmailClient = EmailClient;
        this.TelClient = TelClient;
	}

	public int getIDClient() {
        return IDClient;
    }

    public void setIDClient(int IDClient) {
        this.IDClient = IDClient;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }

    public String getAdresseClient() {
        return AdresseClient;
    }

    public void setAdresseClient(String AdresseClient) {
        this.AdresseClient = AdresseClient;
    }

    public String getEmailClient() {
        return EmailClient;
    }

    public void setEmailClient(String EmailClient) {
        this.EmailClient = EmailClient;
    }

    public String getTelClient() {
        return TelClient;
    }

    public void setTelClient(String TelClient) {
        this.TelClient = TelClient;
    }


    // Methods


    public boolean modifierClient(String NomClient,
                                  String AdresseClient,
                                  String EmailClient,
                                  String TelClient) {
        String query = "UPDATE clients SET NomClient = ?, AdresseClient = ?, EmailClient = ?, TelClient = ? WHERE IDClient = ?";
        boolean flag = false;
    
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            preparedStatement.setString(1, NomClient);
            preparedStatement.setString(2, AdresseClient);
            preparedStatement.setString(3, EmailClient);
            preparedStatement.setString(4, TelClient);
            preparedStatement.setInt(5, this.IDClient);

            int lignesModifiees = preparedStatement.executeUpdate();
            flag = lignesModifiees > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    
        return flag;
    }
    

    public boolean supprimerClient() {
        String query = "DELETE FROM clients WHERE IDClient = ?";
        boolean flag = false;
    
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            preparedStatement.setInt(1, IDClient);
    
            int lignesSupprimees = preparedStatement.executeUpdate();
            flag = lignesSupprimees > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    
        return flag;
    }
    
}

