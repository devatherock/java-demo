package io.github.devatherock.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDTORequest {

    private UUID processId;

    private Set<User> users = new HashSet<>();
    private Set<AnsweredQuestionnaire> answeredQuestionnaires = new HashSet<>();

    private Set<UnitType> units = new HashSet<>();
}