package org.baeldung.persistence.repo;

import java.util.List;

import org.baeldung.persistence.model.Mitglied;
import org.springframework.data.repository.CrudRepository;

public interface MitgliederRepository extends CrudRepository<Mitglied, Long> {
    List<Mitglied> findByVorname(String vorname);
}
