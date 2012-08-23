package ru.spbau.sorokin.task2;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * PatternFilter is used to reject files with specific names.
 *
 * @author Sorokin Artyom
 * @see ru.spbau.sorokin.task2.FilesystemWalker
 */
public class PatternFilter implements FilenameFilter {
    private Pattern pattern;

    /**
     * Create new PatternFilter, witch allows any file
     */
    public PatternFilter() {
        pattern = Pattern.compile("");
    }

    /**
     * Create new PatternFilter, witch reject files matches with regularExpression
     *
     * @param regularExpression Regular Expression
     */
    public PatternFilter(String regularExpression) {
        pattern = Pattern.compile(regularExpression);
    }

    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param filename the name of the file.
     * @return true if and only if the name should be included in the file list
     */
    @Override
    public boolean accept(File dir, String filename) {
        return !pattern.matcher(filename).matches();
    }
}
