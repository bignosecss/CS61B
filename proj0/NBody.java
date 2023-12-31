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

}
