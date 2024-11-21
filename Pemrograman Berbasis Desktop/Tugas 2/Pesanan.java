import java.util.ArrayList;
import java.util.Scanner;

public class Pesanan {
    static Scanner scanner = new Scanner(System.in);
    static public void terimaPesanan(ArrayList<String> pesanan, ArrayList<Integer> jumlahPesanan, ArrayList<Menu> daftarMenu) {
        boolean running = true;
        int index = 0;

        do {
            System.out.printf("Pesanan %s: ", index+1);

            if (pesanan.size() == 1 && pesanan.get(0).equalsIgnoreCase("selesai")) {
                pesanan.set(index, scanner.nextLine());       
            } else {
                pesanan.add(index, scanner.nextLine());
            }

            if (pesanan.get(index).equalsIgnoreCase("selesai")) {
                if (index != 0) {
                    running = false;
                    break;
                }
                
                System.out.println("Pesanan pertama tidak boleh kosong. Silahkan pilih salah satu menu untuk dipesan.");
                running = true;
                continue;
            }

            boolean menuAda = Pesanan.cekKetersediaanPesanan(daftarMenu, pesanan, index, jumlahPesanan);
            if (!menuAda) { // jika menu tidak tersedia, ulangi pemesanan
                System.out.println("(!) Menu tidak tersedia atau format salah. Mohon masukkan pesanan dengan menu yang tersedia dan format yang benar.");
                pesanan.remove(pesanan.size()-1);
                continue;
            }
            index++;

        } while (pesanan.size() == 1 || pesanan.get(index-1) != "selesai");
    }

    public static boolean cekKetersediaanPesanan(ArrayList<Menu> daftarMenu, ArrayList<String> pesanan,int noPesanan, ArrayList<Integer> jumlahPesanan) {
        boolean ketemu = false;
        for (int index = 0; index < daftarMenu.size() - 1; index++) {
            if (ketemu == true){ break; }
            
            String namaPesanan = pesanan.get(noPesanan).split(" = ")[0];
            boolean hasil = daftarMenu.get(index).getNama().equalsIgnoreCase(namaPesanan);
            if (hasil) {
                int tempJumlahPesanan = Integer.parseInt(pesanan.get(noPesanan).split(" = ")[1]);
                jumlahPesanan.add(tempJumlahPesanan);
                pesanan.set(noPesanan,namaPesanan);
                ketemu =  true;
            } else { ketemu = false; }
        }
        return ketemu;
    }

    /* jumlah biaya pesanan = array yang menyimpan biaya2 pesanan */
    protected static double totalBiayaPesanan(ArrayList <String> pesanan, ArrayList<Integer> jumlahPesanan, ArrayList<Menu> daftarMenu, ArrayList<Double> jumlahBiayaPesanan) {        
        if (pesanan.size() == 0) return 0;
        double hargaPesanan;
        for (int indexPesanan = 0; indexPesanan < pesanan.size(); indexPesanan++) {
            for (int indexMenu = 0; indexMenu < daftarMenu.size(); indexMenu++) {
                boolean namaPesananDanMenuCocok = daftarMenu.get(indexMenu).getNama().equalsIgnoreCase(pesanan.get(indexPesanan));
                if (namaPesananDanMenuCocok) {
                    hargaPesanan = daftarMenu.get(indexMenu).getHarga();
                    jumlahBiayaPesanan.add(hargaPesanan * jumlahPesanan.get(indexPesanan));
                }
            }
        }
        return jumlahBiayaPesanan.stream().mapToDouble(Double::doubleValue).sum(); // totalkan semua biaya pesanan
    }

    protected static double temukanHargaPesanan(int indexPesanan, ArrayList<String> pesanan,ArrayList<Menu> daftarMenu) {
        for (Menu menu : daftarMenu) {
            boolean namaPesananDanMenuCocok = menu.getNama().equalsIgnoreCase(pesanan.get(indexPesanan));
            if (namaPesananDanMenuCocok) return menu.getHarga();
        }
        return 0;
    }
}
