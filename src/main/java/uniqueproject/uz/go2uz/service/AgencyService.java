package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.request.AgencyRequest;
import uniqueproject.uz.go2uz.dto.auth.response.AgencyResponse;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.entity.enums.ServiceType;
import uniqueproject.uz.go2uz.exception.DataAlreadyExistsException;
import uniqueproject.uz.go2uz.repository.AgencyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final AgencyRepository agencyRepository;
    private final ModelMapper modelMapper;

    public Optional<Agency> getById(UUID id) {
        return agencyRepository.findById(id);
    }

    public List<AgencyResponse> getAgenciesByUserId(UUID adminId) {
//        List<Agency> agencies = agencyRepository.findByOwnerId(userId);
//        return agencies.stream().map(agency -> new AgencyResponse(
//                agency.getName(),
//                agency.getTours(),
//                agency.getServiceTypes(),
//                agency.getCountOfOrders(),
//                agency.getRating()
//        )).collect(Collectors.toList());
        List<Agency> byOwnerId = agencyRepository.findByOwnerId(adminId);
        return modelMapper.map(byOwnerId, new TypeToken<List<AgencyResponse>>() {
        }.getType());
    }

    public AgencyResponse createAgency(AgencyRequest agencyRequest, List<ServiceType> serviceTypes, UUID ownerId) {
        if (agencyRepository.existsByName(agencyRequest.getName())) {
            throw new DataAlreadyExistsException("Agency already exists with this name : " + agencyRequest.getName());
        }
        Agency agency = modelMapper.map(agencyRequest, Agency.class);
        agency.setCountOfOrders(0);
        agency.setRating(0);
        agency.setOwnerId(ownerId);
        agency.setServiceTypes(serviceTypes);
        agencyRepository.save(agency);

        return AgencyResponse.builder()
                .id(agency.getId())
                .name(agency.getName())
//                .tours(modelMapper.map(agency.getTours(), new TypeToken<List<AgencyResponse>>() {
//                }.getType()))
                .serviceTypes(agency.getServiceTypes())
                .countOfOrders(agency.getCountOfOrders())
                .rating(agency.getRating())
                .build();

    }
}
