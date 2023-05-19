package org.example.CLI;

import org.example.ADT.HashMapSpace;
import org.example.Dictionary.DictionaryFactory;
import org.example.Dictionary.IDictionary;

public class InitializeCommand extends AbstractCommand<IDictionary> {

    public InitializeCommand() {
        super(null);
    }

    @Override
    public IDictionary execute(String input) {

        int N;
        while (true) {
            try {
                System.out.print("Please enter table size: ");
                N = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid option");
            }
        }

        this.dictionary = switch (input) {
            case "1" -> DictionaryFactory.getDictionary(HashMapSpace.Linear, N);
            case "2" -> DictionaryFactory.getDictionary(HashMapSpace.Quadratic, N);
            default -> null;
        };

        return this.dictionary;
    }
}
