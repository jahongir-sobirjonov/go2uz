package uniqueproject.uz.go2uz.dto.auth.report;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uniqueproject.uz.go2uz.entity.enums.ReportType;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderReportRequest {
//    @NotNull
    private LocalDate startDate;
//    @NotNull
    private LocalDate endDate;
    private List<String> travelTypes;
    private List<String> bookingStatuses;
    private ReportType reportType;
}


