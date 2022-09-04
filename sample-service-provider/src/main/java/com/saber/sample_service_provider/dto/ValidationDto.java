package com.saber.sample_service_provider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDto {
    private String fieldName;
    private String constraintMessage;
}
