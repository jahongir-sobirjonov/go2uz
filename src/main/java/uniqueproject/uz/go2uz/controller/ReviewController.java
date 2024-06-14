package uniqueproject.uz.go2uz.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniqueproject.uz.go2uz.dto.auth.request.ReviewRequest;
import uniqueproject.uz.go2uz.dto.auth.response.ReviewResponse;
import uniqueproject.uz.go2uz.service.ReviewService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/write-review")
    public ResponseEntity<ReviewResponse> writeReview(@Valid @RequestBody ReviewRequest reviewRequest, Principal principal) {
        ReviewResponse reviewResponse = reviewService.writeReview(reviewRequest, UUID.fromString(principal.getName()));
        return ResponseEntity.ok(reviewResponse);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByTour(@PathVariable UUID tourId) {
        List<ReviewResponse> reviews = reviewService.getReviewsByTour(tourId);
        return ResponseEntity.ok(reviews);
    }


}

