package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    private HashMap<Order, Payment> orderPaymentMap = new HashMap<Order, Payment>();


    @Override
    public Payment addPayment(Order order, String method, HashMap<String, String> paymentData){
        if (orderPaymentMap.containsKey(order)){
            return null;
        }

        Payment payment = new Payment(null, method, paymentData);
        orderPaymentMap.put(order, payment);

        paymentRepository.addPayment(payment);
        return payment;
    }

    public Payment setStatus(Payment payment, String status){
        Payment updatedPayment = paymentRepository.setStatus(payment, status);
        Order order = null;
        for (Map.Entry<Order, Payment> entry: orderPaymentMap.entrySet()) {
            if (entry.getValue() == payment){
                order = entry.getKey();
                break;
            }
        }
        if (order != null){
            if (status.equals(PaymentStatus.SUCCESS.getValue())){
                order.setStatus(OrderStatus.SUCCESS.getValue());
            }
            else if (status.equals(PaymentStatus.REJECTED.getValue())){
                order.setStatus(OrderStatus.FAILED.getValue());
            }
        }

        return updatedPayment;
    }

    @Override
    public Payment getPayment(String paymentId){
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments(){
        Iterator<Payment> paymentIterator = paymentRepository.getAllPayments();
        List<Payment> allPayments = new ArrayList<>();
        paymentIterator.forEachRemaining(allPayments::add);
        return allPayments;
    }
}