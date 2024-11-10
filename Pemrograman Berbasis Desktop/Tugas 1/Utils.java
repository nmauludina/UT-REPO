import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/* Kelas Utils
 * 
 * Adalah kelas untuk mempersingkat program, agar program terlihat bersih dan fokus kepada permasalahannya.
 * 
 */
public class Utils {
    public static String tampilkanRupiah(double harga) {
        Locale loc = new Locale("ID");
        NumberFormat idrFormatter = NumberFormat.getCurrencyInstance(loc);

        Currency idr = Currency.getInstance("IDR");
        idrFormatter.setCurrency(idr);
        String formatRupiah = idrFormatter.format(harga);

        return formatRupiah;
    }

    public static String kapitalisasiHurufPertama(String kata) {
        return kata.substring(0, 1).toUpperCase() + kata.substring(1);
    }
}
