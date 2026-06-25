package br.nthing.pizza;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class PizzaExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<ProblemDetail> mapDuplicateName(DuplicatePizzaNameException e) {
        return RestResponse.status(
                RestResponse.Status.CONFLICT,
                new ProblemDetail("Pizza duplicada", 409, e.getMessage())
        );
    }
}
