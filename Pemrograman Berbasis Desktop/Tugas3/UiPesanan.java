import java.util.Scanner;

public class UiPesanan implements Ui {
    public UiPesanan(ManagementMenu managementMenu) {
        this.managementMenu = managementMenu;
    }

    // UI management menu, akses data "menu" juga dari sini
    ManagementMenu managementMenu;

    // membuat order baru
    Order order = new Order();

    Scanner scanner = new Scanner(System.in);

    public UiPesanan(Order order) {
    }

    @Override
    public void main() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n\nMENU PESANANAN PELANGGAN");
            System.out.println("------------------------------------------------------------");

            // print list order yang sudah dimasukkan
            order.printListOrder();
            
            System.out.println("------------------------------------------------------------\n");
            System.out.println("[1] Masukkan Item Menu");
            System.out.println("[2] Edit Jumlah Item Menu");
            System.out.println("[3] Hapus Item Menu");
            System.out.println("[4] Tambah diskon");
            System.out.println("[5] Hapus diskon");
            System.out.println("[6] Tampilkan struk terakhir");
            System.out.println("[98] cetak struk");
            System.out.println("[99] Kembali ke menu utama");

            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // untuk menghapus karakter newline
            switch (pilihan) {
                case 1:
                    formCreatePesanan();
                    break;
                case 2:
                    formEditPesanan();
                    break;
                case 3:
                    formRemovePesanan();
                    break;
                case 4:
                    formTerapkanDiskon();
                    break;
                case 5:
                    formHapusDiskon();
                    break;
                case 6:
                    tampilkanStrukTerakhir();
                    break;
                case 98:
                    formFinishPesanan();
                    break;
                case 99:
                    isRunning = false;
                    break;

                default:
                    System.out.println("-- Masukan invalid");
                    break;
            }
        }
    }

    public void displayListMenu() {
        System.out.println("");
        System.out.printf("%s %-14s %-14s %-13s %-14s\n","No", "Nama", "Harga", "Kategori", "Jenis");
        this.managementMenu.menu.tampilkanMenuNonDiskon();
    }

    public void displayListPesanan() {
        this.order.printListOrder();
    }

    public void formCreatePesanan() {
        displayListMenu();

        System.out.print("Masukkan nama menu: ");
        String nama = scanner.nextLine();
        if (managementMenu.menu.cariMenu(nama) == null) {
            System.out.println("Menu tidak ditemukan");
            return;
        }
        System.out.print("Masukkan jumlah (Qty): ");
        int jumlah = scanner.nextInt();

        // setup order item
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(managementMenu.menu.cariMenu(nama));
        orderItem.setQuantity(jumlah);

        // add to order
        order.addOrderItem(orderItem);
        scanner.nextLine(); // untuk menghapus karakter newline

    }

    public void formEditPesanan() {
        if(order.getDaftarPesanan().isEmpty()) {
            System.out.println("-- Tidak ada pesanan yang dapat diedit");
            return;
        }

        displayListPesanan();
        System.out.print("Masukkan nomor pesanan yang ingin diedit: ");
        int nomorPesanan = scanner.nextInt();
        scanner.nextLine(); // untuk menghapus karakter newline
        if (nomorPesanan < 1 || nomorPesanan > order.getDaftarPesanan().size()) {
            System.out.println("-- Nomor pesanan tidak valid");
            return;
        }
        displayListMenu();
        System.out.print("Masukkan nama menu:");
        String nama = scanner.nextLine();
        if (managementMenu.menu.cariMenu(nama) == null) {
            System.out.println("-- Menu tidak ditemukan");
            return;
        }
        System.out.print("Masukkan jumlah (Qty): ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // untuk menghapus karakter newline
        order.editOrderItem(nomorPesanan - 1, managementMenu.menu.cariMenu(nama), jumlah);
        System.out.println("-- Pesanan berhasil diedit");
    }

    public void formRemovePesanan() {
        if (order.getDaftarPesanan().size() == 0) {
            System.out.println("-- Tidak ada pesanan yang dapat dihapus");
            return;
        }
        displayListPesanan();

        System.out.print("\nMasukkan nomor pesanan yang ingin dihapus: ");
        int nomorPesanan = scanner.nextInt();
        scanner.nextLine(); // unntuk menghapus karakter newline
        if (nomorPesanan < 1 || nomorPesanan > order.getDaftarPesanan().size()) {
            System.out.println("-- Nomor pesanan tidak valid");
            return;
        }
        order.deleteOrderItem(nomorPesanan - 1);
    }

    public void formTerapkanDiskon() {
        if(order.getDaftarPesanan().size() == 0) {
            System.out.println("-- Belum ada pesanan, belum dapat diberi diskon");
            return;
        }

        if (order.getDiskon() != null) {
            System.out.println("-- Diskon sudah diterapkan");
            return;
        }

        managementMenu.uiDisplayDaftarDiskon();

        System.out.print("Pilih diskon:");
        int diskonNomor = scanner.nextInt();
        if (diskonNomor < 1 || diskonNomor > managementMenu.menu.daftarDiskon().size()) {
            System.out.println("Diskon tidak valid");
            return;
        }
        Diskon diskon = managementMenu.menu.daftarDiskon().get(diskonNomor - 1);
        order.setDiskon(diskon);

        scanner.nextLine(); // untuk menghapus karakter newline
        System.out.println("Diskon berhasil diterapkan");
    }

    public void formHapusDiskon() {
        if (order.getDiskon() == null) {
            System.out.println("-- Tidak ada diskon yang dapat dihapus");
            return;
        }
        order.setDiskon(null);
        System.out.println("-- Diskon berhasil dihapus");
    }
    
    public void formFinishPesanan() {
        System.out.println("Pesanan telah selesai");
        order.printStruk();
        order.deleteAllOrderItem();
    }

    public void tampilkanStrukTerakhir() {
        System.out.println("\n\n");
        order.muatStrukDariFile();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Tekan ENTER untuk kembali ke Menu Pemesanan");
        scanner.nextLine();
    }
}
