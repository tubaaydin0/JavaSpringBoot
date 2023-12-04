package dev.tugbaislyn.api;

import dev.tugbaislyn.business.abstracts.IAuthorService;
import dev.tugbaislyn.dto.request.AuthorSaveRequest;
import dev.tugbaislyn.dto.request.AuthorUpdateRequest;
import dev.tugbaislyn.dto.response.AuthorResponse;
import dev.tugbaislyn.entities.Author;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/lms")
public class AuthorController {
    //depency injection
    private final IAuthorService authorService;
    private final  ModelMapper modelMapper;

    public AuthorController(IAuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    //READ İŞLEMİ--- Tüm verileri okur.

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse findById(@PathVariable ("id") Long id){

        return this.modelMapper.map(this.authorService.getById(id), AuthorResponse.class);
    }


    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponse> findAll(){
       List<AuthorResponse> authorResponseList=this.authorService.findAll().stream().map(
               author -> this.modelMapper.map(author,AuthorResponse.class)
       ).collect(Collectors.toList());

       return authorResponseList;
    }

    //CREATE İŞLEMİ--- Yeni veri kaydeder
    @PostMapping("/author")
    @ResponseStatus(HttpStatus.CREATED)
    public  Author  save(@RequestBody AuthorSaveRequest authorSaveRequest){ //bodyden alınan json veriler author formatına çevrilir.
        return this.authorService.save(this.modelMapper.map(authorSaveRequest,Author.class));
    }

    //UPDATE İŞLEMİ--- Verileri günceller

    @PutMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public Author update(@RequestBody AuthorUpdateRequest authorUpdateRequest){ // bodyden alınan veri author formatına çevrilir.
       Author updatedAuthor=this.authorService.getById(authorUpdateRequest.getId());  //idsi girilen verinin bilgileri veritabanından  updatedAuthora gönderildi
       updatedAuthor.setName(authorUpdateRequest.getName());//UpdateAuthordaki bilgiler dışardan girilen bilgilerle değiştirildi.
       updatedAuthor.setBirthDate(authorUpdateRequest.getBirthDate());
       updatedAuthor.setCountry(authorUpdateRequest.getCountry());
       return this.authorService.update(updatedAuthor);
    }


    //DELETE İŞLEMİ---ID'ye göre veri siler.
    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable ("id") Long id){
            this.authorService.delete(id);
    }
}
