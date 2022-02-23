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
        System.out.println(webAdress +
                " wird überwacht, drücken Sie eine beliebige Taste um das Programm zu beenden...");

        //initiates the executor
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new ProovingUrl(webAdress);
        //here the task is executed, initially immediately and then every 30 seconds
        executor.scheduleWithFixedDelay(task, 0, 30, TimeUnit.SECONDS);
        //here the abort signal (keystroke + enter) is accepted and processed
        scanner.next();
        executor.shutdown();
    }
}