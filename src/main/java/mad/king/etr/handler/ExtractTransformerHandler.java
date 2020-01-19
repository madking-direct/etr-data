package mad.king.etr.handler;

import mad.king.etr.model.ExtractTransformer;

public abstract class ExtractTransformerHandler implements ExtractTransformer {

    private ExtractTransformMapper extractTransformMapper;

    public ExtractTransformerHandler(ExtractTransformMapper extractTransformMapper) {
        this.extractTransformMapper = extractTransformMapper;
    }

    public ExtractTransformMapper getExtractTransformMapper() {
        return extractTransformMapper;
    }

}
