public class Diskon extends MenuItem {
    public double diskon;

    @Override
    public void tampilMenu() {
        System.out.printf("%-14s %-14s %-13s %-14s\n", nama, diskon, "-", kategori);
    }
}
