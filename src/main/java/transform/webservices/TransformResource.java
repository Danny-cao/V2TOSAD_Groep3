package transform.webservices;

import com.sun.org.apache.xpath.internal.operations.Bool;
import transform.model.*;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/generate")
public class TransformResource {

    private TransformService transformService = ServiceProvider.getTransformService();

    @POST
    @Produces("application/json")
    public Response getGeneratedBusinessRule(@FormParam("rules") String type){

        // Haal business rule op
        int result = Integer.parseInt(type);
        BusinessRule businessRule = transformService.getBusinessRule(result);

        // generate businessrule code

        Boolean transform = transformService.transform(businessRule);
        System.out.println(transform);

        Gson gson = new Gson();
        String json = gson.toJson(businessRule);

        //return Response.status(200).entity(businessRuleType).build();
        return Response.ok(json).build();
    }

    // testen of restservices het doen
    @GET
    @Produces("application/json")
    public String gettestData(){

        BusinessRuleType businessRuleType = transformService.getBusinessRuleType(1);

        Gson gson = new Gson();

        String result = gson.toJson(businessRuleType);

        return result;
    }

}
