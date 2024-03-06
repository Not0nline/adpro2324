package enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    WAITING("WAITING"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final String value;
    private PaymentStatus(String value){
        this.value = value;
    }

    public static boolean contains(String param){
        for (PaymentStatus paymentStatus : PaymentStatus.values()){
            if (paymentStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}
