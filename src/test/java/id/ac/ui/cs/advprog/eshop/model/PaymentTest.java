package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    private List<Product> products;
    List<Order> orders;

    HashMap<String ,String> paymentDataVoucherCode = new HashMap<>();
    HashMap<String ,String> paymentDataCashOnDelivery = new HashMap<>();
    HashMap<String ,String> paymentDataBankTransfer = new HashMap<>();



    @BeforeEach
    void setUp(){


        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708570000L, "Safira Sudrajat");

        orders.add(order1);
        orders.add(order2);

        paymentDataVoucherCode.put("voucherCode", "");
        paymentDataCashOnDelivery.put("address", "");
        paymentDataCashOnDelivery.put("deliveryFee", "");
        paymentDataBankTransfer.put("bankName", "");
        paymentDataBankTransfer.put("referenceCode", "");


    }

    @Test
    void testCreatePaymentDefaultStatus(){
        Payment payment = new Payment("id-1", "VOUCHER_CODE", paymentDataVoucherCode);

        assertEquals("id-1", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("WAITING", payment.getStatus());
        assertEquals(paymentDataVoucherCode, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id-1", "VOUCHER_CODED", paymentDataVoucherCode);
        });
    }

    @Test
    void testCreatePaymentSuccessStatus(){
        Payment payment = new Payment("id-1", "VOUCHER_CODE", "SUCCESS", paymentDataVoucherCode);

        assertEquals("id-1", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentDataVoucherCode, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id-1", "VOUCHER_CODE", "Gak success", paymentDataCashOnDelivery);
        });
    }

    @Test
    void testCreatePaymentInvalidPaymentData(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id-1", "VOUCHER_CODE", paymentDataCashOnDelivery);
        });
    }

    @Test
    void testEditPaymentWithCancelledStatus(){
        Payment payment = new Payment("id-1", "VOUCHER_CODE", paymentDataVoucherCode);
        payment.setStatus("CANCELLED");
        assertEquals("CANCELLED", payment.getStatus());
    }

    @Test
    void testCreateOrderInvalidMethod() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");

        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("6c93d3e2-b009-46ba-9d15-f03d85adc2de", "Berhasil :D", paymentDataVoucherCode);
        });
    }
}