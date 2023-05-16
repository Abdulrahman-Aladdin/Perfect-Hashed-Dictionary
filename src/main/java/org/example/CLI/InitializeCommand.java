package org.example.CLI;

import org.example.Dictionary.DictionaryFactory;
import org.example.Dictionary.IDictionary;
import org.example.Map.HashMap;

public class InitializeCommand extends AbstractCommand<IDictionary>{


    public InitializeCommand() {
        super(null);
    }

    @Override
    public IDictionary execute(String input) {
        this.dictionary =  switch (input){
            case "1" -> DictionaryFactory.getDictionary(HashMap.Linear);
            case "2" -> DictionaryFactory.getDictionary(HashMap.Quadratic);
            default -> null;
        };

        return this.dictionary;
    }
}
