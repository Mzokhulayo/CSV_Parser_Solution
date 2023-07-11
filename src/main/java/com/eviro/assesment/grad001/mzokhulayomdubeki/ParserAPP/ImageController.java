package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.servlet.view.RedirectView;
import java.io.File;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Controller class for handling image-related operations.
 */
@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    private final FileParser fileParser;

    public ImageController(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    /**
     * Endpoint for parsing and saving CSV data.
     *
     * @return ResponseEntity indicating the status of CSV parsing.
     */
    @GetMapping("/parse-and-save-csv")
    public ResponseEntity<String> parseAndSaveCSV() {
        File csvFile = new File("1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");
        fileParser.parseAndSaveCSV(csvFile,Implementation.accountProfiles);
        return ResponseEntity.ok("CSV parsing initiated, Working on file");
    }

    /**
     * Endpoint for converting CSV data to an image.
     *
     * @param base64ImageData The base64-encoded image data.
     * @return ResponseEntity containing the converted image link.
     */
    @GetMapping("/convert-image")
    public ResponseEntity<URI> convertCSVDataToImage(@RequestParam String base64ImageData,@RequestParam String imageFormat) {

        for (AccountProfile accountProfile : Implementation.accountProfiles) {
            String ImageData = accountProfile.getBase64ImageData();
            base64ImageData = ImageData;
        }

        File imageFile = fileParser.convertCSVDataToImage(base64ImageData,imageFormat);

        if (imageFile != null) {
            URI imageLink = fileParser.createImageLink(imageFile);
            return ResponseEntity.ok(imageLink);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving the HTTP image link for a specific name, surname, and filename.
     *
     * @param name     The name parameter in the URL path.
     * @param surname  The surname parameter in the URL path.
     * @param filename The filename parameter in the URL path.
     * @return ResponseEntity containing the HTTP image link if found, or a 404 response if not found.
     */
    @GetMapping("/name/surname/{filename:.+}")
    public ResponseEntity<Resource> getHttpImageLink(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable String filename) {
        try {
            String encodedFilename = URLEncoder.encode(filename, "UTF-8");
            String filePath = "/path/to/converted/images/" + name + "_" + surname + "/" + encodedFilename;

            // Check if the file exists
            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                Resource resource = new FileSystemResource(imageFile);
                MediaType mediaType = determineMediaType(filename);

                return ResponseEntity.ok()
                        .contentLength(imageFile.length())
                        .contentType(mediaType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private MediaType determineMediaType(String filename) {
        String extension = FilenameUtils.getExtension(filename);

        if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_JPEG;
        } else if ("png".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_PNG;
        } else if ("gif".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_GIF;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    /**
     * Endpoint for accessing the H2 database console.
     *
     * @return The redirect URL for accessing the H2 database console login page.
     */
    @GetMapping("/h2-console")
    public RedirectView h2Console() {
        return new RedirectView("/h2-console/login.do");
    }
}
