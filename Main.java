import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Book[] library = new Book[1];
    static int numberOfBooks = library.length - 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;

        while (!endProgram) {
            System.out.print("\n=== LIBRARY MANAGEMENT SYSTEM MENU ===");
            System.out.print("\n1 - Add a book\n4 - Exit\n--> Option: ");
            int option = sc.nextInt();
            sc.nextLine();

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


}