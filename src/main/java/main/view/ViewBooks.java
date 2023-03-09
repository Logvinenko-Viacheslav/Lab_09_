package main.view;

import main.entities.Book;

import java.util.List;

public class ViewBooks {
    public void showList(List<Book> books) {
        System.out.println("~~~~~ Books ~~~~~");
        books.forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~");
    }
    public void showAuthors(List<String> authors) {
        System.out.println("~~~~~ Authors ~~~~~");
        authors.forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~");
    }
    public void showPublisherAndTheirBooks(List<Book> books, String publisher){
        System.out.println("Publisher: "+publisher);
        showList(books);
        System.out.println(" ");
    }
    public void showPublishers(List<String> publishers){
        System.out.println("~~~~~ Publishers ~~~~~");
        publishers.forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~");
    }
}
