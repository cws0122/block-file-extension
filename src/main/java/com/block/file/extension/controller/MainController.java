package com.block.file.extension.controller;

import com.block.file.extension.service.ExtensionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final ExtensionService extensionService;

    @GetMapping("/")
    public String index(Model model) {
        log.debug("index called");
        model.addAttribute("fixedExtensions", extensionService.getFixedExtensions());
        model.addAttribute("customExtensions", extensionService.getCustomExtensions());
        return "index";
    }
}
