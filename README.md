## <p align="center"> Pair 4:Elif Nida Karakaş,Halef Budanur,Ahmet Çetiner,Baran Büyük,Hande Arslan,Yavuz Selim Özbey, Fuat Hüriyetoğlu
 </p>

Proje Çalışması 2
Uygulamanıza araba resimleri yüklenebilmelidir. 
Bu yapı için bir CDN servis kullanılmalıdır. 
Önerim "Cloudinary" sisteminin bedava olan yapısıdır ama dilerseniz diğer CDN sistemlerini de 
kullanabilirsiniz. Spring'de çok basit bir implementasyonu var.
Yüklenen resimlerin url'leri yeni bir CarImages tablosunda tutulmalıdır. 
(Her arabanın birden fazla resmi olabilir)


CustomerService'i oluşturalım. +
Bu servis arkasında PostgreSql kullanan (ayrı bir db) ve müşteri bilgilerimizi tutacak servis olacak. 
Temel olarak şuanlık aşağıdaki görevleri yerine getirmesini bekliyoruz; +

Kayıt olma +
Müşteri servisi ile rental servisi arasına 
sync communacation için altyapıyı şimdiden hazırlayalım. +


RentalService ile NotificationService arasındaki async communication için gerekli kafka
konfigürasyonları yapalım. +
RentalService içerisinde bir adet kiralama fonksiyonu oluşturalım + 
ve bu fonksiyon NotificationService'i async bir şekilde uyarsın.  +

NotificationService içerisinde uyarılan fonksiyonda mail ile bildirim 
gönderme simülasyonu yapalım. (Gerçekten mail gönderilmesine gerek yok simülasyon) +