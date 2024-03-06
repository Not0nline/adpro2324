package id.ac.ui.cs.advprog.eshop.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

interface PaymentData {


    public static boolean checkData(String method, HashMap<String, String> param) {
        Set<String> paramKeySet = param.keySet();

        return switch (method) {
            case "VOUCHER_CODE" -> {
                Set<String> voucherCodeKeySet = getVoucherCodeKeySet();
                yield paramKeySet.equals(voucherCodeKeySet);
            }
            case "CASH_ON_DELIVERY" -> {
                Set<String> cashOnDeliveryKeySet = getCashOnDeliveryKeySet();
                yield paramKeySet.equals(cashOnDeliveryKeySet);
            }
            default -> false;
        };
    }

    public static Set<String> getVoucherCodeKeySet() {
        Set<String> voucherCodeKeys = new HashSet<>();
        voucherCodeKeys.add("voucherCode");

        return voucherCodeKeys;
    }

    public static Set<String> getCashOnDeliveryKeySet() {
        Set<String> cashOnDeliveryKeys = new HashSet<>();
        cashOnDeliveryKeys.add("address");
        cashOnDeliveryKeys.add("deliveryFee");

        return cashOnDeliveryKeys;
    }
}
