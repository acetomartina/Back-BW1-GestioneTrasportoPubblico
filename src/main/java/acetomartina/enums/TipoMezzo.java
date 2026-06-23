package acetomartina.enums;

public enum TipoMezzo {

        TRAM(Integer.MAX_VALUE, TipoBiglietto.GENERICO),
        AUTOBUS(100, TipoBiglietto.SINGOLATRATTA);

        private final int capienzaMassima;
        private final TipoBiglietto tipoBiglietto;

        TipoMezzo(int capienzaMassima, TipoBiglietto tipoBiglietto) {
            this.capienzaMassima = capienzaMassima;
            this.tipoBiglietto = tipoBiglietto;
        }

        public int getCapienzaMassima() {
            return capienzaMassima;
        }

        public TipoBiglietto getTipoBiglietto() {
            return tipoBiglietto;
        }
    }

