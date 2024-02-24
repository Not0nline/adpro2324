<h1>Week 1</h1>
<h3>Reflection 1</h3>

- Clean Code menurut saya ketika code:
1. hanya melakukan 1 fungsi
2. tidak mendampak terhadap objek lain
3. effisien tapi mudah dibaca
4. tidak memerlukan komen yang banyak
5. tidak memberi return "null"


<h3>Reflection 2</h3>

1. menurut saya test yang bagus tidak penting berapa banyaknya asalkan kita melakukan 100% coverage dan error-error dan edge-case yang mungkin terjadi sehingga cover semua scenario. Kalau sudah terjadi maka test cukup untuk melakukan semua coverage

2. menurut saya, kodenya tidak akan rapih terutama karena 2 fungsi yang kompleks tidak bisa dibuat persis sama (kalaupun bisa, lebih baik digabung menjadi 1 fungsi saja), dan karena melakukan copy pasta, maka program juga tidak keliatan bagus

<h1>Week 2</h1>

1. Code quality issue(s) yang diberarkan: 
- test semua fungsi yang ada pada code agar coverage 100%
- hanya melakukan test secukupnya
- penyesuaian nama String
- penyesuaian nama fungsi

2. Saya rasa, implementasi yang telah dilakukan sudah sesuai dengan definisi CI/CD. Alasannya adalah, setiap kali ada commit baru yang di push ke branch tertentu, GitHub akan menjalankan Actions untuk workflow yang ada di branch tersebut, seperti PMD dan CI untuk module 2 ini. Untuk branch main, ada workflow tambahan yaitu scorecard dan deployment melalui koyeb. Jika ada branch yang di merge ke main, maka GitHub Actions akan mengulangi workflow yang ada di main sehingga setiap perubahan pada commit akan selalu di verifikasi secara otomatis oleh GitHub Actions. Selain itu, untuk deployment dari web juga sudah di atur agar dapat redeploy secara otomatis setiap ada perubahan pada branch main. Karena banyak proses yang di otomatisasi, maka saya rasa definisi CI/CD untuk module 2 ini sudah terpenuhi.

<h1>Week 3</h1>

1. SOLID principles that i have applied in my code: <br>
- Single Responsibility Principle: memisahkan class Controller menjadi file-file yang berbeda, satu file untuk satu class, agar file Program hanya jadi controller untuk mapping path website. <br>
- Interface Segregation Principle: memisahkan interface class untuk CarService dan ProductService, agar satu class hanya punya method yang relevan dan sesuai dengan kelasnya. <br>
- Dependency Inversion Principle: memakai AutoWired untuk CarController dan ProductController ke CarService/ProductService, bukan ke class implementasinya, agar perubahan service tidak bikin bug karena controller cuma tergantung dari interface classnya.
<br>

2. The benefits of using SOLID principles in my code:
- Lebih mudah untuk memodifikasi class karena dependensi lebih sedikit.<br>
  Contoh: memakai interface atau abstract class sebagai tipe data, bukan class implementasinya. Ini prinsip Dependency Inversion.<br>
- Lebih mudah untuk dimaintain karena tidak mengubah kode yang sudah ada, hanya menambah fitur baru.<br>
  Contoh: mengextend class yang sudah ada, bukan ubah kodenya. Ini prinsip Open-Closed.<br>
- Lebih mudah untuk dibaca karena class dibagi sesuai fungsi, dan tipe data pakai interface class.<br>
  Contoh: memisahkan class Controller, Service, dan Model, dan pakai interface CarService dan ProductService. Ini prinsip Single Responsibility dan Interface Segregation.

3. The disadvantages of not applying SOLID principles
- Kode susah dimengerti oleh developer baru.<br>
  Contoh: Class Product punya fungsi setter, getter, dan beli product. Ini bikin bingung karena Product harusnya cuma punya fungsi setter dan getter, sedangkan beli product harusnya di class lain. Harusnya pisah class sesuai fungsinya.<br>
- Kode susah dimaintain karena ubah kode yang sudah ada. <br>
  Contoh: Fungsi A butuh return value dari fungsi B, tapi fungsi B diubah jadi return value beda. Ini bikin fungsi A error. Harusnya tambah fitur baru, jangan ubah yang sudah ada.<br>
- Kode susah diuji karena dependensi banyak.<br>
  Contoh: Program pakai banyak class yang saling tergantung. Ini bikin testing repot karena harus mocking banyak class. Harusnya kurangi dependensi, pisah program jadi lebih modular.
