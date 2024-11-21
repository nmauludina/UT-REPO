import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Menu> daftarMenu = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> pesanan = new ArrayList<String>();
    static ArrayList<Integer> jumlahPesanan = new ArrayList<>();
    static ArrayList<Double> jumlahBiayaPesanan = new ArrayList<>();
    static double totalBiaya = 0;


    private static void tampilkanDaftarMenu(ArrayList<Menu> daftarMenu) {
        System.out.println("\n\nSelamat datang di Restoran Bakso!");
        System.out.println("Di bawah adalah menu yang tersedia di sini. \n");

        System.out.println("============ DAFTAR MENU RESTORAN BAKSO ============ \n");

        System.out.println("----- MAKANAN --------------------------------------");
        System.out.printf("%-14s | %-14s %n", "Nama", "Harga");
        System.out.println("----------------------------------------------------");
        Menu.cetakMenuBerdasarkanKategori(daftarMenu, "Makanan");


        System.out.println("\n\n----- MINUMAN --------------------------------------");
        System.out.printf("%-14s | %-14s %n", "Nama", "Harga");
        System.out.println("----------------------------------------------------");
        Menu.cetakMenuBerdasarkanKategori(daftarMenu, "Minuman");
        System.out.println("----------------------------------------------------\n");

        System.out.println("Sudah melihat menu kami? Tekan ENTER untuk memesan.");
        scanner.nextLine();
    }

    
    private static void terimaDanOlahPesanan(ArrayList <String> pesanan, ArrayList<Integer> jumlahPesanan) {
        System.out.println("Tuliskan pesanan anda di bawah ini (Format: Nama Menu = jumlahPesanan).");
        System.out.println("Tulis \"Selesai\" untuk menyudahi pesanan.\n");

        Pesanan.terimaPesanan(pesanan, jumlahPesanan, daftarMenu);
        
        totalBiaya = hitungTotalBiayaPembayaran(pesanan,jumlahPesanan,daftarMenu);
    }

    static double totalBiayaPesanan = 0.0, pajak = 0.0, diskon = 0.0;
    static final double biayaPelayanan = 20000;
    static int jumlahPenawaran = 0;
    static String[] promoBeli1Gratis1 = new String[2]; // indeks-1 untuk simpan teks, indeks-2 untuk total harga promo

    static private double hitungTotalBiayaPembayaran(ArrayList <String> pesanan, ArrayList<Integer> jumlahPesanan, ArrayList<Menu> daftarMenu) {
        totalBiayaPesanan = Pesanan.totalBiayaPesanan(pesanan,jumlahPesanan, daftarMenu, jumlahBiayaPesanan);
        pajak = 0.10 * totalBiayaPesanan;

        System.out.println("\nTotal pembelian anda adalah " + Utils.tampilkanRupiah(totalBiayaPesanan));
        
        if (totalBiayaPesanan > 100000) {
            diskon = 0.10 * totalBiayaPesanan;
            System.out.printf("\n * Pembelian min Rp.100.000 mendapatkan diskon 10%% (%s). \n\n", 
                Utils.tampilkanRupiah(diskon)
            );
        } else if (totalBiayaPesanan > 50000) terapkanBonusMinuman(pesanan, jumlahPesanan, daftarMenu);

        double totalBiayaPembayaran = (totalBiayaPesanan + pajak + biayaPelayanan) - diskon;
        return totalBiayaPembayaran;
    }

    static void terapkanBonusMinuman(ArrayList <String> pesanan, ArrayList<Integer> jumlahPesanan, ArrayList<Menu> daftarMenu) {
        for (int indexPesanan = 0; indexPesanan < daftarMenu.size(); indexPesanan++) {
            for (int indexMenu = 0; indexMenu < daftarMenu.size(); indexMenu++) {
                boolean namaPesananDanMenuCocok = daftarMenu.get(indexMenu).getNama().equalsIgnoreCase(pesanan.get(indexPesanan));
                boolean kategoriPesananAdalahMinuman = daftarMenu.get(indexMenu).getKategori().equalsIgnoreCase("Minuman");

                if (namaPesananDanMenuCocok && kategoriPesananAdalahMinuman) { // jika cocok terapkan bonus
                    jumlahPenawaran = jumlahPesanan.get(indexPesanan);
                    jumlahPesanan.set(indexPesanan, jumlahPenawaran * 2);  // Beli 1 Gratis 1 untuk item pertama
                    
                    double hargaTotal = daftarMenu.get(indexMenu).getHarga() * jumlahPesanan.get(indexPesanan); // Hitung harga sesuai jumlah asli
                    jumlahBiayaPesanan.set(indexPesanan, hargaTotal); // Set total biaya tanpa tambahan bonus
                    
                    promoBeli1Gratis1[0] = "Promo Beli 1 Gratis 1 (" + Utils.kapitalisasiHurufPertama(pesanan.get(indexPesanan)) + ")";
                    promoBeli1Gratis1[1] = Integer.toString(jumlahPenawaran * daftarMenu.get(indexMenu).getHarga());
                    
                    System.out.printf("\n * Pembelian min Rp.50.000 mendapatkan penawaran Beli 1 Gratis 1 untuk pesanan minuman pertama (%s x %d). \n\n", 
                        Utils.kapitalisasiHurufPertama(pesanan.get(indexPesanan)), 
                        jumlahPenawaran
                    );

                    return;
                }
            }
        }
    }
    
    public static void cetakStrukPesanan() {
        System.out.println("Cetak struk pemesanan? (Tekan ENTER)");
        scanner.nextLine();

        System.out.println("======== STRUK PEMBAYARAN RESTORAN BAKSO ========");
        System.out.println("-------------------------------------------------");
        cetakPesanan(pesanan, jumlahPesanan);
        
        /* Cetak jika dapat penawaran Beli 1 Gratis 1 */
        if (promoBeli1Gratis1[0] != null) {
            System.out.printf("%-32s  x%-2s(%s)%n", 
                promoBeli1Gratis1[0], jumlahPenawaran, 
                Utils.tampilkanRupiah(Double.parseDouble(promoBeli1Gratis1[1]))
            );
        }
        
        System.out.println("-------------------------------------------------");
        System.out.printf("TOTAL%s%s\n", 
            Utils.dapatkanJumlahSpasiStruk("TOTAL", totalBiayaPesanan), 
            Utils.tampilkanRupiah(totalBiayaPesanan)
        );
        if (diskon != 0) {
            System.out.printf("Diskon 10%%%s(%s)\n", Utils.dapatkanJumlahSpasiStruk("(Diskon10%)", diskon), Utils.tampilkanRupiah(diskon));
        }
        System.out.printf("Pajak%s%s\n", 
            Utils.dapatkanJumlahSpasiStruk("Pajak", pajak), 
            Utils.tampilkanRupiah(pajak)
        );
        System.out.printf("B.Pelayanan%s%s\n", 
            Utils.dapatkanJumlahSpasiStruk("B.Pelayanan", biayaPelayanan), 
            Utils.tampilkanRupiah(biayaPelayanan)
        );
        System.out.println("-------------------------------------------------");
        System.out.printf("TOTAL PEMBAYARAN%s%s\n", 
            Utils.dapatkanJumlahSpasiStruk("TOTAL PEMBAYARAN", totalBiaya), 
            Utils.tampilkanRupiah(totalBiaya)
        );
    
        System.out.println("\n\nTerima kasih sudah berbelanja. Have a nice day! :)");
    }   

    public static void menuUtama() {
        boolean ulangi = true;
        do {
            System.out.println("-------------------------------------------------");
            System.out.println("SELAMAT DATANG DI RESTORAN BAKSO!");
            System.out.println("1 - Kelola menu restoran");
            System.out.println("2 - Pesan makanan/minuman");
            
            System.out.print("Masukkan pilihan untuk meneruskan tindakan: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    PengelolaanMenu.kelolaMenuRestoran();
                    break;
            
                case "2":
                    tampilkanDaftarMenu(daftarMenu);
                    terimaDanOlahPesanan(pesanan, jumlahPesanan);
                    cetakStrukPesanan();
                    break;
            
                default:
                    System.out.println("\t(!) Masukan tidak tersedia, silahkan masukkan pilihan yang benar.");
                    ulangi = true;
                    break;
            }
        } while (ulangi);     
    }

    private static void cetakPesanan(ArrayList <String> pesanan, ArrayList<Integer> jumlahPesanan) {      
        for (int index = 0; index < pesanan.size(); index++) {
            if (pesanan.get(index).isEmpty() || pesanan.get(index).equalsIgnoreCase("selesai")) return;

            System.out.printf("%-18s %-14s x%-2s %-14s %n", 
                Utils.kapitalisasiHurufPertama(pesanan.get(index)), 
                Utils.tampilkanRupiah(Pesanan.temukanHargaPesanan(index, pesanan, daftarMenu)) , 
                jumlahPesanan.get(index), Utils.tampilkanRupiah(jumlahBiayaPesanan.get(index))
            );
        }
    }

    public static void inisialisasiDaftarMenu() {
        daftarMenu.add(new Menu("Air Mineral", 7000, "Minuman"));
        daftarMenu.add(new Menu("Es Teh", 8000, "Minuman"));
        daftarMenu.add(new Menu("Kopi", 10000, "Minuman"));
        daftarMenu.add(new Menu("Es Teler", 15000, "Minuman"));
        daftarMenu.add(new Menu("Bakso", 25000, "Makanan"));
        daftarMenu.add(new Menu("Soun Bakso", 32000, "Makanan"));
        daftarMenu.add(new Menu("Mie Bakso", 35000, "Makanan"));
        daftarMenu.add(new Menu("Bakso Mie Kari", 43000, "Makanan"));
        daftarMenu.add(new Menu("Mie Ayam Bakso", 38000, "Makanan"));
    }

    public static void main(String[] args) {
        inisialisasiDaftarMenu();        
        menuUtama();
    }
}
