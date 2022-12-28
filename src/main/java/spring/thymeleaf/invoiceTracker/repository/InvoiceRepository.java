package spring.thymeleaf.invoiceTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.thymeleaf.invoiceTracker.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query("SELECT i FROM Invoice i "
			+ " WHERE CONCAT(i.name,i.location,i.date,i.amount) LIKE %?1% ")
			
	public List<Invoice> search(String name); 
}
