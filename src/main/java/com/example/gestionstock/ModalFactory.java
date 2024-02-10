package com.example.gestionstock;

import java.util.Objects;

public class ModalFactory {
    public static ModalUi createModal(String type) throws Exception{

        if (Objects.equals(type.split(" ")[1], "Client")){
            switch (type.split(" ")[0]) {
                case "Add" -> {
                    return new CModalAdd();
                }
                case "Edit" -> {
                    return new CModalEdit();
                }
                case "Delete" -> {
                    return new CModalDelete();
                }
            }
        }else {
            switch (type.split(" ")[0]) {
                case "Add" -> {
                    return new PModalAdd();
                }
                case "Edit" -> {
                    return new PModalEdit();
                }
                case "Delete" -> {
                    return new PModalDelete();
                }
            }
        }
        throw new Exception("Problem in creating modal : modal type doesn't exist");
    }
}
