package pl.sawiak_company.sok.code.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.sawiak_company.sok.code.Code;

import java.io.IOException;

public class CodeSerializer extends JsonSerializer<Code> {
    @Override
    public void serialize(Code code, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        CodeDto dto = new CodeDto(code);
        jsonGenerator.writeObject(dto);
    }
}
