package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URLEncoder;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    private final FileParser fileParser;

    public ImageController(FileParser fileParser) {
        this.fileParser = fileParser;
    }

//    public ImageController() {
//        this.fileParser = new Implementation();
//    }




    @GetMapping("/parse-and-save-csv")
    public ResponseEntity<String> parseAndSaveCSV() {
        // read file from within the program

        File csvFile = new File("1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");
        System.out.println(Implementation.accountProfiles);
        fileParser.parseAndSaveCSV(csvFile,Implementation.accountProfiles);
        System.out.println(Implementation.accountProfiles);

        return ResponseEntity.ok("CSV parsing initiated, Working on file");
    }

    @GetMapping("/convert-image")
    public ResponseEntity<URI> convertCSVDataToImage(@RequestParam String base64ImageData) {

        for (AccountProfile accountProfile : Implementation.accountProfiles) {
            String ImageData = accountProfile.getBase64ImageData();
            base64ImageData = ImageData;
        }

        System.out.println(base64ImageData);
        File imageFile = fileParser.convertCSVDataToImage(base64ImageData);

        if (imageFile != null) {
            URI imageLink = fileParser.createImageLink(imageFile);
            System.out.println("base64ImageData has been converted to a httpLink");
            return ResponseEntity.ok(imageLink);

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/name/surname/{filename:.+}")
    public ResponseEntity<Resource> getHttpImageLink(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable String filename) {
        try {
            // Encode the filename
            String encodedFilename = URLEncoder.encode(filename, "UTF-8");

            // Adjust the file path based on the actual location where the converted image files are stored
            String filePath = "/path/to/converted/images/" + name + "_" + surname + "/" + encodedFilename;

            // Check if the file exists
            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                // Return the FileSystemResource pointing to the file
                Resource resource = new FileSystemResource(imageFile);

                // Adjust the MediaType based on the actual image format
                MediaType mediaType = determineMediaType(filename);

                return ResponseEntity.ok()
                        .contentLength(imageFile.length())
                        .contentType(mediaType)
                        .body(resource);
            } else {
                // If the file does not exist, return a 404 Not Found response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exception that occurs (e.g., FileNotFoundException)
            // You can customize the error handling based on your requirements
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private MediaType determineMediaType(String filename) {
        // Extract the file extension from the filename
        String extension = FilenameUtils.getExtension(filename);

        // Adjust the MediaType based on the file extension
        if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_JPEG;
        } else if ("png".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_PNG;
        } else if ("gif".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_GIF;
        } else {
            // Adjust this fallback MediaType based on your requirements
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

//    @GetMapping("/{name}/{surname}/{filename:.+}")
//    public FileSystemResource getHttpImageLink(
//            @PathVariable String name,
//            @PathVariable String surname,
//            @PathVariable String filename) {
//        try {
//            // Construct the file path based on the provided name, surname, and filename
//            String filePath = "images/" + name + "_" + surname + "/" + filename;
//
//            // Check if the file exists
//            File imageFile = new File(filePath);
//            System.out.println(imageFile);
//            if (imageFile.exists()) {
//                // Return the FileSystemResource pointing to the file
//                return new FileSystemResource(imageFile);
//            } else {
//                // If the file does not exist, return a 404 Not Found response
//                throw new FileNotFoundException();
//            }
//        } catch (Exception e) {
//            // Handle any exception that occurs (e.g., FileNotFoundException)
//            // You can customize the error handling based on your requirements
//            e.printStackTrace();
//            return null; // Return an appropriate response based on your error handling strategy
//        }



        @GetMapping("/h2-console")
        public String h2Console() {
            // Handle the request and return the appropriate response
            // ...
//            return "h2-console"; // or the name of the HTML template
        return "redirect:/h2-console/login.do";
    }

}
