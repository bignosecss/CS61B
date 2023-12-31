public class Planet {

    public static double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

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

    // --------- Add myself ----------------
    public double calcDistanceX(Planet p) {
        return p.xxPos - this.xxPos;
    }

    public double calcDistanceY(Planet p) {
        return p.yyPos - this.yyPos;
    }

    // -------------------------------------

    public double calcDistance(Planet p) {
        double dx = calcDistanceX(p);
        double dy = calcDistanceY(p);

        return Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
    }

    public double calcForceExertedBy(Planet p) {
        return (Planet.G * this.mass * p.mass) / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = calcDistanceX(p);

        return calcForceExertedBy(p) * (dx / calcDistance(p));
    }

    public double calcForceExertedByY(Planet p) {
        double dy = calcDistanceY(p);

        return calcForceExertedBy(p) * (dy / calcDistance(p));
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sumX = 0;

        for (Planet allPlanet : allPlanets) {
            if (this.equals(allPlanet)) {
                continue;
            }
            sumX = sumX + calcForceExertedByX(allPlanet);
        }
        
        return sumX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sumY = 0;

        for (Planet allPlanet : allPlanets) {
            if (this.equals(allPlanet)) {
                continue;
            }
            sumY = sumY + calcForceExertedByY(allPlanet);
        }
        
        return sumY;
    }

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

}
