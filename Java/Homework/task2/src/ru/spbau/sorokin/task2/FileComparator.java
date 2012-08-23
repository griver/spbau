package ru.spbau.sorokin.task2;

import java.util.Comparator;
import java.io.File;

/**
 * FileComparator imposes a total ordering by name on files.
 *
 * @author Sorokin Artyom
 * @see ru.spbau.sorokin.task2.FilesystemWalker#walk(String, java.io.File, boolean, boolean)
 */
class FileComparator implements Comparator<File> {

    /**
     * Compares its two arguments for order.
     *
     * @param  a the first file to be compared.
     * @param  b the second file to be compared.
     * @return a < b - a negative integer, a == b - zero, a > b - a positive integer
     */
    @Override
    public int compare(File a, File b) {
        return String.CASE_INSENSITIVE_ORDER.compare(a.getName(), b.getName());
    }
}
