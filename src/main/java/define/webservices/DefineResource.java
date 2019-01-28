package define.webservices;


import com.google.gson.Gson;
import define.model.Attribute_Compare;
import define.model.BusinessRule;
import define.model.DefineService;
import define.model.ServiceProvider;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/compare")
public class DefineResource {

        private DefineService defineService = ServiceProvider.getDefineService();


        @POST
        @Produces("application/json")
        public Response getBusinessRuleWithParam(@FormParam("name") String naam, @FormParam("table") String table, @FormParam("type") String type){

                int result = Integer.parseInt(type);

                BusinessRule rule = defineService.getBusinessRule(naam, table, result);

                Gson gson = new Gson();
                String json = gson.toJson(rule);

            return Response.ok(json).build();
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

