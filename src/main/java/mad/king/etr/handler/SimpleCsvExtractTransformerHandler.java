package mad.king.etr.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import mad.king.etr.model.ExtractTransformReader;

import java.io.IOException;

public class SimpleCsvExtractTransformerHandler extends ExtractTransformerHandler {

    public SimpleCsvExtractTransformerHandler(ExtractTransformMapper extractTransformMapper) {
        super(extractTransformMapper);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void read(ExtractTransformReader dataTransformReader) throws IOException {

    }
}
