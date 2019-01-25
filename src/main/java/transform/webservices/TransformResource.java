package transform.webservices;

import transform.model.ServiceProvider;
import transform.model.TransformService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/generate")
public class TransformResource {

    private static TransformService transformService = ServiceProvider.getTransformService();

    @POST
    @Produces("application/json")
    public Response getGeneratedBusinessRule(@FormParam("type") String type){

        // Haal business rule op
        int result = Integer.parseInt(type);
        transformService.getBusinessRule(result);

        // Haal constraint op

        // Haal businessrule type op

        // generate businessrule code

        return Response.ok(type).build();
    }

    // testen of restservices het doen
    @GET
    @Produces("application/json")
    public String gettestData(){

        String test = "hallo";
        return test;
    }

}
