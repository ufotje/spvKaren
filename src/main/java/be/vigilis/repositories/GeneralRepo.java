package be.vigilis.repositories;

import be.vigilis.entities.Address;
import be.vigilis.entities.General;
import be.vigilis.entities.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GeneralRepo extends JpaRepository<General, Long> {

    @Transactional
    General findByKboNumber(long kboNummer);

    @Transactional
    General findByNameKbo(String nameKbo);

    @Transactional
    List<General> findByApplicationType(String type);

    @Transactional
    General findByInvoice(Invoices invoice);

    @Transactional
    List<General> findByAddress(Address address);

    @Transactional
    List<General> findByLanguage(String language);

    @Transactional
    int deleteByKboNumber(long kboNumber);

    @Transactional
    int deleteByNameKbo(String nameKbo);

}
