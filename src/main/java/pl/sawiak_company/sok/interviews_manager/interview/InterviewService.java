package pl.sawiak_company.sok.interviews_manager.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interviews_manager.interview.dto.InterviewRequest;
import pl.sawiak_company.sok.interviews_manager.quotation.Quotation;
import pl.sawiak_company.sok.interviews_manager.quotation.QuotationService;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;
import pl.sawiak_company.sok.sociological_project.SociologicalProjectService;

import java.util.List;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private SociologicalProjectService projectService;
    @Autowired
    private QuotationService quotationService;

    public Interview createInterview(Integer projectId, InterviewRequest request) {
        SociologicalProject project = projectService.getById(projectId);

        Interview interview = Interview.builder()
                .name(request.getName())
                .text(request.getText())
                .sociologicalProject(project)
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        interviewRepository.save(interview);
        return interview;
    }

    public Interview getById(Integer id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new RequestException("No INTERVIEW within given Id: " + id, HttpStatus.BAD_REQUEST, "INTERVIEW_NOT_EXISTS"));
    }

    public List<Interview> getAllByProject(Integer projectId) {
        SociologicalProject project = projectService.getById(projectId);
        return getAllByProject(project);
    }

    public List<Interview> getAllByProject(SociologicalProject project) {
        return interviewRepository.findAllBySociologicalProject(project);
    }

    public Interview editInterview(Integer id, InterviewRequest request) {
        Interview interview = getById(id);

        interview.setName(request.getName());
        interview.setText(request.getText());
        interview.setEditionSignature(new EditionSignature());
        interviewRepository.save(interview);
        return interview;
    }

    public void deleteInterview(Integer id) {
        Interview interview = getById(id);
        deleteInterview(interview);
    }

    @Transactional
    public void deleteInterview(Interview interview) {
        List<Quotation> quotations = quotationService.getAllByInterview(interview);
        quotations.forEach(quotation -> quotationService.deleteQuotation(quotation));

        interviewRepository.delete(interview);
    }
}
