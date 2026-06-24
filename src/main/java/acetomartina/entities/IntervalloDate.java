package acetomartina.entities;


import java.time.LocalDate;

public record IntervalloDate(LocalDate inizio, LocalDate fine) {
    @Override
    public String toString() {
        return inizio + " → " + fine;
    }
}

