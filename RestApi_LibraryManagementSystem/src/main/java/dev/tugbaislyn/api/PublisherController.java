package dev.tugbaislyn.api;

import dev.tugbaislyn.business.abstracts.IPublisherService;
import dev.tugbaislyn.dto.request.PublisherSaveRequest;
import dev.tugbaislyn.dto.request.PublisherUpdateRequest;
import dev.tugbaislyn.dto.response.PublisherResponse;
import dev.tugbaislyn.entities.Publisher;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/lms")
public class PublisherController {
    private final IPublisherService publisherService;
    private  final ModelMapper modelMapper;

    public PublisherController(IPublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    //CREATE İŞLEMİ: Veri gönderme işleminde veriler publisher nesnesi halinde tutuldukları için kullanılan metotların türü de publisher olacak.
    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher save(@RequestBody PublisherSaveRequest publisherSaveRequest){// jsondan olarak gelen veriler Publisher formatına çevrildi
        return this.publisherService.save(this.modelMapper.map(publisherSaveRequest,Publisher.class));// bodyden gelen veriler publisher nesnesine çevrildi.
    }

    //READ İŞLEMİ: Veri alma işlemi olurken publisherResponse formatında veri listeleyeceğimiz için kullanılan metotların türü publisherResponse türünde oluyor.
    //1. Idye göre listele
    @GetMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PublisherResponse findById(@PathVariable ("id") Long id){ // Listeleme işlemi PublisherResponse formatında olacak yani address bilgisinin görünmemesi gerekiyor.
        return this.modelMapper.map(this.publisherService.getById(id), PublisherResponse.class); // veritabanından idye göre gelen verileri PublisherResponse.class formatına çevirdik.

    }

    //1.Hepsini listeleme

    @GetMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    public List<PublisherResponse> findAll(){
        List<PublisherResponse> publisherResponseList=this.publisherService.findAll().stream().map(
                publisher -> this.modelMapper.map(publisher,PublisherResponse.class)
        ).collect(Collectors.toList());// Veritabanından çekilen publisher verileri PublisherResponse formatına çevirip bir listede tuttuk.Sonra bu listeyi publisherResponseList'e aktardık.

        return  publisherResponseList;
    }


    //UPDATE İŞLEMİ: Veri gönderirken metotların türü Publisher olacak.
    @PutMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    public Publisher update(@RequestBody PublisherUpdateRequest publisherUpdateRequest){
        Publisher updatedPublisher=this.publisherService.getById(publisherUpdateRequest.getId());//idsi girilen verinin bilgileri veritabanından  updatedAuthora gönderildi.Eğer veritabanında olmayan bir id girersem bulamayacağı için hata verecek.
        updatedPublisher.setName(publisherUpdateRequest.getName());
        updatedPublisher.setEstablishmentYear(publisherUpdateRequest.getEstablishmentYear());
        updatedPublisher.setAddress(publisherUpdateRequest.getAddress());
        return this.publisherService.update(updatedPublisher);
    }

    //DELETE İŞLEMİ
    @DeleteMapping("/publisher/{id}")
    public void delete(@PathVariable ("id") Long id){
        this.publisherService.delete(id);
    }



}
