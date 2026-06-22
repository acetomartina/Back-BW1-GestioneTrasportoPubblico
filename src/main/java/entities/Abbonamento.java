package entities;

import enom.PeriodicitàAbbonamento;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class Abbonamento extends TitoloViaggio {

    private static int counter = 0;

    @Column(name = "tessera_associata", nullable = false)
    private Tessera tessera_id;

    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    @Column(nullable = false)
    private PeriodicitàAbbonamento periodicita;

    @Column(name = "validità", nullable = false)
    private boolean valido;

}
