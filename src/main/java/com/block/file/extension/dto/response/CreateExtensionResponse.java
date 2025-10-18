package com.block.file.extension.dto.response;

import com.block.file.extension.entity.Extension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExtensionResponse {
    private Long id;
    private String name;
    private Boolean checked;
    private Boolean fixed;

    public static CreateExtensionResponse convertDTO(Extension extension) {
        return CreateExtensionResponse.builder()
                .id(extension.getId())
                .name(extension.getName())
                .checked(extension.getChecked())
                .fixed(extension.getFixed())
                .build();
    }
}
