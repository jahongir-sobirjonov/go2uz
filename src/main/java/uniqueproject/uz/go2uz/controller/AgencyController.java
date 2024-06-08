package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.dto.auth.response.AgencyResponse;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.service.AgencyService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("agencies")
public class AgencyController { // admin controller
    private final AgencyService agencyService;

   @GetMapping("/get{id}")
    public Optional<Agency> getAgencies(@PathVariable UUID id) {
       return agencyService.getById(id);
   }

    @GetMapping("/user-agencies")
    public ResponseEntity<List<AgencyResponse>> getUserAgencies(@RequestParam UUID userId) {
        List<AgencyResponse> agencies = agencyService.getAgenciesByUserId(userId);
        return ResponseEntity.ok(agencies);
    }

}
