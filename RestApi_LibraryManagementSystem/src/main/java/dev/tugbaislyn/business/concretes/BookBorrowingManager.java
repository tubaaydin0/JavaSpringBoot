package dev.tugbaislyn.business.concretes;

import dev.tugbaislyn.business.abstracts.IBookBorrowingService;
import dev.tugbaislyn.business.abstracts.IBookService;
import dev.tugbaislyn.dao.BookBorrowingRepo;
import dev.tugbaislyn.dto.request.BookBorrowingSaveRequest;
import dev.tugbaislyn.entities.Book;
import dev.tugbaislyn.entities.BookBorrowing;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor//Depency injectionların constructorlaeını oluşturan lombok anatasyonu.
public class BookBorrowingManager implements IBookBorrowingService {

    private final BookBorrowingRepo bookBorrowingRepo;
    private final IBookService bookService;
    private final ModelMapper modelMapper;

    /*
    BookBorrowing tablosuna yeni kayıt yapılırken (bir kitabın ödünç verilme senaryosu) ödünç verilen kitabın
    stok kontrolünü yapmalısın
     */
    @Override
   // public BookBorrowing save(BookBorrowingSaveRequest borrowingSaveRequest) {
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        //BookBorrowing bookBorrowing=modelMapper.map(borrowingSaveRequest,BookBorrowing.class);
        Book book = this.bookService.getById(bookBorrowing.getBook().getId());
       if (book.getStock() <= 0) {
            throw new RuntimeException("Bu kitabın stoğu kalmamıştır!");
        }
        book.setStock(book.getStock() - 1);

       this.bookService.update(book); // book tablosu güncellendi
       return this.bookBorrowingRepo.save(bookBorrowing);

    }
    @Override
    public BookBorrowing getById(Long id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(()-> new RuntimeException("Sistemde böyle bir ID bulunmamaktadır'"));
    }

    @Override
    public List<BookBorrowing> findAll() {
        return this.bookBorrowingRepo.findAll();
    }

    @Override //Burada b_book_id be book null geliyor??? Tekrar Bak!
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        Optional<BookBorrowing> bookBorrowingIdDB=this.bookBorrowingRepo.findById(bookBorrowing.getId()); // Dışardan gelen bookBorrowing in idsi veritabanında var mı bakılır

        if(bookBorrowingIdDB.isPresent()){ // id sistemde varsa
            Book book=bookBorrowingIdDB.get().getBook();
            if (bookBorrowing.getReturnDate() != null) { // Ödünç alınan kitap teslim edildiğinde tarih bilgisi girilir. Bu yüzden kitap stoğunu 1 artırırız.
                book.setStock(book.getStock() + 1);
                this.bookService.update(book); // Book un stok sayısının değişmesiyle book tablosu yenilendi.
            }
            return this.bookBorrowingRepo.save(bookBorrowing);
        }else{
            throw new RuntimeException("Sistemde kayıtlı böyle bir ID bulunamamıştır!");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<BookBorrowing> deleteIdDB=this.bookBorrowingRepo.findById(id);
        if (deleteIdDB.isEmpty()){
            throw new RuntimeException("Sistemde bu ID kayıtlı değildir!");
        }else{
            BookBorrowing bookBorrowing=deleteIdDB.get();
            if (bookBorrowing.getReturnDate()==null){// Eğer ödünç alınan ve teslim edilme tarihi girilmemişse bu ödünç alma işleminin iptal edildiğini gösterir.
                //Bu yüzden de ödünç alınan kitabın stok sayısı 1 artırılır.
                Book book=bookBorrowing.getBook();
                book.setStock(book.getStock()+1);
                this.bookService.update(book); // Kitap tablosu güncellenir.
            }//Değilse ve iften çıkınca  direk silme işlemi yapılır.
            this.bookBorrowingRepo.delete(bookBorrowing);
        }

    }
}
