import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    static final String NAMA_FILE = "daftar_menu.txt";
    public ArrayList<MenuItem> daftarMenu = new ArrayList<>();

    public void tambahMenu(MenuItem menuItem) {
        daftarMenu.add(menuItem);
        simpanKeFile();
    }

    public void ubahMenu(int index, MenuItem newMenu) {
        if (index >= 0 && index < daftarMenu.size()) {
            daftarMenu.set(index, newMenu);
        }
        simpanKeFile();
    }

    public void hapusMenu(int index) {
        if (index >= 0 && index < daftarMenu.size()) {
            daftarMenu.remove(index);
        }
        simpanKeFile();
    }

    public void tampilkanMenu() {
        int index = 1;
        for (MenuItem menuItem : daftarMenu) {
            System.out.printf("%-2d ", index);
            menuItem.tampilMenu();
            index += 1;
        }
    }

    public void tampilkanMenuNonDiskon() {
        int index = 1;
        for (MenuItem menuItem : daftarMenuNonDiskon()) {
            if (!(menuItem instanceof Diskon)) {
                System.out.printf("%-2d ", index);
                menuItem.tampilMenu();
                index += 1;
            }
        }
    }

    // Ambil menu non diskon saja (makanan, minuman)
    public ArrayList<MenuItem> daftarMenuNonDiskon() {
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        for (MenuItem menuItem : daftarMenu) {
            if (!(menuItem instanceof Diskon)) {
                menu.add(menuItem);
            }
        }
        return menu;
    }

    // Print list diskon saja
    public void tampilkanDiskon() {
        int index = 1;
        ArrayList<Diskon> diskonList = daftarDiskon();
        if (diskonList.size() == 0) {
            System.out.println("Tidak ada diskon");
            return;
        }
        for (Diskon menuItem : diskonList) {
            Diskon diskon = (Diskon) menuItem;
            System.out.printf("%-2d ", index);
            diskon.tampilMenu();
            index++;
        }
    }

    public ArrayList<Diskon> daftarDiskon() {
        ArrayList<Diskon> diskonList = new ArrayList<>();
        for (MenuItem menuItem : daftarMenu) {
            if (menuItem instanceof Diskon) {
                diskonList.add((Diskon) menuItem);
            }
        }
        return diskonList;
    }

    public MenuItem cariMenu(String namaMenu) {
        for (MenuItem menuItem : daftarMenu) {
            if (menuItem.nama.equalsIgnoreCase(namaMenu)) {
                return menuItem;
            }
        }
        return null;
    }

    public void simpanKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            for (MenuItem item : daftarMenu) {
                if (item instanceof Makanan) {
                    Makanan makanan = (Makanan) item;
                    writer.write("Makanan;" + makanan.nama + ";" + makanan.harga + ";" + makanan.kategori + ";"
                            + makanan.jenisMakanan + "\n");
                } else if (item instanceof Minuman) {
                    Minuman minuman = (Minuman) item;
                    writer.write("Minuman;" + minuman.nama + ";" + minuman.harga + ";" + minuman.kategori + ";"
                            + minuman.jenisMinuman + "\n");
                } else if (item instanceof Diskon) {
                    Diskon diskon = (Diskon) item;
                    writer.write("Diskon;" + diskon.nama + ";" + diskon.diskon + ";" + diskon.kategori + "\n");
                }
            }
            System.out.println("-- Daftar menu berhasil disimpan ke file \"" + NAMA_FILE + "\"");
        } catch (IOException e) {
            System.out.println("[!] Gagal menyimpan ke file: " + e.getMessage());
        }
    }

    public void muatDariFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                String[] data = baris.split(";");
                String tipe = data[0];
                if (tipe.equals("Makanan")) {
                    Makanan makanan = new Makanan();
                    makanan.nama = data[1];
                    makanan.harga = Double.parseDouble(data[2]);
                    makanan.kategori = data[3];
                    makanan.jenisMakanan = data[4];

                    daftarMenu.add(makanan);
                } else if (tipe.equals("Minuman")) {
                    Minuman minuman = new Minuman();
                    minuman.nama = data[1];
                    minuman.harga = Double.parseDouble(data[2]);
                    minuman.kategori = data[3];
                    minuman.jenisMinuman = data[4];

                    daftarMenu.add(minuman);
                } else if (tipe.equals("Diskon")) {
                    Diskon diskon = new Diskon();
                    diskon.nama = data[1];
                    diskon.diskon = Double.parseDouble(data[2]);
                    diskon.kategori = data[3];

                    daftarMenu.add(diskon);
                }
            }
            System.out.println("-- Daftar menu berhasil dimuat dari file \"" + NAMA_FILE + "\"\n");
        } catch (IOException e) {
            System.out.println("[!] Gagal memuat file: " + e.getMessage());
        }
    }
}
