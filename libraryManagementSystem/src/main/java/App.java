import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class App {
        public static void main(String[] args) {
            EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("library_management_system");
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            System.out.println("test");

            EntityTransaction transaction=entityManager.getTransaction();
            transaction.begin();
            Author author=new Author();
            author.setName("Matt Haig");
            author.setBirthDate(LocalDate.of(1975, 07, 03));
            author.setCountry("England");
            entityManager.persist(author);

            Publisher publisher=new Publisher();
            publisher.setName("Domingo Yayınevi");
            publisher.setEstablishmentYear(2007);
            publisher.setAddress("Beyoğlu/İstanbul");
            entityManager.persist(publisher);

            Book book=new Book();
            book.setName("Gece Yarısı Kütüphanesi");
            book.setPublicationYear(2020);
            book.setStock(10);
            book.setAuthor(author);
            book.setPublisher(publisher);
            entityManager.persist(book);

            Book book2=new Book();
            book2.setName("Rahatlama Kitabı - Suyun Üstünde Kalmamı Sağlayan Düşünceler");
            book2.setPublicationYear(2022);
            book2.setStock(5);
            book2.setAuthor(author);
            book2.setPublisher(publisher);
            entityManager.persist(book2);


            BookBorrowing bookBorrowing=new BookBorrowing();
            bookBorrowing.setName("Tuğba İşleyen");
            bookBorrowing.setBorrowingDate(LocalDate.of(2023,11,10));
            bookBorrowing.setReturnDate(LocalDate.of(2023,11,17));
            bookBorrowing.setBook(book);
            entityManager.persist(bookBorrowing);

            BookBorrowing bookBorrowing2=new BookBorrowing();
            bookBorrowing2.setName("Yaren İşleyen");
            bookBorrowing2.setBorrowingDate(LocalDate.of(2023,11,10));
            bookBorrowing2.setReturnDate(LocalDate.of(2023,11,22));
            bookBorrowing2.setBook(book2);
            entityManager.persist(bookBorrowing2);

            Category novel=new Category("Roman","42 Dile çevrilen , Çok Satan Kitap");
            Category comicBook=new Category("Çizgi Roman","Çocuklar için");
            Category science=new Category("Bilim","7'den 75'e");
            Category selfImprovement=new Category("Kişisel Gelişim","Kişisel Gelişimin Adresi");
            Category translationBook=new Category("Çeviri Kitap","Türkçe");
            entityManager.persist(novel);
            entityManager.persist(comicBook);
            entityManager.persist(science);
            entityManager.persist(selfImprovement);
            entityManager.persist(translationBook);



            Book bookCategory=entityManager.find(Book.class,1);
            List<Category> categories=new ArrayList<>();
            categories.add(novel);
            categories.add(translationBook);
            book.setCategoryList(categories);
            entityManager.persist(bookCategory);

            Book bookCategory2=entityManager.find(Book.class,2);
            List<Category> categories2=new ArrayList<>();
            categories2.add(selfImprovement);
            categories2.add(translationBook);
            book2.setCategoryList(categories2);
            entityManager.persist(bookCategory2);



            transaction.commit();
        }
    }

