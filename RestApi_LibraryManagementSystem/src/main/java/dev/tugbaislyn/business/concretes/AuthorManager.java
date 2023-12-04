package dev.tugbaislyn.business.concretes;

import dev.tugbaislyn.business.abstracts.IAuthorService;
import dev.tugbaislyn.dao.AuthorRepo;
import dev.tugbaislyn.entities.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorManager implements IAuthorService { // Servis implement edildi.

    //Depency Injection
    private final AuthorRepo authorRepo;


    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;

    }


    @Override
    public Author getById(Long id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new RuntimeException(id+" id nolu yazar bilgisi bulunamadı!"));
    }

    @Override
    public Author save(Author author) {

        //Veritabanında jsondan gelen author bilgilerini isAuthorexist değişkenine atadık.
        Optional<Author> isAuthorExist=this.authorRepo.findByNameAndBirthDateAndCountry(author.getName(),author.getBirthDate(),author.getCountry());
       //isAuthorExist in içi doluysa yani veritabanında bu veri varsa
        if (isAuthorExist.isPresent()){
            throw new RuntimeException("Sistemde bu yazar bulunmaktadır. Tekrar aynı yazar bilgileri girilemez!");
        }else{ //Yoksa kaydetme işlemi yapar.
            return this.authorRepo.save(author);
        }

    }

    @Override
    public Author update(Author author) {
        Optional<Author> authorDB = authorRepo.findById(author.getId()); // jsondan gelen id bilgisi veritabanında var mı kontrolü yapılır.
        Optional<Author> isAuthorExist = authorRepo.findByNameAndBirthDateAndCountry(author.getName(), author.getBirthDate(), author.getCountry());// Sistemde jsondan gelen veriler varsa bunu isAuthorExiste atar.

        if (authorDB.isPresent()) { // veritabanında id kayıtlıysa
            if (isAuthorExist.isPresent()) { // kişi bilgileri varsa
                throw new RuntimeException("Sistemde bu yazar bilgileri zaten kayıtlıdır."); //Aynı verilerin girilmesi engellendi.
            } else {// id kayıtlı ama içerisindeki bilgiler farklıysa günceller.
                return this.authorRepo.save(author);
            }
        } else {
        throw new RuntimeException("");
        }

    }

   @Override
    public void delete(Long id) {
        Optional<Author> authorDB=this.authorRepo.findById(id);// dışardan gelen id veritabaında varsa onun bilgileri authorDB nesnesine aktarılır.
       if (authorDB.isPresent()) {
           this.authorRepo.delete(authorDB.get()); // idye ait authorDB nesnesi silindi.
       }

    }



    @Override
    public List<Author> findAll() {
        return this.authorRepo.findAll();
    }
}
