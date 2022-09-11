package uk.ac.ucl.model;

import java.io.IOException;
import java.text.ParseException;

// This class gives access to the noteModel to any other class that needs it.
// Calling the static method getModel (i.e., NoteModelFactory.getModel()) returns
// an initialised NoteModel object. This version limits the program to one noteModel object,
// which is returned whenever getModel is called.
// The factory also illustrates how a data file can be passed to the noteModel.

public class NoteModelFactory {
    private static NoteModel noteModel;

    public static NoteModel getModel() throws ParseException, IOException, org.json.simple.parser.ParseException {
        if (noteModel == null) {
            noteModel = new NoteModel();
        }
        return noteModel;
    }
}
