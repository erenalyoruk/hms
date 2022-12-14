package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;

public class PrescriptionRepository {
    private final EntityManager em;

    public PrescriptionRepository(EntityManager em) {
        this.em = em;
    }
}
