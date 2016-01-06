public class Planet{
	double x;
	double y;
	double xVelocity;
	double yVelocity;
	double xAccel;
	double yAccel;
	double mass;
	String img;
	double xNetForce=0;
	double yNetForce=0;
	static final double G=6.67*Math.pow(10, -11);
	public Planet(double xPos, double yPos, double xVel, 
		double yVel, double m, String imgName){
		x=xPos;
		y=yPos;
		xVelocity=xVel;
		yVelocity=yVel;
		mass=m;
		img=imgName;
	}

	public double calcDistance(Planet p){
		double delt1=x-p.x;
		double delt2=y-p.y;

		return Math.sqrt(delt1*delt1+delt2*delt2);
	}

	public double calcPairwiseForce(Planet p){
		double r=calcDistance(p);
		return G*mass*p.mass/r/r;
	}

	public double calcPairwiseForceX(Planet p){
		double f=calcPairwiseForce(p);
		double cos=(p.x-x)/calcDistance(p);
		return f*cos;
	}

	public double calcPairwiseForceY(Planet p){
		double f=calcPairwiseForce(p);
		double sin=(p.y-y)/calcDistance(p);
		return f*sin;
	}

	public void setNetForce(Planet[] ps){
		for(int i=0;i<ps.length;++i){
			if(this==ps[i]){
				//System.out.println(i);
				continue;
			}
			//System.out.println(i);
			xNetForce += calcPairwiseForceX(ps[i]);
			yNetForce += calcPairwiseForceY(ps[i]);
			//System.out.println(i);
		}
	}

	public void draw(){
		StdDraw.point(x,y);
	}

	public void update (double dt){
		xAccel=xNetForce/mass;
		yAccel=yNetForce/mass;
		xVelocity += xAccel*dt;
		yVelocity += yAccel*dt;
		x += xVelocity*dt;
		y += yVelocity*dt;
	}

	public void printPlanet(){
		System.out.printf("%f, %f, %f, %f, %f, %s\n",x,y,xVelocity,yVelocity,mass,img);
	}
}