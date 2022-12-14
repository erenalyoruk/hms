package net.erenalyoruk.hms.repository;

import java.util.List;
import net.erenalyoruk.hms.model.Admin;

public interface IAdminRepository {
    List<Admin> findAll();

    Admin findOneById(int id);

    void insertOne(Admin admin);
}
