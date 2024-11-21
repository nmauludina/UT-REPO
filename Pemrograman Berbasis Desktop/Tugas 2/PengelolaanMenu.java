import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PengelolaanMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void kelolaMenuRestoran() {
        System.out.println("-------------------------------------------------");
        System.out.println("\t> KELOLA MENU RESTORAN");
        System.out.println("\t  1 - Tambah menu baru");
        System.out.println("\t  2 - Ubah harga menu");
        System.out.println("\t  3 - Hapus menu");
        System.out.println("\t  4 - Kembali (Menu Utama)\n");

        boolean ulangi = false;
        do {
            ulangi = false;
            System.out.print("\t  Pilihan: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    tambahMenuBaru();
                    break;
                case "2":
                    ubahHargaMenu();
                    break;
                case "3":
                    hapusMenu();
                    break;
                case "4":
                    System.out.println("\t  Kembali ke menu utama");
                    // Main.menuUtama();
                    // break;
                    return;
            
                default:
                    System.out.println("\t  (!) Pilihan tidak tersedia, silahkan masukkan pilihan yang valid.");
                    ulangi = true;
                    break;
            }
        } while (ulangi);
    }

    private static void tambahMenuBaruBulk(String konfirmasi, ArrayList<Menu> menuBaru) {
        int index = 0;
        boolean lanjutkan = false;
        do {
            System.out.println("\n \t   Silahkan masukkan data menu ke-" + (index+1));
    
            System.out.print(" \t   Nama: ");
            String nama = scanner.nextLine();

            Integer harga;
            do {
                System.out.print(" \t   Harga: ");
                harga = scanner.nextInt();
                scanner.nextLine();
            } while (!(harga instanceof Integer));

            String kategori;
            do {
                System.out.print(" \t   Kategori (Makanan/Minuman): ");
                kategori = scanner.nextLine();
            } while (!(
                        kategori.equalsIgnoreCase("makanan") || 
                        kategori.equalsIgnoreCase("minuman")
                    ));

            menuBaru.add(new Menu(nama, harga, kategori));

            System.out.print(" \t   Tambahkan menu lagi (Ya/Tidak)? ");
            String tambahlagi = scanner.nextLine();

            if (tambahlagi.equalsIgnoreCase("ya")){
                lanjutkan = true;
                index++;
            } else {
                lanjutkan = false;
                break;
            }

            System.out.println("Kondisi: "+!konfirmasi.equalsIgnoreCase("ya")  +" lanjutkan:" +!!lanjutkan);
        } while (!konfirmasi.equalsIgnoreCase("ya") && !!lanjutkan);
    }

    private static void tambahMenuBaru() {
        System.out.println("-------------------------------------------------");
        System.out.println(" \t > KELOLA MENU RESTORAN - TAMBAH MENU BARU");

        System.out.println(" \t   Daftar Menu Saat Ini:");
        tampilkanDaftarMenu(Main.daftarMenu, true);
        System.out.print("\n \t   Tambah menu (Ya/Tidak)? ");
        String input = scanner.nextLine();
        
        /* 
         * <String>.matches() mereturn true/false
         * digunakan untuk menyaring String input:
         * 1. Apakah input terdapat yang bukan "ya"?
         * 2. Apakah input merupakan angka untuk kembali ke Menu Kelola
         * 3. Apakah input merupakan kata "tidak"?
         */
        if (input.matches("(?i)\bya\b|^"+ (Main.daftarMenu.size()+1) + "$|(?i)tidak")) {
            kembaliKeMenuKelolaMenuRestoran(true);
        } else {
            String konfirmasi = "";
            ArrayList<Menu> menuBaru = new ArrayList<Menu>();
            tambahMenuBaruBulk(konfirmasi, menuBaru);

            System.out.println("\n \t   Menu baru yang akan ditambahkan: ");
            tampilkanDaftarMenu(menuBaru, false);
            
            System.out.printf("\n \t   Yakin menambah menu baru di atas (Ya/Tidak)? ");
            String yakinTambah = scanner.nextLine();

            if (!yakinTambah.equalsIgnoreCase("ya")) {
                kembaliKeMenuKelolaMenuRestoran(true);
                return;
            }

            for (Menu menu : menuBaru) {
                Main.daftarMenu.add(menu);
            }
            kembaliKeMenuKelolaMenuRestoran(true);
            return;
        }
    }

    private static void ubahHargaMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println(" \t > KELOLA MENU RESTORAN - UBAH HARGA MENU");
        tampilkanDaftarMenu(Main.daftarMenu, true);
        Integer pilihan;
        do {
            System.out.println(" \t   Menu berapa yang harganya mau diubah?");
            System.out.print(" \t   Pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            if (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer)) {
                System.out.println(" \t   Masukkan pilihan yang valid.");
                continue;
            }
        } while (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer));
        
        kembaliKeMenuKelolaMenuRestoran(pilihan.equals(Main.daftarMenu.size()+1));

        System.out.print(" \t   Masukkan nominal harga untuk menu "+Main.daftarMenu.get(pilihan-1).getNama()+": ");
        int harga = scanner.nextInt();
        scanner.nextLine();

        Menu menuTerpilih = Main.daftarMenu.get(pilihan-1);
        
        System.out.print("\n \t   Yakin mengubah harga menu " + 
                            Utils.tampilkanRupiah(menuTerpilih.getHarga()) + 
                            " menjadi " + 
                            Utils.tampilkanRupiah(harga) + 
                            " (Ya/Tidak)? ");
        String konfirmasi = scanner.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            menuTerpilih.setHarga(harga);
            System.out.print(" \t   ");
            menuTerpilih.cetakMenu();
            System.out.println(" \t   - Harga menu " + menuTerpilih.getNama() + " berhasil diperbaharui.");
            kembaliKeMenuKelolaMenuRestoran(true);
        } else {
            System.err.println(" \t   - Ubah harga menu dibatalkan.");
            System.err.println(" \t   < Kembali ke Menu Ubah Harga Menu.");
            ubahHargaMenu();
            return;
        }
    }

    private static void hapusMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println(" \t > KELOLA MENU RESTORAN - HAPUS ITEM MENU");
        tampilkanDaftarMenu(Main.daftarMenu, true);
        Integer pilihan;
        do {
            System.out.println(" \tMenu berapa yang mau dihapus?");
            System.out.print(" \tPilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Pilihan hapus menu: " + pilihan);
            tampilkanDaftarMenu(Main.daftarMenu, true);
            
            if (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer)) {
                System.out.println(" \t   Masukkan pilihan yang valid.");
            }
        } while (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer));

        kembaliKeMenuKelolaMenuRestoran(pilihan.equals(Main.daftarMenu.size()+1));

        System.out.print("\n \t   Yakin menghapus menu " + Main.daftarMenu.get(pilihan-1).getNama() + " (Ya/Tidak)? ");
        String konfirmasi = scanner.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            Main.daftarMenu.remove(pilihan-1);
            System.out.println(" \t   - Menu berhasil dihapus.");
            kembaliKeMenuKelolaMenuRestoran(true);
        } else {
            System.err.println(" \t   - Hapus item menu dibatalkan.");
            System.err.println(" \t   < Kembali ke Menu Hapus Item Menu.");
            hapusMenu();
        }
    }

    private static void tampilkanDaftarMenu(ArrayList<Menu> daftarMenu, boolean tampilkanNavigasiKembali) {
        for (int i = 0; i < daftarMenu.size();i++) {
            System.out.print("\t   " + (i+1) + " - ");
            daftarMenu.get(i).cetakMenu();
        }
        
        if (tampilkanNavigasiKembali) {
            System.out.println("\t   " + (Main.daftarMenu.size()+1) + " - Kembali (Menu Kelola Menu Restoran)");
        }
    }

    private static void kembaliKeMenuKelolaMenuRestoran(boolean kondisi) {
        if (!kondisi) return;

        System.out.println(" \t   < Kembali ke Menu Kelola Menu Restoran");
        kelolaMenuRestoran();
    }
}
