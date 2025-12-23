package com.anygroup.splitfair.repository;

import com.anygroup.splitfair.model.Group;
import com.anygroup.splitfair.model.GroupMember;
import com.anygroup.splitfair.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, UUID> {


    List<GroupMember> findByGroup(Group group);


    List<GroupMember> findByUser(User user);


    Optional<GroupMember> findByGroupAndUser(Group group, User user);


    void deleteAllByGroup_Id(UUID groupId);
}
