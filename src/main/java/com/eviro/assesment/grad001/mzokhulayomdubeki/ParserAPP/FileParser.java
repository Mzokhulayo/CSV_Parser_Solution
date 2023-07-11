package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import java.io.File;
import java.net.URI;
import java.util.List;

/**
 * Interface for file parsing and processing.
 */
public interface FileParser {

    /**
     * Converts the base64-encoded image data to an image file.
     *
     * @param base64ImageData The base64-encoded image data.
     * @return The image file generated from the data.
     */
    File convertCSVDataToImage(String base64ImageData);

    /**
     * Creates an image link for the given image file.
     *
     * @param fileImage The image file.
     * @return The URI representing the image link.
     */
    URI createImageLink(File fileImage);

    /**
     * Parses the provided CSV file and saves the data into a list of AccountProfile entities.
     *
     * @param csvFile The CSV file to parse.
     * @param accountProfiles The list of AccountProfile entities to populate with parsed data.
     * @return The updated list of AccountProfile entities after parsing and saving.
     */
    List<AccountProfile> parseAndSaveCSV(File csvFile, List<AccountProfile> accountProfiles);
}
