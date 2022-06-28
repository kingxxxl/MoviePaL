package com.example.moviepal.service;

import com.example.moviepal.model.Invoice;
import com.example.moviepal.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepo;

    public Invoice saveInvoice(Invoice inv) {

        return invoiceRepo.save(inv);
    }

    @CachePut(value="Invoice", key="#invId")
    public Invoice updateInvoice(Invoice inv, Integer invId) {
        Invoice invoice = invoiceRepo.findById(invId).get();
        invoice.setInvAmount(inv.getInvAmount());
        invoice.setInvName(inv.getInvName());
        return invoiceRepo.save(invoice);
    }

    @CacheEvict(value="Invoice", key="#invId")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple entires to delete
    public void deleteInvoice(Integer invId) {
        Invoice invoice = invoiceRepo.findById(invId).get();
        invoiceRepo.delete(invoice);
    }

    @Cacheable(value="Invoice", key="#invId")
    public Invoice getOneInvoice(Integer invId) {
        System.out.println("getOneInvoice");
        System.out.println("id = " + invId);

        Invoice invoice = invoiceRepo.findById(invId).get();
        System.out.println("invoice = " + invoice);
        return invoice;
    }

    @Cacheable(value="Invoice")
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }
}
