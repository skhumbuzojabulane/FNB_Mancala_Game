package com.mancala.mancala.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Error response with messages and (when available) more info
 */

public class ErrorResponseDTO {
    
    private List<ValidationErrorDTO> validationErrorDTO = new ArrayList<>();

    public List<ValidationErrorDTO> getViolations() {
        return validationErrorDTO;
    }
}
