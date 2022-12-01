package teste;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.UtilizaDAO;

public class TesteDataHora {
    public static void main(String[] args) {
        UtilizaDAO dao = new UtilizaDAO();
        LocalDate localDate = LocalDate.now();
        LocalTime localHora = LocalTime.now();

        System.out.println(localDate);
        System.out.println(localHora);
        // try {
        //     dao.salvardatahora(localDate, localHora);
        // } catch (SQLException e) {
        //     // TODO Auto-generated catch block
        //    System.out.println(e.getMessage());
        // }
    }

}
