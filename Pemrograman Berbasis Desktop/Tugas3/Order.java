import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private ArrayList<OrderItem> daftarPesanan = new ArrayList<>();
    private Diskon diskon;
    private static final double PAJAK = 0.1;
    private static final double BIAYA_PELAYANAN = 20000;
    private static final String NAMA_FILE_STRUK = "struk.txt";

    public ArrayList<OrderItem> getDaftarPesanan() {
        return daftarPesanan;
    }

    public void addOrderItem(OrderItem orderItem) {
        daftarPesanan.add(orderItem);
    }

    public void deleteOrderItem(int index) {
        if (index >= 0 && index < daftarPesanan.size()) {
            daftarPesanan.remove(index);
        }
    }

    public void deleteAllOrderItem() {
        daftarPesanan.clear();
    }

    private double calculateSubtotalOrders() {
        double subtotal = 0;
        for (OrderItem item : daftarPesanan) {
            subtotal += item.getOrderPrice();
        }
        return subtotal;
    }

    private double calculateTotalDiskon() {
        return calculateSubtotalOrders() * (diskon != null ? diskon.diskon / 100 : 0);
    }

    private double calculateTotalPajak() {
        return PAJAK * calculateSubtotalOrders();
    }

    private double calculateTotalOrders() {
        double subtotal = calculateSubtotalOrders();
        double totalDiskon = calculateTotalDiskon();
        return subtotal - totalDiskon + calculateTotalPajak() + BIAYA_PELAYANAN;
    }

    public void printListOrder() {
        System.out.println("Daftar Pesanan:");
        if (daftarPesanan.isEmpty()) System.out.println("Pesanan kosong");
        else {
            int index = 1;

            System.err.printf("%-2s %-14s %-14s %-13s %-14s\n", "No", "Nama", "Jumlah", "Harga", "Total");
            for (OrderItem item : daftarPesanan) {
                System.out.printf("%d. %-14s x%-13d %-13s %-14s\n", 
                                    index, 
                                    item.getMenuItem().nama, 
                                    item.getQuantity(), 
                                    Utils.tampilkanRupiah(item.getMenuItem().harga), 
                                    Utils.tampilkanRupiah(item.getOrderPrice()));
                index += 1;
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("Subtotal: " + Utils.tampilkanRupiah(calculateSubtotalOrders()));
    
            // Print diskon jika ada
            if (diskon != null) {
                System.out.println("Diskon: " + Utils.tampilkanRupiah(calculateTotalDiskon()) +" (" + diskon.nama + ")");
            }
        }
    }

    public void printStruk() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("-- Pesanan kosong, tidak dapat mencetak struk");
            return;
        }

        System.out.println("\n\n");
        System.out.println("STRUK PEMBAYARAN");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-14s %-14s %-13s %-14s\n", "Nama", "Jumlah", "Harga", "Total");
        for (OrderItem item : daftarPesanan) {
            System.out.printf("%-14s x%-13d %-13s %-14s\n",
                            item.getMenuItem().nama, 
                            item.getQuantity(), 
                            Utils.tampilkanRupiah(item.getMenuItem().harga), 
                            Utils.tampilkanRupiah(item.getOrderPrice()));
        }
        System.out.println("------------------------------------------------------------");

        System.out.println("Subtotal: " + Utils.tampilkanRupiah(calculateSubtotalOrders()));
        System.out.println("Diskon: " + Utils.tampilkanRupiah(calculateTotalDiskon()));
        System.out.println("Pajak: " + Utils.tampilkanRupiah(calculateTotalPajak()));
        System.out.println("Biaya Layanan: " + Utils.tampilkanRupiah(BIAYA_PELAYANAN));
        System.out.println("------------------------------------------------------------");

        System.out.println("TOTAL: " + Utils.tampilkanRupiah(calculateTotalOrders()));
        System.out.println();
        System.out.println("Terima kasih telah berkunjung!");

        simpanStrukKeFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tekan ENTER untuk kembali ke Menu Pemesanan");
        scanner.nextLine();
    }

    // Getters and Setters
    public Diskon getDiskon() {
        return diskon;
    }

    public void setDiskon(Diskon diskon) {
        this.diskon = diskon;
    }

    public void editOrderItem(int i, MenuItem cariMenu, int jumlah) {
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(cariMenu);
        orderItem.setQuantity(jumlah);
        daftarPesanan.set(i, orderItem);
    }

    private void simpanStrukKeFile() {
        try (FileWriter writer = new FileWriter(NAMA_FILE_STRUK)) {
            writer.write("STRUK PEMBAYARAN\n");
            writer.write("------------------------------------------------------------\n");
            String teksJudul = String.format("%-14s %-14s %-13s %-14s\n", "Nama", "Jumlah", "Harga", "Total");
            writer.write(teksJudul);
            for (OrderItem item : daftarPesanan) {
                String teks =  String.format("%-14s x%-13s %-13s %-14s\n",
                                    item.getMenuItem().nama,
                                    item.getQuantity(),
                                    Utils.tampilkanRupiah(item.getMenuItem().harga),
                                    Utils.tampilkanRupiah(item.getOrderPrice()));
                writer.write(teks);
            }
            writer.write("------------------------------------------------------------\n");
            writer.write("Subtotal: " + Utils.tampilkanRupiah(calculateSubtotalOrders()) + "\n");
            writer.write("Diskon: " + Utils.tampilkanRupiah(calculateTotalDiskon()) + "\n");
            writer.write("Pajak: " + Utils.tampilkanRupiah(calculateTotalPajak()) + "\n");
            writer.write("Biaya Layanan: " + Utils.tampilkanRupiah(BIAYA_PELAYANAN) + "\n");
            writer.write("------------------------------------------------------------\n");
            writer.write("TOTAL: " + Utils.tampilkanRupiah(calculateTotalOrders()) + "\n");
            writer.write("\nTerima kasih telah berkunjung!\n");
        } catch(IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan struk ke file.");
        }
    }

    public void muatStrukDariFile() {
        File file = new File(NAMA_FILE_STRUK);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File struk tidak ditemukan.");
        }
    }
}
