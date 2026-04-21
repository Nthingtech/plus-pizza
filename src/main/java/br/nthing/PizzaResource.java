package br.nthing;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.awt.*;
import java.util.List;

@Path("/pizza")
public class PizzaResource {

    @Transactional
    public void init(@Observes StartupEvent event) {
        var pizza1 = new Pizza();
        pizza1.name = "Quatro Queijos";
        pizza1.description = "Provolone, Mussarela, Catupiry e Parmesão";
        pizza1.persist();

        var pizza2 = new Pizza();
        pizza2.name = "Queijos";
        pizza2.description = "Vários queijos kkkkk";
        pizza2.persist();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pizza> findAll() {
        return Pizza_.repo().findAllPizza();
    }
}
