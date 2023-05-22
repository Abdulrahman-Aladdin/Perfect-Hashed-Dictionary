package org.example.CLI;

import org.apache.commons.lang3.tuple.Pair;
import org.example.Dictionary.IDictionary;

public class InsertCommand extends AbstractCommand<Void>{

    public InsertCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        System.out.print("Enter your input : ");
        Pair<Boolean,Integer> state = dictionary.insert(scanner.nextLine());

        if (state.getLeft()) {
            System.out.println("Insertion completed successfully");
            System.out.println("Number of collisions = " + state.getRight());
        }
        else
            System.out.println("Error , word already exist");


        return null;
    }
}
