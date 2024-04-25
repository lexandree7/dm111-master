package br.inatel.dm111.persistence.promo;


import br.inatel.dm111.persistence.product.Product;

import java.util.List;

//{
//    "id": "",
//    "name": "Detergente, maça",
//    "starting": "24/04/2024
//    "expiration": "30/04/2024
//    "products": "123", "15",
//}
public class Promo {
    private String id;
    private String name;
    private String starting;
    private String expiration;
    private List<Product> products;



    public Promo() {
    }

    public Promo(String id, String name, String starting, String expiration, List<Product> products
    ) {
        this.id = id;
        this.name = name;
        this.starting = starting;
        this.expiration = expiration;
        this.products = products;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }




}
