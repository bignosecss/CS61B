public class NBody {

    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);

        int numberOfPlanets = in.readInt();
        double radiusOfUniverse = in.readDouble();

        return radiusOfUniverse;
    }

    public static Planet[] readPlanets(String planetsTxtPath) {
        In in  = new In(planetsTxtPath);

        int numberOfPlanets = in.readInt();
        double readRadius = in.readDouble();

        Planet[] planets = new Planet[numberOfPlanets];

        for (int rows = 0; rows < numberOfPlanets; rows++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String planetImgFileName = in.readString();

            planets[rows] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, planetImgFileName);
        }

        return planets;
    }

    public static void main(String[] args) {

        // Store the 0th and 1st command line arguments.
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        // Store the 2nd command line argument.
        String filename = args[2];

        // Read planets and universe radius
        Planet[] planets = readPlanets(filename);
        double universeRadius = readRadius(filename);

        // Draw the background iamge
        StdDraw.setScale(-universeRadius, universeRadius);

        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();

    }

}
