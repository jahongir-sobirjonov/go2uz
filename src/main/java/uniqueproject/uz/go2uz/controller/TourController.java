package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.dto.auth.request.FilterToursRequest;
import uniqueproject.uz.go2uz.dto.auth.request.OrderRequest;
import uniqueproject.uz.go2uz.dto.auth.request.TourRequest;
import uniqueproject.uz.go2uz.dto.auth.request.TourUpdateRequest;
import uniqueproject.uz.go2uz.dto.auth.response.TourResponse;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.service.TourService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("tours")
public class TourController {
    private final TourService tourService;
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-tour")
    public ResponseEntity<TourResponse> addTour(@RequestBody TourRequest tourRequest) {
        TourResponse tourResponse = tourService.addTourToAgency(tourRequest.getAgencyId(), tourRequest);
        return ResponseEntity.status(200).body(tourResponse);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update-tour")
    public ResponseEntity<String> updateTour(@RequestBody TourUpdateRequest tourUpdateRequest) {
        return ResponseEntity.status(200).body(tourService.updateTour(tourUpdateRequest));

    }

//    @GetMapping("/get-by-date")
//    public ResponseEntity<TourResponse> getTourByDate(@RequestParam String date) {}
    @PostMapping("/filter")
    public ResponseEntity<List<TourResponse>> filterTours(@RequestBody FilterToursRequest filterRequest) {
        List<TourResponse> tours = tourService.filterTours(filterRequest);
        return ResponseEntity.ok(tours);
    }

}
