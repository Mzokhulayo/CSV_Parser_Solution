package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

@Component
public class ApplicationShutdown {

    private final String imagesFolderPath = "path/to/images/folder";

    @PreDestroy
    public void onShutdown() {
        try {
            // Delete the contents of the images folder
            FileUtils.cleanDirectory(new File(imagesFolderPath));
        } catch (IOException e) {
            // Handle any exception that occurs during the deletion process
            e.printStackTrace();
        }
    }
}
