import java.util.ArrayList;
import java.util.Scanner;

public class PengelolaanMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void kelolaMenuRestoran() {
        boolean ulangi = true;
        do {
            System.out.println("-------------------------------------------------");
            System.out.println("\t> KELOLA MENU RESTORAN");
            System.out.println("\t  1 - Tambah menu baru");
            System.out.println("\t  2 - Ubah harga menu");
            System.out.println("\t  3 - Hapus menu");
            System.out.println("\t  4 - Kembali (Menu Utama)\n");
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
                    System.out.println("\t  - Kembali ke menu utama");
                    ulangi = false;
                    break;
            
                default:
                    System.out.println("\t  (!) Pilihan tidak tersedia, silahkan masukkan pilihan yang valid.");
                    ulangi = true;
                    break;
            }
        } while (ulangi);
    }

    private static boolean tambahMenuBaruBulk(String konfirmasi, ArrayList<Menu> menuBaru) {
        int index = 0;
        boolean lanjutkan = true;
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
                index++;
            } else {
                lanjutkan = false;
            }
        } while (!konfirmasi.equalsIgnoreCase("ya") && !!lanjutkan);
        return lanjutkan;
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
         * 2. Apakah input merupakan angka untuk kembali ke Menu Kelola?
         * 3. Apakah input merupakan kata "tidak"?
         */
        if (input.matches("(?i)\bya\b|^"+ (Main.daftarMenu.size()+1) + "$|(?i)tidak")) {
            System.out.println(" \t   - Batal tambah menu baru");
            kembaliKeMenu("Tambah Menu Baru");
            return;
        } else {
            String konfirmasi = "";
            ArrayList<Menu> menuBaru = new ArrayList<Menu>();
            tambahMenuBaruBulk(konfirmasi, menuBaru); // menambahkan 1 atau lebih menu sekaligus

            if (menuBaru.size() == 0) return;

            System.out.println("\n \t   Menu baru yang akan ditambahkan: ");
            tampilkanDaftarMenu(menuBaru, false);
            
            System.out.printf("\n \t   Yakin menambah menu baru di atas (Ya/Tidak)? ");
            String yakinTambah = scanner.nextLine();

            if (!yakinTambah.equalsIgnoreCase("ya")) {
                System.out.println(" \t   - Tambah menu baru dibatalkan");
                kembaliKeMenu("Tambah Menu Baru");
                return;
            } else {
                for (Menu menu : menuBaru) {
                    Main.daftarMenu.add(menu);
                }
                System.out.println(" \t   - Berhasil menambahkan menu baru");
                kembaliKeMenu("Tambah Menu Baru");
                return;
            }

        }
    }

    private static void ubahHargaMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println(" \t > KELOLA MENU RESTORAN - UBAH HARGA MENU");
        tampilkanDaftarMenu(Main.daftarMenu, true);
        Integer pilihan;
        do {
            System.out.println("\n \t   Menu berapa yang harganya mau diubah?");
            System.out.print(" \t   Pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            if (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer)) {
                System.out.println(" \t   Masukkan pilihan yang valid.");
                continue;
            }
        } while (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer));
        
        if (pilihan.equals(Main.daftarMenu.size()+1)) {
            System.out.println(" \t   - Batal mengubah harga menu");
            kembaliKeMenu("Ubah Harga Menu");
            return;
        }

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
            System.out.println(" \t   - Harga menu " + Utils.kapitalisasiHurufPertama(menuTerpilih.getNama()) + " berhasil diperbaharui.");
        } else {
            System.err.println(" \t   - Ubah harga menu dibatalkan.");
        }
        kembaliKeMenu("Ubah Harga Menu");
        return;
    }

    private static void hapusMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println(" \t > KELOLA MENU RESTORAN - HAPUS ITEM MENU");
        tampilkanDaftarMenu(Main.daftarMenu, true);
        Integer pilihan;
        do {
            System.out.println("\n \tMenu berapa yang mau dihapus?");
            System.out.print(" \tPilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            if (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer)) {
                System.out.println(" \t   Masukkan pilihan yang valid.");
            }
        } while (!(pilihan > 0 && pilihan <= (Main.daftarMenu.size()+1) || pilihan instanceof Integer));

        if (pilihan.equals(Main.daftarMenu.size()+1)) {
            System.out.println(" \t   - Batal menghapus item menu");
            kembaliKeMenu("Hapus Item Menu");
            return;
        }

        System.out.print("\n \t   Yakin menghapus menu " + Main.daftarMenu.get(pilihan-1).getNama() + " (Ya/Tidak)? ");
        String konfirmasi = scanner.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            Main.daftarMenu.remove(pilihan-1);
            System.out.println(" \t   - Menu berhasil dihapus.");
        } else {
            System.err.println(" \t   - Hapus item menu dibatalkan.");
        }
        kembaliKeMenu("Hapus Item Menu");
        return;
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

    private static void kembaliKeMenu(String judulMenu) {
        System.out.println(" \t   < Kembali ke Menu " + judulMenu);
    }
}
