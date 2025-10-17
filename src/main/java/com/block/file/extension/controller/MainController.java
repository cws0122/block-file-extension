package com.block.file.extension.controller;

import com.block.file.extension.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ExtensionService extensionService;

    @GetMapping("/")
    public String index() {
        extensionService.getExtensions();
        return "index";
    }
}
