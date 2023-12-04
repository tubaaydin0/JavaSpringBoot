package dev.tugbaislyn.business.concretes;

import dev.tugbaislyn.business.abstracts.IBookService;
import dev.tugbaislyn.business.abstracts.ICategoryService;
import dev.tugbaislyn.dao.CategoryRepo;
import dev.tugbaislyn.entities.Book;
import dev.tugbaislyn.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryManager implements ICategoryService {
    //Depency injection
    private final CategoryRepo categoryRepo;
    private final IBookService bookService;

    public CategoryManager(CategoryRepo categoryRepo,IBookService bookService) {
        this.categoryRepo = categoryRepo;
        this.bookService = bookService;
        //Delete işlemide kategorye ait kitaplar var mı kontrolünü yapmak için.

    }


    @Override
    public Category save(Category category) {
        //Kategori adı sistemde varsa tekrar aynı ismi kaydemesin.Var mı diye Optional ile kontrol ediyoruz.
        Optional<Category> isCategoryNameExist = this.categoryRepo.findByName(category.getName());
        if (isCategoryNameExist.isPresent()) {
            throw new RuntimeException("Sistemde bu kategori ismi mevcuttur. Tekrar kayıt edilemez!");
        } else {
            return this.categoryRepo.save(category);
        }
    }

    @Override
    public Category getById(Long id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Sistemde böyle bir id bulunamamıştır!"));
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepo.findAll();
    }

    @Override
    public Category update(Category category) {
        //Dbde girilen id varsa bulunur
        Optional<Category> categoryIdDB = this.categoryRepo.findById(category.getId());
        //Dbde güncellenmek istenen kategori ismi var mı bakılır
        Optional<Category> isCategoryNameExist = this.categoryRepo.findByName(category.getName());

        if (categoryIdDB.isEmpty()) {
            throw new RuntimeException("Sistemde kayıtlı böyle bir id bulunamadı!");
        }
        if (isCategoryNameExist.isPresent()) { // Kayıtlı kategori adı varsa
            throw new RuntimeException("Bu kategori ismi zaten kayıtlıdır. Tekrar kaydedilemez!");
        } else {
            return this.categoryRepo.save(category);
        }

    }

    /* Kategori silme işleminde metodu void olarak değil String olarak tanımlayın.
  Eğer silinecek kategoriye ait sistemde kayıtlı kitap varsa silme işlemi gerçekleşmesin, bunu kontrol edin ve
   “Bu kategoriye ait kitap var. Bu kategori silinemedi.” şeklinde dönüş versin.
   */
    @Override
    public String delete(Long id) {
        //Önce gelen id sistemde var mı ona bakalım
        Optional<Category> categoryIdDB = this.categoryRepo.findById(id);

        //Kategorinin kitabı var mı kontrol etmek için BookRepoda findByCategoryId metodu oluşturmalıyız.
        //Bir kategorinin birden fazla kitabı olabilir bu yüzden bir kategoriye karşılı gelen kitaları liste olarak almalıyız.
        // Sonra buradaki id book sisteminde kayıtlıysa bookLisstin içine atmalıyız.
        //Eğer bookListte eleman varsa kategoriyi silmeyeceğiz.
        List<Book> bookList = this.bookService.findByCategoryId(id);
        if (categoryIdDB.isEmpty()) {
            throw new RuntimeException("Sistemde kayıtlı böyle bir id bulunamadı!");
        } else {

            if (bookList.isEmpty()) {
                this.categoryRepo.delete(categoryIdDB.get());
                return "İşlem Başarılı";
            } else {
                return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
            }

        }
    }
}
