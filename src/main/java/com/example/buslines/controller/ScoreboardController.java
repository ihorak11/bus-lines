package com.example.buslines.controller;

import com.example.buslines.model.TopBusLinesScoreboard;
import com.example.buslines.service.BusLineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
@Slf4j
public class ScoreboardController {

    private BusLineService busLineService;

    @GetMapping("/scoreboard")
    public String getScoreboard(Model model) {
        log.info("Starting getScoreboard");

        TopBusLinesScoreboard topBusLines = busLineService.getTop10BusLines();
        model.addAttribute("scoreboard", topBusLines);

        return "scoreboard";
    }
}