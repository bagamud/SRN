package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.RelatedFiles;

public interface RelatedFilesRepository extends CrudRepository<RelatedFiles, Integer> {
    Iterable<RelatedFiles> findAllBySrnAndTypeOrderById(int srn_id, int type);
}
