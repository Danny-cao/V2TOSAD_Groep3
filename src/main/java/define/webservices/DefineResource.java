package define.webservices;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import define.model.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.ws.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;




@Path("/define")
public class DefineResource {

        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high-low) + low;

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


        /*
        @DELETE
        @Path("delete")
        @Produces("application/json")
        public Response delete(@FormParam("rules") String rule){

                int result = Integer.parseInt(rule);

                BusinessRule businessRule = defineService.getBusinessRuleByID(result);

                Boolean del = defineService.delete(businessRule);

                Gson gson = new Gson();
                String json = gson.toJson(del);

                return Response.ok(json).build();
        }
*/

        @DELETE
        @Path("{code}")
        @Produces("application/json")
        public Response deleteRule(@PathParam("code") int code) throws SQLException {
                System.out.println("Delete rule");
                Constraint found = null;
                for (Constraint c : defineService.findAllRules()) {
                        if (c.getId() == code) {
                                found = c;
                                break;
                        }
                }
                if (found == null) {
                        return Response.status(404).build();
                } else {
                        defineService.delete(found);
                        System.out.println("businessRule verwijderd");
                        return Response.ok().build();
                }
        }
        @POST
        @Path("/postCompare")
        @Produces("application/json")
        public Response createConstraint(@FormParam("tabel_compare") String table,
                                         @FormParam("atribuut_compare") String attribute,@FormParam("operator_compare") String operator,
                                         @FormParam("value_compare")String value) throws SQLException {







                System.out.println("constraint opslaan");
                Attribute_Compare compare = new Attribute_Compare(table,result,attribute,value,operator);
                defineService.Save(compare);
                System.out.println("gelukt");
                return Response.ok(compare).build();

        }

        @POST
        @Path("/postRange")
        @Produces("application/json")
        public Response createRange(@FormParam("tabel_range") String table,
                                    @FormParam("atribuut_range") String attribute,@FormParam("operator_range") String operator,
                                    @FormParam("mvalue_range")int value1,@FormParam("maxvalue_range")int value2) throws SQLException{



                System.out.println("constraint_range opslaan");
                Attribute_Range range = new Attribute_Range(table,result,attribute,value1,value2,operator);
                defineService.Save(range);
                System.out.println("constraint_range opgeslagen");
                return Response.ok(range).build();

        }

        @POST
        @Path("/postOther")
        @Produces("application/json")
        public Response createOther(@FormParam("tabel_tcompare") String table,
                                    @FormParam("atribuut_tcompare") String atribuut1,@FormParam("operator_tcompare") String operator,
                                    @FormParam("atribuut_acompare")String atribuut2) throws SQLException{

;
                System.out.println("constraint_other opslaan");
                Attribute_Other other = new Attribute_Other(table,result,atribuut1,atribuut2,operator);
                defineService.Save(other);
                System.out.println("constraint_other opgeslagen");
                return Response.ok(other).build();

        }

        @POST
        @Path("/postInter")
        @Produces("application/json")
        public Response createInter(@FormParam("tabel_inter") String table,@FormParam("atribuut_inter") String atribuut1,
                                    @FormParam("operator_inter") String operator, @FormParam("tabel_interA") String ref_table,
                                    @FormParam("atribuut_interA") String atribuut2) throws SQLException{


                System.out.println("constraint_inter opslaan");
                Attribute_InterEntity inter = new Attribute_InterEntity(table,result,atribuut1,atribuut2,operator,ref_table);
                defineService.Save(inter);
                System.out.println("constraint_inter opgeslagen");
                return Response.ok(inter).build();

        }


        @GET
        @Path("{id}")
        @Produces("application/json")
        public String get(@PathParam("id") int id) throws SQLException  {
//
//                Constraint constraint = defineService.findByConstrantID(id);
//
//                JsonObjectBuilder job = Json.createObjectBuilder();
//                job.add("constraint_id", constraint.getId());
//                return job.build().toString();

                Gson gson = new Gson();
                String json = gson.toJson(defineService.findByConstrantID(id));

                return json;
        }



        @PUT
        @Path("/compare/{constraintnummerCompare}")
        @Produces("application/json")
        public Response updateCompare(@PathParam("constraintnummerCompare") int constraintnummerCompare,
                                      @FormParam("compare_tabel") String tabel,@FormParam("compare_atribuut") String atribuut
                ,@FormParam("compare_operator") String operator,@FormParam("compare_value") String value) throws SQLException{
                System.out.println("update compare_constraint");
                Attribute_Compare found = null;
                for (Attribute_Compare r : defineService.findAllRulesCompare()) {
                        if(r.getId() == constraintnummerCompare) {
                                r.setTable(tabel);
                                r.setAttribute(atribuut);
                                r.setOperator(operator);
                                r.setValue(value);
                                found = r;
                                break;
                        }
                }
                if (found == null) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                        System.out.println("gegevens geupdate");
                        defineService.UpdateCompare(found);

                        return Response.ok().build();
                }
        }


        @PUT
        @Path("/range/{constraintnummerRange}")
        @Produces("application/json")
        public Response updateRange(@PathParam("constraintnummerRange") int constraintnummerRange,
                                    @FormParam("range_tabel") String tabel,@FormParam("range_atribuut") String atribuut
                ,@FormParam("range_operator") String operator,@FormParam("range_value1") int value1,
                                    @FormParam("range_value2") int value2)throws SQLException{
                System.out.println("update range_constraint");
                Attribute_Range found = null;
                for (Attribute_Range r : defineService.findAllRulesRange()) {
                        if(r.getId() == constraintnummerRange) {
                                r.setTable(tabel);
                                r.setAttribute(atribuut);
                                r.setOperator(operator);
                                r.setValue1(value1);
                                r.setValue2(value2);
                                found = r;
                                break;
                        }
                }
                if (found == null) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                        System.out.println("range constraint geupdate");
                        defineService.UpdateRange(found);

                        return Response.ok().build();
                }
        }


        @PUT
        @Path("/other/{constraintnummerOther}")
        @Produces("application/json")
        public Response updateOther(@PathParam("constraintnummerOther") int constraintnummerOther,
                                    @FormParam("tuple_tabel") String tabel,@FormParam("tuple_atribuut") String atribuut1
                ,@FormParam("tuple_operator") String operator,@FormParam("tuple_refatribuut") String atribuut2)throws SQLException{
                System.out.println("update otherCompare_constraint");
                Attribute_Other found = null;
                for (Attribute_Other r : defineService.findAllRulesOther()) {
                        if(r.getId() == constraintnummerOther) {
                                r.setTable(tabel);
                                r.setAttribute1(atribuut1);
                                r.setAttribute2(atribuut2);
                                r.setOperator(operator);
                                found = r;
                                break;
                        }
                }
                if (found == null) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                        System.out.println("otherCompare constraint geupdate");
                        defineService.UpdateOther(found);

                        return Response.ok().build();
                }
        }

        @PUT
        @Path("/inter/{constraintnummerInter}")
        @Produces("application/json")
        public Response updateInter(@PathParam("constraintnummerInter") int constraintnummerInter,
                                    @FormParam("inter_tabel") String tabel,@FormParam("inter_reftabel") String ref_tabel,
                                    @FormParam("inter_atribuut") String atribuut,@FormParam("inter_refatribuut") String ref_atribuut
                ,@FormParam("inter_operator") String operator)throws SQLException{
                System.out.println("update InterEntity_constraint");
                Attribute_InterEntity found = null;
                for (Attribute_InterEntity r : defineService.findAllRulesInter()) {
                        if(r.getId() == constraintnummerInter) {
                                r.setTable(tabel);
                                r.setAttribute1(atribuut);
                                r.setAttribute2(ref_atribuut);
                                r.setOperator(operator);
                                r.setRef_tabel(ref_tabel);
                                found = r;
                                break;
                        }
                }
                if (found == null) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                        System.out.println("InterEntity constraint geupdate");
                        defineService.UpdateInter(found);

                        return Response.ok().build();
                }
        }



        @GET
        @Path("/getRulesCompare")
        @Produces("application/json")
        public String getRulesCompare() throws SQLException {


                Gson gson = new Gson();
                String json = gson.toJson(defineService.findAllBusinessRulesCompare());
                return json;
        }

        @GET
        @Path("/getRulesRange")
        @Produces("application/json")
        public String getRulesRange() throws SQLException {


                Gson gson = new Gson();
                String json = gson.toJson(defineService.findAllBusinessRulesRange());
                return json;
        }

        @GET
        @Path("/getRulesOther")
        @Produces("application/json")
        public String getRulesOther() throws SQLException {


                Gson gson = new Gson();
                String json = gson.toJson(defineService.findAllBusinessRulesOther());
                return json;
        }

        @GET
        @Path("/getRulesInter")
        @Produces("application/json")
        public String getRulesInter() throws SQLException {



                Gson gson = new Gson();
                String json = gson.toJson(defineService.findAllBusinessRulesInter());
                return json;
        }










}

