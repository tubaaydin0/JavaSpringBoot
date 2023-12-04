package dev.tugbaislyn.business.concretes;

import dev.tugbaislyn.business.abstracts.IBookService;
import dev.tugbaislyn.dao.BookRepo;
import dev.tugbaislyn.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookManager implements IBookService {
    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    @Transactional // Eğer kayıt sırasında bir hatayla karşılaşılırsa tüm işlemler geri alınsın.(rollback)
    public Book save(Book book) {
        //Aynı kitap ismi ve yazar önceden kayıtlı mı kontrol ediliyor.
        Optional<Book> isBookExist=this.bookRepo.findByNameAndAuthor(book.getName(),book.getAuthor());
        if (isBookExist.isPresent()){
            throw new RuntimeException("Girilen  kitap ismi ve yazar bilgisi zaten sisteme kayıtlıdır. Tekrar kaydedilemez!");
        }
        else{
            return this.bookRepo.save(book);
        }
    }

    @Override
    public Book getById(Long id) {
        return this.bookRepo.findById(id).orElseThrow(()->new RuntimeException("Sistemde kayıtlı böyle bir id bulunamadı!"));
    }

    @Override
    public Book update(Book book) {
        Optional<Book> bookIdDB=this.bookRepo.findById(book.getId()); // Kitap idsi var mı
        //Optional<Book> isBookExist=this.bookRepo.findByNameAndAuthor(book.getName(),book.getAuthor());//Aynı kitap kaydeilmemesi için

        if (bookIdDB.isEmpty()){
            throw new RuntimeException("Bu ID sistemde kayıtlı değildir!");
        }else{
            //if (isBookExist.isPresent()){
            //     throw new RuntimeException("Bu bilgiler sistemde bulunmaktadır.Tekrar kaydedilemez!");
            //}
            return this.bookRepo.save(book);
        }
    }

    @Override
    public void delete(Long id) {
        Book book=this.bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Sistemde böyle bir id bulunmamaktadır!"));

        this.bookRepo.delete(book);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepo.findAll();
    }

    @Override
    public List<Book> findByCategoryId(Long id) { //Categori idye sahip kitap listesine erişmek için
        return this.bookRepo.findByCategoryId(id);
    }
}
