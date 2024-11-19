public class Menu {
    private String nama;
    private int harga;
    private String kategori;
    
    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return this.nama;
    }

    public int getHarga() {
        return this.harga;
    }

    public String getKategori() {
        return this.kategori;
    }

    public void cetakMenu() {
        System.out.printf("%-15s %-15s%n", nama, Utils.tampilkanRupiah(harga), kategori);
    }

    public static void cetakMenuBerdasarkanKategori(Menu[] menu, String kategori) {
        for (Menu item : menu) {
            if (item.getKategori().equalsIgnoreCase(kategori)) item.cetakMenu();
        }
    }
}
