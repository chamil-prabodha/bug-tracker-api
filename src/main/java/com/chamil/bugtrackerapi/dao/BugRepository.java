package com.chamil.bugtrackerapi.dao;

import com.chamil.bugtrackerapi.model.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    Optional<Bug> findBugByProjectIdAndId(Long projectId, Long bugId);
}
