package mad.king.etr.model;

import java.io.File;

public interface ExtractTransformReader {

    File getFile();

    int getBatch();

    void begin();

    void commit();

    void readLine(String s);

    void readBatch(String s);

}
