package br.nthing.pizza;

import br.nthing.utils.TextUtil;
import io.quarkus.hibernate.panache.PanacheEntity;
import io.quarkus.hibernate.panache.PanacheRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "normalizedName"))
public class Pizza extends PanacheEntity {

    @NotBlank
    public String name;

    @NotBlank
    public String description;
    public String normalizedName;

    public Pizza() {
    }

    @PrePersist
    @PreUpdate
    void normalize() {
        name = TextUtil.normalizeSpaces(name);
        normalizedName = name.toLowerCase();
    }

    public interface Repo extends PanacheRepository<Pizza> {

        @Find
        List<Pizza> findAllPizza();

        @HQL("where normalizedName = :normalizedName")
        Optional<Pizza> findByName(String normalizedName);
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