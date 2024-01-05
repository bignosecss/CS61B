public class Planet {

    private static double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**
     * Constructor 1
     * @param xP
     * @param yP
     * @param xV
     * @param yV
     * @param m
     * @param img
     */
    public Planet(double xP, double yP, double xV, 
                double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /**
     * Constructor 2
     * @param p
     */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    // ------ self-defined method ---------
    private double calcDistanceX(Planet p) {
        return p.xxPos - this.xxPos;
    }

    private double calcDistanceY(Planet p) {
        return p.yyPos - this.yyPos;
    }

    // -------------------------------------

    /**
     * Calculate the distance between two Planets.
     * @param p
     * @return
     */
    public double calcDistance(Planet p) {
        double dx = calcDistanceX(p);
        double dy = calcDistanceY(p);

        return Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
    }

    /**
     * Takes in a Planets, and returns a double describing the force exerted on this planet
     * by the given planet.
     * @param p
     * @return
     */
    public double calcForceExertedBy(Planet p) {
        return (Planet.G * this.mass * p.mass) / Math.pow(calcDistance(p), 2);
    }

    /**
     * Returns the force exerted in the X directions.
     * @param p
     * @return
     */
    public double calcForceExertedByX(Planet p) {
        double dx = calcDistanceX(p);

        return calcForceExertedBy(p) * (dx / calcDistance(p));
    }

    /**
     * Returns the force exerted in the Y directions.
     * @param p
     * @return
     */
    public double calcForceExertedByY(Planet p) {
        double dy = calcDistanceY(p);

        return calcForceExertedBy(p) * (dy / calcDistance(p));
    }

    /**
     * Takes in an array of Planets and calculate the net X force exerted by all planets
     * in that array upon the current planet.
     * @param allPlanets
     * @return
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sumX = 0;

        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            sumX = sumX + calcForceExertedByX(planet);
        }
        
        return sumX;
    }

    /**
     * Takes in an array of Planets and calculate the net Y force exerted by all planets
     * in that array upon the current planet.
     * @param allPlanets
     * @return
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sumY = 0;

        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            sumY = sumY + calcForceExertedByY(planet);
        }
        
        return sumY;
    }

    /**
     * Determines how much the forces exerted on the planet will cause that planet
     * to accelerate, and the resulting change in the planet's velocity and position
     * in a small period of time dt.
     * @param dt
     * @param fX
     * @param fY
     */
    public void update(double dt, double fX, double fY) {
        // Step1: Calculate the acceleration
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        // Step2: Calculate the new velocity
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        // Step3: Calculate the new position
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    /**
     * Uses the StdDraw API to draw the Planet's image
     * at the Planet's position.
     * 
     * Takes in no parameters and return nothing.
     */
    public void draw() {        
        /* Draw a single planet */
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
