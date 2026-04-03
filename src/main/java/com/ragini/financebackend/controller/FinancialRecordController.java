package com.ragini.financebackend.controller;

import com.ragini.financebackend.entity.FinancialRecord;
import com.ragini.financebackend.entity.RecordType;
import com.ragini.financebackend.service.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    public FinancialRecordController(FinancialRecordService recordService) {
        this.recordService = recordService;
    }

    // ✅ Create Record (ADMIN only)
    @PostMapping
    public FinancialRecord createRecord(@Valid @RequestBody FinancialRecord record,
                                        @RequestParam Long userId) {
        return recordService.createRecord(record, userId);
    }

    // ✅ Get All Records (All roles)
    @GetMapping
    public List<FinancialRecord> getAllRecords(@RequestParam Long userId) {
        return recordService.getAllRecords(userId);
    }

    // ✅ Get Record by ID
    @GetMapping("/{id}")
    public FinancialRecord getRecordById(@PathVariable Long id,
                                         @RequestParam Long userId) {
        return recordService.getRecordById(id, userId);
    }

    // ✅ Filter Records
    @GetMapping("/filter")
    public List<FinancialRecord> filterRecords(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam Long userId) {

        if (type != null) {
            return recordService.getRecordsByType(type, userId);
        }

        if (category != null) {
            return recordService.getRecordsByCategory(category, userId);
        }

        return recordService.getAllRecords(userId);
    }

    // ✅ Get Records by User
    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getRecordsByUser(@PathVariable Long userId) {
        return recordService.getRecordsByUser(userId);
    }

    // ✅ Update Record (ADMIN only)
    @PutMapping("/{id}")
    public FinancialRecord updateRecord(@PathVariable Long id,
                                        @Valid @RequestBody FinancialRecord record,
                                        @RequestParam Long userId) {
        return recordService.updateRecord(id, record, userId);
    }

    // ✅ Delete Record (ADMIN only)
    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Long id,
                               @RequestParam Long userId) {
        recordService.deleteRecord(id, userId);
        return "Record deleted successfully";
    }
    @GetMapping("/paginated")
    public Page<FinancialRecord> getPaginatedRecords(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Long userId) {

        return recordService.getRecordsWithPagination(page, size, userId);
    }
    @GetMapping("/search")
    public List<FinancialRecord> searchRecords(
            @RequestParam String keyword,
            @RequestParam Long userId) {

        return recordService.searchRecords(keyword, userId);
    }
}