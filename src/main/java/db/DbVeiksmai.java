package db;

import model.Knyga;

import java.sql.*;
import java.util.ArrayList;

public class DbVeiksmai {
    public final static String DB_PAVADINIMAS = "jdbc:mysql://localhost:3306/knyga?useUnicode=true&characterEncoding=UTF-8";
    public final static String DB_USER = "root";
    public final static String DB_PASSWORD = "";

    public final static String KNYGOS_IDEJIMAS = "INSERT INTO knyga(pavadinimas, puslapiu_skaicius, leidejas, ivertinimas) VALUES (?, ?, ?, ?)";
    public final static String KNYGOS_ISTRYNIMAS_ID = "DELETE FROM knyga WHERE id = ?";
    public final static String KNYGOS_REDAGAVIMAS_ID = "UPDATE knyga\n" +
            "Set pavadinimas = ?, puslapiu_skaicius = ?, leidejas = ?, ivertinimas = ?\n" +
            "WHERE id = ?";
    public final static String VISU_KNYGU_GAVIMAS = "SELECT * FROM knyga";
    public final static String KNYGOS_GAVIMAS_PAVADINIMA = "SELECT FROM knyga WHERE name = ?";
    public final static String KNYGOS_GAVIMAS_PAGAL_PUSLAPIU_SKAICIU = "SELECT * FROM knyga WHERE puslapiu_skaicius > 200";

    public static Connection prisijungimasPrieDb() throws SQLException {
        Connection jungtis = DriverManager.getConnection(DB_PAVADINIMAS, DB_USER, DB_PASSWORD);
        return jungtis;
    }

    public static boolean idetiKnyga(Connection jungtis, String pavadinimas, int puslapiuSkaicius, String leidejas, double ivertinimas) throws SQLException {

        PreparedStatement paruostukas = jungtis.prepareStatement(KNYGOS_IDEJIMAS);

        paruostukas.setString(1, pavadinimas);
        paruostukas.setInt(2, puslapiuSkaicius);
        paruostukas.setString(3, leidejas);
        paruostukas.setDouble(4, ivertinimas);

        boolean ats = paruostukas.execute();
        paruostukas.close();

        return ats;
    }

    public static boolean redaguotiKnyga(Connection jungtis, String pavadinimas, int puslapiuSkaicius, String leidejas, double ivertinimas, int id) throws SQLException {

        PreparedStatement paruostukas = jungtis.prepareStatement(KNYGOS_REDAGAVIMAS_ID);

        paruostukas.setString(1, pavadinimas);
        paruostukas.setInt(2, puslapiuSkaicius);
        paruostukas.setString(3, leidejas);
        paruostukas.setDouble(4, ivertinimas);
        paruostukas.setInt(5, id);

        boolean ats = paruostukas.execute();
        paruostukas.close();

        return ats;

    }

    public static ArrayList<Knyga> grazintiKnygasDidesniasUz200Puslapiu (Connection jungtis) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(KNYGOS_GAVIMAS_PAGAL_PUSLAPIU_SKAICIU);
        ResultSet rezultatas = paruostukas.executeQuery();

        ArrayList<Knyga> knygos = new ArrayList<>();

        while (rezultatas.next()) {
            int id = rezultatas.getInt("id");
            String pavadinimas = rezultatas.getString("pavadinimas");
            int puslapiuSkaicius = rezultatas.getInt("puslapiu_skaicius");
            String leidejas = rezultatas.getNString("leidejas");
            double ivertinimas = rezultatas.getDouble("ivertinimas");

            Knyga didesneUz = new Knyga(id, pavadinimas, puslapiuSkaicius,leidejas, ivertinimas);

            knygos.add(didesneUz);
        }
        return knygos;
    }

    public static ArrayList<Knyga> grazintiVisasKnygas(Connection jungtis) throws SQLException {
        ArrayList<Knyga> knygos = new ArrayList<>();

        PreparedStatement paruostukas = jungtis.prepareStatement(VISU_KNYGU_GAVIMAS);

        ResultSet rezultatas = paruostukas.executeQuery();

        while (rezultatas.next()) {
            int id = rezultatas.getInt("id");
            String pavadinimas = rezultatas.getString("pavadinimas");
            int puslapiuSkaicius = rezultatas.getInt("puslapiu_skaicius");
            String leidejas = rezultatas.getNString("leidejas");
            double ivertinimas = rezultatas.getDouble("ivertinimas");

            Knyga knyga = new Knyga(id, pavadinimas, puslapiuSkaicius, leidejas, ivertinimas);

            knygos.add(knyga);
        }
        return knygos;
    }
}


