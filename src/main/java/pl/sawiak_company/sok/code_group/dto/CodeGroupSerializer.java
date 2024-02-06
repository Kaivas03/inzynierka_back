package pl.sawiak_company.sok.code_group.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.interviews_manager.interview.dto.InterviewDto;

import java.io.IOException;

public class CodeGroupSerializer extends JsonSerializer<CodeGroup> {
    @Override
    public void serialize(CodeGroup codeGroup, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        CodeGroupDto dto = new CodeGroupDto(codeGroup);
        jsonGenerator.writeObject(dto);
    }
}
