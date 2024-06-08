package uniqueproject.uz.go2uz.service;

import org.modelmapper.ModelMapper;
import uniqueproject.uz.go2uz.dto.auth.request.OrderRequest;
import uniqueproject.uz.go2uz.dto.auth.response.OrderResponse;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.OrderRepository;
import uniqueproject.uz.go2uz.repository.TourRepository;
import uniqueproject.uz.go2uz.repository.UserRepository;

import java.util.Date;

public class OrderService {
    private final OrderRepository orderRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public OrderResponse createOrder(OrderRequest orderRequest) {
        UserEntity user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        Tour tour = tourRepository.findById(orderRequest.getTourId())
                .orElseThrow(() -> new DataNotFoundException("Tour not found"));

        Order order = Order.builder()
                .user(user)
                .tour(tour)
                .status(OrderStatus.PENDING)
                .orderDate(new Date())
                .build();

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .id(savedOrder.getId())
                .userId(savedOrder.getUser().getId())
                .tourId(savedOrder.getTour().getId())
                .status(savedOrder.getStatus())
                .orderDate(savedOrder.getOrderDate())
                .build();
    }
}
