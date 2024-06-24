package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.request.RatingRequest;
import uniqueproject.uz.go2uz.dto.response.RatingResponse;
import uniqueproject.uz.go2uz.entity.Rating;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.RatingRepository;
import uniqueproject.uz.go2uz.repository.TourRepository;
import uniqueproject.uz.go2uz.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;


    public RatingResponse addRating(RatingRequest ratingRequest, UUID authorId) {
        Tour tour = tourRepository.findById(ratingRequest.getTourId())
                .orElseThrow(() -> new DataNotFoundException("Tour not found"));
        UserEntity author = userRepository.findById(authorId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (ratingRepository.existsByAuthorIdAndTour(authorId, tour)) {
            throw new IllegalArgumentException("User has already rated this tour");
        }

        Rating rating = Rating.builder()
                .author(author)
                .rating(ratingRequest.getRating())
                .tour(tour)
                .build();

        rating = ratingRepository.save(rating);
        return convertToDto(rating);
    }

    public void deleteRating(UUID ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    public List<RatingResponse> getRatingsByTour(UUID tourId) {
        List<Rating> ratings = ratingRepository.findByTourId(tourId);
        return ratings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public double getAverageRatingByTour(UUID tourId) {
        List<Rating> ratings = ratingRepository.findByTourId(tourId);
        return ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
    }

    private RatingResponse convertToDto(Rating rating) {
        return RatingResponse.builder()
                .id(rating.getId())
                .authorName(rating.getAuthor().getName())
                .rating(rating.getRating())
                .build();
    }
}
