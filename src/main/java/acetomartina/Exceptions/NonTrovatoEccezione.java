package acetomartina.Exceptions;

public class NonTrovatoEccezione extends RuntimeException {

    private static final String MESSAGGIO_STANDARD = "Elemento non trovato nel Database !";

    public NonTrovatoEccezione() {
        super(MESSAGGIO_STANDARD);
    }
    public NonTrovatoEccezione(String message){super(message);}
}
