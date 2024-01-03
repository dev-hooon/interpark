package dev.hooon.auth.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {

	ACCESS("prgrmsjsonwebtokensecretkey202312access", 24 * 60 * 60 * 1000L),
	REFRESH("prgrmsjsonwebtokensecretkey202312refresh", 30 * 24 * 60 * 60 * 1000L);

	private final String secretKey;
	private final long validTime;
}
