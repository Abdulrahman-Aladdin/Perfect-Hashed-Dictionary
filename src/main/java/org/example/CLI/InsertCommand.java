package org.example.CLI;

import org.example.Dictionary.IDictionary;

public class InsertCommand extends AbstractCommand<Void>{

    public InsertCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        System.out.print("Enter your input : ");
        boolean state = dictionary.insert(scanner.nextLine());

        if (state)
            System.out.println("Insertion completed successfully");
        else
            System.out.println("Error , word already exist");


        return null;
    }
}
