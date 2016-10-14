package ovdm.library;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Patron {
    private final String name;
    private final String phone;

    Map<Book, Date> checkouts = new HashMap<>();

    public Patron(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void checkout(Book book, Library library){

    }

    public void checkin(Book book, Library library){

    }
}
