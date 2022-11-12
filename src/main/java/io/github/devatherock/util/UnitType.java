package io.github.devatherock.util;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UnitType {
    private UUID unitTypeId;

    private Boolean isResponsibleUnit = false;
}