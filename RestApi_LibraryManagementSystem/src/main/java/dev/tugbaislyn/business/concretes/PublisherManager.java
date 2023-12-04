package dev.tugbaislyn.business.concretes;

import dev.tugbaislyn.business.abstracts.IPublisherService;
import dev.tugbaislyn.dao.PublisherRepo;
import dev.tugbaislyn.entities.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PublisherManager implements IPublisherService {
    private  final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        //veritabanında, kaydedilmek istenen isim ve kuruluş yılı bilgisinin olup olmama durumunu  isPublisherexist nesnesine yolluyoruz.
        Optional<Publisher> isPublisherExist=this.publisherRepo.findByNameAndEstablishmentYear(publisher.getName(),publisher.getEstablishmentYear());

        if (isPublisherExist.isPresent()){ // Aynı veriler varsa
            throw  new RuntimeException("Sistemde bu bilgiler zaten kayıtlıdır, tekrar kaydedilemez!");
        }else {//yoksa kaydeder.
            return this.publisherRepo.save(publisher);
        }
    }

    @Override
    public Publisher getById(Long id) {
        return this.publisherRepo.findById(id).orElseThrow(()->new RuntimeException("Bu id ye ait yayınevi bulunamadı!"));
    }

    @Override
    public Publisher update(Publisher publisher) {
        // Burada id var mı diye kontrol etmiyorum çünkü yoksa getById metodundaki throw mesajını veriyor olacak. Conrollerda bu metodu çağırdığım için.
       //Güncellerken Aynı yayınevi ismi ve kuruluş tarihi sistemde kayıtlıysa isPublisherExist e aktarılır yoksa isPublisherExist boş olur.
        Optional<Publisher> isPublisherExist=this.publisherRepo.findByNameAndEstablishmentYear(publisher.getName(),publisher.getEstablishmentYear());

       if (isPublisherExist.isPresent()){//Daha önceden aynı yayınevi ismi ve kuruluş tarihi kayıtlıysa hata mesajı verir.
           throw  new RuntimeException("Sistemde bu bilgilere sahip yayınevi zaten kayıtlıdır!");
       }else{
           return  this.publisherRepo.save(publisher);
       }

    }

    @Override
    public void delete(Long id) {
       // Optional<Publisher> publisherDB=this.publisherRepo.findById(id); // id veri tabanında varsa  veriler publisherDB nesnesine aktarılır
        Publisher publisher=this.publisherRepo.findById(id).orElseThrow(()-> new RuntimeException("Sistemde böyle bir id bulunamadı!"));
        //if (publisherDB.isPresent()){
            this.publisherRepo.delete(publisher);
        //}
    }

    @Override
    public List<Publisher> findAll() {
        return this.publisherRepo.findAll();
    }
}
