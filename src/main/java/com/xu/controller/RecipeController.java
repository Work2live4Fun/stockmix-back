package com.xu.controller;

import com.xu.dto.ApiResponse;
import com.xu.dto.PortfolioRecipe;
import com.xu.dto.StockWeight;
import com.xu.entity.Portfolio;
import com.xu.entity.PositionDetail;
import com.xu.repository.PortfolioRepository;
import com.xu.repository.PositionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/portfolio")
@CrossOrigin(origins = "*") // 允许你的前端网页跨域调用
@RequiredArgsConstructor // 🌟 必须有这个！它负责帮你写构造函数来初始化 final 变量
public class RecipeController {
    private final PortfolioRepository portfolioRepository;
    private final PositionDetailRepository positionDetailRepository;

    @GetMapping("/recipe")
    public ResponseEntity<ApiResponse<PortfolioRecipe>> getRecipe(@RequestParam String code) {

        // 1. 从 MySQL 查主表，如果没有这个组合，直接返回 404
        Optional<Portfolio> portfolioOpt = portfolioRepository.findByPortfolioCode(code);
        if (portfolioOpt.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(404, "未找到该组合配方", null));
        }
        Portfolio portfolio = portfolioOpt.get();

        // 2. 从 MySQL 查出该组合对应的所有明细股票
        List<PositionDetail> details = positionDetailRepository.findByPortfolioCode(code);

        // 3. 将 Entity 转换为前端需要的 DTO Record 列表
        List<StockWeight> holdings = details.stream()
                .map(d -> new StockWeight(d.getStockCode(), d.getStockName(), d.getWeight().doubleValue()))
                .toList();

        // 4. 组装并返回标准响应
        PortfolioRecipe recipe = new PortfolioRecipe(
                portfolio.getPortfolioCode(),
                portfolio.getPortfolioName(),
                portfolio.getUpdateQuarter(),
                holdings
        );

        return ResponseEntity.ok(new ApiResponse<>(200, "success", recipe));
    }
}
