package mad.king.etr.handler;

import mad.king.etr.model.ExtractTransformReader;
import mad.king.etr.model.ExtractTransformer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleJsonExtractTransformerHandlerTest {

    ExtractTransformer extractTransformerHandler;

    @Before
    public void setUp() throws Exception {

        extractTransformerHandler =
                ExtractTransformer.newSimpleJsonTransformer(new ExtractTransformMapper() {{
                    getMap().put("fogground", 0);
                    getMap().put("snowfall", 1);
                    getMap().put("fastest2minwindspeed", 2);
                    getMap().put("fogheavy", 3);
                    getMap().put("hail", 4);
                }});
    }

    @Test
    @Ignore
    public void testRead() throws IOException {

        extractTransformerHandler.read(new ExtractTransformReader() {

            int rows = 0;

            @Override
            public File getFile() {
                return new File("data/rdu-weather-history.json");
            }

            @Override
            public int getBatch() {
                return 199;
            }

            @Override
            public int hasHeader() {
                return 0;
            }

            @Override
            public void begin() {

            }


            @Override
            public void readLine(String s) {
                rows++;
            }

            @Override
            public void readBatch(String s) {
                System.out.println(s);
            }

            @Override
            public void commit() {
                System.out.printf("rows count = %s", rows);
            }
        });

    }
}