import java.util.Scanner;

public class Main {
    static Book[] library = new Book[1];
    static int numberOfBooks = library.length - 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;

        while (!endProgram) {
            switch (option) {
                case 1:
                    break;
                case 4:
                    endProgram = true;
                    break;
            }
        }
    }

    public static void addBook() {
        // Gather data
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter year: ");
        int year = sc.nextInt();

        // Add the book
        library[numberOfBooks] = new Book(title, author, year);
    }


}