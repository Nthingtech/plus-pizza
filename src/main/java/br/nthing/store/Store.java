package br.nthing.store;

import br.nthing.delivery.Location;
import io.quarkus.hibernate.panache.PanacheEntity;
import io.quarkus.hibernate.panache.PanacheRepository;
import jakarta.persistence.Entity;
import org.hibernate.annotations.processing.Find;

@Entity
public class Store extends PanacheEntity {

    public String name;
    public String code;

    public Store() {
    }

    public interface Repo extends PanacheRepository<Store> {

        @Find
        Store findByLocation(Location location);
    }
}
