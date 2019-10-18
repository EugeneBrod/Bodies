public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
		/** Constructs Body object. */
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}

	public Body(Body b) {
		/** Constructs a copy of an existing Body object. */
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/** Constants */
	private static final Double g = 6.67e-11;

	public double calcDistance(Body b) {
		/** Calculates distance between this Body and Body b */
		double distance = Math.sqrt((this.xxPos - b.xxPos)*(this.xxPos - b.xxPos)
							+ (this.yyPos - b.yyPos)*(this.yyPos - b.yyPos));
		return distance;
	}

	public double calcForceExertedBy(Body b) {
		/** Calculates force between this Body and Body b. */
		double force = g*this.mass*b.mass/(this.calcDistance(b)*this.calcDistance(b));
		return force;
	}

	public double calcForceExertedByX(Body b) {
		/** Calculates force in X-direction. */
		double forceX = this.calcForceExertedBy(b)*(b.xxPos - this.xxPos)/this.calcDistance(b);
		return forceX;
	}

	public double calcForceExertedByY(Body b) {
		/** Calculates force in X-direction. */
		double forceY = this.calcForceExertedBy(b)*(b.yyPos - this.yyPos)/this.calcDistance(b);
		return forceY;
	}

	public double calcNetForceExertedByX(Body[] b) {
		/** Calculates net force in X-direction. */
		double net = 0;
		for (Body t: b) {
			if (this == t) continue;
			net += this.calcForceExertedByX(t);
		}
		return net;
	}

	public double calcNetForceExertedByY(Body[] b) {
		/** Calculates net force in X-direction. */
		double net = 0;
		for (Body t: b) {
			if (this == t) continue;
			net += this.calcForceExertedByY(t);
		}
		return net;
	}

	public void update(double inc, double xxForce, double yyForce) {
		/** Calculates new Positions and Velocities with time interval inc. */
		double xxAccel = xxForce/this.mass;
		this.xxVel = this.xxVel + inc*xxAccel;

		double yyAccel = yyForce/this.mass;
		this.yyVel = this.yyVel + inc*yyAccel;

		this.xxPos = this.xxPos + inc*xxVel;
		this.yyPos = this.yyPos + inc*yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}