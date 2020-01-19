package mad.king.etr.handler;

import mad.king.etr.model.ExtractTransformReader;
import mad.king.etr.model.ExtractTransformer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleXmlExtractTransformerHandlerTest {

    ExtractTransformer extractTransformerHandler;

    @Before
    public void setUp() throws Exception {

        extractTransformerHandler =
                ExtractTransformer.newSimpleXmlTransformer(new ExtractTransformMapper() {{
                    getMap().put("fogground", 0);
                    getMap().put("snowfall", 1);
                    getMap().put("fastest2minwindspeed", 2);
                    getMap().put("fogheavy", 3);
                    getMap().put("hail", 4);
                }});
    }

    @Test
    public void testRead() throws IOException {

        extractTransformerHandler.read(new ExtractTransformReader() {

            int rows = 0;

            @Override
            public File getFile() {
                return null;
            }

            @Override
            public int getBatch() {
                return 0;
            }

            @Override
            public void begin() {

            }

            @Override
            public void commit() {

            }

            @Override
            public void readLine(String s) {

            }

            @Override
            public void readBatch(String s) {

            }
        });

    }
}