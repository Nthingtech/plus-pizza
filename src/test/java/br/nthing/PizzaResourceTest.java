package br.nthing;

import br.nthing.delivery.Location;
import br.nthing.pizza.Pizza;
import br.nthing.pizza.Pizza_;
import br.nthing.store.Store;
import br.nthing.store.Store_;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
class PizzaResourceTest {

    @BeforeEach
    @Transactional
    public void beforeAll() {
        Pizza_.repo().deleteAll();
        Store_.repo().deleteAll();
        var store = new Store();
        store.name = "Plus Pizza";
        store.code = "__default__";
        store.location = Location.current();
        store.persist();

        var pizza1 = new Pizza();
        pizza1.name = "Quatro Queijos";
        pizza1.description = "Provolone, Mussarela, Catupiry e Parmesão";
        pizza1.persist();

        var pizza2 = new Pizza();
        pizza2.name = "Queijos";
        pizza2.description = "Vários queijos kkkkk";
        pizza2.persist();

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
