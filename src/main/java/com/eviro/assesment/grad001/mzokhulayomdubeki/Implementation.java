package com.eviro.assesment.grad001.mzokhulayomdubeki;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.UUID;

@Component
public class Implementation implements FileParser {
    // Constants
    private static final String IMAGE_DIRECTORY = "images/";


    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        try {
            // Decode base64 image data
            byte[] imageBytes = Base64.getDecoder().decode(base64ImageData);

            // Generate a unique file name
            String fileName = generateUniqueFileName();

            // Create a new file with the generated file name
            File imageFile = new File(IMAGE_DIRECTORY + fileName);

            // Write the image data to the file
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }

            return imageFile;
        }
        catch (IllegalArgumentException e){
            // Handle the IllegalArgumentException
            e.printStackTrace();
            // You can throw a custom exception, return null, or handle the error in any appropriate way
            // For example:
            throw new RuntimeException("Invalid base64 image data provided");

        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URI createImageLink(File fileImage) {
        try {
            // Construct the URI for the image file
            URI imageUri = new URI("/v1/api/image/" + fileImage.getName());

            return imageUri;
        } catch (URISyntaxException e) {
            // Handle URISyntaxException
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void parseAndSaveCSV(File csvFile2) {

        try{
//            CSVReader reader = new CSVReader(new FileReader(csvFile));
//            read file from within program
            File csvFile = new File("1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                String surname = nextLine[1];
                String imageFormat = nextLine[2];
                String base64ImageData = nextLine[3];

//                System.out.println(name);
//                System.out.println(surname);
                System.out.println(name);

                File imageFile = convertCSVDataToImage(base64ImageData);
                System.out.println(base64ImageData);
                URI imageLink = createImageLink(imageFile);
                System.out.println("image link= "+imageLink);

                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setName(name);
                accountProfile.setSurname(surname);
                accountProfile.setHttpImageLink(imageLink.toString());

                // Save the accountProfile object to the database
                // Assuming you are using JPA, you can inject an instance of EntityManager or JpaRepository to persist the object.
                // For example: entityManager.persist(accountProfile);
//                entityManager.persist(accountProfile);
        }
            reader.close();

    } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
    private String generateUniqueFileName() {
        // Generate a unique file name for the image file
        // You can implement your own logic here, such as using a UUID
        String uniqueFileName = UUID.randomUUID().toString();
        return uniqueFileName + ".jpg";
    }
}