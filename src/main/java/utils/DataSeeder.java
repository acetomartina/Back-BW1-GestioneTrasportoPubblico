package utils;

import acetomartina.DAO.*;
import acetomartina.entities.Mezzo;
import acetomartina.entities.PuntoEmissione;
import acetomartina.enums.TipoPuntoEmissione;

public class DataSeeder {

    public static void popolaDatabase(
            UtenteDao utenteDao,
            MezzoDao mezzoDao,
            PuntoEmissioneDao puntoEmissioneDao,
            TesseraDao tesseraDao,
            TitoloViaggioDao titoloViaggioDao,
            TrattaDao trattaDao){

        popolaPuntiEmissione(puntoEmissioneDao);
        popolaMezzi(mezzoDao);
        popolaTratte(trattaDao);
    }

    public static void popolaPuntiEmissione(PuntoEmissioneDao puntoEmissioneDao){

        PuntoEmissione p1 = new PuntoEmissione(
                TipoPuntoEmissione.DISTRIBUTORE,true
        );

        PuntoEmissione p2 = new PuntoEmissione(
                TipoPuntoEmissione.DISTRIBUTORE,true
        );

        PuntoEmissione p3 = new PuntoEmissione(
                TipoPuntoEmissione.DISTRIBUTORE,false
        );

        PuntoEmissione p4 = new PuntoEmissione(
                TipoPuntoEmissione.RIVENDITORE,true
        );

        PuntoEmissione p5 = new PuntoEmissione(
                TipoPuntoEmissione.RIVENDITORE,true
        );

        PuntoEmissione p6 = new PuntoEmissione(
                TipoPuntoEmissione.RIVENDITORE,false
        );

        puntoEmissioneDao.save(p1);
        puntoEmissioneDao.save(p2);
        puntoEmissioneDao.save(p3);
        puntoEmissioneDao.save(p4);
        puntoEmissioneDao.save(p5);
        puntoEmissioneDao.save(p6);

        System.out.println("Punti emissione caricati!");


    }

    private static void popolaMezzi(MezzoDao mezzoDao){}

    private static void popolaTratte(TrattaDao trattaDao){}
}
