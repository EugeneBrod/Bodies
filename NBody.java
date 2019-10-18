public class NBody {

	public static double readRadius(String file) {
		In in = new In(file);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String file) {
		In in = new In(file);
		int n = in.readInt();
		in.readDouble();
		Body[] bodies = new Body[n];
		int x = 0;
		while (x<n) {
			bodies[x] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			x +=1;
		}
		return bodies;
	}

	public static void main(String[] args) {
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Double effective_radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		StdDraw.setScale(-effective_radius, effective_radius);

		StdDraw.enableDoubleBuffering();

		Double time = 0.0;
		while (time < T) {
			Double[] xForces = new Double[bodies.length];
			Double[] yForces = new Double[bodies.length];
		for (int i=0; i<bodies.length; i++) {
			xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
			yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
		}
		for (int i=0; i<bodies.length; i++) {
			bodies[i].update(dt, xForces[i], yForces[i]);
		}
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int i=0; i<bodies.length; i++) {
			bodies[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(10);
		time += dt;
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", effective_radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
              bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}
}