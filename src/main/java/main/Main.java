package main;

import lombok.SneakyThrows;
import main.dao.BookDao;
import main.entities.Book;
import main.view.ViewBooks;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private BookDao bookDao;
    private ViewBooks viewBooks;
    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }

    @SneakyThrows
    private void run() {
        Properties props = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"))) {
            props.load(reader);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
            bookDao = new BookDao(connection);
            viewBooks = new ViewBooks();
        }
        int m;
        while ((m = menu()) != 0) {
            switch (m) {
                case 1 -> {
                    showAll();
                }
                case 2 -> {
                    byAuthor();
                }
                case 3 ->{
                    byPublishing();
                }
                case 4->{
                    byPublishYear();
                }
                case 5->{
                    authorsList();
                }
                case 6->{
                    publishingByNotDuplicate();
                }
                case 7->{
                    publishingBooks();
                }
                // 34:03 timing

            }
        }
    }


    private int menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                1. Show All Books
                2. Books by Author
                3. Books by Publishing
                4. Books after Publication Year
                5. Author's list
                6. Publishing with unduplicate books
                7. Publishing and they books
                0. Exit
                
                Choose option:
                """);
        return scanner.nextInt();
    }
    private void showAll() {
        List<Book> books = bookDao.findAll();
        viewBooks.showList(books);
    }

    private void byAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write Author's name");
        String input = scanner.nextLine();
        List<Book> books = bookDao.byAuthorSortByYearAscending(input);
        viewBooks.showList(books);
    }

    private void byPublishing(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write Publishing");
        String input = scanner.nextLine();
        List<Book> books = bookDao.byPublishing(input);
        viewBooks.showList(books);
    }
    private void byPublishYear(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write Publish year");
        int input = scanner.nextInt();
        List<Book> books = bookDao.afterPublishYear(input);
        viewBooks.showList(books);
    }

    private void authorsList(){
        List<String> authors = bookDao.authorsList();
        viewBooks.showAuthors(authors);
    }

    private void publishingBooks() {
        List<String> publishingList = bookDao.publishingList();
        for(int i=0; i<publishingList.size(); i++){
            List<Book> tmp = bookDao.publishingBooks(publishingList.get(i));
            viewBooks.showPublisherAndTheirBooks(tmp, publishingList.get(i));
        }
    }
    private void publishingByNotDuplicate() {
        List<String> publishingList = bookDao.publishingByNotDuplicate();
        viewBooks.showPublishers(publishingList);
    }
}
