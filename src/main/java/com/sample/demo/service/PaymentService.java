package com.sample.demo.service;

import com.sample.demo.dto.InvoiceDTO;
import com.sample.demo.dto.PaymentDTO;
import com.sample.demo.dto.ProductDTO;
import com.sample.demo.exception.SaleNotFoundException;
import com.sample.demo.model.*;
import com.sample.demo.repository.InvoiceDetailRepository;
import com.sample.demo.repository.InvoiceRepository;
import com.sample.demo.repository.OrderRepository;
import com.sample.demo.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final OrderRepository orderRepository;
    private final SaleRepository saleRepository;
    private final InvoiceService invoiceService;
    private final UserService userService;

    public boolean payment(PaymentDTO paymentDTO){
        long totalInvoice = 0L;
        Optional<Sale> sale = saleRepository.findById(paymentDTO.getSaleId());
        if(sale.isEmpty()){
            throw new SaleNotFoundException("Not found sale with saleId: " + paymentDTO.getSaleId());
        }
        List<ProductDTO> listProducts = paymentDTO.getListProducts();
        User user = userService.findUserById(paymentDTO.getUserId());

        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .createAt(new Date())
                .totalInvoice(0L)
                .isOnline(paymentDTO.getIsOnline())
                .userId(user.getUserId())
                .saleId(sale.map(Sale::getSaleId).orElse(null))
                .build();
        Invoice createdInvoice =  invoiceService.addInvoice(invoiceDTO);

        Optional<Invoice> invoice = invoiceService.getInvoiceLastes();
        if(invoice.isPresent()){
            //create Order
            Order order = Order.builder()
                    .invoice(invoice.get())
                    .email(user.getUsername())
                    .phoneNumber(user.getPhoneNumber())
                    .paymentMethod(
                            paymentDTO.getIsOnline() ? "Online" : "COD"
                    )
                    .status( paymentDTO.getIsOnline() ? Status.PENDING : Status.DELIVERED)
                    .note(paymentDTO.getNote())
                    .build();
            orderRepository.save(order);

            //create list invoicedetails

            for(ProductDTO productDTO : listProducts){
                Long upSize = Long.valueOf(0);
                if(productDTO.getSize() != Size.S){
                    upSize = productDTO.getSize() == Size.M ? Long.valueOf(5000) : Long.valueOf(10000);
                }
                String topping = productDTO.getTopping().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
                InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                        .invoice(invoice.get())
                        .size(productDTO.getSize())
                        .topping(topping)
                        .price(productDTO.getPrice())
                        .quantity(productDTO.getQuantity())
                        .totalPrice((upSize + productDTO.getPrice()) * productDTO.getQuantity())
                        .build();
                invoiceDetailRepository.save(invoiceDetail);

                totalInvoice += (upSize + productDTO.getPrice()) * productDTO.getQuantity();
            }

            if (invoice.isPresent()) {
                InvoiceDTO invoiceUpdate = new InvoiceDTO();
                Invoice invoiceModel = invoice.get();

                invoiceUpdate.setIsOnline(invoiceModel.getIsOnline());
                invoiceUpdate.setCreateAt(invoiceModel.getCreateAt());
                invoiceUpdate.setTotalInvoice(totalInvoice);

                // Điền các giá trị cho userId và saleId dựa trên đối tượng User và Sale tương ứng
                if (invoiceModel.getUser() != null) {
                    invoiceUpdate.setUserId(invoiceModel.getUser().getUserId());
                }
                if (invoiceModel.getSale() != null) {
                    invoiceUpdate.setSaleId(invoiceModel.getSale().getSaleId());
                }

                invoiceService.updateInvoice(invoiceUpdate);
            }
        }
        else {
            invoiceService.deleteInvoice(createdInvoice.getInvoiceId());
            return false;
        }
        return true;
    }
}
