# CSV file parser
ParserAPP is a Java application that provides functionality for parsing CSV files, converting CSV data to images, and managing image links. It utilizes Spring Boot and Java Persistence API (JPA) for handling CSV parsing and image-related operations.


## Scenario:
You are joining a data centre division for Enviro-Bank:
This division receives a flat file that contains name, surname, imageFormat and imageData.
The image in the file is currently shared as a base64 encoded image data.

## Task
Write a basic implementation of a file parser to convert the contents of the file and
store the records into a database.
The base64 image data processed from the flat file must be converted into a
physical file with the format as prescribed in the flat file.
You must create a rest controller and name it: ImageController
The database record (AccountProfile) must contain the following fields:
1.Account holder name
2.Account holder surname
3.httpImageLink

Notes:
The httpImageLink must be a link exposed via a rest endpoint, which will give you
access to the physical image file that was converted from the flat file.

**_See Full_Description.pdf on the root folder._**

## ----------------------------------------------------------------------------------------

## Requirements

To run the ParserAPP, make sure you have the following software installed on your system:

- Java Development Kit (JDK) 8 or higher
- Apache Maven (for building the application)

## Installation

1. Clone the repository to your local machine:
2. Build the application using Maven:
   * cd ParserAPP
   * mvn clean install

## Configuration

The application requires the following configurations:

- Database Configuration: Configure the database connection details in the `application.properties` file located in the `src/main/resources` directory.

## Usage

1. Start the application:
   * mvn spring-boot:run
2. The application will start running on the configured port (default: 8080).

3. Use a tool like cURL, Postman, or a web browser to interact with the available endpoints.

## Endpoints

The following endpoints are available:

- `/v1/api/image/parse-and-save-csv`: Initiates the parsing of a CSV file and saves the data.
- `/v1/api/image/convert-image`: Converts base64-encoded image data to an image file and provides the image link.
- `/v1/api/image/name/surname/{filename}`: Retrieves the HTTP image link for a specific name, surname, and filename.

## Contributing

Contributions to ParserAPP are welcome! If you find any issues or want to add new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

