import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
    static Book[] library = new Book[1];
    static int numberOfBooks = library.length - 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;

        while (!endProgram) {
            System.out.print("\n=== LIBRARY MANAGEMENT SYSTEM MENU ===");
            System.out.print("\n1 - Add a book\n4 - Exit\n");
            int option = getValidInt("--> Option: ");

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 4:
                    endProgram = true;
                    break;
            }
        }
    }

    public static void addBook() {
        System.out.print("\n=== ADD A BOOK ===\n");
        // Gather data
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter year: ");
        int year = sc.nextInt();

        // Add the book
        library[numberOfBooks] = new Book(title, author, year);

        // Add another slot
        library = addSlot(library);
    }
    
    public static Book[] addSlot(Book[] library) {
        return Arrays.copyOf(library, library.length + 1);
    }

    public static int getValidInt(String prompt) {
        int number = -1;
        while (number < 0) {
            System.out.print(prompt);
            try {
                number = sc.nextInt();
                if (number < 0) {
                    System.out.println("Error: Must be greater than or equal to 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number, not text.");
            } finally {
                sc.nextLine();
            }
        }
        return number;
    }
}