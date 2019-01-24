package transform.persistence;

import transform.model.Attribute_Compare;
import transform.model.Attribute_List;
import transform.model.Attribute_Other;
import transform.model.Attribute_Range;
import transform.model.BusinessRule;

import java.util.List;

public class TransformBusinessRule implements TransformBusinessRuleDao {

    public boolean transform(BusinessRule rule) {

        if (rule.getType().equals("Attribute_Compare")) {

            Attribute_Compare constraint = ((Attribute_Compare) rule.getConstraint());

            String table = constraint.getTable();
            String name = constraint.getNaam();
            String attribute = constraint.getAttribute();
            String operator = constraint.getOperator();
            String value = constraint.getValue();

            String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + attribute + " " + operator + " " + value + ")";

            return true;

        } else if (rule.getType().equals("Attribute_List")) {

            Attribute_List constraint = ((Attribute_List) rule.getConstraint());

            String table = constraint.getTable();
            String name = constraint.getNaam();
            String value = constraint.getValue();
            List<String> valuesList = constraint.getValues();

            String values = "(";

            for (String x : valuesList) {

                String v = "'" + x + "'";
                values += "'" + v + "'";
                values += ",";

            }

            values.substring(0,values.length()-1);
            values += ")";

            if (constraint.getInList().equals("yes")) {

                String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + value + " IN " + values + ")";
                return true;

            } else {

                String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + value + " NOT IN " + values + ")";
                return true;

            }

        } else if (rule.getType().equals("Attribute_Other")) {

            Attribute_Other constraint = ((Attribute_Other) rule.getConstraint());

            String query = "";

            return true;

        } else if (rule.getType().equals("Attribute_Range")) {

            Attribute_Range constraint = ((Attribute_Range) rule.getConstraint());

            String table = constraint.getTable();
            String name = constraint.getNaam();
            String attribute = constraint.getAttribute();
            String value1 = String.valueOf(constraint.getValue1());
            String value2 = String.valueOf(constraint.getValue2());

            if (constraint.getBetween().equals("yes")) {

                String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + attribute + " > " + value1 + " AND " + attribute + " < " + value2 + ")";
                return true;

            } else {

                String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + attribute + " < " + value1 + " AND " + attribute + " > " + value2 + ")";
                return true;

            }

        } else {

            return false;

        }

    }

}
