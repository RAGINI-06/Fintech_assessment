package com.ragini.financebackend.controller;

import com.ragini.financebackend.dto.DashboardSummaryDTO;
import com.ragini.financebackend.service.FinancialRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final FinancialRecordService recordService;

    public DashboardController(FinancialRecordService recordService) {
        this.recordService = recordService;
    }

    // ✅ Global dashboard
    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary(@RequestParam Long userId) {
        return recordService.getSummary(userId);
    }

    // ✅ User dashboard (FIXED)
    @GetMapping("/user/{userId}")
    public DashboardSummaryDTO getUserSummary(@PathVariable Long userId) {
        return recordService.getUserSummary(userId); // ✅ FIX HERE
    }
}