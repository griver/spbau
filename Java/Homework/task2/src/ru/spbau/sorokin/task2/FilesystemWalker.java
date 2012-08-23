package ru.spbau.sorokin.task2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * FilesystemWalker traverses and outputs File System subtree.
 *
 * @author Sorokin Artyom
 * @see ru.spbau.sorokin.task2.Task2
 */
public class FilesystemWalker {
    private PrintStream stream;
    private FilenameFilter filter;

    /**
     * Create new FilesystemWalker.
     *
     * @param stream used to output filesystem subtree
     */
    public FilesystemWalker(PrintStream stream) {
        this.stream = stream;
    }

    /**
     * Walks and outputs subtree off directory specified in rootPath argument
     *
     * @param rootPath The absolute path to the directory
     * @param filter FilenameFilter to reject files by name
     */
    public void startWalk(String rootPath, FilenameFilter filter) {
        this.filter = filter;
        File root = new File(rootPath);

        if(!root.exists())
            stream.println("File even does not exist!");
        else
            walk("", root, true, true);
    }

    private void walk(String prefix, File current, boolean isBottom, boolean isRoot) {
        String filename = "";

        if(!isRoot)
            filename += "|_";

        try {
            if(current.isHidden())
                return;

            filename += current.getName();

            if(!current.canRead()) {
                stream.println(prefix + filename +"(access denied)");
                return;
            }

            if(!current.isDirectory()) {
                stream.println(prefix + filename);
                return;
            }

            File[] files = current.listFiles(filter);
            Arrays.sort(files, new FileComparator());

            stream.println(prefix + filename);

            for(int i = 0; i < files.length ; ++i) {
                String newPrefix = prefix + getPrefix(isBottom, filename.length());
                walk(newPrefix, files[i], i == files.length - 1, false);
            }

        } catch(SecurityException e) {
            stream.println(prefix + filename + "(access denied)");
        }

    }

    private String getPrefix(boolean withoutBranch, int nameLength) {
        String prefix;

        if(withoutBranch)
            prefix = " ";
        else
            prefix = "|";

        for(int i = 1; i < nameLength; ++i)
            prefix += " ";

        return prefix;
    }
}
