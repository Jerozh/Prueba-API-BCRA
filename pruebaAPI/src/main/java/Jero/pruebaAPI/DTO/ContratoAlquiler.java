package Jero.pruebaAPI.DTO;

import java.time.LocalDate;

public record ContratoAlquiler(
        double alquilerBase,
        LocalDate fechaInicio,
        int mesesEntreAjustes
) {}