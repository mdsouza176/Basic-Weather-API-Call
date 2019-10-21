import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    public static void main(String[] args) {

        String appid = "4e8f4ece0985ca1849a7d5a32bf1afd3";
        boolean cityFound = false;

        try {
            if(args.length <1) {
                System.out.println("Please enter a city ID.");
                return;
            }
            File json = new File("city.list.json");
            ObjectMapper obj = new ObjectMapper();
            City[] cities = obj.readValue(json, City[].class);
            City cityInfo = null;
            for(int i =0; i < cities.length; i++) {
                if(Integer.parseInt(args[0]) == cities[i].id) {
                    cityFound = true;
                    cityInfo = cities[i];
                    break;
                }
            }
            if(!cityFound) {
                System.out.println("Invalid City ID");
                return;
            }
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?id=" +args[0] +"&units=imperial&APPID=4e8f4ece0985ca1849a7d5a32bf1afd3");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            URL url2 = new URL("https://openweathermap.org/find?q=" + args[0]);

            byte[] targetArray = new byte[con.getInputStream().available()];
            byte[] countries = new byte[con.getInputStream().available()];
            con.getInputStream().read(targetArray);
            String results = new String(targetArray);
            JsonNode jsonNode = obj.readTree(results);
            printWeatherData(jsonNode, cityInfo);


        }
        catch (java.io.IOException e) {
            System.out.println("Failed to get results from Weather API. City ID may be invalid or API may be down");
        }
    }

    static void printWeatherData(JsonNode jsonNode, City cityInformation) {
        System.out.println("Weather Information about " + cityInformation.name + ", " + cityInformation.country + "\n");
        System.out.println("Coordinates-\nLatitude: " + jsonNode.get("coord").get("lat") + ", Longitude: " +
                jsonNode.get("coord").get("lon") + "\n");
        System.out.println("Current Temperature in Fahrenheit info- \nCurrent-Temp: " + jsonNode.get("main").get("temp").asText());
        System.out.println("Temp-min: " + jsonNode.get("main").get("temp_min"));
        System.out.println("Temp-max: " + jsonNode.get("main").get("temp_max") + "\n");

        System.out.println("Current humidity: " + jsonNode.get("main").get("humidity"));
        System.out.println("Current pressure: " + jsonNode.get("main").get("pressure"));
        System.out.println("Current wind speed: " + jsonNode.get("wind").get("speed"));
        System.out.println("Wind direction Degrees: " + jsonNode.get("wind").get("deg"));
    }
}
