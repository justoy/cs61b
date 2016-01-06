public class NBody{
	public static Planet getPlanet(In input){
		double x1=input.readDouble();
		double x2=input.readDouble();
		double x3=input.readDouble();
		double x4=input.readDouble();
		double x5=input.readDouble();
		String x6=input.readString();

		Planet p= new Planet(x1,x2,x3,x4,x5,x6);
		return p;
	}

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		In input=new In(filename);
		int numPlanet=input.readInt();
		double radius =input.readDouble();
		StdDraw.setScale(-radius,radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		Planet[] planets = new Planet[numPlanet];
		for(int i=0;i<numPlanet;++i){
			planets[i]=getPlanet(input);
			StdDraw.picture(planets[i].x, planets[i].y, "images/"+planets[i].img);
			//planets[i].printPlanet();
		}
		for(double time=0;time<T;time+=dt){
			for(int i=0;i<numPlanet;++i){
				planets[i].setNetForce(planets);
				//System.out.println(i);
			}
			for(int i=0;i<numPlanet;++i){
				planets[i].update(dt);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(int i=0;i<numPlanet;++i){
				StdDraw.picture(planets[i].x, planets[i].y, "images/"+planets[i].img);
			}
			StdDraw.show(10);
		}
		for(int i=0;i<numPlanet;++i){
			planets[i].printPlanet();
		}
		
	}
}