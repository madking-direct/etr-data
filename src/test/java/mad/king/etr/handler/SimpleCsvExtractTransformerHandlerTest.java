package mad.king.etr.handler;

import mad.king.etr.model.ExtractTransformReader;
import mad.king.etr.model.ExtractTransformer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleCsvExtractTransformerHandlerTest {

    ExtractTransformer extractTransformerHandler;

    @Before
    public void setUp() throws Exception {

        extractTransformerHandler =
                ExtractTransformer.newSimpleCsvTransformer(new ExtractTransformMapper() {{
                    getMap().put("0", 0);
                    getMap().put("1", 1);
                    getMap().put("2", 2);
                    getMap().put("3", 3);
                    getMap().put("4", 4);
                    getMap().put("5", 5);
                    getMap().put("6", 6);
                    getMap().put("7", 7);
                    getMap().put("8", 8);
                    getMap().put("9", 9);
                    getMap().put("10", 10);
                    getMap().put("11", 11);
                    getMap().put("12", 12);
                    getMap().put("13", 13);
                    getMap().put("14", 14);
                    getMap().put("15", 15);
                    getMap().put("16", 16);
                    getMap().put("17", 17);
                    getMap().put("18", 18);
                    getMap().put("19", 19);
                    getMap().put("20", 20);
                    getMap().put("21", 21);
                    getMap().put("22", 22);
                    getMap().put("23", 23);
                    getMap().put("24", 24);
                    getMap().put("25", 25);
                    getMap().put("26", 26);
                    getMap().put("27", 27);
                    getMap().put("28", 28);
                    getMap().put("29", 29);
                    getMap().put("30", 30);
                    getMap().put("31", 31);
                    getMap().put("32", 32);
                    getMap().put("33", 33);
                    getMap().put("34", 34);
                    getMap().put("35", 35);
                    getMap().put("36", 36);
                    getMap().put("37", 37);
                    getMap().put("38", 38);
                    getMap().put("39", 39);
                    getMap().put("40", 40);
                    getMap().put("41", 41);
                    getMap().put("42", 42);
                    getMap().put("43", 43);
                    getMap().put("44", 44);
                    getMap().put("45", 45);
                    getMap().put("46", 46);
                    getMap().put("47", 47);
                    getMap().put("48", 48);
                }});
    }

    @Test
    public void testRead() throws IOException {

        extractTransformerHandler.read(new ExtractTransformReader() {
            @Override
            public File getFile() {
                return new File("data/PartnerPRICING.csv");
            }

            @Override
            public int getBatch() {
                return 12;
            }

            @Override
            public int hasHeader() {
                return 1;
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
                System.out.println(s);
            }
        });
    }
}