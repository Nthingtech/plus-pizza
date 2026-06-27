package br.nthing;

import br.nthing.pizza.Pizza;
import br.nthing.pizza.PizzaResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
class PizzaResourceTest {
    @Inject
    PizzaResource pizzaResource;

    @Test
    void testPizzaEndpoint() {
        given()
                .when().get("/pizza")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("name", hasItems("Quatro Queijos", "Queijos"));
    }

    @Test
    public void testSanity() {
        List<Pizza> ps = pizzaResource.findAll();
        assertFalse(ps.isEmpty());
    }
}
