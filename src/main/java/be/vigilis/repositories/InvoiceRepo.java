package be.vigilis.repositories;

import be.vigilis.entities.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoices, Long> {
    @Transactional
    List<Invoices> findByState(String State);
}
