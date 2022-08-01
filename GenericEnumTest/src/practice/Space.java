package practice;

class Space {
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double radius;

		private Planet(double radius) {
			this.radius = radius;
		}

		public double getR() {
			return radius;
		}
		
	}

	public static void main(String[] args) {
		Planet[] planet = Planet.values();
		for (int i = 0; i < planet.length; i++) {
			System.out.println(planet[i].name() + "의 면적 : " + 4 * Math.PI * planet[i].getR() * planet[i].getR() + "km");
		}
	}

}
