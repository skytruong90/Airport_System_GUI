package application;


import java.io.File;
import java.util.*;

public class AirportManagerTest {
	static final String path = "src/";
	static final String airportsSmallFileName = path + "airportsSmall.txt";
	static final String airportsMediumFileName = path + "airportsMedium.txt";

	static final String airportsSameCitiesFileName = path + "airportsSameCities.txt";
	static final String airportsAllFileName = path + "airports.txt";
	static final File airportsSmallFile = new File(airportsSmallFileName);
	static final File airportsMediumFile = new File(airportsMediumFileName);
	static final File airportsSameCitiesFile = new File(airportsSameCitiesFileName);
	static final File airportsAllFile = new File(airportsAllFileName);
		public static void main(String args[]) {
			
			getAirportsListTest();
			getAirportTestTrue();
			
			
			getAirportTestNull();
			getAirportByCityTest();
			
			
			getAirportByCityStateTest();
			getNWSNamedAirportsTest();
			
			
			getAirportsSortedByTest();
			getAirportsWithinTest();
			
			
			getAirportWithinLatLonTest();
			getAirportsWithinCodeTest();
			
			
			getAirportClosestTest();
			getAirportsClosestTest();
			
			getDistanceBetweenTest();
			getDistanceBetweenWithCodesTest();
			
		}
		public static AirportManager getAirportManager(File file) {
			Map<String,Airport> airportMap = AirportLoader.getAirportMap(file);
			AirportManager airportManager = new AirportManager(airportMap);
			return airportManager;
		}
		//testing getAirport Null
		public static void getAirportsListTest() {
			System.out.println("getAirportTestNull()---expected--->Prints airportsMediumFile");
			AirportManager manager = getAirportManager(airportsMediumFile);
			List<Airport> a = manager.getAirports();
			System.out.println(a);
		}
		//testing getAirport CSG
		public static void getAirportTestTrue() {
			System.out.println("getAirportTestTrue()---expected--->Columbus ");
			AirportManager manager = getAirportManager(airportsMediumFile);
			Airport a = manager.getAirport("CSG");
			System.out.println(a);
		}
		
		//testing getAirport Null
		public static void getAirportTestNull() {
			System.out.println("getAirportTestNull()---expected--->null");
			AirportManager manager = getAirportManager(airportsMediumFile);
			Airport a = manager.getAirport("VQ");
			System.out.println(a);
		}
		//Returns a list of the Airports whose “City” is city
		public static void getAirportByCityTest() {
			System.out.println("getAirportByCityTest()---expected--->prints list of airport with city 'Greenville' ");
			AirportManager manager = getAirportManager(airportsSameCitiesFile);
			List<Airport> a = manager.getAirportsByCity("Greenville");
			System.out.println(a);
		}
		//Returns a list of the Airports whose “City” is city and “State” is state.
		public static void getAirportByCityStateTest() {
			System.out.println("getAirportByCityStateTest()---expected--->prints list city burlington state VT");
			AirportManager manager = getAirportManager(airportsSameCitiesFile);
			List<Airport> a = manager.getAirportsByCityState("Burlington", "VT");
			System.out.println(a);
		}
		
		public static void getNWSNamedAirportsTest(){
			System.out.println("getNWSNamedAirportsTest()---expected--->prints list airport with X");
			AirportManager manager = getAirportManager(airportsAllFile);
			List<Airport> a = manager.getNWSNamedAirports();
			System.out.println(a);
		}
		//Returns a list of Airports that are sorted according to sortType. 
		public static void getAirportsSortedByTest() {
			System.out.println("getAirportsSortedByTest()---expected--->prints list SortType: FL");
			AirportManager manager = getAirportManager(airportsMediumFile);
			List<Airport> a = manager.getAirportsSortedBy("FL");
			System.out.println(a);
			System.out.println("getAirportsSortedByTest()---expected--->prints list SortType: Jacksonville");
			AirportManager manager1 = getAirportManager(airportsMediumFile);
			List<Airport> b = manager1.getAirportsSortedBy("Jacksonville");
			System.out.println(b);
			
		}
		public static void getAirportsWithinTest() {
			System.out.println("getAirportsWithinTest()---expected--->prints list AIRPORT WITHIN 96 MILES OF CSG");
			AirportManager manager = getAirportManager(airportsSmallFile);
			List<Airport>  a = manager.getAirportsWithin("CSG", 96);
			System.out.println(a);
		}
		
		public static void getAirportWithinLatLonTest(){
			System.out.println("getAirportWithinLatLonTest()---expected--->prints list AIRPORT WITHIN 96 MILES OF CSG");
			AirportManager manager = getAirportManager(airportsSmallFile);
			List<Airport>  a = manager.getAirportsWithin( 96, 32.52, 84.93);
			System.out.println(a);
		}
		public static void getAirportsWithinCodeTest(){
			System.out.println("getAirportsWithinCodeTest()---expected--->prints list AIRPORT WITHIN 100 of 'VLD' & 'CSG' ");
			AirportManager manager = getAirportManager(airportsAllFile);
			List<Airport>  a = manager.getAirportsWithin("VLD", "CSG", 100);
			System.out.println(a);
		}
		
		
		
		public static void getAirportClosestTest() {
			System.out.println("getAirportClosestTest()---expected--->prints Airport closest to VLD");
			AirportManager manager = getAirportManager(airportsMediumFile);
			Airport  a = manager.getAirportClosestTo("VLD");
			System.out.println(a);
		}
		public static void getAirportsClosestTest() {
			System.out.println("getAirportsClosestTest()---expected--->prints 4 Airports closest to MCO ");
			AirportManager manager = getAirportManager(airportsMediumFile);
			List<Airport>  a = manager.getAirportsClosestTo("MCO", 4);
			System.out.println(a);
		}
		// Returns the distance between two airports using airport
		public static void getDistanceBetweenTest() {
			System.out.println("getDistanceBetweenTest()---expected--->Distance between Anniston and Valdosta");
			AirportManager manager = getAirportManager(airportsSmallFile);
			Airport airport1 = new Airport("ANB", 33.58 , 85.85, "Anniston" , "AL");
			Airport airport2 = new Airport("VLD", 30.78 , 83.28, "Valdosta" , "GA");
			double a = manager.getDistanceBetween(airport1, airport2);
			System.out.printf(" the distance between %s and %s is %.2f miles ", airport1.getCode(), airport2.getCode(), a);
		}
		//Returns the distance between two airports as specified by their codes 
		public static void getDistanceBetweenWithCodesTest() {
			System.out.println("getDistanceBetweenWithCodesTest()---expected--->Distance between LAX and MIA");
			AirportManager manager = getAirportManager(airportsAllFile);
			double a = manager.getDistanceBetween("LAX", "MIA");
			System.out.printf(" the distance between the two airports is %.2f miles ",a);
		}
}
