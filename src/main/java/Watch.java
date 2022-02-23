import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Watch {

    public static void watching() {

        //asks for web address to be checked
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gib die zu Überwachende Adresse ein!");
        String webAdress = scanner.nextLine();
        System.out.println(webAdress + " wird überwacht, drücken Sie eine beliebige Taste um das Programm zu beenden...");

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new ProovingUrl(webAdress);
        executor.scheduleWithFixedDelay(task, 0, 30, TimeUnit.SECONDS);
        scanner.next();
        executor.shutdown();
    }
}