package org.launchcode.PFT_api.Controllers;

import org.launchcode.PFT_api.Models.Transaction;
import org.launchcode.PFT_api.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @GetMapping
    public List<Transaction> getAllSubmittedEvents() {
        return transactionRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam("amount") String amount,
            @RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("type") String type,
            @RequestParam("description") String description)
            {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setType(type);
        transaction.setDescription(description);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!transactionRepository.existsById(id)) {
            // if event is not found indicates no deletion was performed.
            return ResponseEntity.notFound().build();
        }
        transactionRepository.deleteById(id);
        //if the event exists it proceed to delete the event using eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
