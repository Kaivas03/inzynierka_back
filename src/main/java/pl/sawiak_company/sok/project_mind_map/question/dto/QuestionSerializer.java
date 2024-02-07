package pl.sawiak_company.sok.project_mind_map.question.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.sawiak_company.sok.project_mind_map.question.Question;

import java.io.IOException;

public class QuestionSerializer extends JsonSerializer<Question> {
    @Override
    public void serialize(Question question, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        QuestionDto dto = new QuestionDto(question);
        jsonGenerator.writeObject(dto);
    }
}
