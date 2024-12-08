public class Diskon extends MenuItem {
    public double diskon;

    public void tampilMenu() {
        // System.out.println("Diskon: " + nama + " : " + diskon + "%");
        System.out.printf("%-14s %-14s %-13s %-14s\n", nama, diskon, "-", kategori);
    }
}
