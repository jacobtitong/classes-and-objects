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
        String title = getNonEmptyString("Enter title: ");
        String author = getNonEmptyString("Enter author: ");
        int year = getValidYear("Enter year: ");

        if (getValidBook(title, author, year)) {
            System.out.print("Error: Book already exists in the library. Please try again.\n");
            return;
        }

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

    public static boolean getValidBook(String title, String author, int year) {
        boolean bookExists = false;
        for (int i = 0; i <  numberOfBooks; i++) {
            bookExists = library[i].title.equalsIgnoreCase(title) && library[i].author.equalsIgnoreCase(author) && library[i].year == year;
            if (bookExists) {
                return bookExists;
            }
        }
        return bookExists;
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

    public static String getNonEmptyString(String prompt) {
    String input = "";
    boolean isValid = false;

    while (!isValid) {
        System.out.print(prompt);
        input = sc.nextLine();

        if (!input.trim().isEmpty()) {
            isValid = true;
        } else {
            System.out.println("Error: Input cannot be empty or just spaces.");
        }
    }
    return input;
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
        String book = getNonEmptyString("Enter a book to search: ");
        
        boolean foundAny = false;

        for (int i = 0; i < numberOfBooks; i++) {
            if (library[i] != null && library[i].title.equalsIgnoreCase(book)) {
                
                if (!foundAny) {
                    System.out.print("\nMatches found!\n");
                    System.out.println("+----------------------+-----------------+--------+");
                    System.out.printf("| %-20.20s | %-15.15s | %-6s |\n", "Title", "Author", "Year");
                    System.out.println("+----------------------+-----------------+--------+");
                    foundAny = true;
                }

                System.out.printf("| %-20.20s | %-15.15s | %-6d |\n", 
                    library[i].title, 
                    library[i].author, 
                    library[i].year);
            }
        }

        if (foundAny) {
            System.out.println("+----------------------+-----------------+--------+");
        } else {
            System.out.print("Book not found!\n");
        }
    }

}