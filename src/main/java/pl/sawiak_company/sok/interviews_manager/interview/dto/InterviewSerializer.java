package pl.sawiak_company.sok.interviews_manager.interview.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.sawiak_company.sok.interviews_manager.interview.Interview;

import java.io.IOException;

public class InterviewSerializer extends JsonSerializer<Interview> {
    @Override
    public void serialize(Interview interview, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        InterviewDto dto = new InterviewDto(interview);
        jsonGenerator.writeObject(dto);
    }
}
