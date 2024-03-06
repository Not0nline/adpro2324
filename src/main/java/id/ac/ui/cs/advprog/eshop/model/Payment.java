package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    HashMap<String, String> paymentData;

    public Payment(String id, String method, String status, HashMap<String, String> paymentData){
    }

    public Payment(String id, String method, HashMap<String, String> paymentData){

    }

    public void setStatus(String status){
        return;
    }
}
