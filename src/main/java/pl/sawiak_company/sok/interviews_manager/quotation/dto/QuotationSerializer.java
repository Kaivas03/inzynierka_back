package pl.sawiak_company.sok.interviews_manager.quotation.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.sawiak_company.sok.interviews_manager.interview.dto.InterviewDto;
import pl.sawiak_company.sok.interviews_manager.quotation.Quotation;

import java.io.IOException;

public class QuotationSerializer extends JsonSerializer<Quotation> {
    @Override
    public void serialize(Quotation quotation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        QuotationDto dto = new QuotationDto(quotation);
        jsonGenerator.writeObject(dto);
    }
}
