# bus-lines

This project is a simple web app that provides a scoreboard of Top 10 SL bus lines with most stops.
The data on which it is based on is retrieved from [TrafikLab.se](https://www.trafiklab.se/).

## Documentation

### How to run locally

1. You will need an API key from TrafikLab for the SL Stops and lines v2.0 (Hållplatser och linjer 2) API.
    You can get one by registering an account on [this link](https://www.trafiklab.se/api/trafiklab-apis/sl/stops-and-lines-2/).
2. After you get an API key you need to insert it into the application.properties file, specifically in the property shown below.
````
api.sl.api-key=*yourApiKeyHere*
````
3. You need to make sure you have Java JDK installed and JAVA_HOME environment variable set up
4. From the root of the project, run the following command in your command prompt:
#### Unix
````
./mvnw spring-boot:run
````

#### Windows
````
mvnw.cmd spring-boot:run
````
5. After the app has fully and successfully started, the home page can be accessed by entering the following URL in the web browser:
````
localhost:8080/scoreboard
````