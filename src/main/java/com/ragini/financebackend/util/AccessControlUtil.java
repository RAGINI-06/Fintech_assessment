package com.ragini.financebackend.util;

import com.ragini.financebackend.entity.Role;
import com.ragini.financebackend.entity.User;

public class AccessControlUtil {

    public static void checkAdmin(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Access Denied: Admin only");
        }
    }

    public static void checkAnalystOrAdmin(User user) {
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.ANALYST) {
            throw new RuntimeException("Access Denied: Analyst or Admin only");
        }
    }

    public static void checkAny(User user) {
        if (user == null) {
            throw new RuntimeException("User not found");
        }
    }
}
