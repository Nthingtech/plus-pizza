package br.nthing.pizza;

import io.quarkus.hibernate.panache.PanacheEntity;
import io.quarkus.hibernate.panache.PanacheRepository;

public class Category extends PanacheEntity {



    public interface Repo extends PanacheRepository<Category> {

    }
}
