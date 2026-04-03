package com.ragini.financebackend.service;

import com.ragini.financebackend.dto.DashboardSummaryDTO;
import com.ragini.financebackend.entity.FinancialRecord;
import com.ragini.financebackend.entity.RecordType;
import com.ragini.financebackend.entity.User;
import com.ragini.financebackend.repository.FinancialRecordRepository;
import com.ragini.financebackend.repository.UserRepository;
import com.ragini.financebackend.util.AccessControlUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository recordRepository;

    private final UserRepository userRepository;

    public FinancialRecordService(FinancialRecordRepository recordRepository,
                                  UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
    }

    // ✅ Create Record (ADMIN only)
    public FinancialRecord createRecord(FinancialRecord record, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAdmin(user);

        record.setUser(user);

        return recordRepository.save(record);
    }

    // ✅ Get All Records (All roles)
    public List<FinancialRecord> getAllRecords(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        return recordRepository.findAll();
    }

    // ✅ Get Record by ID
    public FinancialRecord getRecordById(Long id, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    // ✅ Filter by Type
    public List<FinancialRecord> getRecordsByType(RecordType type, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        return recordRepository.findByType(type);
    }

    // ✅ Filter by Category
    public List<FinancialRecord> getRecordsByCategory(String category, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        return recordRepository.findByCategory(category);
    }

    // ✅ Get Records by User
    public List<FinancialRecord> getRecordsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        return recordRepository.findByUserId(userId);
    }

    // ✅ Update Record (ADMIN only)
    public FinancialRecord updateRecord(Long id, FinancialRecord updatedRecord, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAdmin(user);

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(updatedRecord.getAmount());
        record.setType(updatedRecord.getType());
        record.setCategory(updatedRecord.getCategory());
        record.setDate(updatedRecord.getDate());
        record.setDescription(updatedRecord.getDescription());

        return recordRepository.save(record);
    }

    // ✅ Delete Record (ADMIN only)
    public void deleteRecord(Long id, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAdmin(user);

        recordRepository.deleteById(id);
    }

    // ✅ Global Dashboard
    public DashboardSummaryDTO getSummary(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        List<FinancialRecord> records = recordRepository.findAll();

        double totalIncome = records.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double totalExpense = records.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double netBalance = totalIncome - totalExpense;

        return new DashboardSummaryDTO(totalIncome, totalExpense, netBalance);
    }

    // ✅ User Dashboard (ANALYST + ADMIN)
    public DashboardSummaryDTO getUserSummary(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAnalystOrAdmin(user);

        List<FinancialRecord> records = recordRepository.findByUserId(userId);

        double totalIncome = records.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double totalExpense = records.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double netBalance = totalIncome - totalExpense;

        return new DashboardSummaryDTO(totalIncome, totalExpense, netBalance);
    }


    public Page<FinancialRecord> getRecordsWithPagination(int page, int size, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        Pageable pageable = PageRequest.of(page, size);

        return recordRepository.findAll(pageable);
    }
    public List<FinancialRecord> searchRecords(String keyword, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccessControlUtil.checkAny(user);

        return recordRepository.findByCategoryContainingIgnoreCase(keyword);
    }
}