package com.block.file.extension.service;

import com.block.file.extension.dto.response.CreateExtensionResponse;
import com.block.file.extension.entity.Extension;
import com.block.file.extension.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtensionService {
    private final ExtensionRepository extensionRepository;

    public List<Extension> getCustomExtensions() {
        return extensionRepository.findByFixedFalseAndDeletedFalseOrderByIdAsc();
    }

    public List<Extension> getFixedExtensions() {
        return extensionRepository.findByFixedTrueAndDeletedFalseOrderByIdAsc();
    }

    public CreateExtensionResponse createCustomExtension(String name){
        try {

            if(name.length() > 20){
                throw new IllegalArgumentException("확장자는 최대 20자리 까지만 가능합니다.");
            }

            if (extensionRepository.countByFixedFalseAndDeletedFalse() >= 200) {
                throw new IllegalArgumentException("확장자는 최대 200개까지만 생성 가능합니다.");
            }

            extensionRepository.findByNameAndDeletedFalse(name).ifPresent(ext -> {
                throw new IllegalArgumentException("이름이 중복되는 확장자는 생성할 수 없습니다.");
            });

            Extension createExtension = Extension.builder()
                    .name(name.toLowerCase()) // EXE와 exe의 차이 없게끔
                    .checked(false)
                    .fixed(false)
                    .deleted(false)
                    .build();

            Extension responseExtension = extensionRepository.save(createExtension);
            return CreateExtensionResponse.convertDTO(responseExtension);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("확장자를 생성할 수 없습니다", e);
        }
    }

    public void deleteCustomExtension(Long id){
        Extension extension = extensionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 확장자가 없습니다"));

        extension.setDeleted(true);
        extensionRepository.save(extension);
    }

    public CreateExtensionResponse confirmCheckExtension(Long id){
        Extension extension = extensionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 확장자가 없습니다"));
        extension.setChecked(!extension.getChecked());
        extensionRepository.save(extension);
        return CreateExtensionResponse.convertDTO(extension);
    }
}
