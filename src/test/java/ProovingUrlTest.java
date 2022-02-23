import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProovingUrlTest {

    @Test
    public void testProoveWithFunctionalUrl() throws IOException {
        //Test run
        String testWebAdress = "https://www.spiegel.de";
        ProovingUrl testProovingUrl = new ProovingUrl(testWebAdress);
        testProovingUrl.run();

        //access the txt.file, read out the last line
        String line = "";
        BufferedReader b = new BufferedReader(new FileReader("C:\\logos.txt"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        while (!line.contains(dateTimeFormatter.format(LocalDateTime.now())
                .toUpperCase(Locale.GERMAN)+ " Uhr : " + testWebAdress )) {
            line = b.readLine();
        }
        b.close();

        //check if the line is in the txt.file
        assertEquals(dateTimeFormatter.format(LocalDateTime.now())
                .toUpperCase(Locale.GERMAN) + " Uhr : " + testWebAdress + " -> erreichbar!", line);

    }

    @Test
    public void testProoveWithNotFunctionalUrl() throws IOException {
        //Test run
        String testAdress = "https://bitly.com/404-error-page";
        ProovingUrl testProovingUrl = new ProovingUrl(testAdress);
        testProovingUrl.run();

        String line = "";
        BufferedReader b = new BufferedReader(new FileReader("C:\\logos.txt"));

        //access the txt.file, read out the last line
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        while (!line.contains(dateTimeFormatter.format(LocalDateTime.now()).toUpperCase(Locale.GERMAN)
                + " Uhr : " + testAdress)) {
            line = b.readLine();
        }
        b.close();

        //check if the line is in the txt.file
        assertEquals(dateTimeFormatter.format(LocalDateTime.now())
                .toUpperCase(Locale.GERMAN) + " Uhr : " + testAdress + " -> nicht erreichbar!", line);
    }
}