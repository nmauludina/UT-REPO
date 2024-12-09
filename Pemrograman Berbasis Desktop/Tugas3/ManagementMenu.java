import java.util.Scanner;

public class ManagementMenu implements Ui {
    Menu menu;

    Scanner scanner = new Scanner(System.in);

    public ManagementMenu(Menu menu) {
        this.menu = menu;
    }

    public void main() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n\nMANAJEMEN MENU");
            System.out.println("-------------------------");
            System.out.println("[1] Tambah menu item");
            System.out.println("[2] Edit menu item");
            System.out.println("[3] Hapus menu item");
            System.out.println("[4] Tampilkan daftar menu");
            System.out.println("[5] Kembali");
            System.out.println("-------------------------");

            System.out.print("Pilihan: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    uiAddMenuItem();
                    break;

                case "2":
                    uiEditMenuItem();
                    break;

                case "3":
                    uiDeleteMenuItem();
                    break;

                case "4":
                    uiDisplayDaftarMenu();
                    break;

                case "5":
                    System.out.println("<-- Kembali ke menu utama\n");
                    isRunning = false;
                    break;

                default:
                    System.out.println("-- Masukan tidak valid.");
                    break;
            }
        }
    }

    private void uiDisplayDaftarMenu() {
        if (!menu.daftarMenu.isEmpty()) {
            System.out.println("\n\nDaftar Menu");
            System.out.println("----------------------------------");
            System.out.printf("%s %-14s %-14s %-13s %-14s\n", "No", "Nama", "Harga", "Kategori", "Jenis");
            menu.tampilkanMenu();
            return;
        }
        System.out.println("[!] Menu kosong!");
    }

    protected void uiDisplayDaftarDiskon() {
        System.out.println("\n\nDaftar Diskon:");
        System.out.printf("%s %-14s %-14s %-13s %-14s\n", "No", "Nama", "Harga", "Kategori", "Jenis");
        menu.tampilkanDiskon();
        return;
    }

    private void uiAddMenuItem() {
        System.out.println("Masukkan informasi menu.");

        boolean isRunning = true;

        while (isRunning) {
            System.out.print("Kategori (makanan, minuman, diskon): ");
            String inputKategori = scanner.nextLine();

            if (inputKategori.equalsIgnoreCase("makanan")) {
                Makanan makanan = new Makanan();

                makanan.kategori = inputKategori;

                System.out.print("Nama:");
                makanan.nama = scanner.nextLine();

                System.out.print("Harga: ");
                makanan.harga = Double.parseDouble(scanner.nextLine());

                System.out.print("Jenis Makanan: ");
                makanan.jenisMakanan = scanner.nextLine();

                System.out.println("-- Item menu " + makanan.nama + " telah disimpan");
                menu.tambahMenu(makanan);
                isRunning = false;
            } else if (inputKategori.equalsIgnoreCase("minuman")) {
                Minuman minuman = new Minuman();

                minuman.kategori = inputKategori;

                System.out.print("Nama:");
                minuman.nama = scanner.nextLine();

                System.out.print("Harga: ");
                minuman.harga = Double.parseDouble(scanner.nextLine());

                System.out.print("Jenis Minuman: ");
                minuman.jenisMinuman = scanner.nextLine();

                System.out.println("-- Item menu " + minuman.nama + " telah disimpan");
                menu.tambahMenu(minuman);
                isRunning = false;
            } else if (inputKategori.equalsIgnoreCase("diskon")) {
                Diskon diskonContainer = new Diskon();
                diskonContainer.kategori = inputKategori;

                System.out.print("Nama:");
                diskonContainer.nama = scanner.nextLine();

                System.out.print("Presentase Diskon (%): ");
                diskonContainer.diskon = scanner.nextDouble();
                scanner.nextLine(); // menghilangkan newline

                System.out.println("-- Item menu " + diskonContainer.nama + " telah disimpan");
                menu.tambahMenu(diskonContainer);
                isRunning = false;
            } else {
                System.out.println("-- Kategori tidak valid.");
            }
        }
    }

    private void uiEditMenuItem() {
        System.out.println("\n\nMANAJEMEN MENU > HAPUS ITEM MENU");
        System.out.println("----------------------------------");
        System.out.println("Daftar Menu:");
        menu.tampilkanMenu();
        System.out.println("----------------------------------");
        System.out.println("Menu nomor berapa yang mau diedit?");

        boolean isRunning = true;
        while (isRunning) {
            try {
                System.out.print("Pilihan: ");
                String masukanPengguna = scanner.nextLine();

                int index = Integer.parseInt(masukanPengguna) - 1;
                MenuItem menuTerpilih = menu.daftarMenu.get(index);

                System.out.println("Menu item terpilih: " + menuTerpilih.nama);

                if (menuTerpilih.kategori.equalsIgnoreCase("makanan")) {
                    Makanan makanan = new Makanan();

                    makanan.kategori = menuTerpilih.kategori;

                    System.out.print("Nama:");
                    makanan.nama = scanner.nextLine();

                    System.out.print("Harga: ");
                    makanan.harga = Double.parseDouble(scanner.nextLine());

                    System.out.print("Jenis Makanan: ");
                    makanan.jenisMakanan = scanner.nextLine();

                    System.out.println("-- Item menu " + makanan.nama + " berhasil diubah");
                    menu.ubahMenu(index, makanan);
                    isRunning = false;
                } else if (menuTerpilih.kategori.equalsIgnoreCase("minuman")) {
                    Minuman minuman = new Minuman();

                    minuman.kategori = menuTerpilih.kategori;

                    System.out.print("Nama:");
                    minuman.nama = scanner.nextLine();

                    System.out.print("Harga: ");
                    minuman.harga = Double.parseDouble(scanner.nextLine());

                    System.out.print("Jenis Minuman: ");
                    minuman.jenisMinuman = scanner.nextLine();

                    System.out.println("-- Item menu " + minuman.nama + " berhasil diubah");
                    menu.ubahMenu(index, minuman);
                    isRunning = false;
                } else if (menuTerpilih.kategori.equalsIgnoreCase("diskon")) {
                    Diskon diskonContainer = new Diskon();
                    diskonContainer.kategori = menuTerpilih.kategori;

                    System.out.print("Nama:");
                    diskonContainer.nama = scanner.nextLine();

                    System.out.print("Presentase Diskon: ");
                    diskonContainer.diskon = scanner.nextDouble();
                    scanner.nextLine(); // untuk menghapus karakter newline

                    System.out.println("-- Item menu " + diskonContainer.nama + " berhasil diubah");
                    menu.ubahMenu(index, diskonContainer);
                    isRunning = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("-- Input tidak valid. Harap masukkan angka.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("-- Input tidak valid. Pilihan tidak tersedia.");
            } catch (Exception e) {
                System.out.println("-- Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    private void uiDeleteMenuItem() {
        System.out.println("\n\nMANAJEMEN MENU > HAPUS ITEM MENU");
        System.out.println("----------------------------------");
        System.out.println("Daftar Menu:");
        menu.tampilkanMenu();
        System.out.println("----------------------------------");
        System.out.println("Menu nomor berapa yang mau dihapus?");

        boolean isRunning = true;
        while (isRunning) {
            try {
                System.out.print("Pilihan: ");
                String masukanPengguna = scanner.nextLine();

                int index = Integer.parseInt(masukanPengguna) - 1;

                // Validasi apakah indeks berada dalam rentang yang valid
                if (index < 0 || index >= menu.daftarMenu.size()) {
                    System.out.println("-- Pilihan tidak tersedia. Masukkan nomor menu yang valid.");
                } else {
                    String namaItem = menu.daftarMenu.get(index).nama;
                    System.out.println("-- Berhasil menghapus menu: " + namaItem);
                    menu.hapusMenu(index);
                    isRunning = false;
                }
            } catch (NumberFormatException e) {
                // Menangani jika input bukan angka
                System.out.println("-- Input tidak valid. Harap masukkan angka.");
            } catch (IndexOutOfBoundsException e) {
                // Menangani kesalahan indeks jika tidak sengaja terjadi
                System.out.println("-- Pilihan tidak valid. Indeks tidak ditemukan.");
            } catch (Exception e) {
                // Menangani kesalahan umum lainnya
                System.out.println("-- Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
}
