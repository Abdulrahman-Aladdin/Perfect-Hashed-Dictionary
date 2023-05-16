package org.example.CLI;

import org.apache.commons.lang3.tuple.Pair;
import org.example.Dictionary.IDictionary;

public class BatchInsertCommand extends AbstractCommand<Void>{


    public BatchInsertCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        System.out.print("Enter the path : ");

        Pair<Integer,Integer> output = dictionary.batchInsert(scanner.nextLine());

        if (output == null){
            System.out.println("File not found");
            return null;
        }

        System.out.println("Number of words inserted = " + output.getLeft());
        System.out.println("Number of words existing = " + output.getRight());


        return null;
    }
}
