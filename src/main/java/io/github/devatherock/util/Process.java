package io.github.devatherock.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public class Process  {

    private UUID processId;

    private List<User> users = new ArrayList<>();

    private List<UnitType> units = new ArrayList<>();

    private String furtherComment;

    private List<AnsweredQuestionnaire> answeredQuestionnaires = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Process)) return false;
        Process process = (Process) o;
        return getProcessId().equals(process.getProcessId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProcessId());
    }
}