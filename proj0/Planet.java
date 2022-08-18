public class NBody{
	public static double readRadius(String img){
		In in = new In(img);
		int NPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String img){
		In in = new In(img);
		int NPlanets = in.readInt();
		double radius = in.readDouble();
		Body[] BodyList = new Body[NPlanets];
		for (int i=0; i<NPlanets; i=i+1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String image = in.readString();
			BodyList[i] = new Body(xP, yP, xV, yV, m, image);
		}
		return BodyList;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] BodyList = readBodies(filename); // why does "new readBodies(filename)" make errors?

		/* Start drawing */ 
		StdDraw.enableDoubleBuffering();
		for (double time=0; time<T; time = time + dt){
			int N = BodyList.length;
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			for(int i=0; i<N; i=i+1){
				xForces[i] = BodyList[i].calcNetForceExertedByX(BodyList);
				yForces[i] = BodyList[i].calcNetForceExertedByY(BodyList);
			}
			for(int j=0; j<N; j=j+1)
				BodyList[j].update(dt, xForces[j], yForces[j]);
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0,0,"images/starfield.jpg");
			for (Body body : BodyList)
				body.draw();
			StdDraw.show();
			StdDraw.pause(10);			
		}

		StdOut.printf("%d\n", BodyList.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < BodyList.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  BodyList[i].xxPos, BodyList[i].yyPos, BodyList[i].xxVel,
		                  BodyList[i].yyVel, BodyList[i].mass, BodyList[i].imgFileName);   
		}		
	}
}