package com.eviro.assesment.grad001.mzokhulayomdubeki;

import java.io.File;
import java.net.URI;
import java.util.List;

public interface FileParser {
//    void parseCSV(File csvFile);
    File convertCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);

    List<AccountProfile> parseAndSaveCSV(File csvFile2, List<AccountProfile> accountProfiles);

}
