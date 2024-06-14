package uniqueproject.uz.go2uz.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.request.OrderRequest;
import uniqueproject.uz.go2uz.dto.auth.response.OrderResponse;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;
import uniqueproject.uz.go2uz.entity.enums.TourStatus;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.OrderRepository;
import uniqueproject.uz.go2uz.repository.TourRepository;
import uniqueproject.uz.go2uz.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;


    public String orderTour(OrderRequest orderRequest) {
        Tour tour = tourRepository.findById(orderRequest.getTourId())
                .orElseThrow(() -> new DataNotFoundException("Tour not found with id: " + orderRequest.getTourId()));

        if (tour.getStatus() == TourStatus.CANCELLED) {
            return "Trip is cancelled";
        }

        if (tour.getAvailableSeats() < orderRequest.getNumberOfSeats()) {
            return "Not enough available seats";
        }


        UserEntity user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + orderRequest.getUserId()));

            Order order = new Order();
            order.setTour(tour);
            order.setStatus(OrderStatus.PENDING);
            order.setOrderDate(LocalDate.now());
            order.setNumberOfSeats(orderRequest.getNumberOfSeats());
            order.setPhoneNumber(orderRequest.getPhoneNumber());
        orderRepository.save(order);
            order.setUser(user);
        notificationService.notifyAgency(order);


        // Update available seats for the trip
        tour.setAvailableSeats(tour.getAvailableSeats() - orderRequest.getNumberOfSeats());
        if (tour.getAvailableSeats() == 0) {
            tour.setStatus(TourStatus.FULL);
        }
        tourRepository.save(tour);

        return "Order successful";
    }



    public OrderResponse updateOrderStatus(UUID orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            order.setUpdatedDate(LocalDateTime.now());
            Order updatedOrder = orderRepository.save(order);

            // Notify the user
            notificationService.notifyUser(order);

            OrderResponse orderResponse = modelMapper.map(updatedOrder, OrderResponse.class);
            orderResponse.setUserId(order.getUser().getId());
            orderResponse.setTourId(order.getTour().getId());
            orderResponse.setStatus(status);
            orderResponse.setPhoneNumber(order.getPhoneNumber());
            return orderResponse;
        } else {
            throw new EntityNotFoundException("Order not found");
        }
    }




}
