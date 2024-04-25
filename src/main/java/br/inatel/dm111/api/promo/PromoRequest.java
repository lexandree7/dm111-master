package br.inatel.dm111.api.promo;

import br.inatel.dm111.persistence.product.Product;

import java.util.List;

public record PromoRequest(String name,
                           String starting,
                           String expiration,
                           List<Product> products) {
}
