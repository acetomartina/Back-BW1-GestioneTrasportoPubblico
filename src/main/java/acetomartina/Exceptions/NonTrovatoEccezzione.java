package acetomartina.Exceptions;

public class NonTrovatoEccezzione extends RuntimeException {

    private static final String MESSAGGIO_STANDARD = "Elemento non trovato nel Database !";

    public NonTrovatoEccezzione() {
        super(MESSAGGIO_STANDARD);
    }
    public NonTrovatoEccezzione(String message){super(message);}
}
