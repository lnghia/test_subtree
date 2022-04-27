package com.example.demo.dto.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginResponseDTO {
    private String accessToken;

    private String refreshToken;
}
