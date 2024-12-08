public abstract class MenuItem {
    public String nama;
    public double harga;
    public String kategori;

    // harusnya deklarasi aja
    public void tampilMenu() {
        System.out.printf("%-14s %-14s %-14s", nama, harga, kategori);
    }
}
