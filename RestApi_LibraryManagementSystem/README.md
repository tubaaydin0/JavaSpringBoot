KÜTÜPHANE YÖNETİM SİSTEMİ
==========================
Bu proje, CRUD işlemlerini gerçekleştiren Spring Boot ile yapılmış bir Rest Api'dir. 
Katmanlı mimari yapısı kullanılarak aşağıdaki teknolojilerden faydalanılmıştır.   
-------------------------------------------------------------------------------------  
Spring Web, Spring Boot Dev Tools, PostgreSql, Spring Data Jpa, Lombok, ModelMapper,Validation  

ENDPOINTS:
-----------  
API'ye ait kullanılan endpointler aşağıda listelenmiştir. 
  
1-Author:
-------
HTTP METODU | ENDPOINT | AÇIKLAMASI
--- | --- | ---
**GET** | `/v1/lms/author` | Kayıtlı tüm yazarları listeler.
**GET** | `/v1/lms/author/{id}` | Kayıtlı ID'ye ait yazarları listeler.
**POST** | `/v1/lms/author` | Yeni yazar ekler.
**PUT** | `/v1/lms/author` | ID'ye göre yazar bilgilerini günceller.
**DELETE** | `/v1/lms/author/{id}` | ID'ye göre yazar bilgilerini siler.  

  2-Publisher:
  -----------  
  HTTP METODU | ENDPOINT | AÇIKLAMASI
--- | --- | ---
**GET** | `/v1/lms/publisher` | Kayıtlı tüm yayınevlerini listeler.
**GET** | `/v1/lms/publisher/{id}` | Kayıtlı ID'ye ait yayınevleri listeler.
**POST** | `/v1/lms/publisher` | Yeni yayınevi ekler.
**PUT** | `/v1/lms/publisher` | ID'ye göre yayınevi bilgilerini günceller.
**DELETE** | `/v1/lms/publisher/{id}` | ID'ye göre yayınevi bilgilerini siler.   

  3-Category: 
  ----------
  HTTP METODU | ENDPOINT | AÇIKLAMASI
--- | --- | ---
**GET** | `/v1/lms/category` | Kayıtlı tüm kategorileri listeler.
**GET** | `/v1/lms/category/{id}` | Kayıtlı ID'ye ait kategorileri listeler.
**POST** | `/v1/lms/category` | Yeni kategori ekler.
**PUT** | `/v1/lms/category` | ID'ye göre kategori bilgilerini günceller.
**DELETE** | `/v1/lms/category/{id}` | ID'ye göre kategori bilgilerini siler.   
  
4-Book:  
-------
  HTTP METODU | ENDPOINT | AÇIKLAMASI
--- | --- | ---
**GET** | `/v1/lms/book` | Kayıtlı tüm kitapları listeler.
**GET** | `/v1/lms/book/{id}` | Kayıtlı ID'ye ait kitapları listeler.
**POST** | `/v1/lms/book` | Yeni kitap ekler.
**PUT** | `/v1/lms/book` | ID'ye göre kitap bilgilerini günceller.
**DELETE** | `/v1/lms/book/{id}` | ID'ye göre kitap bilgilerini siler.    

5-BookBorrowing:
----------------
  HTTP METODU | ENDPOINT | AÇIKLAMASI
--- | --- | ---
**GET** | `/v1/lms/bookborrowing` | Kayıtlı tüm ödünç alma işlemlerini listeler.
**GET** | `/v1/lms/bookborrowing/{id}` | Kayıtlı ID'ye ait ödünç alma işlemlerini listeler.
**POST** | `/v1/lms/bookborrowing` | Yeni ödünç alma işlemi ekler.
**PUT** | `/v1/lms/bookborrowing` | ID'ye göre ödünç alma bilgilerini günceller.
**DELETE** | `/v1/lms/bookborrowing/{id}` | ID'ye göre ödünç alma bilgilerini siler.   

**Projenin CRUD işlemleri dışında dikkat edilmesi gereken isterleri:**
----------------------------------------------------------------------
1. Kategori silme işleminde metodu void olarak değil String olarak tanımlayarak,
Eğer silinecek kategoriye ait sistemde kayıtlı kitap varsa silme işleminin gerçekleşmemesi 
ve “Bu kategoriye ait kitap var. Bu kategori silinemedi.” şeklinde dönüş verilmesi.

2. BookBorrowing tablosuna yeni kayıt yapılırken (bir kitabın ödünç verilme senaryosu) ödünç verilen kitabın stok kontrolünün yapılması.



