package mad.king.etr.model;

import java.io.File;

public interface ExtractTransformReader {

    File getFile();

    int getBatch();

    int hasHeader();

    void begin();

    void readLine(String s);

    void readBatch(String s);

    void commit();

}
