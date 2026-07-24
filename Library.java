import java.util.Arrays;

public class Library {
    private Book[] books = new Book[0];
    private int numberOfBooks = 0;

    public boolean addBook(String title, String author, int year) {
        if (getValidBook(title, author, year)) {
            return false;
        }

        books = Arrays.copyOf(books, books.length + 1);
        books[numberOfBooks] = new Book(title, author, year);
        numberOfBooks++;
        return true;
    }

    private boolean getValidBook(String title, String author, int year) {
        for (int i = 0; i < numberOfBooks; i++) {
            if (books[i] != null && 
                books[i].getTitle().equalsIgnoreCase(title) && 
                books[i].getAuthor().equalsIgnoreCase(author) && 
                books[i].getYear() == year) {
                return true;
            }
        }
        return false;
    }

    public void displayBooks() {
        System.out.println("\n=== BOOK LIST: ===");
        
        System.out.printf("%-20s %-16s %-4s\n", "Title", "Author", "Year");
        
        for (int i = 0; i < numberOfBooks; i++) {
            if (books[i] != null) {
                System.out.printf("%-20.20s %-16.16s %-4d\n", 
                    books[i].getTitle(), 
                    books[i].getAuthor(), 
                    books[i].getYear());
            }
        }
    }
}
