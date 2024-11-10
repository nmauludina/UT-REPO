import java.util.Arrays;
import java.util.Scanner;

public class Pesanan {
    static Scanner scanner = new Scanner(System.in);
    static public void terimaPesanan(int index, String[] pesanan, int[] jumlahPesanan, Menu[] daftarMenu) {
        if  (index > jumlahPesanan.length - 1) return;

        System.out.printf("Pesanan %s: ", index+1);
        pesanan[index] = scanner.nextLine();

        if (pesanan[index].equalsIgnoreCase("selesai")) {
            if (index != 0) return;

            System.out.println("Pesanan pertama tidak boleh kosong. Silahkan pilih salah satu menu untuk dipesan.");
            terimaPesanan(index, pesanan, jumlahPesanan, daftarMenu);
            return;
        }
        
        boolean menuAda = Pesanan.cekKetersediaanPesanan(0, daftarMenu, pesanan, index, jumlahPesanan);
        if (!menuAda) { // jika menu tidak tersedia, ulangi pemesanan
            System.out.println("Menu tidak tersedia atau format salah. Mohon masukkan pesanan dengan menu yang tersedia dan format yang benar.");
            terimaPesanan(index, pesanan, jumlahPesanan, daftarMenu);
            return;
        }

        terimaPesanan(index + 1, pesanan, jumlahPesanan, daftarMenu);
    }

    public static boolean cekKetersediaanPesanan(int index, Menu[] daftarMenu, String[] pesanan,int noPesanan, int[] jumlahPesanan) {
        if (index > daftarMenu.length - 1) return false;
        
        String namaPesanan = pesanan[noPesanan].split(" = ")[0];
        boolean hasil = daftarMenu[index].getNama().equalsIgnoreCase(namaPesanan);
        if (hasil) {
            jumlahPesanan[noPesanan] = Integer.parseInt(pesanan[noPesanan].split(" = ")[1]);
            pesanan[noPesanan] = namaPesanan;
            return true;
        }

        return cekKetersediaanPesanan(index+1, daftarMenu, pesanan, noPesanan, jumlahPesanan);
    }


    protected static double totalBiayaPesanan(int indexPesanan, String[] pesanan, int[] jumlahPesanan, Menu[] daftarMenu, double[] jumlahBiayaPesanan) {        
        if (indexPesanan > pesanan.length - 1) // jika index melebihi panjang array pesanan
            return Arrays.stream(jumlahBiayaPesanan).sum(); // totalkan semua biaya pesanan

        jumlahBiayaPesanan[indexPesanan] = temukanHargaPesanan(0, indexPesanan, pesanan, daftarMenu) * jumlahPesanan[indexPesanan];
        
        return totalBiayaPesanan(indexPesanan + 1, pesanan, jumlahPesanan, daftarMenu, jumlahBiayaPesanan);
    }

    protected static double temukanHargaPesanan(int index, int indexPesanan, String[] pesanan,Menu[] daftarMenu) {
        if (index > daftarMenu.length - 1 || indexPesanan > pesanan.length - 1) return 0;

        boolean namaPesananDanMenuCocok = daftarMenu[index].getNama().equalsIgnoreCase(pesanan[indexPesanan]);
        if (namaPesananDanMenuCocok) return daftarMenu[index].getHarga();
    
        return temukanHargaPesanan(index + 1, indexPesanan, pesanan, daftarMenu);
    }
}
