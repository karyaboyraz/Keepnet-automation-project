# Keepnet Labs Test Otomasyon Projesi

Bu proje, web uygulamalarının otomatik olarak test edilmesini sağlamak amacıyla geliştirilmiştir. Gauge, Selenium ve Java kullanılarak hazırlanan bu proje, kullanıcıların test senaryolarını kolayca yazmasına ve koşturmasına olanak tanır. Web tarayıcılarının otomasyonu sayesinde uygulamanın çeşitli fonksiyonlarının doğru şekilde çalıştığını doğrulamak için kullanılır. Bu proje, modern yazılım geliştirme süreçlerinde test otomasyonunun gerekliliğini karşılayacak şekilde tasarlanmıştır ve geliştiricilere yüksek kaliteli yazılım üretimi sürecinde yardımcı olmayı hedeflemektedir.

## Projedeki Sınıflar ve İşlevleri

- **BaseSteps**: Bu sınıf, test senaryolarında kullanılan adımları tanımlar ve gerçekleştirir. Web uygulaması üzerindeki belirli elementlerin kontrol edilmesi, varlıklarının doğrulanması, üzerine tıklama, veri girme gibi işlemler gerçekleştirilir. `createWebElement` metodu, verilen element adına göre ilgili WebElement'i oluşturur. `MFACode` metodu, iki faktörlü kimlik doğrulama kodunu girer. `clickButton`, `clickIconButton`, `clickElement`, `clickMenuButton`, `clickTabButton`, ve `clickCheckboxOnTable` gibi metotlar sayesinde belirli UI öğeleri üzerinde tıklama işlemleri yapılabilir. `goToUrl` metodu, belirtilen URL'ye gitmeyi sağlar. `writeTextToInputArea` metodu ise belirli bir alana metin yazmak için kullanılır. Bu sınıf, web uygulaması üzerinde etkileşim sağlamak ve gerekli adımları gerçekleştirmek amacıyla tasarlanmıştır.


- **TwoFactorAuthHelper**: Bu sınıf, iki faktörlü kimlik doğrulama (2FA) işlemleri için Google Authenticator kullanılarak geçerli bir TOTP (Time-based One-Time Password) kodu oluşturur. `generateCurrentNumber` yöntemi ile sağlanan gizli anahtar (secret key) kullanılarak geçerli bir TOTP kodu üretilebilir. Bu sınıf, testlerde veya manuel doğrulama işlemlerinde kullanılmak üzere tasarlanmıştır.


- **Faker**: Bu sınıf, testler sırasında kullanılacak sahte kullanıcı verilerini oluşturmak için kullanılır. `generateUser` metodu ile rastgele isim, soyisim, e-posta adresi, şirket unvanı ve diğer bilgileri içeren bir kullanıcı nesnesi oluşturulur. Ayrıca, `generateUserCSV` metodu ile oluşturulan bu kullanıcı verileri CSV dosyası olarak kaydedilir. Bu, testler sırasında farklı kullanıcı profilleri ile senaryoları denemek için oldukça faydalıdır.


- **DriverFactory**: Bu sınıf, Selenium WebDriver örneklerini yönetmek için kullanılır. `initialize` metodu, ChromeDriver kullanarak yeni bir WebDriver örneği oluşturur ve tarayıcı penceresini maksimum boyuta getirir. `getDriver` metodu, mevcut WebDriver örneğini döndürürken `quitDriver` metodu mevcut WebDriver örneğini kapatır ve bellekten temizler. Bu sınıf, tarayıcı oturumlarının verimli bir şekilde yönetilmesini sağlamak amacıyla geliştirilmiştir.


- **BaseDriver**: Bu sınıf, her bir test senaryosu ve test serisi öncesinde ve sonrasında çalışacak işlemleri yönetmek için kullanılır. `@BeforeSuite` anotasyonu ile `faker` metodu, tüm testlerden önce sahte kullanıcı verisi oluşturmak için çalıştırılır. `@BeforeScenario` anotasyonu ile `setUp` metodu her testten önce WebDriver'ı başlatırken, `@AfterScenario` anotasyonu ile `tearDown` metodu her testin ardından WebDriver'ı kapatır. Bu yapı, test senaryolarının bağımsız ve temiz bir ortamda çalışmasını sağlar. Bu sınıf, aynı zamanda `Faker` sınıfını kullanarak testlerin her birinin başlangıcında gerekli verilerin oluşturulmasını sağlar ve böylece farklı kullanıcı verileri ile testler yürütülür.


## Proje Mimarisi

Bu projede **Dinamik Page Object Model (POM)** kullanılan bir mimari benimsenmiştir. Page Object Model, kullanıcı arayüzü bileşenlerini (sayfalar veya bileşenler) nesneler olarak tanımlayan bir tasarım desenidir. Dinamik POM, bu yapının esnek ve yeniden kullanılabilir olmasını sağlamak amacıyla geliştirilmiş bir yaklaşımdır. Bu model sayesinde web elementleri dinamik olarak bulunur ve test senaryolarında kullanılabilir. Örneğin, bir giriş formundaki kullanıcı adı ve şifre alanlarını temsil eden nesneler dinamik olarak belirlenip, farklı sayfalarda veya farklı senaryolarda aynı yapı kullanılarak tekrar kullanılabilir. Bu sayede, hem bakım kolaylığı sağlanır hem de test senaryoları daha modüler hale gelir.

> NOT: Bu proje, bir örnek çalışma olması nedeniyle çoğu metodda kullanılan yapı basit ve anlaşılır olacak şekilde tasarlanmıştır. Projenin başlangıç aşamasında karmaşık bir altyapı gereksinimi ve zaman kısıtı nedeniyle temel bir yapı tercih edilmiştir. Gelecek aşamalarda ise benzer bir mimari kullanılarak daha modüler bir tasarıma geçiş yapılacaktır.

### Dinamik Page Object Model'in Avantajları

1. **Bakım Kolaylığı**: POM, kullanıcı arayüzü elemanlarını merkezileştirerek ve yeniden kullanılabilir hale getirerek bakım kolaylığı sağlar. Arayüzde bir değişiklik yapıldığında, yalnızca ilgili Page sınıfında güncelleme yapmak yeterlidir, böylece test senaryolarında değişiklik gerekmez.


2. **Kod Tekrarını Azaltma**: Aynı web elementlerine birden fazla testte erişilmesi durumunda kod tekrarını ortadan kaldırır. Bu sayede test senaryoları daha temiz ve anlaşılır hale gelir.


3. **Dinamik Kullanım**: Dinamik POM ile, kullanıcı arayüzündeki değişikliklere karşı daha dayanıklı testler yazılabilir. Örneğin, elementlerin `xpath` veya `CSS` yolunu dinamik olarak belirlemek, özellikle elementlerin konumlarının veya niteliklerinin değişmesi durumunda testlerin stabil kalmasına olanak tanır.


4. **Modüler Yapı**: Proje, bileşen bazında modüler bir yapıya sahiptir. Her sayfa veya bileşen bir sınıf olarak tanımlanır ve bu sınıflar gerektiğinde bir araya getirilerek test senaryoları oluşturulur. Bu yapı, projeyi büyük bir ekiple yönetmeyi ve geliştirmeyi kolaylaştırır.


## Gereksinimler

- **Java 21**: Proje için gerekli olan Java versiyonu.
- **Maven**: Proje bağımlılıklarını yönetmek için kullanılır.
- **Gauge**: Test senaryolarını koşturmak için gerekli olan aracın yüklenmesi gerekmektedir.
- **Selenium WebDriver**: Tarayıcı otomasyonu için gerekli.

### Bağımlılıkları Yükleme

Aşağıdaki komut, `pom.xml` dosyasında tanımlı olan tüm bağımlılıkları yükler ve projeyi derler:

```sh
mvn clean install
```

## Kurulum

1. **Projeyi Klonlayın**: GitHub üzerindeki depo adresine gidin ve `git clone` komutunu kullanarak projeyi yerel makinenize indirin.

   ```sh
   git clone https://github.com/karyaboyraz/Keepnet-automation-project.git
   ```

2. **Proje Dizini**: Klonlama tamamlandıktan sonra proje dizinine geçiş yapın:

   ```sh
   cd keepNetCase
   ```

3. **Bağımlılıkları Yükleyin ve Derleyin**: Proje bağımlılıklarını yüklemek ve projeyi derlemek için Maven kullanın:

   ```sh
   mvn clean install
   ```

   Bu komut, proje dizinini temizler, gerekli bağımlılıkları indirir ve projeyi derler.

4. **Gauge Eklentilerini Yükleyin**: Gauge test senaryolarını çalıştırmak için gerekli eklentileri yükleyin:

   ```sh
   gauge install java
   ```

   Bu komut, Gauge eklentisini kurarak testlerinizi çalıştırmanız için gerekli ortamı hazırlar.

## Maven Bağımlılıkları

`pom.xml` dosyasında kullanılan başlıca bağımlılıklar şunlardır:

- **Selenium**: Web tarayıcı otomasyonu için (`version: 4.24.0`)
- **WebDriverManager**: WebDriver yönetimi için (`version: 5.9.2`)
- **TestNG**: Test koşum framework'ü (`version: 7.10.2`)
- **Gauge Java**: Gauge entegrasyonu (`version: 0.11.1`)
- **AssertJ**: Daha okunabilir assertion'lar için (`version: 3.26.3`)
- **Reflections**: Dinamik sınıf tarama için (`version: 0.10.2`)
- **JavaFaker**: Test verisi oluşturmak için (`version: 1.0.2`)
- **Rest-Assured**: API testi için (`version: 5.4.0`)

## Yazarlar

- **Karya Boyraz** - Proje geliştiricisi - [GitHub Profilim](https://github.com/karyaboyraz)

## Potansiyel Sorular

1. **Hangi tarayıcılar destekleniyor?**

    - Proje, Chrome, Firefox ve Edge tarayıcılarını destekleyecek altyapıya sahiptir. Ancak, case çalışmasında yalnızca Chrome optimizasyonu yapılmıştır.

2. **Hangi Java sürümünü kullanmalıyım?**

    - Proje, Java 21 ile uyumludur. Bu nedenle, Java 21 veya üzeri bir sürüm kullanmanız önerilir. Daha eski sürümler uyumluluk sorunlarına yol açabilir.

3. **Testler için hangi araçlar kullanılıyor?**

    - Projede Selenium, Gauge, TestNG ve JavaFaker gibi araçlar kullanılarak otomasyon sağlanmaktadır. Selenium tarayıcı otomasyonu için, Gauge test senaryolarını yönetmek için, TestNG ise test koşumlarını organize etmek için kullanılır. JavaFaker ile sahte kullanıcı verileri üreterek test senaryolarının zenginleştirilmesi sağlanır.

4. **JavaFaker kütüphanesinin Gauge entegrasyonu neden önemlidir?**

    - Projede kullanılan BDD yapısının dinamik olması ve tek bir sınıf ile kontrol edilebilmesi, hem projenin ilerleyen aşamalarında yapılacak değişikliklerin dinamik olmasını sağlar hem de kullanılan veri setlerinde rastgele üretilen veriler ile gerçek kullanıcı deneyimine daha çok yaklaşılmasını sağlar.

5. **Proje dosyalarının yapısı nasıldır?**

    - Proje dosyaları, test senaryolarının ve Page Object Model (POM) yapılandırmasının düzenli bir şekilde yönetilmesi amacıyla belirli dizinlerde organize edilmiştir. `src/` dizininde temel kaynak kodları, sayfa sınıfları ve yardımcı sınıflar bulunur. `specs/` dizininde Gauge senaryoları ve adım tanımlamaları yer alır. `reports/` dizininde, testlerin sonucuna dair raporlar HTML ve XML formatında oluşturulmuş şekilde bulunur. `lib/` dizininde ise projede kullanılan harici kütüphaneler ve bağımlılıklar yer almaktadır. Bu yapı, projedeki her bir dosyanın ve dizinin işlevini açıkça belirler ve yönetimi kolaylaştırır.

6. **Page Object Model'in projedeki rolü nedir?**

    - Projede kullanılan Dinamik Page Object Model, kullanıcı arayüzü bileşenlerinin kod tekrarını azaltarak daha verimli ve esnek bir test otomasyon süreci sunar. Bu model, elementlerin konumlarının veya niteliklerinin değişmesi durumunda yalnızca ilgili Page sınıfında yapılan güncellemelerle testlerin stabil kalmasını sağlar.

7. **Testlerin nasıl çalıştırılması gerekiyor?**

    - Testleri çalıştırmak için `gauge run specs` komutunu kullanabilirsiniz. Bu komut, Gauge ile yazılmış olan senaryoları çalıştırır ve test sonuçlarını `reports/` dizininde görüntüleyebilirsiniz.

8. **Farklı test senaryoları nasıl oluşturabilirim?**

    - Yeni bir test senaryosu oluşturmak için `specs/` dizinindeki mevcut senaryolara benzer şekilde yeni `.spec` dosyaları ekleyebilir ve bu dosyalarda adımları tanımlayabilirsiniz. Adımları yazarken POM yapısını kullanarak `BaseSteps` sınıfındaki metotları çağırabilirsiniz.

9. **Proje hangi modüler yapılara sahiptir?**

    - Proje, bileşen bazında modüler bir yapıya sahiptir. Her sayfa veya bileşen bir sınıf olarak tanımlanmıştır ve bu sınıflar gerektiğinde bir araya getirilerek test senaryoları oluşturulmaktadır. Bu modüler yapı, projeyi büyük bir ekiple yönetmeyi ve geliştirmeyi kolaylaştırır.