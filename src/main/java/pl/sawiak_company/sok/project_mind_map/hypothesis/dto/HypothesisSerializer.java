package pl.sawiak_company.sok.project_mind_map.hypothesis.dto;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;

import java.io.IOException;

public class HypothesisSerializer extends JsonSerializer<Hypothesis> {
    @Override
    public void serialize(Hypothesis hypothesis, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        HypothesisDto dto = new HypothesisDto(hypothesis);
        jsonGenerator.writeObject(dto);
    }
}
