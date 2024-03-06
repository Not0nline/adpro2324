package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentData;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);
        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        HashMap<String, String> paymentData1 = PaymentData.getNewVoucherCodeData("voucherCode-1");
        Payment payment1 = new Payment("id-1", "VOUCHER_CODE", paymentData1);
        HashMap<String, String> paymentData2 = PaymentData.getNewCashOnDeliveryData
                ("address-1", "deliveryFee-1");
        Payment payment2 = new Payment("id-2", "CASH_ON_DELIVERY", paymentData2);
        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        HashMap<String, String> paymentData = PaymentData.getNewVoucherCodeData
                ("voucherCode-1");
        Payment result = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        verify(paymentRepository, times(1)).addPayment(result);
    }

    @Test
    void testAddPaymentIfOrderAlreadyExists() {
        HashMap<String, String> paymentData = PaymentData.getNewVoucherCodeData
                ("voucherCode-1");
        Payment result = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        verify(paymentRepository, times(1)).addPayment(result);

        HashMap<String, String> paymentData2 = PaymentData.getNewVoucherCodeData
                ("voucherCode-2");
        Payment result1 = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), paymentData2);
        verify(paymentRepository, times(0)).addPayment(result1);
    }

    @Test
    void testSetStatus(){
        HashMap<String, String> paymentData = PaymentData.getNewVoucherCodeData
                ("voucherCode-1");
        Payment payment = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), orders.getFirst().getStatus());
    }

    @Test
    void testGetPayment(){
        HashMap<String, String> paymentData = PaymentData.getNewVoucherCodeData
                ("voucherCode-1");
        Payment payment = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment, result);
    }

    @Test
    void testGetAllPayments(){
        HashMap<String, String> paymentData1 = PaymentData.getNewVoucherCodeData
                ("voucherCode-1");
        HashMap<String, String> paymentData2 = PaymentData.getNewVoucherCodeData
                ("voucherCode-2");

        Payment payment1 = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        Payment payment2 = paymentService.addPayment(orders.get(1), PaymentMethod.VOUCHER_CODE.getValue(), paymentData2);

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment1);
        paymentList.add(payment2);
        List<Payment> result = paymentService.getAllPayments();
        for (int i=0; i<= result.size(); i++){
            assertEquals(paymentList.get(i), result.get(i));
        }
        assertEquals(2, result.size());
    }
}