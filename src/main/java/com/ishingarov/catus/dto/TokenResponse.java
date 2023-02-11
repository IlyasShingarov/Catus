package com.ishingarov.catus.dto;

import com.ishingarov.catus.dto.user.UserResponseSlim;

public record TokenResponse(String token, UserResponseSlim user) { }
