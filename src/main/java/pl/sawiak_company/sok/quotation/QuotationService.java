package pl.sawiak_company.sok.quotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.code.CodeService;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interview.Interview;
import pl.sawiak_company.sok.interview.InterviewService;
import pl.sawiak_company.sok.quotation.dto.QuotationRequest;

import java.util.List;

@Service
public class QuotationService {
    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private InterviewService interviewService;
    @Autowired
    private CodeService codeService;

    public Quotation createQuotation(Integer interviewId, QuotationRequest request) {
        Interview interview = interviewService.getById(interviewId);

        Quotation quotation = Quotation.builder()
                .text(request.getText())
                .lineNumber(request.getLineNumber())
                .interview(interview)
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        if (request.getCodeId() != null) {
            Code code = codeService.getById(request.getCodeId());
            quotation.setCode(code);
        }

        quotationRepository.save(quotation);
        return quotation;
    }

    public Quotation getById(Integer id) {
        return quotationRepository.findById(id)
                .orElseThrow(() -> new RequestException("No QUOTATION within given Id: " + id, HttpStatus.BAD_REQUEST, "QUOTATION_NOT_EXISTS"));
    }

    public List<Quotation> getAll() {
        return quotationRepository.findAll();
    }

    public Quotation editQuotation(Integer quotationId, QuotationRequest request) {
        Quotation quotation = getById(quotationId);

        if (request.getCodeId() != null) {
            Code code = codeService.getById(request.getCodeId());
            quotation.setCode(code);
        }

        quotation.setText(request.getText());
        quotation.setLineNumber(request.getLineNumber());
        quotation.setEditionSignature(new EditionSignature());
        quotationRepository.save(quotation);
        return quotation;
    }

    public Quotation addCode(Integer quotationId, Integer codeId) {
        Quotation quotation = getById(quotationId);

        if (codeId != null) {
            Code code = codeService.getById(codeId);
            quotation.setCode(code);
        }

        quotation.setEditionSignature(new EditionSignature());
        quotationRepository.save(quotation);
        return quotation;
    }

    public void deleteQuotation(Integer id) {
        Quotation quotation = getById(id);
        quotationRepository.delete(quotation);
    }
}
