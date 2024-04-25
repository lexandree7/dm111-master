package br.inatel.dm111.api.promo.service;

import br.inatel.dm111.api.core.ApiException;
import br.inatel.dm111.api.core.AppErrorCode;
import br.inatel.dm111.api.promo.PromoRequest;
import br.inatel.dm111.persistence.promo.Promo;
import br.inatel.dm111.persistence.promo.PromoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PromoService {

    private final PromoRepository repository;

    public PromoService(PromoRepository repository) {
        this.repository = repository;
    }

    public List<Promo> searchPromos() throws ApiException {
        try {
            return repository.findAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOS_QUERY_ERROR);
        }
    }

    public Promo searchPromo(String id) throws ApiException {
        return retrievePromo(id);
    }

    public Promo createPromo(PromoRequest request) {
        var promo = buildPromo(request);
        repository.save(promo);
        return promo;
    }

    public Promo updatePromo(String id, PromoRequest request) throws ApiException {
        var promo = retrievePromo(id);
        promo.setName(request.name());
        promo.setProducts(request.products());
        promo.setStarting(request.starting());
        promo.setExpiration(request.expiration());

        repository.update(promo);
        return promo;
    }

    public void removePromo(String id) throws ApiException {
        try {
            repository.delete(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOS_QUERY_ERROR);
        }
    }

    private Promo retrievePromo(String id) throws ApiException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ApiException(AppErrorCode.PROMO_NOT_FOUND));
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOS_QUERY_ERROR);
        }
    }

    private Promo buildPromo(PromoRequest request) {
        var id = UUID.randomUUID().toString();
        return new Promo(id,
                request.name(),
                request.starting(),
                request.expiration(),
                request.products());
    }
}
