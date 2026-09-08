package com.microservices.Reviews.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews,Long> {
    List<Reviews> findByCompanyId(Long companyId);

}
