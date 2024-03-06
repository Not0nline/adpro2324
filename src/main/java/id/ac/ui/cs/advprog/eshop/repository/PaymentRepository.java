package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();
    public Payment addPayment(Payment payment) {return null;}
    public Payment getPayment(String id){return null;}
    public Iterator<Payment> getAllPayments(){return null;}
    public Payment setStatus(Payment payment, String newStatus){return null;}
}