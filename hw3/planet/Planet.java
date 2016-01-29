public class Planet{
	private double radius;
	private double mass;

	public Planet(double r, double m){
		mass=m;
		radius = r;
	}

	public double getMass(){
		return mass;
	}

	public double getRadius(){
		return radius;
	}

}