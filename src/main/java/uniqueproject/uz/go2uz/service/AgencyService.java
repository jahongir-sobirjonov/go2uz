package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.response.AgencyResponse;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.repository.AgencyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return modelMapper.map(byOwnerId, new TypeToken<List<AgencyResponse>>() {}.getType());
    }
}
