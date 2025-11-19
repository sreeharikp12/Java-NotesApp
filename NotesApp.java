import java.io.*;
import java.util.*;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Notes App ======");
            System.out.println("1. Add a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Clear all notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    clearNotes();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    // Function to add a note
    private static void addNote(Scanner sc) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            System.out.print("Enter your note: ");
            String note = sc.nextLine();
            fw.write(note + "\n");
            System.out.println("✅ Note added successfully!");
        } catch (IOException e) {
            System.out.println("⚠ Error writing to file: " + e.getMessage());
        }
    }

    // Function to view all notes
    private static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n📒 Your Notes:");
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) System.out.println("(No notes found)");
        } catch (FileNotFoundException e) {
            System.out.println("⚠ No notes found. Add a note first!");
        } catch (IOException e) {
            System.out.println("⚠ Error reading file: " + e.getMessage());
        }
    }

    // Function to clear all notes
    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write(""); // overwrite with empty content
            System.out.println("🧹 All notes cleared successfully!");
        } catch (IOException e) {
            System.out.println("⚠ Error clearing notes: " + e.getMessage());
        }
    }
}