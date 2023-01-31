package com.ishingarov.catus.dto.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record ChangeGroupRequest(
        @NotNull @Min(1) @Max(1024)
        Integer groupId
) {
}
