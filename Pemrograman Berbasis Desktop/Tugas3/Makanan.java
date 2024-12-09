public class Makanan extends MenuItem {
    public String jenisMakanan;

    @Override
    public void tampilMenu() {
        super.tampilMenu();
        System.out.printf("%-14s\n", jenisMakanan);
    }
}
