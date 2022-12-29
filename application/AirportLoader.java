package application;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class AirportLoader {
	public static Map<String,Airport> getAirportMap(File airportFile){
		Map<String,Airport> airportMap = new HashMap<>();
		  try {
		     
		      Scanner input = new Scanner(airportFile);
		      while (input.hasNextLine()) {
		        String data = input.nextLine();
		        String[] tokens = data.split("\\s");  
		        String code = tokens[0];
		        double latitude = Double.parseDouble(tokens[1]);
		        double longitude = Double.parseDouble(tokens[2]);;
		        String city = tokens[3];
		        String state = tokens[4];
		        Airport a = new Airport(code, latitude, longitude, city, state);
		        airportMap.put(code, a);
		      }
		
		      input.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return airportMap;
	}
}
