package uniqueproject.uz.go2uz.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.request.TourRequest;
import uniqueproject.uz.go2uz.dto.auth.response.TourResponse;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.entity.Tour;
import uniqueproject.uz.go2uz.exception.DataAlreadyExistsException;
import uniqueproject.uz.go2uz.repository.AgencyRepository;
import uniqueproject.uz.go2uz.repository.TourRepository;

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
                .date(tourRequest.getDate())
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
}
