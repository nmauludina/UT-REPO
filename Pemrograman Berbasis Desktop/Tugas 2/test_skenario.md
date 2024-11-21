# Poin Pengerjaan dan Video Penjelasan
1. Ada kelas 'Menu' untuk representasi menu makanan dan minuman (minimal 4 menu dari masing-masing kategori menu).
2. Ada kelas utama 'Main' yang punya method:
    a. menampilkan daftar menu (makanan dan minuman)
    b. menerima dan mengolah pesanan
    c. menghitung total biaya pesanan
    d. mencetak struk pesanan untuk tampilkan ke layar
    e. manajemen menu aplikasi
    f. menambah menu restoran baru
    g. mengubah harga menu restoran
    h. menghapus pesanan
3. format menampilkan data menu dikelompokkan berdasarkan kategori.
4. gunakan array dalam mengelola menu restoran dan pesanan pelanggan.
5. gunakan struktur keputusan dalam implementasi fungsi perhitungan total biaya dan mencetak struk pesanan.
6. gunakan struktur pengulangan untuk menampilkan:
    - menampilkan daftar menu
    - menginput pesanan pelanggan
    - menghitung total biaya
    - menambahkan beberapa menu baru sekaligus
7. Menampilkan skenario struktur keputusan yang ada pada soal (durasi video maks 15 menit).

# Testing skenario
## A. Pelanggan memesan Makanan
1. Item memiliki nama, harga, kategori.
2. Memesan menu tanpa batas, hingga input adalah 'selesai'.
3. Jika input diluar nilai/teks diluar pilihanmenu, sistem meminta input kembali.
4. Program dapat menghitung total biaya pesanan berdasarkan item menu yang dipilih dan jumlahnya.
5. Kenakan pajak 10% dan b.pelayanan Rp.20.000,-
6. Diskon 10% jika total pesanan lebih dari Rp.100.000,-.
7. Penawaran beli 1 gratis 1 jika total pesanan lebih dari Rp.50.000,-. 
8. Cetak struk pesanan (ada item yang dipesan, jumlah dan harga per item, total harga per item, total biaya pemesanan, informasi pajak dan biaya pajak, dan biaya pelayanan. tampilkan informasi jika ada diskon dan harga setelah diskon/penawaran).

## B. Manajemen Menu Aplikasi
1. Ada dua menu utama (untuk pelanggan memesan dan pengelolaan menu pemilik restoran).
2. Navigasi menu dapat kembali ke menu parent sebelumnya.
3. Saat ubah/hapus data, memunculkan daftar menu dan kemudian menginput nomor menu tersebut.
4. Ada konfirmasi ke layar monitor sebelum eksekusi ubah/hapus data (jika input 'ya' akan dieksekusi). Setelah itu kembali ke menu pengelolaan.
5. Pastikan ketika pilih 'ya' ada perubahan pada daftar menu, jika tidak maka tidak ada perubahan.
6. jika menginput nilai/teks di luar pilihan menu yang ada, sistem akan terus meminta input kembali.