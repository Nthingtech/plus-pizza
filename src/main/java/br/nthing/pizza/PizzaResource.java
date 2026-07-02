package br.nthing.pizza;

import br.nthing.pizza.exceptions.DuplicatePizzaNameException;
import br.nthing.utils.TextUtil;
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
