package ru.ic.information_portal.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ic.information_portal.entity.Journal;

import java.util.ArrayList;

public interface JournalRepository extends CrudRepository<Journal, Integer> {
    ArrayList<Journal> findAllBySrn_IdOrderByEntryDate(int id);
}
