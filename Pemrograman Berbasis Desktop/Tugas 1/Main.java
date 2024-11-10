import java.util.Scanner;

public class Main {
    static Menu[] daftarMenu = {
        new Menu("Air Mineral", 7000, "Minuman"),
        new Menu("Es Teh", 8000, "Minuman"),
        new Menu("Kopi", 10000, "Minuman"),
        new Menu("Es Teler", 15000, "Minuman"),
        new Menu("Bakso", 25000, "Makanan"),
        new Menu("Soun Bakso", 32000, "Makanan"),
        new Menu("Mie Bakso", 35000, "Makanan"),
        new Menu("Bakso Mie Kari", 43000, "Makanan"),
        new Menu("Mie Ayam Bakso", 38000, "Makanan"),
    };

    static Scanner scanner = new Scanner(System.in);

    static String[] pesanan = new String[4];
    static int[] jumlahPesanan = new int[4];
    static double totalBiaya = 0;
    static double[] jumlahBiayaPesanan = new double[pesanan.length];


    private static void tampilkanDaftarMenu(Menu[] daftarMenu) {
        System.out.println("Selamat datang di Restoran Bakso Kami!");
        System.out.println("Di bawah adalah menu yang tersedia di resto kami. \n");

        System.out.println("============ DAFTAR MENU RESTORAN BAKSO ============ \n");

        System.out.println("----- MAKANAN --------------------------------------");
        System.out.printf("%-14s | %-14s %n", "Nama", "Harga");
        System.out.println("----------------------------------------------------");
        Menu.filterMenuBerdasarkanKategori(0, daftarMenu, "Makanan");


        System.out.println("\n\n----- MINUMAN --------------------------------------");
        System.out.printf("%-14s | %-14s %n", "Nama", "Harga");
        System.out.println("----------------------------------------------------");
        Menu.filterMenuBerdasarkanKategori(0, daftarMenu, "Minuman");
        System.out.println("----------------------------------------------------\n");

        System.out.println("Sudah melihat menu kami? Tekan ENTER untuk memesan.");
        scanner.nextLine();
    }

    
    private static void terimaDanOlahPesanan(String[] pesanan, int[] jumlahPesanan) {
        System.out.println("Tuliskan pesanan anda di bawah ini (Maksimal 4 menu, format: Nama Menu = jumlahPesanan).");
        System.out.println("Tulis \"Selesai\" untuk menyudahi pesanan.\n");

        Pesanan.terimaPesanan(0, pesanan, jumlahPesanan, daftarMenu);
        
        totalBiaya = hitungTotalBiayaPembayaran(pesanan,jumlahPesanan,daftarMenu);
    }

    static double totalBiayaPesanan = 0.0, pajak = 0.0, diskon = 0.0;
    static final double biayaPelayanan = 20000;
    static int jumlahPenawaran = 0;
    static String[] promoBeli1Gratis1 = new String[2];

    static private double hitungTotalBiayaPembayaran(String[] pesanan, int[] jumlahPesanan, Menu[] daftarMenu) {
        totalBiayaPesanan = Pesanan.totalBiayaPesanan(0,pesanan,jumlahPesanan, daftarMenu, jumlahBiayaPesanan);
        pajak = 0.10 * totalBiayaPesanan;

        System.out.println("\nTotal pembelian anda adalah " + Utils.tampilkanRupiah(totalBiayaPesanan));
        
        if (totalBiayaPesanan > 100000) {
            diskon = 0.10 * totalBiayaPesanan;
            System.out.printf("\n * Pembelian min Rp.100.000 mendapatkan diskon 10%% (%s). \n\n", 
                Utils.tampilkanRupiah(diskon)
            );
        } else if (totalBiayaPesanan > 50000) 
            terapkanBonusMinuman(0, 0, pesanan, jumlahPesanan, daftarMenu);

        double totalBiayaPembayaran = (totalBiayaPesanan + pajak + biayaPelayanan) - diskon;

        return totalBiayaPembayaran;
    }

    static void terapkanBonusMinuman(int indexPesanan, int indexMenu, String[] pesanan, int[] jumlahPesanan, Menu[] daftarMenu) {
        if (indexPesanan >= pesanan.length || indexMenu >= daftarMenu.length) {
            return;
        }

        boolean namaPesananDanMenuCocok = daftarMenu[indexMenu].getNama().equalsIgnoreCase(pesanan[indexPesanan]);
        boolean kategoriPesananAdalahMinuman = daftarMenu[indexMenu].getKategori().equalsIgnoreCase("Minuman");
    
        // Cek apakah item saat ini dalam pesanan cocok dengan item menu yang bersifat "Minuman"
        if (namaPesananDanMenuCocok && kategoriPesananAdalahMinuman) { // jika cocok terapkan bonus

            jumlahPenawaran = jumlahPesanan[indexPesanan];
            jumlahPesanan[indexPesanan] = jumlahPenawaran * 2; // Beli 1 Gratis 1 untuk item pertama
            
            double hargaTotal = daftarMenu[indexMenu].getHarga() * jumlahPesanan[indexPesanan]; // Hitung harga sesuai jumlah asli
            jumlahBiayaPesanan[indexPesanan] = hargaTotal; // Set total biaya tanpa tambahan bonus
            
            promoBeli1Gratis1[0] = "Promo Beli 1 Gratis 1 (" + Utils.kapitalisasiHurufPertama(pesanan[indexPesanan]) + ")";
            promoBeli1Gratis1[1] = Integer.toString(jumlahPenawaran * daftarMenu[indexMenu].getHarga());
            
            System.out.printf("\n * Pembelian min Rp.50.000 mendapatkan penawaran Beli 1 Gratis 1 untuk pesanan minuman pertama (%s x %d). \n\n", 
                Utils.kapitalisasiHurufPertama(pesanan[indexPesanan]), 
                jumlahPenawaran
            );

            return; // Setelah menerapkan bonus pada satu item, selesai
        }
    
        /* Recursive scope Bonus Minuman */
        // Jika item saat ini tidak sesuai, lanjutkan ke item berikutnya
        if (indexMenu < daftarMenu.length - 1) {
            // Cek dengan item berikutnya di daftar menu
            terapkanBonusMinuman(indexPesanan, indexMenu + 1, pesanan, jumlahPesanan, daftarMenu);
        } else {
            // Jika sudah mengecek semua item di daftar menu untuk pesanan saat ini, lanjut ke pesanan berikutnya
            terapkanBonusMinuman(indexPesanan + 1, 0, pesanan, jumlahPesanan, daftarMenu);
        }
    }
    
    public static void cetakStrukPesanan() {
        System.out.println("Cetak struk pemesanan? (Tekan ENTER)");
        scanner.nextLine();

        System.out.println("======== STRUK PEMBAYARAN RESTORAN BAKSO ========");
        System.out.println("-------------------------------------------------");
        cetakPesanan(0, pesanan, jumlahPesanan);
        
        /* Jika dapat penawaran Beli 1 Gratis 1 */
        if (promoBeli1Gratis1[0] != null) {
            // System.out.printf("%-18s %-14s x%-2s %-14s %n", Utils.kapitalisasiHurufPertama(pesanan[index]), Utils.tampilkanRupiah(Pesanan.temukanHargaPesanan(0, index, pesanan, daftarMenu)) , jumlahPesanan[index], Utils.tampilkanRupiah(jumlahBiayaPesanan[index]));
            System.out.printf("%-32s  x%-2s(%s)%n", 
                promoBeli1Gratis1[0], jumlahPenawaran, 
                Utils.tampilkanRupiah(Double.parseDouble(promoBeli1Gratis1[1]))
            );
        }
        
        System.out.println("-------------------------------------------------");
        System.out.printf("TOTAL%s%s\n", 
            dapatkanJumlahSpasiStruk("TOTAL", totalBiayaPesanan), 
            Utils.tampilkanRupiah(totalBiayaPesanan)
        );
        if (diskon != 0) {
            System.out.printf("Diskon 10%%%s(%s)\n", dapatkanJumlahSpasiStruk("(Diskon10%)", diskon), Utils.tampilkanRupiah(diskon));
        }
        System.out.printf("Pajak%s%s\n", 
            dapatkanJumlahSpasiStruk("Pajak", pajak), 
            Utils.tampilkanRupiah(pajak)
        );
        System.out.printf("B.Pelayanan%s%s\n", 
            dapatkanJumlahSpasiStruk("B.Pelayanan", biayaPelayanan), 
            Utils.tampilkanRupiah(biayaPelayanan)
        );
        System.out.println("-------------------------------------------------");
        System.out.printf("TOTAL PEMBAYARAN%s%s\n", 
            dapatkanJumlahSpasiStruk("TOTAL PEMBAYARAN", totalBiaya), 
            Utils.tampilkanRupiah(totalBiaya)
        );
    
        System.out.println("\n\nTerima kasih sudah berbelanja. Have a nice day! :)");
    }

    private static String dapatkanJumlahSpasiStruk(String kata,double rupiah) {
        return " ".repeat(49-kata.length()-Utils.tampilkanRupiah(rupiah).length());
    }

    private static void cetakPesanan(int index, String[] pesanan, int[] jumlahPesanan) {
        if (index > pesanan.length - 1) return;
        if (pesanan[index].isEmpty() || pesanan[index].equalsIgnoreCase("selesai")) return;

        System.out.printf("%-18s %-14s x%-2s %-14s %n", 
            Utils.kapitalisasiHurufPertama(pesanan[index]), 
            Utils.tampilkanRupiah(Pesanan.temukanHargaPesanan(0, index, pesanan, daftarMenu)) , 
            jumlahPesanan[index], Utils.tampilkanRupiah(jumlahBiayaPesanan[index])
        );
        cetakPesanan(index + 1, pesanan, jumlahPesanan);
    }
    public static void main(String[] args) {
        tampilkanDaftarMenu(daftarMenu);

        terimaDanOlahPesanan(pesanan, jumlahPesanan);

        cetakStrukPesanan();
    }
}
