package transform.webservices;

import transform.model.BusinessRule;
import transform.model.ServiceProvider;
import transform.model.TransformService;
import transform.persistence.BusinessRuleDao;
import transform.persistence.BusinessRuleOracleDaoImpl;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.*;
import javax.xml.ws.Response;


@Path("/generate")
public class TransformResource {

//    @POST
//    @Produces("application/json")
//    public Response getGeneratedBusinessRule(@FormParam("rule") String rule){
////        if(rule == "compare"){
////
////        }
//
//
//    }

    private static TransformService transformService = ServiceProvider.getTransformService();

    @GET
    @Produces("text/html")
    public String gettestData(){

        String test = "hallo";
        return test;
    }

}
