package org.example.CLI;

import org.example.Dictionary.IDictionary;

public class DeleteCommand extends AbstractCommand<Void>{


    public DeleteCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {


        System.out.print("Enter your input : ");
        boolean state = dictionary.delete(scanner.nextLine());

        if (state)
            System.out.println("Deletion completed successfully");
        else
            System.out.println("Error , word doesn't exist");


        return null;
    }
}
