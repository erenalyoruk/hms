package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;

public class DoctorRepository {
    private final EntityManager em;

    public DoctorRepository(EntityManager em) {
        this.em = em;
    }
}
