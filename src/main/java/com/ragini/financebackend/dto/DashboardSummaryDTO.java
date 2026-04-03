package com.ragini.financebackend.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class DashboardSummaryDTO {

    private Double totalIncome;
    private Double totalExpense;
    private Double netBalance;
}