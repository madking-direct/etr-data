package mad.king.etr.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mad.king.etr.model.ExtractTransformReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleJsonExtractTransformerHandler extends ExtractTransformerHandler {

    public SimpleJsonExtractTransformerHandler(ExtractTransformMapper extractTransformMapper) {
        super(extractTransformMapper);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void read(ExtractTransformReader extractTransformReader) throws IOException {

        File file = extractTransformReader.getFile();
        List<Map<String, Object>> maps = objectMapper.readValue(file, new TypeReference<List>() {
        });

        List<String> list = new ArrayList<>();
        extractTransformReader.begin();
        maps.forEach(objectMap -> {
            Object[] array = new Object[getExtractTransformMapper().getMap().size()];
            objectMap.entrySet().forEach(entry -> {
                if (getExtractTransformMapper().getMap().containsKey(entry.getKey()))
                    array[getExtractTransformMapper().getMap().get(entry.getKey())] = entry.getValue();
            });

            try {
                String arrayValue = objectMapper
                        .writeValueAsString(array)
                        .replaceAll("\\[", "(")
                        .replaceAll("]", ")")
                        .replaceAll("\"", "'");
                extractTransformReader.readLine(arrayValue);
                list.add(arrayValue);
                if (list.size() % extractTransformReader.getBatch() == 0) {
                    String listValue = objectMapper
                            .writeValueAsString(list)
                            .replaceAll("\\[", "")
                            .replaceAll("]", "")
                            .replaceAll("\"", "");
                    extractTransformReader.readBatch(listValue);
                    list.clear();
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        if (list.size() > 0) {
            String listValue = objectMapper
                    .writeValueAsString(list)
                    .replaceAll("\\[", "")
                    .replaceAll("]", "")
                    .replaceAll("\"", "");
            extractTransformReader.readBatch(listValue);
        }
        list.clear();
        extractTransformReader.commit();
    }
}
