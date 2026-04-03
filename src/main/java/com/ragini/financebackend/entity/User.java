
package com.ragini.financebackend.entity;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "users")   // ✅ ADD THIS
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active = true;
}