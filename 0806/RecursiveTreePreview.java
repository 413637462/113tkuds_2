import java.util.List;

public class RecursiveTreePreview {
    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for(Folder sub : folder.subfolders) count += countFiles(sub);
        return count;
    }
    
    public static void printMenu(MenuItem item, int level) {
        System.out.println("  ".repeat(level) + item.name);
        for(MenuItem child : item.children) printMenu(child, level+1);
    }
    
    public static void flatten(Object[] array, List<Object> result) {
        for(Object o : array) {
            if(o instanceof Object[]) flatten((Object[])o, result);
            else result.add(o);
        }
    }
}

class Folder {
    List<File> files;
    List<Folder> subfolders;
}

class MenuItem {
    String name;
    List<MenuItem> children;
}