package pl.sawiak_company.sok.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interview.dto.InterviewRequest;

import java.util.List;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    public Interview createInterview(InterviewRequest request) {
        Interview interview = Interview.builder()
                .name(request.getName())
                .text(request.getText())
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        interviewRepository.save(interview);
        return interview;
    }

    public Interview getById(Integer id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new RequestException("No PROJECT within given Id: " + id, HttpStatus.BAD_REQUEST, "PROJECT_NOT_EXISTS"));
    }

    public List<Interview> getAll() {
        return interviewRepository.findAll();
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
        interviewRepository.delete(interview);
    }
}
