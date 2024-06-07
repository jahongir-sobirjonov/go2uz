package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.service.AgencyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("agencies")
public class AgencyController {
    private final AgencyService agencyService;
}
