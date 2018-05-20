package be.vigilis.repositories;

import be.vigilis.entities.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GeneralRepo extends JpaRepository<General, Long> {

    @Transactional
    General findByKboNumber(long kboNummer);

    @Transactional
    General findByNameKbo(String nameKbo);

    @Transactional
    int deleteByKboNumber(long kboNumber);

    @Transactional
    int deleteByNameKbo(String nameKbo);
}
