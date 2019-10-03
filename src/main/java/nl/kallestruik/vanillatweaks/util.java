package nl.kallestruik.vanillatweaks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class util {

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
}
