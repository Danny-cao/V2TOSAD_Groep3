package define.persistence;

import define.model.InterEntity_Compare;
//
public interface InterEntity_CompareDao {
    InterEntity_Compare findByID(int id);
    InterEntity_Compare save(InterEntity_Compare compare);
    InterEntity_Compare update(InterEntity_Compare compare);

}
