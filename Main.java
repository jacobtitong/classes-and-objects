import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    static Library library = new Library();
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
                    library.displayBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    endProgram = true;
                    break;
                default:
                    System.out.println("Error: Invalid option. Please choose 1-4.");
            }
        }
    }

    public static void addBook() {
        System.out.print("\n=== ADD A BOOK ===\n");
        // Gather data
        String title = getNonEmptyString("Enter title: ");
        String author = getNonEmptyString("Enter author: ");
        int year = getValidYear("Enter year: ");

        boolean added = library.addBook(title, author, year);
        if (added) {
            System.out.print("Book added successfully\n");
        } else {
            System.out.print("Error: Book already exists in the library.\n");
        }
    }

    public static void searchBook() {
        System.out.print("\n=== SEARCH FOR A BOOK ===\n");
        String title = getNonEmptyString("Enter a book to search: ");
        Book[] results = library.searchBook(title);

        if (results.length == 0) {
            System.out.print("Book not found!\n");
            return;
        }

        for (Book b : results) {
            System.out.print("Book found!\n");
            System.out.printf("Title: %s\nAuthor: %s\nYear: %d\n\n", b.getTitle(), b.getAuthor(), b.getYear());
        }

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

}