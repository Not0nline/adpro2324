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
