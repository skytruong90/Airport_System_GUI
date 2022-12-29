package application;

import java.util.Comparator;

	public class AirportStateComparator implements Comparator<Airport>{
		public int compare(Airport a1, Airport a2) {
			return a1.getState().compareTo(a2.getState());
		}
	}
