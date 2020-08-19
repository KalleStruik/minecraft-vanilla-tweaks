package nl.kallestruik.vanillatweaks.util;

import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class Util {

    private static final Random random = new Random();

    /**
     * Print an obvious header above the stack trace so it is easier to spot.
     * @param e The {@link Exception} to print the stacktrace of.
     */
    public static void printException(Exception e) {
        // Print the header.
        System.err.println("-------------------------------------------------------------------------------------");
        System.err.println("An error seems to have occurred. A detailed stacktrace of the error is printed below.");
        System.err.println("-------------------------------------------------------------------------------------");
        // Let the exception print its stacktrace.
        e.printStackTrace();
    }

    /**
     * Export a resource from inside the jar to an outside directory.
     * @param resourceName The file name inside the jar.
     * @param output The {@link File} to write the resource to.
     */
    public static void exportResource(String resourceName, File output) {
        // Try to create an input stream from the resource inside the jar and an output stream for the target file.
        try (InputStream inputStream = Util.class.getResourceAsStream("/" + resourceName); OutputStream resStreamOut = new FileOutputStream(output)) {
            // Make sure the input stream exists.
            if (inputStream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            // Read the input stream and write it to a file.
            int readBytes;
            byte[] buffer = new byte[4096];
            while ((readBytes = inputStream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            Util.printException(ex);
        }
    }

    /**
     * Create a random vector with integer offsets within the min and max.
     * @param min The minimum offset.
     * @param max The maximum offset.
     * @param height Whether the height should also have a random offset.
     * @return A {@link Vector} containing the offsets.
     */
    public static Vector getRandomLocationOffset(int min, int max, boolean height) {
        Vector vec;
        // Create the vectors with all positive numbers.
        if (height) {
            vec = new Vector(
                    min + random.nextInt(max - min),
                    min + random.nextInt(max - min),
                    min + random.nextInt(max - min));
        } else {
            vec = new Vector(
                    min + random.nextInt(max - min),
                    0,
                    min + random.nextInt(max - min));
        }

        // Randomly make some of the components negative instead of positive.
        vec.multiply(new Vector(
                random.nextBoolean() ? -1 : 1,
                random.nextBoolean() ? -1 : 1,
                random.nextBoolean() ? -1 : 1));

        // Return the completed vector.
        return vec;
    }
}
