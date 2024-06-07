package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.service.TourService;

@RestController
@RequiredArgsConstructor
@RequestMapping("tours")
public class TourController {
    private final TourService tourService;


}
