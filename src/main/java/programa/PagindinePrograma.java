package programa;

import db.DbVeiksmai;
import model.Knyga;
import service.Utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagindinePrograma {
    public static void main(String[] args) {

        Knyga pirmaKnyga = new Knyga(9, "Dievų Miškas", 200, "Alma Litera", 4.5);
        System.out.println("pirmaKnyga = " + pirmaKnyga);
        try {
            Connection jungtis = DbVeiksmai.prisijungimasPrieDb();
            //DbVeiksmai.idetiKnyga(jungtis, "Mobis Dikas", 512, "Baltos Lankos", 5);
            DbVeiksmai.redaguotiKnyga(jungtis, "Dievų Miškas", 200, "Alma Litera", 4.5, 2);

            //DbVeiksmai.idetiKnyga(jungtis, "Krikštatėvis", 398, "Alma Litera", 5);

            //DbVeiksmai.idetiKnyga(jungtis, "Karo menas", 128, "Obuolys", 4);

            //DbVeiksmai.idetiKnyga(jungtis, "Mėlynoji pasakų knyga", 32, "Margas lapas", 4);

            ArrayList<Knyga> knygos = DbVeiksmai.grazintiVisasKnygas(jungtis);
            System.out.println("knygos = " + knygos);
            Utility.isvestiVisasKnygasIsNaujosEilutes(knygos);

            System.out.println("Išvedu knygas, kurių puslapių skaičius yra didesnis nei 200");
            ArrayList<Knyga> knygosDidesnes = DbVeiksmai.grazintiKnygasDidesniasUz200Puslapiu(jungtis);
            System.out.println("knygosDidesnes = " + knygosDidesnes);
            Utility.isvestiVisasKnygasIsNaujosEilutes(knygosDidesnes);

            jungtis.close();

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Nepavyko prisijungti prie duomenų bazės");
        }
    }




}
