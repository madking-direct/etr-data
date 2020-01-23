package mad.king.etr.handler;

import mad.king.etr.model.ExtractTransformReader;
import mad.king.etr.model.ExtractTransformer;
import org.junit.Before;
import org.junit.Ignore;
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
                    getMap().put("transactionDate", 0);
                    getMap().put("transactionTime", 1);
                    getMap().put("tierCode", 2);
                    getMap().put("mscAmount", 3);
                    getMap().put("entityCode", 4);
                    getMap().put("scheme", 5);
                    getMap().put("cardType", 6);
                    getMap().put("accountType", 7);
                    getMap().put("cdtDesc", 8);
                    getMap().put("cgt", 9);
                    getMap().put("cgtDesc", 10);
                    getMap().put("extMIDhimms", 11);
                    getMap().put("merchantDBAtxt", 12);
                    getMap().put("merchantCntryCode", 13);
                    getMap().put("transactionAmount", 14);
                    getMap().put("transactionCurrency", 15);
                    getMap().put("escLong", 16);
                    getMap().put("region", 17);
                    getMap().put("subregion", 18);
                }});
    }

    @Test
    @Ignore
    public void testRead() throws IOException {

        extractTransformerHandler.read(new ExtractTransformReader() {

            int rows = 0;

            @Override
            public File getFile() {
                return new File("data/tranhist.xml");
            }

            @Override
            public int getBatch() {
                return 24;
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
                System.out.println("rows = " + rows);
            }
        });

    }
}