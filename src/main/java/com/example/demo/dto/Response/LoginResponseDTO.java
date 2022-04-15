package com.example.demo.dto.Response;

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
