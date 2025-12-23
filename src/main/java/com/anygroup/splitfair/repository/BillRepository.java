package com.anygroup.splitfair.repository;

import com.anygroup.splitfair.model.Bill;
import com.anygroup.splitfair.model.Group;
import com.anygroup.splitfair.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {


    List<Bill> findByGroup_Id(UUID groupId);
    
    List<Bill> findByGroup(Group group);


    List<Bill> findByCreatedBy(User createdBy);


}