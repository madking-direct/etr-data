package mad.king.etr.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import mad.king.etr.model.ExtractTransformReader;

import java.io.IOException;

public class SimpleXmlExtractTransformerHandler extends ExtractTransformerHandler {

    public SimpleXmlExtractTransformerHandler(ExtractTransformMapper extractTransformMapper) {
        super(extractTransformMapper);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void read(ExtractTransformReader dataTransformReader) throws IOException {

    }
}
