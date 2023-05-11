package com.tennis.matching.domain.application.repository;

import com.tennis.matching.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long>,
                                               ApplicationRepositoryCustom {
}
