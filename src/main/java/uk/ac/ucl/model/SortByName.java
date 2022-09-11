package uk.ac.ucl.model;

import java.util.ArrayList;

public class SortByName {

    public ArrayList<Note> sortArrayListofNotesByName(ArrayList<Note> arrayListofNotes) {
        for (int i = 0; i < arrayListofNotes.size(); i++) {
            for (int j = 0; j < arrayListofNotes.size() - 1; j++) {
                if (compareStrings(arrayListofNotes.get(j).getTitle().toLowerCase(), arrayListofNotes.get(j + 1).getTitle().toLowerCase()) != 0) {
                    Note temp = arrayListofNotes.get(j + 1);
                    arrayListofNotes.set(j + 1, arrayListofNotes.get(j));
                    arrayListofNotes.set(j, temp);
                }
            }
        }
        return arrayListofNotes;
    }

    private int compareStrings(String s1, String s2) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> sortedStrings = new ArrayList<>();
        strings.add(s1);
        strings.add(s2);
        sortedStrings.add(s1);
        sortedStrings.add(s2);
        java.util.Collections.sort(sortedStrings);
        if (strings.equals(sortedStrings)) {
            return 0;
        } else {
            return -1;
        }
    }
}
