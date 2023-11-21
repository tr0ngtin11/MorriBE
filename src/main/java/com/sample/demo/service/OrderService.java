package com.sample.demo.service;

import com.sample.demo.dto.ResponseStringDTO;
import com.sample.demo.model.Order;
import com.sample.demo.model.Status;
import com.sample.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public boolean updateStutusOrder(Long orderId) {
        Optional<Order> currentOrder = orderRepository.findById(orderId);
        if (currentOrder.isPresent()) {
            Integer currentIndex = currentOrder.get().getStatus().ordinal();
            Integer nextIndex = currentIndex++;
            Status[] listStatus = Status.values();
            if (nextIndex < listStatus.length) {
                currentOrder.get().setStatus(listStatus[nextIndex]);
                orderRepository.save(currentOrder.get());
                return true;
            }
        }
        return false;
    }

    public boolean cancelOrder(Long orderId) {
        Optional<Order> currentOrder = orderRepository.findById(orderId);
        if (currentOrder.isPresent()) {
            if (currentOrder.get().getStatus() == Status.PENDING) {
                currentOrder.get().setStatus(Status.CANCALED);
                return true;
            }
        }
        return false;
}


}
