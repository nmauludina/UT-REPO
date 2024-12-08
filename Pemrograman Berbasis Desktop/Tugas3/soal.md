# Tugas 3

Tugas Praktik 3 ini memiliki tiga Indikator untuk menilai Hasil Belajar Mahasiswa, yaitu:
1. Mampu mengimplementasikan konsep abstraksi dan/atau inheritance
2. Mampu mengimplementasikan konsep encapsulation, polymorphism, exception, I/O, dan Operasi File
3. Mampu mengimplementasikan materi-materi sebelumnya seperti struktur keputusan, struktur pengulangan, Array dan String.

## Tugas Praktik Pemrograman Berbasis Objek - Studi Kasus: Manajemen Restoran
Sekarang, anda diminta untuk membuat program Java yang akan digunakan untuk manajemen restoran. Program ini akan mencakup berbagai konsep pemrograman berbasis objek yang telah dipelajari.

## Petunjuk Pengerjaan Tugas dan Poin untuk Penjelasan di Rekaman Video:
1. Buatlah sebuah kelas abstrak MenuItem yang akan menjadi kelas dasar untuk semua menu item dalam restoran. Kelas ini harus memiliki atribut nama (String), harga (double), dan kategori (String). Definisikan metode abstrak tampilMenu() yang akan digunakan untuk menampilkan informasi tentang item menu.
2. Buatlah tiga kelas turunan dari MenuItem: Makanan, Minuman, dan Diskon.
    - Kelas Makanan dan Minuman adalah subkelas dari MenuItem dan harus memiliki atribut tambahan yang sesuai dengan jenisnya (misalnya, jenisMakanan dan jenisMinuman). Implementasikan metode tampilMenu() untuk menampilkan informasi khusus tentang makanan dan minuman.
    - Kelas Diskon adalah subkelas dari MenuItem yang akan digunakan untuk menerapkan diskon khusus pada menu. Kelas ini harus memiliki atribut diskon (double) dan mengimplementasikan metode tampilMenu() untuk menampilkan informasi tentang diskon yang ditawarkan.

3. Buatlah sebuah kelas Menu yang akan digunakan untuk mengelola semua item menu dalam restoran. Kelas ini harus memiliki atribut berupa sebuah ArrayList untuk menyimpan semua item menu.
4. Buatlah kelas Pesanan yang akan digunakan untuk mencatat pesanan pelanggan. Kelas ini harus memiliki atribut berupa ArrayList untuk menyimpan item-item yang dipesan oleh pelanggan.
5. Implementasikan konsep inheritance dengan benar antara kelas-kelas ini.
6. Implementasikan konsep encapsulation dengan membatasi akses ke atribut-atribut kelas.
7. Pergunaan pengecualian (exception) yang mungkin terjadi, seperti ketika mencoba mengakses item yang tidak ada dalam menu.
8. Program harus memiliki menu utama yang memungkinkan pengguna untuk:
    - Menambahkan item baru ke menu (makanan, minuman, atau diskon).
    - Menampilkan menu restoran.
    - Menerima pesanan dari pelanggan dan mencatatnya.
    - Menghitung total biaya pesanan dengan mempertimbangkan diskon yang mungkin diterapkan.
    - Menampilkan struk pesanan pelanggan.
    - Keluar dari program.

9. Implementasikan operasi I/O dan operasi file untuk menyimpan dan memuat daftar menu dari sebuah file teks, dan juga menyimpan struk pesananan pelanggan dan memuat dari file teks. | gimana cara upload nya? gimana cara simpannya? jenis file nya gimana? .txt
10. Gunakan struktur keputusan dan pengulangan sesuai dengan kebutuhan, seperti saat menampilkan menu dan memproses pesanan.

11. Durasi video penjelasan maksimal 15 menit

## Petunjuk Tambahan:

- Anda dapat menggunakan antarmuka atau abstract class jika diperlukan untuk mengimplementasikan abstraksi, seperti antarmuka untuk makanan, minuman, dan diskon.
- Gunakan polymorphism untuk mengimplementasikan metode tampilMenu() pada masing-masing kelas turunan.
- Pastikan untuk menjaga struktur yang rapi dan dokumentasi yang baik dalam kode Anda.

Dengan tugas praktik 3 ini, diharapkan rekan-rekan mahasiswa dapat mempraktikkan berbagai konsep pemrograman berbasis objek, penggunaan I/O dan exception handling, serta operasi File dalam sebuah aplikasi manajemen restoran yang lebih kompleks.

> Setelah menyelesaikan Tugas Praktik 3, rekan-rekan dapat mengirimkan file source code dan link rekaman video penjelasan pengerjaan Tugas Praktik 3.