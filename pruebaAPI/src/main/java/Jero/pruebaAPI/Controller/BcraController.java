package Jero.pruebaAPI.Controller;

import Jero.pruebaAPI.DTO.BcraResult;
import Jero.pruebaAPI.Service.BcraApiService;
import Jero.pruebaAPI.DTO.BcraResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static Jero.pruebaAPI.calculos.CalculosUtiles.calcularAlquilerAjustado;
import static Jero.pruebaAPI.calculos.CalculosUtiles.correspondeAjuste;

@RestController
@RequestMapping("/api/bcra")
public class BcraController {

    private final BcraApiService bcraApiService;

    public BcraController(BcraApiService bcraApiService) {
        this.bcraApiService = bcraApiService;
    }

    @GetMapping("/datos")
    public Mono<BcraResponse> getDatos() {
        return bcraApiService.obtenerDatos();
            }
    @GetMapping("/ultimo")
    public Mono<BcraResult> getUltimoDato() {
        return bcraApiService.obtenerDatos()
                .map(response -> response.results().isEmpty() ? null : response.results().get(0));
    }
    @GetMapping("/alquiler-ajustado")
    public Mono<Double> getAlquilerAjustado(
            @RequestParam double alquilerBase,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam int mesesEntreAjustes) {

        LocalDate hoy = LocalDate.now();

        if (!correspondeAjuste(fechaInicio, mesesEntreAjustes, hoy)) {
            return Mono.just(alquilerBase); // Aún no corresponde ajustar
        }

        return bcraApiService.obtenerDatos()
                .map(response -> response.results().isEmpty() ? null : response.results().get(0))
                .map(icl -> calcularAlquilerAjustado(alquilerBase, icl.valor()));
    }
}
