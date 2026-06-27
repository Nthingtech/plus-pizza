package br.nthing.pizza;

import br.nthing.pizza.exceptions.DuplicatePizzaNameException;
import br.nthing.utils.TextUtil;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pizza")
public class PizzaResource {

    @Transactional
    @IfBuildProfile("dev")
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
        return Pizza_.repo().listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Pizza pizza) {
        String key = TextUtil.normalizeSpaces(pizza.name).toLowerCase();

        if (Pizza_.repo().findByName(key) != null) {
            throw new DuplicatePizzaNameException("Já existe uma pizza \"" + pizza.name + "\"");
        }

        pizza.persist();
        return Response.status(Response.Status.CREATED).entity(pizza).build();
    }
}
