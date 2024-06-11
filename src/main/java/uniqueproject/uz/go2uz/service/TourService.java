package uniqueproject.uz.go2uz.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.request.TourRequest;
import uniqueproject.uz.go2uz.dto.auth.request.TourUpdateRequest;
import uniqueproject.uz.go2uz.dto.auth.response.TourResponse;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.repository.AgencyRepository;
import uniqueproject.uz.go2uz.repository.TourRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final AgencyRepository agencyRepository;
    private final ModelMapper modelMapper;

    public TourResponse addTourToAgency(UUID agencyId, TourRequest tourRequest) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new EntityNotFoundException("Agency not found with id: " + agencyId));

//        boolean tourExists = agency.getTours().stream()
//                .anyMatch(tour -> tour.getTitle().equals(tourRequest.getTitle()));
//        if (tourExists) {
//            throw new DataAlreadyExistsException("Tour with the same title already exists for this agency.");
//        }
        Tour newTour = Tour.builder()
                .agency(agency)
                .category(tourRequest.getCategory())
                .cost(tourRequest.getCost())
                .startDate(tourRequest.getStartDate())
                .endDate(tourRequest.getEndDate())
                .availableSeats(tourRequest.getAvailableSeats())
                .description(tourRequest.getDescription())
                .location(tourRequest.getLocation())
                .title(tourRequest.getTitle())
                .pictures(tourRequest.getPictures())
                .build();
       tourRepository.save(newTour);
       agency.getTours().add(newTour);
       agencyRepository.save(agency);

        TourResponse tourResponse = modelMapper.map(newTour, TourResponse.class);
        tourResponse.setAgencyName(agency.getName());

        return tourResponse;
    }

    public String updateTour(TourUpdateRequest tourUpdateRequest) {
        Tour tour = tourRepository.findById(tourUpdateRequest.getTourId())
                .orElseThrow(() -> new EntityNotFoundException("Tour not found with id: " + tourUpdateRequest.getTourId()));

        tour.setAvailableSeats(tourUpdateRequest.getAvailableSeats());
        tour.setCategory(tourUpdateRequest.getCategory());
        tour.setCost(tourUpdateRequest.getCost());
        tour.setDescription(tourUpdateRequest.getDescription());
        tour.setEndDate(tourUpdateRequest.getEndDate());
        tour.setLocation(tourUpdateRequest.getLocation());
        tour.setPictures(tourUpdateRequest.getPictures());
        tour.setStartDate(tourUpdateRequest.getStartDate());
        tour.setStatus(tourUpdateRequest.getStatus());
        tour.setTitle(tourUpdateRequest.getTitle());
        tour.setUpdatedDate(LocalDateTime.now());
        tourRepository.save(tour);

        return "Tour updated successfully";
    }
}
