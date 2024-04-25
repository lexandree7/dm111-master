package br.inatel.dm111.api.promo.controller;

import br.inatel.dm111.api.core.ApiException;
import br.inatel.dm111.api.promo.PromoRequest;
import br.inatel.dm111.api.promo.service.PromoService;
import br.inatel.dm111.persistence.promo.Promo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/dm111/promos
@RestController
@RequestMapping("/dm111")
public class PromoController {

    private static final Logger log = LoggerFactory.getLogger(PromoController.class);

    private final PromoService service;

    public PromoController(PromoService service) {
        this.service = service;
    }

    @GetMapping("/promos")
    public ResponseEntity<List<Promo>> getPromos() throws ApiException {
        log.debug("Getting all promos");
        var promos = service.searchPromos();
        return ResponseEntity.ok(promos);
    }

    @GetMapping("/promos/{id}")
    public ResponseEntity<Promo> getPromo(@PathVariable("id") String id) throws ApiException {
        log.debug("Getting the promo by id: " + id);
        var promo = service.searchPromo(id);
        return ResponseEntity.ok(promo);
    }

    @PostMapping("/promos")
    public ResponseEntity<Promo> postPromo(@RequestBody PromoRequest request) {
        var promo = service.createPromo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(promo);
    }

    @PutMapping("/promos/{id}")
    public ResponseEntity<Promo> putPromo(@PathVariable("id") String id,
                                              @RequestBody PromoRequest request) throws ApiException {
        var promo = service.updatePromo(id, request);
        return ResponseEntity.ok(promo);
    }

    @DeleteMapping("/promos/{id}")
    public ResponseEntity<?> deletePromo(@PathVariable("id")String id) throws ApiException {
        service.removePromo(id);
        return ResponseEntity.noContent().build();
    }
}
