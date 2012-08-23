package ru.spbau.sorokin.task2;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 13.08.12
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 *First task in second homework
 *
 *
 */

public class FilesystemWalker {
    private PrintStream stream;
    private FilenameFilter filter;

    public FilesystemWalker(PrintStream stream) {
        this.stream = stream;
    }

    public void startWalk(File root, FilenameFilter filter) {
        this.filter = filter;
        stream.println(root.getName());
        if(root.canRead())
            walk(getPrefix(false, root.getName().length()- 1), root);
    }

    public void walk(String prefix, File currentRoot) {

        File[] files = currentRoot.listFiles(filter);
        Arrays.sort(files, new FileComparator());

        for(int i = 0; i < files.length ; ++i) {

            if(files[i].isHidden()) continue;

            stream.print(prefix + "|_"+files[i].getName());

            if(!files[i].canRead())
                stream.println(" (access denied)");
            else {
                stream.println();
                if(files[i].isDirectory())
                    walk(prefix + getPrefix((i < files.length -1), files[i].getName().length()+ 1), files[i]);
            }

        }
    }

    public void startWalk2(String rootPath, FilenameFilter filter) {
        this.filter = filter;
        File root = new File(rootPath);

        if(!root.exists())
            stream.println("File even does not exist!");
        else
            walk2("", root, true, true);
    }

    public void walk2(String prefix, File current, boolean isBottom, boolean isRoot) {

        String writeString = prefix;

        if(!isRoot)
            writeString+= "_|";

        try {
            if(current.isHidden())
                return;

            writeString += current.getName();

            if(!current.canRead()) {
                stream.println(writeString +"(access denied)");
                return;
            }

            if(!current.isDirectory()) {
                stream.println(writeString);
                return;
            }

            File[] files = current.listFiles(filter);
            Arrays.sort(files, new FileComparator());

            stream.println(writeString);

            for(int i = 0; i < files.length ; ++i) {
                String newPrefix = prefix + getPrefix2(isBottom, current.getName().length() + 1);
                walk2(newPrefix, files[i], i == files.length - 1, false);
            }

        } catch(SecurityException e) {
            stream.println(writeString + "(access denied)");
        }

    }

    private String getPrefix2(boolean withoutBranch, int nameLength) {
        String prefix;

        if(withoutBranch)
            prefix = " ";
        else
            prefix = "|";

        for(int i = 0; i < nameLength; ++i)
            prefix += " ";

        return prefix;
    }

    private String getPrefix(boolean withBranch, int nameLength) {
        String prefix;

        if(withBranch)
            prefix = "|";
        else
            prefix = " ";

        for(int i = 0; i < nameLength; ++i)
            prefix += " ";

        return prefix;
    }
}
