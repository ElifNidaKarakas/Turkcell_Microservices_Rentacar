## <p align="center"> Pair 4:Elif Nida Karakaş,Halef Budanur,Ahmet Çetiner,Baran Büyük,Hande Arslan,Yavuz Selim Özbey, Fuat Hüriyetoğlu
 </p>

<b>Proje Çalışması 1</b>

#Projenize aşağıdaki servisleri base yapıda ekleyiniz; +

Keycloak

Gateway

Eureka (Service Discovery)

Rental Service & PostgreSql

Car Service MongoDb

docker-compose.yml

#İlk faz için aşağıdaki özelliklerin uygulamanıza entegrasyonu beklenmektedir:

Arabaların marka,model,renk,model yılı, günlük ücret ve resim bilgisi tutulmalıdır. +

Arabalar listelenebilmelidir. +

Arabanın durumu o an rental servisle haberleşerek "kiralamaya uygun" veya "değil" şeklinde listeleme endpointinde gösterilmelidir. +

Sisteme giriş yapan kullanıcılar tarafından araba eklenebilmelidir (şu anlık rol kontrolü gerekmiyor) +

#Araba servisinde aşağıdaki 5 endpoint bulunmalıdır; +

GetAll

GetById

Add

Update

Delete

<b>Proje Çalışması 2</b>

Uygulamanıza araba resimleri yüklenebilmelidir.
Bu yapı için bir CDN servis kullanılmalıdır.Önerim "Cloudinary" sisteminin bedava olan yapısıdır ama dilerseniz diğer CDN sistemlerini de
kullanabilirsiniz. Spring'de çok basit bir implementasyonu var.
Yüklenen resimlerin url'leri yeni bir CarImages tablosunda tutulmalıdır.
(Her arabanın birden fazla resmi olabilir) +

CustomerService'i oluşturalım. +

Bu servis arkasında PostgreSql kullanan (ayrı bir db) ve müşteri bilgilerimizi tutacak servis olacak.+

#Temel olarak şuanlık aşağıdaki görevleri yerine getirmesini bekliyoruz; +

Kayıt olma +

Müşteri servisi ile rental servisi arasına sync communacation için altyapıyı şimdiden hazırlayalım. +

RentalService ile NotificationService arasındaki async communication için gerekli kafka konfigürasyonları yapalım. +

RentalService içerisinde bir adet kiralama fonksiyonu oluşturalım +

ve bu fonksiyon NotificationService'i async bir şekilde uyarsın.  +

NotificationService içerisinde uyarılan fonksiyonda mail ile bildirim gönderme simülasyonu yapalım. (Gerçekten mail gönderilmesine gerek yok simülasyon) +

<b>Proje Çalışması 3</b>

Rental service'iniz aşağıdaki özellikleri sağlayarak kiralama verisini veritabanına ekleme işlevini sağlamalıdır;

Kiralama yapılırken sync bir şekilde car serviceden arabanın durumu kontrol edilmeli +

Yine sync bir şekilde kiralama için fiyatın CustomerService'den customer tablosunda yeterli bakiye olup olmadığı kontrol edilsin +
(tabloda basitce bir alan tutabilirsin customer servicede)

Şu anlık CustomerId'yi kiralama yaparken giden bilgide manual gönderebilirsiniz. +