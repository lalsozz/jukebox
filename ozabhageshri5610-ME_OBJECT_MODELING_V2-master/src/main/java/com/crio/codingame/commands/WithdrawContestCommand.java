package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.services.IUserService;





public class WithdrawContestCommand implements ICommand{

    private final IUserService userService;
    
    public WithdrawContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute withdrawContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["WITHDRAW_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {

        
        String userName = tokens.get(2);
        
     
        // if (userService.withdrawContest(tokens.get(1), userName)
        //         .getRegisterationStatus() == RegisterationStatus.NOT_REGISTERED)
        // {
        //     result = "Cannot Withdraw Contest. User for given contest id:" + contestId
        //             + " is not registered!";
        // }
        //  if(userService.withdrawContest(tokens.get(1), userName).getContestName()==null){
        //   result =  "Cannot Withdraw Contest. Contest Creator:" + userName
        //             + "not allowed to withdraw contest!";
        //  }
         
       
        try{
          String w = userService.withdrawContest(tokens.get(1), userName).toString();
            System.out.println(w);
        }
        // catch(ContestNotFoundException c){
        //     System.out.println("Cannot Withdraw Contest. Contest for given id:"+contestId+" not found!");
        // }
        //  catch(UserNotFoundException u){
        //     System.out.println("Cannot Withdraw Contest. User for given name:"+ userName+" not found!");
        // }
        // catch (InvalidOperationException i) {
            
        //     System.out.println("Cannot Withdraw Contest. Contest for given id:" + contestId + " is ended!");
             
        // }
           
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
          
    }
    
}
