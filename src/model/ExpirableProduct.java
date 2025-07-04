package model;

import java.time.LocalDate;

public interface ExpirableProduct {
    LocalDate getExpiryDate();

    default boolean isExpired() {
        return LocalDate.now().isAfter(getExpiryDate());
    }
}
