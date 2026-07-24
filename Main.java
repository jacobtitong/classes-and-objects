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
            System.out.print("\n1 - Add a book\n2 - Display books\n3 - Search for a book\n4 - Exit\n");
            int option = getValidInt("--> Option: ");

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    searchBook();
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
        int year = getValidYear("Enter year: ");

        // Add the book
        library[numberOfBooks] = new Book(title, author, year);

        // Add another slot
        library = addSlot(library);
        numberOfBooks = library.length - 1;
        
        System.out.print("Book added successfully!\n");
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

    public static int getValidYear(String prompt) {
        boolean isValid = false;
        int year = -1;
        while (!isValid) {
            year = getValidInt(prompt);
            if (year >= 1000 && year <= 2026) {
                isValid = true;
            } else {
                System.out.println("Error: Please enter a realistic publication year (4 digits, between 1000 - 2026) ");
            }
        }
        return year;
    }

    public static void displayBooks() {
        System.out.print("\n=== BOOK LIST ===");
        System.out.println("\n+----------------------+-----------------+--------+");
        System.out.printf("| %-20.20s | %-15.15s | %-6s |\n", "Title", "Author", "Year");
        System.out.println("+----------------------+-----------------+--------+");

        for (int i = 0; i < numberOfBooks; i++) {
            if (library[i] != null) {
                System.out.printf("| %-20.20s | %-15.15s | %-6d |\n", 
                library[i].title, 
                library[i].author, 
                library[i].year);
            }
        }
        System.out.println("+----------------------+-----------------+--------+");
    }

    public static void searchBook() {
        System.out.print("\n=== SEARCH FOR A BOOK ===\n");
        System.out.print("Enter a book to search: ");
        String book = sc.nextLine();
        boolean found = false;
        int ctr = 0;

        for (int i = 0; i < numberOfBooks; i++) {
            if (library[i] != null && library[i].title.equalsIgnoreCase(book)) {
                found = true;
                break;
            }
            ctr++;
        }

        if (found) {
            System.out.print("Book found!\n");
            System.out.printf("Title: %s\nAuthor: %s\nYear: %d\n", library[ctr].title, library[ctr].author, library[ctr].year);

        } else {
            System.out.print("Book not found!\n");
        }
    }
}