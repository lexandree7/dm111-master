package br.inatel.dm111.persistence.promo;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@Component
public class PromoMemoryRepository implements PromoRepository {

    private Set<Promo> db = new HashSet<>();

    @Override
    public void save(Promo promo) {
        db.add(promo);
    }

    @Override
    public void update(Promo promo) {
        delete(promo.getId());
        save(promo);
    }

    @Override
    public List<Promo> findAll() {
        return db.stream().toList();
    }

    @Override
    public Optional<Promo> findById(String id) {
        return db.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(String id) {
        db.removeIf(p -> p.getId().equals(id));
    }
}
