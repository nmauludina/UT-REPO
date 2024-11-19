import java.util.ArrayList;
import java.util.Scanner;

public class Pesanan {
    static Scanner scanner = new Scanner(System.in);
    static public void terimaPesanan(ArrayList<String> pesanan, ArrayList<Integer> jumlahPesanan, Menu[] daftarMenu) {

        boolean running = true;
        int index = 0;
        while (running) {
            System.out.printf("Pesanan %s: ", index+1);
            pesanan.add(scanner.nextLine());

            if (pesanan.get(index).equalsIgnoreCase("selesai")) {
                if (index != 0) {
                    running = false;
                    break;
                }
    
                System.out.println("Pesanan pertama tidak boleh kosong. Silahkan pilih salah satu menu untuk dipesan.");
                running = false;
                break;
            }

            boolean menuAda = Pesanan.cekKetersediaanPesanan(daftarMenu, pesanan, index, jumlahPesanan);
            if (!menuAda) { // jika menu tidak tersedia, ulangi pemesanan
                System.out.println(pesanan.toString());
                pesanan.remove(index);
                System.out.println("Menu tidak tersedia atau format salah. Mohon masukkan pesanan dengan menu yang tersedia dan format yang benar.");
                continue;
            }
            
            index++;            
        }      
    }

    public static boolean cekKetersediaanPesanan(Menu[] daftarMenu, ArrayList<String> pesanan,int noPesanan, ArrayList<Integer> jumlahPesanan) {
        boolean ketemu = false;
        for (int index = 0; index < daftarMenu.length - 1; index++) {
            if (ketemu == true){ break; }
            
            String namaPesanan = pesanan.get(noPesanan).split(" = ")[0];
            boolean hasil = daftarMenu[index].getNama().equalsIgnoreCase(namaPesanan);
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
    protected static double totalBiayaPesanan(ArrayList <String> pesanan, ArrayList<Integer> jumlahPesanan, Menu[] daftarMenu, ArrayList<Double> jumlahBiayaPesanan) {        
        if (pesanan.size() == 0) return 0;
        double hargaPesanan;
        for (int indexPesanan = 0; indexPesanan < pesanan.size(); indexPesanan++) {
            for (int indexMenu = 0; indexMenu < daftarMenu.length; indexMenu++) {
                boolean namaPesananDanMenuCocok = daftarMenu[indexMenu].getNama().equalsIgnoreCase(pesanan.get(indexPesanan));
                if (namaPesananDanMenuCocok) {
                    hargaPesanan = daftarMenu[indexMenu].getHarga();
                    jumlahBiayaPesanan.add(hargaPesanan * jumlahPesanan.get(indexPesanan));
                }
            }
        }
        return jumlahBiayaPesanan.stream().mapToDouble(Double::doubleValue).sum(); // totalkan semua biaya pesanan
    }

    protected static double temukanHargaPesanan(int indexPesanan, ArrayList<String> pesanan,Menu[] daftarMenu) {
        for (Menu menu : daftarMenu) {
            boolean namaPesananDanMenuCocok = menu.getNama().equalsIgnoreCase(pesanan.get(indexPesanan));
            if (namaPesananDanMenuCocok) return menu.getHarga();
        }
        return 0;
    }
}
