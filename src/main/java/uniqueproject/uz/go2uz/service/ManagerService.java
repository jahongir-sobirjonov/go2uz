package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.response.OrderResponse;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.OrderRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final OrderRepository orderRepository;


    public List<OrderResponse> getOrdersByAgency(UUID agencyId) {
        List<Order> orders = orderRepository.findByTour_Agency_Id(agencyId);
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .userId(order.getUser().getId())
                        .tourId(order.getTour().getId())
                        .status(order.getStatus())
                        .orderDate(order.getOrderDate())
                        .build())
                .collect(Collectors.toList());
    }

    public OrderResponse updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .id(updatedOrder.getId())
                .userId(updatedOrder.getUser().getId())
                .tourId(updatedOrder.getTour().getId())
                .status(updatedOrder.getStatus())
                .orderDate(updatedOrder.getOrderDate())
                .build();
    }
}
