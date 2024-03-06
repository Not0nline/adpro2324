package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();
    public Payment addPayment(Payment payment) {
        return savePayment(payment);
    }


    public Payment getPayment(String id) {
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        throw new IllegalArgumentException();
    }

    public Iterator<Payment> getAllPayments() {
        return payments.iterator();
    }

    public Payment setStatus(Payment payment, String newStatus) {
        if (payment != null && payments.contains(payment)) {
            Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                    newStatus, payment.getPaymentData());
            savePayment(newPayment);
            return newPayment;
        }
        throw new IllegalArgumentException();
    }


    public Payment savePayment(Payment payment) {
        int i = 0;
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(payment.getId())) {
                payments.remove(i);
                payments.add(i, payment);
                return payment;
            }
            i += 1;
        }

        payments.add(payment);
        return payment;
    }
}