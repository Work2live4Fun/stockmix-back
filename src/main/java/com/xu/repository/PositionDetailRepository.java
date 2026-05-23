package com.xu.repository;

import com.xu.entity.PositionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PositionDetailRepository extends JpaRepository<PositionDetail, Long> {
    List<PositionDetail> findByPortfolioCode(String portfolioCode);
}