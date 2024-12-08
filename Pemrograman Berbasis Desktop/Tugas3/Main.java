import java.util.Scanner;

public class Main {
    public static Menu menu = new Menu();
    public static Order order = new Order();

    public static void main(String[] args) {
        ManagementMenu managementMenu = new ManagementMenu(menu);
        UiPesanan uiPesanan = new UiPesanan(managementMenu);

        menu.muatDariFile();

        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("SELAMAT DATANG DI RESTORAN BAKSO");
            System.out.println("--------------------------------");
            System.out.println("[1] Manajemen Item Menu");
            System.out.println("[2] Membuat pesanan pelanggan");
            System.out.println("[3] Keluar program");

            System.out.print("Pilihan: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    managementMenu.main();
                    break;

                case "2":
                    uiPesanan.main();
                    break;

                case "3":
                    System.out.println("Keluar dari program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
                    break;
            }
        }

        scanner.close();
    }
}
