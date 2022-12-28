package spring.thymeleaf.invoiceTracker;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import spring.thymeleaf.invoiceTracker.model.Invoice;
import spring.thymeleaf.invoiceTracker.repository.InvoiceRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class InvoiceRepositoryTests{
	@Autowired private InvoiceRepository repo;
	
	@Test
	public void testAddName() {
		Invoice invoice = new Invoice();
		invoice.setName("Christina John");
		invoice.setDate("2022-2-12");
		invoice.setLocation("Korea");
		invoice.setAmount(250);
		
		Invoice savedInvoice = repo.save(invoice);
		
		Assertions.assertThat(savedInvoice).isNotNull();
		Assertions.assertThat(savedInvoice.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testAll() {
		Iterable<Invoice> invoices = repo.findAll();
		
		Assertions.assertThat(invoices).hasSizeGreaterThan(0);
		
		for(Invoice invoice : invoices) {
			System.out.println(invoice);
		}
	}
	
//	@Test
//	public void testUpdate() {
//		long invoiceId = 2;
//		Optional<Invoice> optionalInvoice = repo.findById(invoiceId);
//		
//		Invoice invoice = optionalInvoice.get();
//		invoice.setLocation("JP");
//		repo.save(invoice);
//		
//		Invoice updatedInvoice = repo.findById(invoiceId).get();
//		Assertions.assertThat(updatedInvoice.getLocation()).isEqualTo("JP");
//		
//		
//	}
	
	@Test
	public void testGet() {
		long invoiceId = 2;
		Optional<Invoice> optionalInvoice = repo.findById(invoiceId);
		Invoice invoice = optionalInvoice.get();
		
		Assertions.assertThat(optionalInvoice).isPresent();
		System.out.println(optionalInvoice.get());
	}
	@Test
	public void testDelete() {
		long id = 2;
		repo.deleteById(id);
		
		Optional<Invoice> optionalInvoice = repo.findById(id);
		Assertions.assertThat(optionalInvoice).isNotPresent();
	}
}