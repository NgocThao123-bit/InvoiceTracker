package spring.thymeleaf.invoiceTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.websocket.server.PathParam;
import spring.thymeleaf.invoiceTracker.model.Invoice;
import spring.thymeleaf.invoiceTracker.service.InvoiceService;



@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping("/")
	public String main() {		
		return "main";
	}
	@GetMapping("/invoice")
	public String getAllInvoice(Model model,@PathParam("keyword") String keyword) {
		List<Invoice> invoices = invoiceService.findAllInvoice(keyword);
		
		model.addAttribute("invoices", invoices);
		model.addAttribute("keyword", keyword);
		return "allInvoice";
	}
	@GetMapping("/invoice/add")
	public String addInvoice(Model model) {
		Invoice invoice = new Invoice();
		model.addAttribute("invoice", invoice);
		return "addInvoice";
	}

	@PostMapping("/save")
	public String saveInvoice(@ModelAttribute("invoice") Invoice invoice , RedirectAttributes redir) {
		String message = "";
		String err = "error";
		String suc = "success"; 
		if(invoice.getName() == "") {
			message += "Enter name please!\n";
			redir.addFlashAttribute(err,message);
			return "redirect:/invoice/add";
		}			
		else if(invoice.getDate()=="") {
			message += "Enter date please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/invoice/add";
		}			
		else if(invoice.getAmount() == 0.0) {
			message = "Enter amount please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/invoice/add";
		}
		else if(invoice.getLocation() == "") {
			message = "Enter location please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/invoice/add";
		}
		else {
			message = "Success!";			
			invoiceService.saveInvoice(invoice);
			redir.addFlashAttribute(suc, message);
			System.out.println(invoice);
			return "redirect:/invoice";
		}

	}
	
	@GetMapping("/updateInvoice/{id}")
	public String showUpdateForm(@PathVariable(value="id") long id, Model model) {
		Invoice invoice = invoiceService.findInvoiceById(id);
		model.addAttribute("invoice",invoice);
		return "updateForm";
	}
	
	@PostMapping("/update")
	public String updateInvoice(@ModelAttribute("invoice") Invoice invoice , RedirectAttributes redir) {
		String message = "";
		String err = "error";
		String suc = "success"; 
		if(invoice.getName() == "") {
			message += "Enter name please!\n";
			redir.addFlashAttribute(err,message);
			return "redirect:/updateInvoice/" + invoice.getId();
		}			
		else if(invoice.getDate()=="") {
			message += "Enter date please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/updateInvoice/" + invoice.getId();
		}			
		else if(invoice.getAmount() == 0.0) {
			message = "Enter amount please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/updateInvoice/" + invoice.getId();
		}
		else if(invoice.getLocation() == "") {
			message = "Enter location please!\n";
			redir.addFlashAttribute(err, message);
			return "redirect:/updateInvoice/" + invoice.getId();
		}
		else {
			message = "Success!";			
			invoiceService.saveInvoice(invoice);
			redir.addFlashAttribute(suc, message);
			System.out.println(invoice);
			return "redirect:/invoice";
		}

	}
	
	@GetMapping("/deleteInvoice/{id}")
	public String deleteInvoice(@PathVariable("id") long id) {
		invoiceService.deleteById(id);
		return "redirect:/invoice";
	}

	
	
}
