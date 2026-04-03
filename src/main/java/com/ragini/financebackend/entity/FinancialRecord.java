package com.ragini.financebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RecordType type;

    @NotBlank
    private String category;

    private LocalDate date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}