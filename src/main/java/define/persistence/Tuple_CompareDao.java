package define.persistence;

import define.model.Tuple_Compare;
//
public interface Tuple_CompareDao {
    Tuple_Compare findByID(int id);
    Tuple_Compare save(Tuple_Compare compare);
    Tuple_Compare update(Tuple_Compare compare);
}
