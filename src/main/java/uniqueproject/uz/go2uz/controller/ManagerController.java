package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniqueproject.uz.go2uz.dto.response.OrderResponse;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;
import uniqueproject.uz.go2uz.service.ManagerService;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("api/v1/managers")
@RequiredArgsConstructor
public class ManagerController {
    private ManagerService managerService;


     @GetMapping("/orders-by-agency/{agencyId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByAgency(@PathVariable UUID agencyId) {
        List<OrderResponse> orders = managerService.getOrdersByAgency(agencyId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/update-orders/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable UUID orderId,
            @RequestParam OrderStatus status) {
        OrderResponse orderResponse = managerService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(orderResponse);
    }

}
