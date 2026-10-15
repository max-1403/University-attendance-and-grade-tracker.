
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Tracker {

    // PARENT CLASS: Inheritance
    static class Person {
        String name;

        Person(String name) {
            this.name = name;
        }
    }

    // CHILD CLASS: Inherits from Person
    static class Student extends Person {
        int present;
        int total;
        float totalGrades;
        int gradeCount;

        // ARRAY: Stores individual grade scores
        float[] grades = new float[100];

        Student(String name) {
            super(name);
            this.present = 0;
            this.total = 0;
            this.totalGrades = 0.0f;
            this.gradeCount = 0;
        }
    }

    // METHOD: Calculate attendance percentage
    static float calculateAttendance(int present, int total) {
        return total == 0 ? 0.0f : (float) present / total * 100;
    }

    // METHOD OVERLOADING: Average of float total
    static float average(float total, int count) {
        return count == 0 ? 0.0f : total / count;
    }

    // METHOD OVERLOADING: Average of integer total
    static float average(int total, int count) {
        return count == 0 ? 0.0f : (float) total / count;
    }

    // METHOD: Add a student
    static void addStudent(Scanner sc, Map<String, Student> db) {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        if (db.containsKey(id)) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.next();

        db.put(id, new Student(name));
        System.out.println("Student added successfully.");
    }

    // METHOD: Log attendance
    static void logAttendance(Scanner sc, Map<String, Student> db) {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        Student s = db.get(id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Attendance (1 = Present, 0 = Absent): ");

        try {
            int status = sc.nextInt();

            if (status != 0 && status != 1) {
                System.out.println("Enter only 1 or 0.");
                return;
            }

            s.total++;

            if (status == 1) {
                s.present++;
            }

            System.out.println("Attendance logged.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter 1 or 0.");
            sc.next();
        }
    }

    // METHOD: Log grades
    static void logGrade(Scanner sc, Map<String, Student> db) {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        Student s = db.get(id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        if (s.gradeCount >= s.grades.length) {
            System.out.println("Maximum number of grades reached.");
            return;
        }

        System.out.print("Enter Grade/Score: ");

        try {
            float grade = sc.nextFloat();

            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100.");
                return;
            }

            // Store the grade in the array
            s.grades[s.gradeCount] = grade;

            s.totalGrades += grade;
            s.gradeCount++;

            System.out.println("Grade logged.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a number.");
            sc.next();
        }
    }

    // METHOD: View student report
    static void viewReport(Scanner sc, Map<String, Student> db) {
        System.out.print("Enter Student ID: ");
        String id = sc.next();

        Student s = db.get(id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        float attPct = calculateAttendance(s.present, s.total);
        float avgGrade = average(s.totalGrades, s.gradeCount);

        System.out.println("\n--- Student Performance Report ---");
        System.out.println("Name: " + s.name);
        System.out.println("Total Classes: " + s.total);
        System.out.println("Classes Present: " + s.present);
        System.out.printf("Attendance: %.1f%%%n", attPct);
        System.out.printf("Average Grade: %.2f%n", avgGrade);

        System.out.println("\nIndividual Grades:");

        if (s.gradeCount == 0) {
            System.out.println("No grades recorded.");
        } else {
            for (int i = 0; i < s.gradeCount; i++) {
                System.out.println("Grade " + (i + 1) + ": " + s.grades[i]);
            }
        }

        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        Map<String, Student> db = new HashMap<>(128);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n[Menu]");
            System.out.println("1: Add Student");
            System.out.println("2: Log Attendance");
            System.out.println("3: Log Grade");
            System.out.println("4: View Report");
            System.out.println("5: Exit");
            System.out.print("Choose option: ");

            try {
                int choice = sc.nextInt();

                if (choice == 5) {
                    break;
                }

                switch (choice) {
                    case 1:
                        addStudent(sc, db);
                        break;

                    case 2:
                        logAttendance(sc, db);
                        break;

                    case 3:
                        logGrade(sc, db);
                        break;

                    case 4:
                        viewReport(sc, db);
                        break;

                    default:
                        System.out.println("Invalid option. Choose 1-5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }

        sc.close();
        System.out.println("Program terminated.");
    }
}
