package br.nthing.pizza;

import br.nthing.utils.TextUtil;
import io.quarkus.hibernate.panache.PanacheEntity;
import io.quarkus.hibernate.panache.PanacheRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;

import java.util.List;
import java.util.Objects;

@Entity
public class Pizza extends PanacheEntity {

    public String name;
    public String description;

    public Pizza() {
    }

    @PrePersist
    @PreUpdate
    void normalize() {
        name = TextUtil.normalizeSpaces(name);
    }

    public interface Repo extends PanacheRepository<Pizza> {

        @Find
        List<Pizza> findAllPizza();

       @HQL("where lower(name) = lower(:name)")
       Pizza findByName(String name);
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
