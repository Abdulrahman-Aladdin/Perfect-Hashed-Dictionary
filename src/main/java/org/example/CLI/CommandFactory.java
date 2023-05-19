package org.example.CLI;

import org.example.Dictionary.IDictionary;

public class CommandFactory {

    public static AbstractCommand<Void> getCommand(Commands command, IDictionary dictionary){
        return switch (command){
            case INSERT -> new InsertCommand(dictionary);
            case SEARCH -> new SearchCommand(dictionary);
            case DELETE -> new DeleteCommand(dictionary);
            case BATCH_INSERT -> new BatchInsertCommand(dictionary);
            case BATCH_DELETE -> new BatchDeleteCommand(dictionary);
            case EXIT -> new ExitCommand(dictionary);
            default -> null;
        };
    }

}
