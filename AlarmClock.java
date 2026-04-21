import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AlarmClock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter alarm time (HH:mm:ss): ");
        String alarmInput = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime;

        try {
            alarmTime = LocalTime.parse(alarmInput, formatter);
        } catch (Exception e) {
            System.out.println("Invalid time format! Use HH:mm:ss");
            return;
        }

        System.out.println("Alarm set for: " + alarmTime);

        while (true) {
            LocalTime now = LocalTime.now();

            // Display current time
            System.out.print("\rCurrent Time: " + now.withNano(0));

            // Check alarm
            if (now.withNano(0).equals(alarmTime)) {
                System.out.println("\n⏰ Alarm Ringing!");
                playBeep();
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    // Simple beep sound
    public static void playBeep() {
        for (int i = 0; i < 5; i++) {
            System.out.print("\007"); // system beep
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
