package com.xu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "mix_position_detail")
public class PositionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String portfolioCode;
    private String stockCode;
    private String stockName;
    private BigDecimal weight;
}
