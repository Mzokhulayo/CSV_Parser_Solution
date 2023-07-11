package com.eviro.assesment.grad001.mzokhulayomdubeki.ParserAPP;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * Implementation class for the FileParser interface.
 */
@Service
@Component
public class Implementation implements FileParser {
    // Constants
    private static final String IMAGE_DIRECTORY = "images/";
    public static List<AccountProfile> accountProfiles = new ArrayList<>();

    private final AccountProfileRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Implementation(AccountProfileRepository repository) {
        this.repository = repository;
    }

    /**
     * Converts the base64-encoded image data to an image file.
     *
     * @param base64ImageData The base64-encoded image data.
     * @return The image file generated from the data.
     * @throws RuntimeException If an invalid base64 image data is provided.
     */
    @Override
    public File convertCSVDataToImage(String base64ImageData, String imageFormat) {
        try {
            // Decode base64 image data
            byte[] imageBytes = Base64.getDecoder().decode(base64ImageData);

            // Generate a unique file name
            String fileName = generateUniqueFileName();

            // Extract the file extension from the image format

            String fileExtension = extractFileExtension(imageFormat);

            // Create a new file with the generated file name and extension
            File imageFile = new File(IMAGE_DIRECTORY + fileName + "." + fileExtension);

            // Write the image data to the file
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }

            return imageFile;
        } catch (IllegalArgumentException e) {
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

    /**
     * Creates an image link for the given image file.
     *
     * @param fileImage The image file.
     * @return The URI representing the image link.
     */
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

    /**
     * Parses the provided CSV file and saves the data into a list of AccountProfile entities.
     *
     * @param csvFile2        The CSV file to parse.
     * @param accountProfiles The list of AccountProfile entities to populate with parsed data.
     * @return The updated list of AccountProfile entities after parsing and saving.
     */
    @Transactional
    @Override
    public List<AccountProfile> parseAndSaveCSV(File csvFile2, List<AccountProfile> accountProfiles) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile2))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                String surname = nextLine[1];
                String imageFormat = "jpg"; // Default image format

                if (nextLine[2].contains("jpeg")) {
                    imageFormat = "jpg";
                } else if (nextLine[2].contains("png")) {
                    imageFormat = "png";
                }
                String base64ImageData = nextLine[3];

                File imageFile = convertCSVDataToImage(base64ImageData, imageFormat);
                URI imageLink = createImageLink(imageFile);

                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setName(name);
                accountProfile.setSurname(surname);
                accountProfile.setHttpImageLink(imageLink.toString());

                accountProfiles.add(accountProfile);

                // Save the accountProfile object to the database
                entityManager.persist(accountProfile);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return accountProfiles;
    }


    /**
     * Generates a unique file name for the image file.
     *
     * @return The generated unique file name.
     */
    private String generateUniqueFileName() {
        // Generate a unique file name for the image file
        // You can implement your own logic here, such as using a UUID
        String uniqueFileName = UUID.randomUUID().toString();
        return uniqueFileName;
    }

    /**
     * Extracts the file extension from the image format.
     *
     * @param imageFormat The image format.
     * @return The extracted file extension.
     */
    private String extractFileExtension(String imageFormat) {
        // Remove leading dot if present
        imageFormat = imageFormat.startsWith(".") ? imageFormat.substring(1) : imageFormat;

        // Convert image format to lowercase
        imageFormat = imageFormat.toLowerCase();

        return imageFormat;
    }
}
