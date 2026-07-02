package br.nthing;

import br.nthing.delivery.Location;
import br.nthing.pizza.Pizza;
import br.nthing.pizza.PizzaResource;
import br.nthing.pizza.Pizza_;
import br.nthing.store.Store;
import br.nthing.store.Store_;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PizzaResourceUnitTest {

    @Inject
    PizzaResource pizzas;

    @BeforeEach
    @Transactional
    public void setup() {
        Pizza_.repo().deleteAll();
        Store_.repo().deleteAll();

        var store = new Store();
        store.name = "Plus Pizza";
        store.code = "__default__";
        store.location = Location.current();
        store.persist();

        var pizza = new Pizza();
        pizza.name = "Quatro Queijos";
        pizza.description = "Provolone, Mussarela, Catupiry e Parmesão";
        pizza.persist();
    }

    @Test
    void testSanity() {
        // GIVEN
        // WHEN
        List<Pizza> ps = pizzas.findAll();

        // THEN
        assertFalse(ps.isEmpty());
    }

    @Test
    void testOrderPizzaFlow() {

        //GIVEN
        var location = Location.current();

        //WHEN
        var store = Store_.repo().findByNearestLocation(location);

        //THEN
        assertNotNull(store);

    }
}
