public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public 	static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body guest){
		double r;
		r = Math.sqrt(Math.pow(xxPos - guest.xxPos, 2) + Math.pow(yyPos - guest.yyPos, 2));
		return r;
	}

	public double calcForceExertedBy(Body guest){
		double F;
		F = G * mass * guest.mass / Math.pow(calcDistance(guest),2);
		return F;
	}

	public double calcForceExertedByX(Body guest){
		double Fx;
		double dx;
		dx = guest.xxPos - xxPos;
		Fx = calcForceExertedBy(guest) * dx / calcDistance(guest);
		return Fx;
	}

	public double calcForceExertedByY(Body guest){
		double Fy;
		double dy;
		dy = guest.yyPos - yyPos;
		Fy = calcForceExertedBy(guest) * dy / calcDistance(guest);
		return Fy;
	}

	public double calcNetForceExertedByX(Body[] guestlist){
		double FNx = 0;
		for (Body guest : guestlist){
			if (equals(guest)){
				continue;
			}else{
				FNx = FNx + calcForceExertedByX(guest);
			}
		}
		return FNx;
	}

	public double calcNetForceExertedByY(Body[] guestlist){
		double FNy = 0;
		for (Body guest : guestlist){
			if (equals(guest)){
				continue;
			}else{
				FNy = FNy + calcForceExertedByY(guest);
			}
		}
		return FNy;
	}	

	public void update(double dt, double fX, double fY){
		double acc_x;
		double acc_y;
		acc_x = fX/mass;
		acc_y = fY/mass;
		xxVel = xxVel + dt * acc_x;
		yyVel = yyVel + dt * acc_y;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}
















