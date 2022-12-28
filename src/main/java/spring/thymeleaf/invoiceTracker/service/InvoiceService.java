package spring.thymeleaf.invoiceTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.thymeleaf.invoiceTracker.model.Invoice;
import spring.thymeleaf.invoiceTracker.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public List<Invoice> findAllInvoice(String name) {
		if(name != null)
			return invoiceRepository.search(name);
		return invoiceRepository.findAll();
	}
	
	public Invoice findInvoiceById(long id) {
		return invoiceRepository.findById(id).get();
	}
	
	public void saveInvoice(Invoice inv) {
		 invoiceRepository.save(inv);
	}
	
	public void deleteById(long id) {
		invoiceRepository.deleteById(id);
	}
}
