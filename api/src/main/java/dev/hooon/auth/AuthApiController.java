package dev.hooon.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hooon.auth.application.AuthService;
import dev.hooon.auth.dto.request.AuthRequest;

import dev.hooon.auth.dto.response.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthApiController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
		@Valid @RequestBody AuthRequest authRequest
	) {
		AuthResponse authResponse = authService.login(authRequest);
		return ResponseEntity.ok(authResponse);
	}

	@NeedAuth
	@PostMapping("/token")
	public ResponseEntity<String> reIssueAccessToken(
		@RequestBody RefreshTokenForReIssue refreshTokenForReIssue
	) {
		String accessToken = authService.generateAccessTokenFromRefreshToken(refreshTokenForReIssue.refreshToken());
		return ResponseEntity.ok(accessToken);
	}
}
