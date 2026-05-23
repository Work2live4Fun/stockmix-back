package com.xu.repository;

import com.xu.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByPortfolioCode(String portfolioCode);
}