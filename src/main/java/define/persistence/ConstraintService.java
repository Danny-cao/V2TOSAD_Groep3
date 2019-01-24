package define.persistence;

import define.model.Attribute_Compare;
import java.sql.SQLException;

public class ConstraintService {
    private ConstraintDao CDAO = new ConstraintDao();

    public ConstraintService() {}

    public Attribute_Compare Save(Attribute_Compare compare) throws SQLException{
        return CDAO.save(compare);
    }


}

