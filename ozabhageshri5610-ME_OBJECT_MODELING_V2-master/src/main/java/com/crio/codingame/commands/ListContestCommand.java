package com.crio.codingame.commands;

import java.util.List;


import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size()>1){
        String s = contestService.getAllContestLevelWise(Level.valueOf(tokens.get(1))).toString();
        System.out.println(s);
        }
        else 
        {
            String s = contestService.getAllContestLevelWise(null).toString();
            System.out.println(s);
        }
    }
    
}
