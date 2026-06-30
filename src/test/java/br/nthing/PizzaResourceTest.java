package br.nthing;

import br.nthing.delivery.Location;
import br.nthing.pizza.Pizza;
import br.nthing.pizza.PizzaResource;
import br.nthing.store.Store;
import br.nthing.store.Store_;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class PizzaResourceTest {
    @Inject
    PizzaResource pizzaResource;

    @BeforeEach
    @Transactional
    public void beforeAll() {
        Store_.repo().deleteAll();
        var store = new Store();
        store.name = "Plus Pizza";
        store.code = "__default__";
        store.location = Location.current();
        store.persist();

    }

    @Test
    void testFindNearestStore() {
        //GIVEN
        var location = Location.current();

        //WHEN
        var store = Store_.repo().findByNearestLocation(location);

        //THEN
        assertNotNull(store);

    }

    @Test
    void testPizzaEndpoint() {
        given()
                .when().get("/pizza")
                .then()
                .statusCode(200)
                .body("name", hasItems("Quatro Queijos", "Queijos"));
    }


}
