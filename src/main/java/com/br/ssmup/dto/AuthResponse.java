package com.br.ssmup.dto;

public record AuthResponse(String token, String type, long expiresIn) {
}
