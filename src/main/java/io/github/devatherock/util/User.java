package io.github.devatherock.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID userId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private List<Process> processes = new ArrayList<>();


    public void addProcessToUser(Process process){
        this.processes.add(process);
        process.getUsers().add(this);
    }

    public void removeProcessFromUser(Process process){
        this.processes.remove(process);
        process.getUsers().remove(this);
    }

}
