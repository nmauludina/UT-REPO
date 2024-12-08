public class Minuman extends MenuItem {
    public String jenisMinuman;

    @Override
    public void tampilMenu() {
        super.tampilMenu();
        // System.out.println(", Jenis Minuman: " + jenisMinuman);
        System.out.printf("%-14s\n", jenisMinuman);
    }
}
