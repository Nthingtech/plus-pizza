package br.nthing;

import io.quarkus.hibernate.panache.PanacheEntity;
import io.quarkus.hibernate.panache.PanacheRepository;
import jakarta.persistence.Entity;
import org.hibernate.annotations.processing.Find;

import java.util.List;
import java.util.Objects;

@Entity
public class Pizza extends PanacheEntity {

    public String name;
    public String description;

    public Pizza() {
    }

    public interface Repo extends PanacheRepository<Pizza> {

        @Find
        List<Pizza> findAllPizza();//teste
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
