public class NBodyExtreme {

    public static double readRadius(String file) {
        In in = new In(file);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static BodyExtreme[] readBodies(String file) {
        In in = new In(file);
        int n = in.readInt();
        in.readDouble();
        BodyExtreme[] bodies = new BodyExtreme[n+1];
        int x = 0;
        while (x<n) {
            bodies[x] = new BodyExtreme(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            x +=1;
        }
        return bodies;
    }

    public static void main(String[] args) {
        Double T = Double.parseDouble(args[0]);
        Double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Double effective_radius = readRadius(filename);
        BodyExtreme[] bodies = readBodies(filename);
        StdDraw.setScale(-effective_radius, effective_radius);

        StdDraw.enableDoubleBuffering();

        BodyExtreme mothership = new BodyExtreme(0, 0, 0, 0, 2.9800e+01, mothership.gif);
        bodies[bodies.length] = mothership;

        Double time = 0.0;
        while (time < T) {
            Double[] xForces = new Double[bodies.length];
            Double[] yForces = new Double[bodies.length];

            for (int i=0; i<bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            //keyboard listener initialization
            KeyEvent event;
            boolean[] directions = new boolean[4];
            if (event.getKeyCode() == KeyEvent.VK_UP) directions[0] = true;
            if (event.getKeyCode() == KeyEvent.VK_LEFT) directions[1] = true;
            if (event.getKeyCode() == KeyEvent.VK_DOWN) directions[2] = true;
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) directions[3] = true;

            for (int i=0; i<bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i], mothership, directions);
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