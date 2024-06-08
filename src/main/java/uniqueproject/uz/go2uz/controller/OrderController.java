package uniqueproject.uz.go2uz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uniqueproject.uz.go2uz.dto.auth.request.OrderRequest;
import uniqueproject.uz.go2uz.dto.auth.response.OrderResponse;
import uniqueproject.uz.go2uz.service.OrderService;

public class OrderController {
    private final OrderService orderService;

    @PostMapping("/buy-tour")
    public ResponseEntity<OrderResponse> buyTour(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
