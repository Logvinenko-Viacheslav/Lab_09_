package main.dao;

import lombok.AllArgsConstructor;
import main.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookDao {
    private Connection connection;

    public List<Book> findAll() {
         ArrayList<Book> list = new ArrayList<>();
         try(PreparedStatement ps = connection.prepareStatement("select * from books")){
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 int id = rs.getInt("id");
                 String title = rs.getString("Title");
                 String author = rs.getString("Author");
                 String publisher = rs.getString("Publisher");
                 int publicationYear = rs.getInt("PublicationYear");
                 int numberOfPages = rs.getInt("Number of Pages");
                 double price = rs.getDouble("Price");
                 list.add((new Book(id, title, author, publisher, publicationYear, numberOfPages, price)));
             }
        }catch(SQLException e){
             throw new RuntimeException(e);
        }
         return list;
    }

    public List<Book> byAuthorSortByYearAscending(String authorR) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from books where Author = ? order by PublicationYear ASC";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, authorR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                int publicationYear = rs.getInt("PublicationYear");
                int numberOfPages = rs.getInt("Number of Pages");
                double price = rs.getDouble("Price");
                list.add((new Book(id, title, author, publisher, publicationYear, numberOfPages, price)));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Book> byPublishing(String publisshing){
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from books where Publisher = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, publisshing);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                int publicationYear = rs.getInt("PublicationYear");
                int numberOfPages = rs.getInt("Number of Pages");
                double price = rs.getDouble("Price");
                list.add((new Book(id, title, author, publisher, publicationYear, numberOfPages, price)));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Book> afterPublishYear(int input) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from books where PublicationYear > ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, input);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                int publicationYear = rs.getInt("PublicationYear");
                int numberOfPages = rs.getInt("Number of Pages");
                double price = rs.getDouble("Price");
                list.add((new Book(id, title, author, publisher, publicationYear, numberOfPages, price)));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;

    }

    public List<String> authorsList() {
        ArrayList<String > list = new ArrayList<>();
        String sql = "select distinct Author from books order by Author ASC";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String author = rs.getString("Author");
                list.add(author);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;

    }
    public List<Book> publishingBooks(String publisherr) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from books where Publisher = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, publisherr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                int publicationYear = rs.getInt("PublicationYear");
                int numberOfPages = rs.getInt("Number of Pages");
                double price = rs.getDouble("Price");
                list.add((new Book(id, title, author, publisher, publicationYear, numberOfPages, price)));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> publishingList() {
        ArrayList<String > list = new ArrayList<>();
        String sql = "select distinct Publisher from books order by Publisher ASC";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String publisher = rs.getString("Publisher");
                list.add(publisher);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> publishingByNotDuplicate() {
        ArrayList<String > list = new ArrayList<>();
        String sql = "select distinct Publisher from books where Title not in  (select Title from books group by Title, Publisher having count(*)>1) and Publisher not in (select Publisher from books group by Title, Publisher having  count(*) > 1)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String publisher = rs.getString("Publisher");
                list.add(publisher);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }
}
