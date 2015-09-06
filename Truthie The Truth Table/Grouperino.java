import java.util.ArrayList;
public class Grouperino {
    public static ArrayList<ArrayList<Integer>> groupsie(int[][][] theMap){
        ArrayList<ArrayList<Integer>> groupList = new ArrayList<ArrayList<Integer>>();
        // Search of all groups
        for (int i = 6; i >= 0;i--){
            int groupSize = (int) Math.pow(2, i);
            for (int dimensionZ = 1; dimensionZ <= groupSize; dimensionZ *= 2){
                for (int dimensionX = 1; dimensionX <= groupSize / dimensionZ; dimensionX *= 2){
                    int dimensionY = groupSize / dimensionZ / dimensionX;
                    if (dimensionX <= theMap[0][0].length && dimensionY <= theMap[0].length && dimensionZ <= theMap.length){
                        for (int startZ = 0; startZ <= theMap.length - dimensionZ + 1; startZ++) {
                            for (int startY = 0; startY <= theMap[0].length - dimensionY + 1; startY++) {
                                for (int startX = 0; startX <= theMap[0][0].length - dimensionX + 1; startX++) {
                                    boolean take = true;
                                    ArrayList<Integer> group = new ArrayList<Integer>();
                                    for (int z = startZ; z < startZ + dimensionZ; z++){
                                        for (int y = startY; y < startY + dimensionY; y++){
                                            for (int x = startX; x < startX + dimensionX; x++){
                                                int zz = z % theMap.length;
                                                int yy = y % theMap[0].length;
                                                int xx = x % theMap[0][0].length;
                                                if (theMap[zz][yy][xx] == 0) {
                                                    take = false;
                                                    break;
                                                } else {
                                                    group.add(zz * 16 + yy * 4 + xx);
                                                }
                                            }
                                        }
                                        if (!take) break;
                                    }
                                    if (take) {
                                        groupList.add(group);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Eliminate redundant terms
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<Integer> copy = new ArrayList<Integer>();
            for (Integer j : groupList.get(i)) {
                copy.add(j);
            }
            for (int j = 0; j < i; j++) {
                for (Integer k : groupList.get(j)) {
                    if (copy.contains(k)){
                        copy.remove(k);
                    }
                }
            }
            if (copy.size() == 0){
                groupList.remove(i);
                i--;
            }
        }
        return groupList;
    }

    public static int translatePosition(int pos){
        int copy = pos;
        for(int i = 0; i <= (int)(Math.log(pos) / Math.log(2)); i += 2){
            if ((copy >> i & 3) == 2) {
                copy = (copy & ~(3 << i) | (3 << i));
            } else if ((copy >> i & 3) == 3) {
                copy = (copy & ~(3 << i) | (2 << i));
            }
        }
        return copy;
    }

    public static int[] checkForChanges(ArrayList<Integer> term, int numOfVar) {
        ArrayList<Integer> translated = new ArrayList<Integer>();
        for (int i = 0; i < term.size(); i++) {
            translated.add(translatePosition(term.get(i)));
        }
        int outputTerm[] = new int[numOfVar];
        for (int i = 0; i < numOfVar; i++) {
            boolean take = true;
            for (int j = 1; j < translated.size(); j++) {
                if ((translated.get(0) >> i & 1) != (translated.get(j) >> i & 1)) {
                    take = false;
                }
            }
            if (take) {
                if ((translated.get(0) >> i & 1) == 0){
                    outputTerm[numOfVar - i - 1] = 1;
                }
                if ((translated.get(0) >> i & 1) == 1){
                    outputTerm[numOfVar - i - 1] = 2;
                }
            } else outputTerm[numOfVar - i - 1] = 0;
        }
        return outputTerm;
    }

    public static String interpret(int[] mode, String vars){
        String result = "";
        for (int i = 0; i < mode.length; i++) {
            if (mode[i] == 1){
                result += vars.charAt(i) + "'";
            } else if (mode[i] == 2){
                result += vars.charAt(i);
            }
        }
        return result;
    }

    public static String simplify (int[][][] theMap, String vars) {
        String result = "";
        ArrayList<ArrayList<Integer>> theList = groupsie(theMap);
        ArrayList<String> sortList = new ArrayList<String>();
        for (int i = 0; i < theList.size(); i++) {
            sortList.add(interpret(checkForChanges(theList.get(i), vars.length()), vars));
        }
        sortList.sort(null);

        for (int i = 0; i < sortList.size() - 1; i++) {
            result += sortList.get(i) + " + ";
        }
        if (sortList.size() > 0)
            result += sortList.get(sortList.size() - 1);
        return result;
    }
}