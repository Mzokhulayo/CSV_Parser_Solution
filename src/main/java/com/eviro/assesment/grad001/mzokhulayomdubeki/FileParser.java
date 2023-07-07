package com.eviro.assesment.grad001.mzokhulayomdubeki;

import java.io.File;
import java.net.URI;

public interface FileParser {
//    void parseCSV(File csvFile);
    File convertCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);

    void parseAndSaveCSV(File csvFile2);

}
