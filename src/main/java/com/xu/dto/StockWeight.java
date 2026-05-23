package com.xu.dto;

/**
 * 股票持仓权重对象
 */
public record StockWeight(
        String stockCode,   // 比如: "sh600519"
        String stockName,   // 比如: "贵州茅台"
        double weight       // 比如: 0.0950
) {}
