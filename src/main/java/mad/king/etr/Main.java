package mad.king.etr;

import mad.king.etr.model.ExtractTransformReader;
import mad.king.etr.model.ExtractTransformer;
import mad.king.etr.handler.ExtractTransformMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // write your code here

        ExtractTransformer extractTransformerHandler =
                ExtractTransformer.newSimpleJsonTransformer(new ExtractTransformMapper() {{
                    getMap().put("fogground", 0);
                    getMap().put("snowfall", 1);
                    getMap().put("fastest2minwindspeed", 2);
                    getMap().put("fogheavy", 3);
                    getMap().put("hail", 4);
                }});

        try {
            extractTransformerHandler.read(
                    new ExtractTransformReader() {
                        int rows = 0;

                        @Override
                        public int getBatch() {
                            return 118;
                        }

                        @Override
                        public int hasHeader() {
                            return 0;
                        }

                        @Override
                        public void begin() {

                        }

                        @Override
                        public void commit() {
                            System.out.printf("rows count = %s", rows);
                        }

                        @Override
                        public File getFile() {
                            return
                                    new File("data/rdu-weather-history.json");
                        }

                        @Override
                        public void readLine(String s) {
                            rows++;
                        }

                        @Override
                        public void readBatch(String s) {
                            System.out.println(s);
                        }
                    }
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
