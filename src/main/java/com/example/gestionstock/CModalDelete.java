package com.example.gestionstock;

import com.db.utils.Client;

import java.util.ArrayList;

public class CModalDelete extends ModalUi{
    public CModalDelete() {
        Title = "Delete Client";
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field("String", "Client Id..." ));
        Fields = fields;
    }

    @Override
    public boolean save() {

        Client client = new Client();
        try{
            int id = Integer.parseInt(Fields.get(0).getTextField().getText());
            client.setIDClient(id);
            client.supprimerClient();
            return  true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void cancel() {

    }


}
