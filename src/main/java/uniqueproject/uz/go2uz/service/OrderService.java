package uniqueproject.uz.go2uz.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.request.OrderRequest;
import uniqueproject.uz.go2uz.dto.response.OrderResponse;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;
import uniqueproject.uz.go2uz.entity.enums.TourStatus;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.forpayment.payme.Transaction;
import uniqueproject.uz.go2uz.forpayment.payme.TransactionRepository;
import uniqueproject.uz.go2uz.repository.OrderRepository;
import uniqueproject.uz.go2uz.repository.TourRepository;
import uniqueproject.uz.go2uz.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final TransactionRepository transactionRepository;


    public String orderTour(OrderRequest orderRequest) {
        // Validate tour
        Tour tour = tourRepository.findById(orderRequest.getTourId())
                .orElseThrow(() -> new DataNotFoundException("Tour not found with id: " + orderRequest.getTourId()));

        if (tour.getStatus() == TourStatus.CANCELLED) {
            return "Trip is cancelled";
        }

        if (tour.getAvailableSeats() < orderRequest.getNumberOfSeats()) {
            return "Not enough available seats";
        }

        // Validate user
        UserEntity user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + orderRequest.getUserId()));

        // Create order
        Order order = Order.builder()
                .tour(tour)
                .status(OrderStatus.PENDING)
                .orderDate(LocalDate.now())
                .numberOfSeats(orderRequest.getNumberOfSeats())
                .phoneNumber(orderRequest.getPhoneNumber())
                .user(user)
                .totalCost(tour.getCost() * orderRequest.getNumberOfSeats())
                .url(orderRequest.getUrl())
                .build();
        orderRepository.save(order);

        // Notify agency
        notificationService.notifyAgency(order);

        // Update available seats for the tour
        tour.setAvailableSeats(tour.getAvailableSeats() - orderRequest.getNumberOfSeats());
        if (tour.getAvailableSeats() == 0) {
            tour.setStatus(TourStatus.FULL);
        }
        tourRepository.save(tour);

        Transaction transaction = Transaction.builder()
                .order(order)
                .amount(order.getTotalCost())
                .url(order.getUrl())
                .user(order.getUser())
                .build();
        transactionRepository.save(transaction);

        // Return payment URL to frontend
        return transaction.getUrl();
    }



//    public PaymentResponseDTO payForOrder(PaymentRequestDTO paymentRequest) {
//        Order order = orderRepository.findById(paymentRequest.getOrderId())
//                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + paymentRequest.getOrderId()));
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("id", paymentRequest.getOrderId().toString());
//        data.put("amount", paymentRequest.getAmount());
//        data.put("paymentMethod", paymentRequest.getPaymentMethod());
//
//        Map<String, Object> paymentResponse = paymeService.createTransaction(data);
//
//        if ("Transaction created successfully".equals(paymentResponse.get("result"))) {
//            Transaction transaction = Transaction.builder()
//                    .transaction(paymentResponse.get("transaction").toString())
//                    .createTime(System.currentTimeMillis())
//                    .state(1) // Assuming 1 means created
//                    .build();
//
//            order.setTransaction(transaction);
//            order.setStatus(OrderStatus.APPROVED);
//            orderRepository.save(order);
//
//            notificationService.notifyUser(order);
//
//            PaymentResponseDTO responseDTO = new PaymentResponseDTO();
//            responseDTO.setResult("Transaction created successfully");
//            responseDTO.setMessage("Payment processed");
//            responseDTO.setTransactionId(paymentResponse.get("transaction").toString());
//            return responseDTO;
//        } else {
//            throw new RuntimeException("Payment failed");
//        }
//    }

//    public PaymentResponseDTO payForOrder(PaymentRequestDTO paymentRequest) {
//        Order order = orderRepository.findById(paymentRequest.getOrderId())
//                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + paymentRequest.getOrderId()));
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("id", paymentRequest.getOrderId().toString());
//        data.put("amount", paymentRequest.getAmount());
//        data.put("paymentMethod", paymentRequest.getPaymentMethod());
//
//        // Convert Map<String, Object> to PaymentRequestDTO
//        PaymentRequestDTO requestDTO = new PaymentRequestDTO();
//        requestDTO.setOrderId(UUID.fromString((String) data.get("id")));
//        requestDTO.setAmount((Double) data.get("amount"));
//        requestDTO.setPaymentMethod((String) data.get("paymentMethod"));
//
//        // Call the createTransaction method with PaymentRequestDTO
//        Map<String, Object> paymentResponse = paymeService.createTransaction(requestDTO);
//
//        if ("Transaction created successfully".equals(paymentResponse.get("result"))) {
//            Transaction transaction = Transaction.builder()
//                    .transaction(paymentResponse.get("transaction").toString())
//                    .createTime(System.currentTimeMillis())
//                    .state(1) // Assuming 1 means created
//                    .build();
//
//            order.setTransaction(transaction);
//            order.setStatus(OrderStatus.APPROVED);
//            orderRepository.save(order);
//
//            notificationService.notifyUser(order);
//
//            PaymentResponseDTO responseDTO = new PaymentResponseDTO();
//            responseDTO.setResult("Transaction created successfully");
//            responseDTO.setMessage("Payment processed");
//            responseDTO.setTransactionId(paymentResponse.get("transaction").toString());
//            return responseDTO;
//        } else {
//            throw new RuntimeException("Payment failed");
//        }
//    }



//    public PaymentResponseDTO payForOrder(PaymentRequestDTO paymentRequest) {
//        Order order = orderRepository.findById(paymentRequest.getOrderId())
//                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + paymentRequest.getOrderId()));
//
//        // Pass paymentRequest directly to createTransaction method
//        Map<String, Object> paymentResponse = paymeService.createTransaction(paymentRequest);
//
//        if ("Transaction created successfully".equals(paymentResponse.get("result"))) {
//            Transaction transaction = Transaction.builder()
//                    .transaction(paymentResponse.get("transaction").toString())
//                    .createTime(System.currentTimeMillis())
//                    .state(1) // Assuming 1 means created
//                    .build();
//
//            order.setTransaction(transaction);
//            order.setStatus(OrderStatus.APPROVED);
//            orderRepository.save(order);
//
//            notificationService.notifyUser(order);
//
//            PaymentResponseDTO responseDTO = new PaymentResponseDTO();
//            responseDTO.setResult("Transaction created successfully");
//            responseDTO.setMessage("Payment processed");
//            responseDTO.setTransactionId(paymentResponse.get("transaction").toString());
//            return responseDTO;
//        } else {
//            throw new RuntimeException("Payment failed");
//        }
//    }




    public OrderResponse updateOrderStatus(UUID orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            order.setUpdatedDate(LocalDateTime.now());
            Order updatedOrder = orderRepository.save(order);
        // Notify user
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

    public List<OrderResponse> getOrdersByUserId(UUID userId) {
        List<Order> myOrders =  orderRepository.findByUserId(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order myOrder : myOrders) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .orderDate(myOrder.getOrderDate())
                    .id(myOrder.getId())
                    .userId(myOrder.getUser().getId())
                    .orderId(myOrder.getId())
                    .numberOfSeats(myOrder.getNumberOfSeats())
                    .phoneNumber(myOrder.getPhoneNumber())
                    .totalCost(myOrder.getTotalCost())
                    .status(myOrder.getStatus())
                    .tourId(myOrder.getTour().getId()).build();
            orderResponses.add(orderResponse);

        }
        return orderResponses;
    }

    public List<OrderResponse> getOrdersByTourId(UUID tourId) {
        List<Order> myOrders =  orderRepository.findByTourId(tourId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order myOrder : myOrders) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .orderDate(myOrder.getOrderDate())
                    .id(myOrder.getId())
                    .userId(myOrder.getUser().getId())
                    .orderId(myOrder.getId())
                    .numberOfSeats(myOrder.getNumberOfSeats())
                    .phoneNumber(myOrder.getPhoneNumber())
                    .totalCost(myOrder.getTotalCost())
                    .status(myOrder.getStatus())
                    .tourId(myOrder.getTour().getId()).build();
            orderResponses.add(orderResponse);

        }
        return orderResponses;
    }
}





