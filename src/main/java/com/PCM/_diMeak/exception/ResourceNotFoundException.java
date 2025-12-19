package com.PCM._diMeak.exception;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resource) {
        super(resource + " non trouvé");
    }
}
