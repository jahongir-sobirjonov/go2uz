package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.service.AgencyService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("agencies")
public class AgencyController {
    private final AgencyService agencyService;

   @GetMapping("/get{id}")
    public Optional<Agency> getAgencies(@PathVariable UUID id) {
       return agencyService.getById(id);
   }
}
