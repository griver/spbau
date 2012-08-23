package ru.spbau.sorokin.task2;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 13.08.12
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.*;

public class PatternFilter implements FilenameFilter {
    private Pattern pattern;
    public PatternFilter() {
        pattern = Pattern.compile("");
    }

    public PatternFilter(String regularExpression) {
        pattern = Pattern.compile(regularExpression);
    }

    @Override
    public boolean accept(File file, String filename) {
        return !pattern.matcher(filename).matches();
    }
}
