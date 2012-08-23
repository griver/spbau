package ru.spbau.sorokin.task2;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 13.08.12
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
import java.util.Comparator;
import java.io.File;


class FileComparator implements Comparator<File> {
    @Override
    public int compare(File a, File b) {
        return String.CASE_INSENSITIVE_ORDER.compare(a.getName(), b.getName());
    }
}
