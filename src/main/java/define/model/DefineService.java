package define.model;

import define.persistence.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefineService {

    private BusinessRuleDao bdao = new BusinessRuleOracleDaoImpl();
    private Attribute_CompareDaoImpl adao = new Attribute_CompareDaoImpl();
    private Attribute_InterEntityDaoImpl idao = new Attribute_InterEntityDaoImpl();
    private Attribute_OtherDaoImpl odao = new Attribute_OtherDaoImpl();
    private Attribute_RangeDaoImpl rdao = new Attribute_RangeDaoImpl();
    private ConstraintDao cdao = new ConstraintOracleDaoImpl();

    // attribute compare dao;

    public Attribute_Compare Save(Attribute_Compare compare) throws SQLException {
        return adao.save(compare);
    }

    public ArrayList<Attribute_Compare> findAllRulesCompare() throws SQLException{
        return adao.findAllCompare();
    }

    public boolean UpdateCompare(Attribute_Compare compare) throws SQLException{
        return adao.updateCompare(compare);
    }

    // Atribute inter-Entity dao

    public Attribute_InterEntity Save(Attribute_InterEntity inter) throws SQLException{
        return idao.save(inter);
    }

    public ArrayList<Attribute_InterEntity> findAllRulesInter() throws SQLException{
        return idao.findAllInter();
    }

    public boolean UpdateInter(Attribute_InterEntity inter) throws SQLException{
        return idao.updateInter(inter);
    }

    // attribute other

    public Attribute_Other Save(Attribute_Other other) throws SQLException{
        return odao.save(other);
    }

    public ArrayList<Attribute_Other> findAllRulesOther() throws SQLException{
        return odao.findAllOther();
    }

    public boolean UpdateOther(Attribute_Other other) throws SQLException{
        return odao.updateOther(other);
    }

    // attribute range

    public Attribute_Range Save(Attribute_Range range) throws SQLException{
        return rdao.save(range);
    }

    public ArrayList<Attribute_Range> findAllRulesRange() throws SQLException{
        return rdao.findAllRange();
    }

    public boolean UpdateRange(Attribute_Range range) throws SQLException{
        return rdao.updateRange(range);
    }

    //businessrule dao

    // range lijst

    public List<BusinessRule> findAllBusinessRulesRange(){
        return bdao.findAllRange();
    }

    // other lijst
    public List<BusinessRule> findAllBusinessRulesOther(){
        return bdao.findAllOther();
    }


    // interEntity lijst
    public List<BusinessRule> findAllBusinessRulesInter(){
        return bdao.findAllInter();
    }

    // constraint

    public boolean delete(Constraint constraint) {
        return cdao.delete(constraint);
    }

    // voor update
    public Constraint findByConstrantID(int constraintnummer) throws SQLException{
        return cdao.findByConstraintnummer(constraintnummer);
    }

    public List<Constraint> findAllRules(){
        return cdao.findAll();
    }

    public BusinessRule getBusinessRuleByID(int id){
        return bdao.getBusinessRuleById(id);
    }

    public BusinessRule getBusinessRule(String naam, String table, int type){
        return bdao.getBusinessRule(naam, table, type);
    }

    public List<BusinessRule> getAllBusinessRules(){
        return bdao.getAllBusinessRules();
    }

    public boolean delete(BusinessRule rule){
        return bdao.delete(rule);
    }

    public BusinessRule update(BusinessRule rule){
        return bdao.update(rule);
    }

    public int createUniqueIDConstraint(){
        return cdao.createUniqueID();
    }

}
