import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ProovingUrl implements Runnable {

    private final String webAdress;

    public ProovingUrl(String webAdress) {
        this.webAdress = webAdress;
    }

    @Override
    public void run() {
        proove(webAdress);
    }

    public static void proove(String webAdress){
        //Create new file if it does not exist
        File file = new File("C:\\logos.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
            //Verification that the website is accessible
            URL url = new URL(webAdress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            //writes the current status to the txt.file
            try {
                if (code >= 200 && code <= 299) {
                    writer.write(dateTimeFormatter.format(LocalDateTime.now())
                            .toUpperCase(Locale.GERMAN) + " Uhr : " + webAdress + " -> erreichbar!");
                } else {
                    writer.write(dateTimeFormatter.format(LocalDateTime.now())
                            .toUpperCase(Locale.GERMAN) + " Uhr : " + webAdress + " -> nicht erreichbar!");
                }
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Writer funktioniert nicht!" + e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}