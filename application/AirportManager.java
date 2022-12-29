package application;


import java.util.*;
import java.util.Map.Entry;

	public class AirportManager {

	private Map<String,Airport>	airports = new HashMap<>();;

	public AirportManager(Map<String, Airport> airports) {
				super();
				this.airports = airports;
			} 
	
	

	public double getDistanceBetween(String code1, String code2) {
		DistanceCalculator calculator = new DistanceCalculator();
		if(airports.containsKey(code1) && airports.containsKey(code2)) {
			Airport airport1 = airports.get(code1);
			Airport airport2 = airports.get(code2);
			double distance = calculator.getDistance(airport1.getLatitude(), airport1.getLongitude(), airport2.getLatitude(), airport2.getLongitude());
			return distance;
		}
	return -1;}
	
	
	
	public double getDistanceBetween(Airport airport1,Airport airport2) {
		double distance = 0;
		DistanceCalculator calculator = new DistanceCalculator();
		if(airports.containsKey(airport1) && airports.containsKey(airport2)) {}
			distance = calculator.getDistance(airport1.getLatitude(), airport1.getLongitude(), airport2.getLatitude(), airport2.getLongitude());
				return distance;
	}
	
	
	public Airport getAirport(String Code)		{
		Airport a = airports.get(Code);
		return a;
	}
	
	
	
	public List<Airport> getAirports(){
		List<Airport> airportsList = new ArrayList<>(airports.values());
		return airportsList;
	}
	
	
	public Airport getAirportClosestTo(String code) {
		Airport closestTo = new Airport("");
		//use the helper method to get a Map of all distances
		Map<Airport, Double> distancesMap = getListDistances(code);
		//add the distances to a list 
		List<Double> sortedDistances = new ArrayList<>(distancesMap.values());
		// sort the list 
		Collections.sort(sortedDistances);
		//make sure the map returned is not empty
		if(!distancesMap.isEmpty()) {
			//get the distances at 0 since thats the lowest 
		double distance = sortedDistances.get(0);
		
			for(Entry<Airport,Double> airportMap : distancesMap.entrySet()) {
				//if the distance is equal to the value of the map(distance), return the entryKey(Airport)
				if(distance == airportMap.getValue()) {
					closestTo = airportMap.getKey();
				}
			}
	
		
			return closestTo;}
		return null;
		
	}
	
	
	
	//same as getAirportClosestTo but returns a list
	public List<Airport> getAirportsClosestTo(String code,int num){
		List<Airport> closestTo = new ArrayList<>();
		Map<Airport, Double> distancesMap = getListDistances(code);
		List<Double> sortedDistances = new ArrayList<>(distancesMap.values());
		Collections.sort(sortedDistances);
		if(!distancesMap.isEmpty() && num <= sortedDistances.size()) {
			//get a sublist from index 0 to num
			List<Double> distances = sortedDistances.subList(0, num);
			for(double d: distances) {
			for(Entry<Airport,Double> airportMap : distancesMap.entrySet()) {
				if(d == airportMap.getValue()) {
					closestTo.add(airportMap.getKey());
				}
			}
	
		
			}return closestTo;}
		return null;
	
	
	}
	
	
	public List<Airport> getAirportsByCity(String city){
		List<Airport> cities = new ArrayList<>();
		for(Entry<String, Airport> a: airports.entrySet()) {
			if(a.getValue().getCity().equals(city)) {
				cities.add(a.getValue());
			}
		}
		return cities;
	}
	
	
	
	public List<Airport> getAirportsByCityState(String city, String state){
		List<Airport> cities = new ArrayList<>();
		for(Entry<String, Airport> a: airports.entrySet()) {
			if(a.getValue().getCity().equals(city) && a.getValue().getState().equals(state)) {
				cities.add(a.getValue());
			}
		}
		return cities;
	}
	
	
	public List<Airport> getNWSNamedAirports(){
		List<Airport> cities = new ArrayList<>();
		for(Entry<String, Airport> a: airports.entrySet()) {
			String at2 = a.getValue().getCode().substring(2);
			if(at2.equals("X")) {
				cities.add(a.getValue());
			}
		}
		return cities;
	}
	
	public List<Airport> getAirportsSortedBy(String sortType){
		List<Airport> cities = new ArrayList<>();
		
		for(Entry<String, Airport> a: airports.entrySet()) {
			if(sortType.length() == 2 && a.getValue().getState().equals(sortType)) {
		
				cities.add(a.getValue());
				Collections.sort(cities, new AirportStateComparator());
				Collections.sort(cities, new AirportCityComparator());
			
				}
			else {
			if(a.getValue().getCity().equals(sortType)) {
				cities.add(a.getValue());
  			   	Collections.sort(cities, new AirportCityComparator());
				Collections.sort(cities, new AirportStateComparator());
			}
			
		}
		}
		return cities;	}
	public List<Airport> getAirportsWithin(String code, double withinDist){
		List<Airport> airportsWithin = new ArrayList<>();
		Map<Airport, Double> distancesMap = getListDistances(code);
		for(Entry<Airport,Double> d : distancesMap.entrySet()) {
			if(d.getValue() <= withinDist) {
			airportsWithin.add(d.getKey());
		
			}
		}
	return airportsWithin;	
	}
	public List<Airport> getAirportsWithin(double withinDist, double lat, double lon){
		DistanceCalculator calculator = new DistanceCalculator();
		List<Airport> airportsList = new ArrayList<>(airports.values());
		Map<Airport, Double> distancesWithinMap = new HashMap<>();
		List<Airport> airportsWithin = new ArrayList<>();
		
		for(Airport target: airportsList) {
		double distance = calculator.getDistance(target.getLatitude(), target.getLongitude(), lat, lon);
		distancesWithinMap.put(target, distance);}
		
		for(Entry<Airport, Double> d: distancesWithinMap.entrySet()) {
			if(d.getValue() <= withinDist && d.getValue()!= 0) {
				airportsWithin.add(d.getKey());
			}
		}
		return airportsWithin;
	}

	public List<Airport> getAirportsWithin(String code1, String code2, double withinDist){
		Map<Airport, Double> airport1 = getListDistances(code1);
		Map<Airport, Double> airport2 = getListDistances(code2);
		List<Airport> airportsWithinA1 = new ArrayList<>();
		List<Airport> airportsWithinA2 = new ArrayList<>();

		for(Entry<Airport, Double> a1: airport1.entrySet()) {
			if(a1.getValue() <= withinDist) {
				airportsWithinA1.add(a1.getKey());
				}
		}
		for(Entry<Airport, Double> a2: airport2.entrySet()) {
			if(a2.getValue() <= withinDist) {
				airportsWithinA2.add(a2.getKey());
				}
		}
		airportsWithinA1.retainAll(airportsWithinA2);
		return airportsWithinA1;
	}
	
	
	
	//GET A MAP OF THE DISTANCE BETWEEN ALL AIRPORTS AND TARGET AIRPORT
	public Map<Airport, Double> getListDistances(String code){
		List<Airport> airportWithoutTarget = new ArrayList<>(airports.values());
		Map<Airport, Double> distancesMap = new HashMap<>();
		DistanceCalculator calculator = new DistanceCalculator();
		Airport target = new Airport("");
		if(airports.containsKey(code)) {
			target = airports.get(code) ;
			airportWithoutTarget.remove(target);
			for(Airport in: airportWithoutTarget) {
				double distance = calculator.getDistance(target.getLatitude(), target.getLongitude(), in.getLatitude(), in.getLongitude());
				distancesMap.put(in, distance);
			

			}
		}
	
		
		return distancesMap;
			}
	
	
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	