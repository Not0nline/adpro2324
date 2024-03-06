package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    HashMap<String, String> paymentData;

    public Payment(String id, String method, String status, HashMap<String, String> paymentData) {
        this(id, method, paymentData);
        String[] statusList = {"WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELLED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    public Payment(String id, String method, HashMap<String,String> paymentData){
        this.id = id;
        this.status = "WAITING";

        String[] methodList = {"VOUCHER_CODE", "CASH_ON_DELIVERY", "BANK_TRANSFER"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))){
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        HashMap<String ,String> paymentDataVoucherCode = new HashMap<>();
        HashMap<String ,String> paymentDataCashOnDelivery = new HashMap<>();
        HashMap<String ,String> paymentDataBankTransfer = new HashMap<>();


        paymentDataVoucherCode.put("voucherCode", "");
        paymentDataCashOnDelivery.put("address", "");
        paymentDataCashOnDelivery.put("deliveryFee", "");
        paymentDataBankTransfer.put("bankName", "");
        paymentDataBankTransfer.put("referenceCode", "");



        ArrayList<HashMap<String, String>> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(paymentDataVoucherCode);
        paymentMethodList.add(paymentDataCashOnDelivery);
        paymentMethodList.add(paymentDataBankTransfer);

        if (!paymentMethodList.contains(paymentData)){
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }

    }

    public void setStatus(String status){
        String[] statusList = {"WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELLED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }
}

