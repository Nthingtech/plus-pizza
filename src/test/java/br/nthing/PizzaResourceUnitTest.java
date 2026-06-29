package br.nthing;

import br.nthing.delivery.Location;
import br.nthing.pizza.Pizza;
import br.nthing.pizza.PizzaResource;
import br.nthing.store.Store;
import br.nthing.store.Store_;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class PizzaResourceUnitTest {

    @Inject
    PizzaResource pizzas;

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
        var location = Location.current();
        var store = Store_.repo().findNearest(location);

    }
}
