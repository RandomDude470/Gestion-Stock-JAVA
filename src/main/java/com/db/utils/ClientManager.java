package com.db.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientManager {

    public static ArrayList<Client> getClients() {
        String query = "SELECT * FROM clients;";
        ArrayList<Client> Clients = new ArrayList<>();


        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Client client = new Client();
                    client.setIDClient (resultSet.getInt("idclient"));
                    client.setNomClient (resultSet.getString("NomClient"));
                    client.setAdresseClient (resultSet.getString("AdresseClient"));
                    client.setEmailClient (resultSet.getString("EmailClient"));
                    client.setTelClient (resultSet.getString("TelClient"));
                    Clients.add(client);

                }
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Clients;
    }
    public static ArrayList<Client> ChercherClient(String name) {
        String query = "SELECT * FROM clients WHERE NomClient = ?";
        ArrayList<Client> Clients = new ArrayList<>();


        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                preparedStatement.setString(1, name);


                ResultSet resultSet = preparedStatement.executeQuery();



                while (resultSet.next()) {
                    Client client = new Client();
                    client.setIDClient (resultSet.getInt("idclient"));
                    client.setNomClient (resultSet.getString("NomClient"));
                    client.setAdresseClient (resultSet.getString("AdresseClient"));
                    client.setEmailClient (resultSet.getString("EmailClient"));
                    client.setTelClient (resultSet.getString("TelClient"));
                    Clients.add(client);

                }
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Clients;
    }
    public static boolean AjouterClient(String NomClient,
                                 String AdresseClient,
                                 String EmailClient,
                                 String TelClient
                                 ) {
        String query = "INSERT INTO clients (NomClient, AdresseClient, EmailClient, TelClient) VALUES ( ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                preparedStatement.setString(1, NomClient);
                preparedStatement.setString(2, AdresseClient);
                preparedStatement.setString(3, EmailClient);
                preparedStatement.setString(4, TelClient);


                if (preparedStatement.executeUpdate() == 0) return false;

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }


}
