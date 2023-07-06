package com.eviro.assesment.grad001.mzokhulayomdubeki;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    private final FileParser fileParser;

    public ImageController(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    @PostMapping("/parse-and-save-csv")
    public ResponseEntity<String> parseAndSaveCSV(@RequestParam("csvFilePath") String csvFilePath) {
        File csvFile = new File(csvFilePath);
        fileParser.parseAndSaveCSV(csvFile);
        return ResponseEntity.ok("CSV parsing initiated.");
    }

    @PostMapping("/convert-image")
    public ResponseEntity<URI> convertCSVDataToImage(@RequestBody String base64ImageData) {
        File imageFile = fileParser.convertCSVDataToImage(base64ImageData);

        if (imageFile != null) {
            URI imageLink = fileParser.createImageLink(imageFile);
            return ResponseEntity.ok(imageLink);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{name}/{surname}/{filename:.+}")
    public FileSystemResource getHttpImageLink(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable String filename) {
        try {
            // Construct the file path based on the provided name, surname, and filename
            String filePath = "images/" + name + "_" + surname + "/" + filename;

            // Check if the file exists
            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                // Return the FileSystemResource pointing to the file
                return new FileSystemResource(imageFile);
            } else {
                // If the file does not exist, return a 404 Not Found response
                throw new FileNotFoundException();
            }
        } catch (Exception e) {
            // Handle any exception that occurs (e.g., FileNotFoundException)
            // You can customize the error handling based on your requirements
            e.printStackTrace();
            return null; // Return an appropriate response based on your error handling strategy
        }
    }
}