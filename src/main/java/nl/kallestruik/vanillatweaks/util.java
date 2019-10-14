package nl.kallestruik.vanillatweaks;

import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class util {

    private static final Random random = new Random();

    public static void printException(Exception e) {
        System.err.println("-------------------------------------------------------------------------------------");
        System.err.println("An error seems to have occurred. A detailed stacktrace of the error is printed below.");
        System.err.println("-------------------------------------------------------------------------------------");
        e.printStackTrace();
    }

    public static void ExportResource(String resourceName, File output) {
        try (InputStream stream = VanillaTweaks.class.getResourceAsStream("/" + resourceName); OutputStream resStreamOut = new FileOutputStream(output)) {
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            util.printException(ex);
        }
    }

    public static Vector getRandomLocationOffset(int min, int max, boolean height) {
        Vector vec;
        if (height)
            vec = new Vector(min + random.nextInt(max - min), min + random.nextInt(max - min), min + random.nextInt(max - min));
        else
            vec = new Vector(min + random.nextInt(max - min), 0, min + random.nextInt(max - min));

        vec.multiply(new Vector(random.nextBoolean() ? -1 : 1, random.nextBoolean() ? -1 : 1, random.nextBoolean() ? -1 : 1));
        return vec;
    }
}
