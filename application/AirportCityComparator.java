package application;

import java.util.Comparator;

	public class AirportCityComparator implements Comparator<Airport>{
		public int compare(Airport a1, Airport a2) {
			return a1.getCity().compareTo(a2.getCity());
		}
	}
