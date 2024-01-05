public class NBody {

    /**
     * Takes in a file name, returns a double corresponding to the radius
     * of the universe in that file.
     * @param planetsTxtPath
     * @return
     */
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);

        int numberOfPlanets = in.readInt();
        double radiusOfUniverse = in.readDouble();

        return radiusOfUniverse;
    }

    /**
     * Takes in a file name, returns an array of Planets corresponding to
     * the planets in the file.
     * @param planetsTxtPath
     * @return
     */
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

        // System.out.println("number of planets: " + numberOfPlanets + " or " + planets.length);
        // All two are correct.

        return planets;
    }

    /**
     * Draw the background image.
     * Added myself. Not required.
     * @param universeRadius
     * @param imgPath
     */
    public static void drawBackgroundImage(double universeRadius, String imgPath) {
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imgPath);
        StdDraw.show();
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        // Store the 0th and 1st command line arguments.
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        // Store the 2nd command line argument.
        String filename = args[2];

        // Read planets and universe radius
        Planet[] planets = readPlanets(filename);
        double universeRadius = readRadius(filename);

        // Draw the background iamge
        drawBackgroundImage(universeRadius, "images/starfield.jpg");

        /* Draw the planets and show */
        for (Planet planet : planets) {
            planet.draw();
        }
        StdDraw.show();

        // System.out.println("Original x position of each planet: ");
        // for (Planet planet : planets) {
        //     System.out.println(planet.xxPos);
        // }

        double timeOfUniverse = 0;
        while (timeOfUniverse != T) {

            // Here, the initial is the point!
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            // Calculate the net x and y forces for each planet.
            for(int i = 0; i < planets.length; i++) {
                // Here the exception comes. No, Look up the two initial statements!
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

                /*
                System.out.println("Updated x position of each planet: ");
                for (Planet planet : planets) {
                    System.out.println(planet.xxPos);
                }
                */
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            // Draw the background iamge
            drawBackgroundImage(universeRadius, "images/starfield.jpg");
    
            /* Draw the planets and show */
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            timeOfUniverse = timeOfUniverse + dt;
        }
    }

}
