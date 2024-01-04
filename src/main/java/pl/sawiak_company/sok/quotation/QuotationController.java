package pl.sawiak_company.sok.quotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.quotation.dto.QuotationRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    @PostMapping("/{interviewId}")
    public ResponseEntity<Quotation> create(@PathVariable(name = "interviewId") Integer interviewId,
                                            @RequestBody QuotationRequest request) {
        Quotation quotation = quotationService.createQuotation(interviewId, request);
        log.info("Created new Project {}", quotation);
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Quotation> edit(@PathVariable(name = "id") Integer id,
                                          @RequestBody QuotationRequest request) {
        Quotation quotation = quotationService.editQuotation(id, request);
        log.info("Updated Project {}", id);
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quotation> getById(@PathVariable(name = "id") Integer id) {
        Quotation quotation = quotationService.getById(id);
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Quotation>> getAll() {
        List<Quotation> quotations = quotationService.getAll();
        return new ResponseEntity<>(quotations, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        quotationService.deleteQuotation(id);
        log.info("Deleted Project {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
