package uniqueproject.uz.go2uz.controller;

import jakarta.validation.*;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.dto.report.OrderReportRequest;
import uniqueproject.uz.go2uz.entity.enums.ReportFormat;
import uniqueproject.uz.go2uz.service.ReportService;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/orders")
    public ResponseEntity<byte[]> generateOrderReport(
            @Valid @RequestBody OrderReportRequest reportRequest,
            @RequestParam ReportFormat format) {

        ByteArrayInputStream reportStream = reportService.generateReport(reportRequest, format);

        String fileName = "order_report." + (format == ReportFormat.PDF ? "pdf" : "xlsx");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        MediaType mediaType = format == ReportFormat.PDF ? MediaType.APPLICATION_PDF : MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(reportStream.readAllBytes());
    }

}
