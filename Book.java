public class Book {
    private static int idCounter = 0;
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.id = generateID();
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int generateID() {
        return idCounter++;
    }

    public int getID() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
}