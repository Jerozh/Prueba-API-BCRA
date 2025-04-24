package Jero.pruebaAPI.calculos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculosUtiles {
    public static double calcularAlquilerAjustado(double alquilerBase, double icl) {
        return alquilerBase += alquilerBase * (icl / 100.0);
    }

    public static boolean correspondeAjuste(LocalDate fechaInicio, int mesesEntreAjustes, LocalDate hoy) {
        long mesesTranscurridos = ChronoUnit.MONTHS.between(fechaInicio, hoy);
        return mesesTranscurridos > 0 && mesesTranscurridos % mesesEntreAjustes == 0;
    }
}
