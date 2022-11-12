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
public class AnsweredQuestionnaire {

    private UUID answeredQuestionnaireId;

    private Process process;

    public void addProcessToAnsweredQuestionnaire(Process process){
        //remove old association
        if(this.process != null){
            this.process.getAnsweredQuestionnaires().remove(this);
        }
        this.process = process;

        //add new association
        if(process != null){
            this.process.getAnsweredQuestionnaires().add(this);
        }
    } 
}