package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

/**
 * Component that performs cleanup operations on application shutdown.
 */
@Component
public class ApplicationShutdown {

    private final String imagesFolderPath = "/images";

    /**
     * Performs cleanup operations when the application is shutting down.
     */
    @PreDestroy
    public void onShutdown() {
        try {
            FileUtils.cleanDirectory(new File(imagesFolderPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}