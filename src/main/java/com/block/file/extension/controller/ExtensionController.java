package com.block.file.extension.controller;

import com.block.file.extension.dto.response.CreateExtensionResponse;
import com.block.file.extension.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/extension")
public class ExtensionController {
    private final ExtensionService extensionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCustomExtension(@RequestParam(value = "name") String name) {
        log.debug("Create extension: {}", name);
        try {
            CreateExtensionResponse data = extensionService.createCustomExtension(name);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "확장자가 추가되었습니다");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("확장자 생성 실패: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            log.error("확장자 생성 실패", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCustomExtension(@PathVariable Long id) {
        log.debug("Delete extension: {}", id);
        try {
            extensionService.deleteCustomExtension(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "확장자가 삭제되었습니다");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("확장자 삭제 실패", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> confirmCheck(@PathVariable Long id) {
        log.debug("confirm Check extension: {}", id);
        try {
            CreateExtensionResponse data = extensionService.confirmCheckExtension(id);
            String message = "체크가 비활성화되었습니다.";
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            if(data.getChecked()){
                message = "체크가 활성화되었습니다.";
            }
            response.put("message", message);
            response.put("data", data);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("확장자 체크 확인 실패", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
