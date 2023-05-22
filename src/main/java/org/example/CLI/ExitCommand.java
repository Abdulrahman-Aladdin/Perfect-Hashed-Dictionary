package org.example.CLI;

import org.example.Analysis.Analyst;
import org.example.Dictionary.IDictionary;

public class ExitCommand extends AbstractCommand<Void> {

    public ExitCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {
        Analyst analyst = Analyst.getInstance();
        analyst.getResults();
        System.exit(0);
        return null;
    }

}
