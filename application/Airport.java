package application;

	public class Airport extends Object{
		private String code;
		private String city; 
		private String state;
		private double latitude = 0;
		private double longitude = 0;
	 
		public Airport(String code, double latitude, double longitude, String city, String state) {
			super();
			this.code = code;
			this.latitude = latitude;
			this.longitude = longitude;
			this.city = city;
			this.state = state;
		}
		public Airport() {}
		protected Airport(String code) {
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public double getLatitude() {
			return latitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public boolean equals(Object o) {
			String a = ((Airport)o).getCode();
			return code.equals(a);
			}
		@Override
		public String toString() {
			return " (code-city: " + code + "-"+ city + ",     State: " + state + ",     latitude= " + latitude
					+ ",     longitude= " + longitude + ")\n";
		}
		}
	
