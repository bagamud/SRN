package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.SFixTerm;

public interface SFixTermRepository extends CrudRepository<SFixTerm, Integer> {
    SFixTerm findByShortcomingIdAndRoadCategoryId(int shortcoming_id, int roadCategory_id);
}