package ovdm.library;

import java.util.Date;

public class Checkout {
    private final Book book;
    private final Patron patron;
    private final Date dueDate;

    public Checkout(Book book, Patron patron, Date dueDate) {
        this.book = book;
        this.patron = patron;
        this.dueDate = dueDate;
    }
}
