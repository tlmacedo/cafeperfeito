import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Temp {


    public static void main(String[] args) {
        StringBuffer buf = new StringBuffer("versão 1");
        chamaMetodoPorReferencia1(buf);
        System.out.println(buf);
        chamaMetodoPorReferencia2(buf);
        System.out.println(buf);

        DateTimeFormatter DTF_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dt1 = LocalDate.parse("03/12/2018", DTF_DATA);
        LocalDate dt2 = LocalDate.parse("06/12/2018", DTF_DATA);

        System.out.printf("now: [%s]\n", LocalDate.now());
        System.out.printf("dt1: [%s]\n", dt1);
        System.out.printf("dt2: [%s]\n", dt2);

        if (dt1.isAfter(LocalDate.now())) {
            System.out.printf("dt1 isAfter to now\n");
        }
        if (dt1.isBefore(LocalDate.now())) {
            System.out.printf("dt1 isBefore to now\n");
        }

        if (dt2.isAfter(LocalDate.now())) {
            System.out.printf("dt2 isAfter to now\n");
        }
        if (dt2.isBefore(LocalDate.now())) {
            System.out.printf("dt2 isBefore to now\n");
        }
    }

    public static void chamaMetodoPorReferencia1(StringBuffer x) {
        x.append("\npassou por aqui.");
    }

    public static void chamaMetodoPorReferencia2(StringBuffer x) {
        x = new StringBuffer("versão 2");
    }


}
