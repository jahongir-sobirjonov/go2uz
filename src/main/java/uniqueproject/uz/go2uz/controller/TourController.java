package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.dto.auth.request.TourRequest;
import uniqueproject.uz.go2uz.dto.auth.response.TourResponse;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.service.TourService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("tours")
public class TourController {
    private final TourService tourService;

    @PostMapping("/add-tour")
    public ResponseEntity<TourResponse> addTour(@RequestBody TourRequest tourRequest) {
        TourResponse tourResponse = tourService.addTourToAgency(tourRequest.getAgencyId(), tourRequest);
        return ResponseEntity.status(200).body(tourResponse);
    }


}
