package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;

public class PatientRepository {
    private final EntityManager em;

    public PatientRepository(EntityManager em) {
        this.em = em;
    }
}
