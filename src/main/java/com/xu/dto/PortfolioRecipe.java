package com.xu.dto;

import java.util.List;

/**
 * 资产组合静态配方传输对象
 */
public record PortfolioRecipe(
        String portfolioCode,
        String portfolioName,
        String lastUpdate,
        List<StockWeight> holdings // 🌟 必须叫 holdings，和前端 index.html 完全对齐
) {}
