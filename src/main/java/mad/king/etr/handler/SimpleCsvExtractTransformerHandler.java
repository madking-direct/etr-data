package mad.king.etr.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mad.king.etr.model.ExtractTransformReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleCsvExtractTransformerHandler extends ExtractTransformerHandler {

    public SimpleCsvExtractTransformerHandler(ExtractTransformMapper extractTransformMapper) {
        super(extractTransformMapper);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void read(ExtractTransformReader dataTransformReader) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(dataTransformReader.getFile()));
        HashMap<String, Integer> map = getExtractTransformMapper().getMap();

        int rows = 0;
        int header = dataTransformReader.hasHeader();
        Object[] objects = new Object[map.size()];
        List<String> list = new ArrayList<>();

        String bufferedLine = null;
        dataTransformReader.begin();
        while ((bufferedLine = bufferedReader.readLine()) != null) {
            rows++;
            if (rows > header) {
                String[] values = bufferedLine.split("[\\|,]");
                objects = new Object[map.size()];
                for (int i = 0; i < values.length; i++)
                    if (map.containsKey(String.valueOf(i)))
                        objects[map.get(String.valueOf(i))] = values[i];

                String value = objectMapper.writeValueAsString(objects)
                        .replaceAll("\\[", "(")
                        .replaceAll("]", ")")
                        .replaceAll("\"", "'");
                dataTransformReader.readLine(value);
                list.add(value);

                if (rows % dataTransformReader.getBatch() == 0) {
                    try {
                        dataTransformReader.readBatch(objectMapper.writeValueAsString(list)
                                .replaceAll("\\[", "")
                                .replaceAll("]", "")
                                .replaceAll("\"", ""));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    list.clear();
                }

            }
        }
        try {
            dataTransformReader.readBatch(objectMapper.writeValueAsString(list)
                    .replaceAll("\\[", "")
                    .replaceAll("]", "")
                    .replaceAll("\"", ""));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        list.clear();
        dataTransformReader.commit();

    }
}
