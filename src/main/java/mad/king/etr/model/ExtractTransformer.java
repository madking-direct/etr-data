package mad.king.etr.model;

import mad.king.etr.handler.SimpleCsvExtractTransformerHandler;
import mad.king.etr.handler.ExtractTransformMapper;
import mad.king.etr.handler.ExtractTransformerHandler;
import mad.king.etr.handler.SimpleJsonExtractTransformerHandler;

import java.io.IOException;

public interface ExtractTransformer {

    void read(ExtractTransformReader dataTransformReader) throws IOException;

    static ExtractTransformerHandler newSimpleCsvTransformer(ExtractTransformMapper extractTransformMapper) {
        return new SimpleCsvExtractTransformerHandler(extractTransformMapper);
    }

    static ExtractTransformerHandler newSimpleJsonTransformer(ExtractTransformMapper extractTransformMapper) {
        return new SimpleJsonExtractTransformerHandler(extractTransformMapper);
    }

    static ExtractTransformerHandler newSimpleXmlTransformer(ExtractTransformMapper extractTransformMapper) {
        return new SimpleCsvExtractTransformerHandler(extractTransformMapper);
    }

    static ExtractTransformer newCustomTransformer(ExtractTransformerHandler extractTransformerHandler) {
        return extractTransformerHandler;
    }
}
