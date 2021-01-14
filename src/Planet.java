public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    private static final double GRAVITY_CONSTANT = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /**
     * Calculate distance between this planet and another planet p
     * @param p Planet to find distance to
     * @return Distance to planet
     */
    public double calcDistance(Planet p) {
        double xDiff = this.xxPos - p.xxPos;
        double yDiff = this.yyPos - p.yyPos;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * Calculate force exerted by another planet to this planet
     * @param p Other planet that exerts force
     * @return the force
     */
    public double calcForceExertedBy(Planet p) {
        return (GRAVITY_CONSTANT * this.mass * p.mass) / (calcDistance(p) * calcDistance(p));
    }

    /**
     * force exerted in X direction from other planet
     * @param p Other planet that exerts force
     * @return the force X
     */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return (calcForceExertedBy(p) * dx) / calcDistance(p);
    }

    /**
     * force exerted in Y direction from other planet
     * @param p Other planet that exerts force
     * @return the force Y
     */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return (calcForceExertedBy(p) * dy) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                sum += calcForceExertedByX(p);
            }
        }
        return sum;

    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                sum += calcForceExertedByY(p);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY){
        // acceleration x and y
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        // new velocity of x and y
        double vx = this.xxVel+dt*ax;
        double vy = this.yyVel+dt*ay;
        // new position of x and y
        double px = this.xxPos + dt*vx;
        double py = this.yyPos + dt*vy;
        this.xxVel = vx;
        this.yyVel = vy;
        this.xxPos = px;
        this.yyPos = py;
    }
}
