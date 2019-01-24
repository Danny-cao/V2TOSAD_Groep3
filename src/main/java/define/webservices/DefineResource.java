package define.webservices;


import define.model.Attribute_Compare;
import define.model.BusinessRule;
import define.model.BusinessRuleType;
import define.model.ServiceProvider;

import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import define.persistence.BusinessRuleService;
import define.persistence.ConstraintService;


@Path("/compare")
public class DefineResource {

        private ConstraintService Service = ServiceProvider.getConstraintService();
        //private BusinessRuleService bService = ServiceProvider.getBusinessRuleService();

        @POST
        @Produces("application/json")
        public Response createConstraint(@FormParam("tabel_compare") String table,
                                     @FormParam("atribuut_compare") String attribute,@FormParam("operator_compare") String operator,
                @FormParam("value_compare")String value) throws SQLException{

            String cnaam = "vbmg_constraint";
            //String bnaam = "vbmg_rule";
            //int btypeid = 2;
            //int bconstraint = 2;
            //int bId = 2;

            System.out.println("constraint opslaan");
            Attribute_Compare compare = new Attribute_Compare(cnaam,table,attribute,value,operator);
            Service.Save(compare);
            System.out.println("gelukt");
            //System.out.println("rule opslaan");
            //BusinessRule businessRule = new BusinessRule(bId,bnaam,btypeid,bconstraint);
            //bService.Save(businessRule);
            //System.out.println("gelukt");
            return Response.ok(compare).build();





        }}

