package mad.king.etr.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mad.king.etr.model.ExtractTransformReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleXmlExtractTransformerHandler extends ExtractTransformerHandler {

    public SimpleXmlExtractTransformerHandler(ExtractTransformMapper extractTransformMapper) {
        super(extractTransformMapper);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void read(ExtractTransformReader dataTransformReader) throws IOException {

        FileInputStream fileInputStream
                = new FileInputStream(dataTransformReader.getFile());

        System.out.println(">>> dataTransformReader.getFile().getName()");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        HashMap<String, Integer> map = getExtractTransformMapper().getMap();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fileInputStream);

            int eventCount = 0;
            Object[] objects = new Object[map.size()];
            List<String> list = new ArrayList<>();


            dataTransformReader.begin();
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "RequestList":
                        case "Request":
                            objects = new Object[map.size()];
                            break;
                        default:
                            String localPart = startElement.getName().getLocalPart();
                            nextEvent = reader.nextEvent();
                            objects[map.get(localPart)] = nextEvent.toString();
                            break;
                    }
                } else if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    switch (endElement.getName().getLocalPart()) {
                        case "Request":
                            eventCount++;
                            try {
                                String value = objectMapper.writeValueAsString(objects)
                                        .replaceAll("\\[", "(")
                                        .replaceAll("]", ")")
                                        .replaceAll("\"", "'");
                                dataTransformReader.readLine(value);
                                list.add(value);

                                if (eventCount % dataTransformReader.getBatch() == 0) {
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
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "RequestList":
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
                            break;
                        default:
                            break;
                    }
                }
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }
}
