package uk.ac.ucl.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SortByDate {

    public ArrayList<Note> sortArrayListofNotesByDate(ArrayList<Note> arrayListofNotes, int choice) throws java.text.ParseException {
        for (int i = 0; i < arrayListofNotes.size(); i++) {
            for (int j = 0; j < arrayListofNotes.size() - 1; j++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date1;
                Date date2 = null;
                //use of ternary operator with choice == 0 being sort by date created otherwise sort by date last edited
                date1 = (choice == 0 ? (sdf.parse(arrayListofNotes.get(j).getDateCreated())) : (sdf.parse(arrayListofNotes.get(j).getDateLastEdited())));
                date2 = (choice == 0 ? (sdf.parse(arrayListofNotes.get(j + 1).getDateCreated())) : (sdf.parse(arrayListofNotes.get(j + 1).getDateLastEdited())));

                if (date1.before(date2)) {
                    Note temp = arrayListofNotes.get(j + 1);
                    arrayListofNotes.set(j + 1, arrayListofNotes.get(j));
                    arrayListofNotes.set(j, temp);
                }
            }
        }
        return arrayListofNotes;
    }
}
