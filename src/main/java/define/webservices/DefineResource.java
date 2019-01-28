package define.webservices;


import com.google.gson.Gson;
import define.model.Attribute_Compare;
import define.model.BusinessRule;
import define.model.DefineService;
import define.model.ServiceProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/define")
public class DefineResource {

        private DefineService defineService = ServiceProvider.getDefineService();

        @GET
        @Path("/businessrules")
        @Produces("application/json")
        public Response getAllBusinessRules(){

                List<BusinessRule> rules = defineService.getAllBusinessRules();
                Gson gson = new Gson();
                String json = gson.toJson(rules);

                return Response.ok(json).build();
        }

        @POST
        @Path("/selecteren")
        @Produces("application/json")
        public Response getBusinessRuleWithParam(@FormParam("name") String naam, @FormParam("table") String table, @FormParam("type") String type){

                int result = Integer.parseInt(type);

                BusinessRule rule = defineService.getBusinessRule(naam, table, result);

                Gson gson = new Gson();
                String json = gson.toJson(rule);

            return Response.ok(json).build();
        }

        @PUT
        @Produces("application/json")
        public Response updateBusinessRule(){

                return Response.ok().build();
        }

        @DELETE
        @Path("{rule}")
        @Produces("application/json")
        public Response delete(@PathParam("rule") String rule){
                return Response.ok().build();
        }



//        @POST
//        @Produces("application/json")
//        public Response createConstraint(@FormParam("tabel_compare") String table,
//                                     @FormParam("atribuut_compare") String attribute,@FormParam("operator_compare") String operator,
//                @FormParam("value_compare")String value) throws SQLException{
//
////            String cnaam = "vbmg_constraint";
////            //String bnaam = "vbmg_rule";
////            //int btypeid = 2;
////            //int bconstraint = 2;
////            //int bId = 2;
////
////            System.out.println("constraint opslaan");
////            Attribute_Compare compare = new Attribute_Compare(cnaam,table,attribute,value,operator);
////            Service.Save(compare);
////            System.out.println("gelukt");
////            //System.out.println("rule opslaan");
////            //BusinessRule businessRule = new BusinessRule(bId,bnaam,btypeid,bconstraint);
////            //bService.Save(businessRule);
////            //System.out.println("gelukt");
//            //return Response.ok(compare).build();
//
//            return Response.ok().build();
//        }

}

