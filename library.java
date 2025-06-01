import java.util.Scanner;

public class Library {
    private String[] availableBooks = new String[100];
    private String[] issuedBooks = new String[100];
    private int availableCount = 0;
    private int issuedCount = 0;
    private Scanner scanner = new Scanner(System.in);

    // Method to add a new book
    public void addBook() {
        System.out.print("Enter book title to add: ");
        String title = scanner.nextLine();
        availableBooks[availableCount] = title;
        availableCount++;
        System.out.println("Book added successfully!");
    }

    // Method to issue a book
    public void issueBook() {
        System.out.print("Enter book title to issue: ");
        String title = scanner.nextLine();
        for (int i = 0; i < availableCount; i++) {
            if (availableBooks[i].equalsIgnoreCase(title)) {
                issuedBooks[issuedCount] = availableBooks[i];
                issuedCount++;
                System.out.println("Book issued successfully!");
                // Shift the remaining books to fill the gap
                for (int j = i; j < availableCount - 1; j++) {
                    availableBooks[j] = availableBooks[j + 1];
                }
                availableBooks[--availableCount] = null;
                return;
            }
        }
        System.out.println("Book not found or already issued.");
    }

    // Method to return a book
    public void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        for (int i = 0; i < issuedCount; i++) {
            if (issuedBooks[i].equalsIgnoreCase(title)) {
                availableBooks[availableCount] = issuedBooks[i];
                availableCount++;
                System.out.println("Book returned successfully!");
                // Shift the remaining issued books to fill the gap
                for (int j = i; j < issuedCount - 1; j++) {
                    issuedBooks[j] = issuedBooks[j + 1];
                }
                issuedBooks[--issuedCount] = null;
                return;
            }
        }
        System.out.println("Book not found in issued list.");
    }

    // Method to display available books
    public void showAvailableBooks() {
        if (availableCount == 0) {
            System.out.println("No available books.");
            return;
        }
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < availableCount; i++) {
            System.out.println((i + 1) + ". " + availableBooks[i]);
        }
    }

    // Main method to test the library system
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show Available Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> library.addBook();
                case 2 -> library.issueBook();
                case 3 -> library.returnBook();
                case 4 -> library.showAvailableBooks();
                case 5 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
