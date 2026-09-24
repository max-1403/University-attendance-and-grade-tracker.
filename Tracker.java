import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tracker {
    private static final class Record {
        int present;
        int total;
        float totalGrades;
        int gradeCount;

        Record() {
            this.present = 0;
            this.total = 0;
            this.totalGrades = 0.0f;
            this.gradeCount = 0;
        }
    }

    public static void main(String[] args) {
        final Map<String, Record> db = new HashMap<>(128);
        final Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\n[Menu] 1:Add Student | 2:Log Attendance | 3:Log Grade | 4:View Report | 5:Exit\nChoose option: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            final int choice = sc.nextInt();
            if (choice == 5) break;

            switch (choice) {
                case 1: {
                    System.out.print("Enter Student ID to add: ");
                    final String id = sc.next();
                    if (db.putIfAbsent(id, new Record()) == null) {
                        System.out.println("Student added successfully.");
                    } else {
                        System.out.println("Student ID already exists.");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter Student ID: ");
                    final String id = sc.next();
                    final Record r = db.get(id);
                    if (r != null) {
                        System.out.print("Enter Attendance (1 for Present, 0 for Absent): ");
                        final int status = sc.nextInt();
                        r.total++;
                        if (status == 1) r.present++;
                        System.out.println("Attendance logged.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter Student ID: ");
                    final String id = sc.next();
                    final Record r = db.get(id);
                    if (r != null) {
                        System.out.print("Enter Grade/Score: ");
                        r.totalGrades += sc.nextFloat();
                        r.gradeCount++;
                        System.out.println("Grade logged.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter Student ID: ");
                    final String id = sc.next();
                    final Record r = db.get(id);
                    if (r != null) {
                        final float attPct = r.total == 0 ? 0.0f : ((float) r.present / r.total) * 100.0f;
                        final float avgGpa = r.gradeCount == 0 ? 0.0f : r.totalGrades / r.gradeCount;
                        System.out.println("\n--- Student Performance Report ---");
                        System.out.printf("Attendance: %.1f%%\n", attPct);
                        System.out.printf("Average GPA: %.2f\n", avgGpa);
                        System.out.println("---------------------------------");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                default:
                    System.out.println("Invalid option. Choose 1-5.");
                    break;
            }
        }
        sc.close();
        System.out.println("Program terminated.");
    }
}
