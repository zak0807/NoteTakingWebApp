package uk.ac.ucl.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class NoteModel {
    ArrayList<Note> arrayListofNotes;

    public NoteModel() throws java.text.ParseException {
        this.arrayListofNotes = setArrayListofNotes();
    }

    private void clearJsonFile() {
        Path path = Paths.get("./data/notes.json");
        {
            try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                // ...
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void deleteNote(String title) {
        int toRemove = 0;
        clearJsonFile();
        for (int i = 0; i < arrayListofNotes.size(); i++) {
            if (!arrayListofNotes.get(i).getTitle().equals(title)) {
                writeToJSonArray(arrayListofNotes.get(i).getTitle(), arrayListofNotes.get(i).getContent(), arrayListofNotes.get(i).getDateCreated(), arrayListofNotes.get(i).getIndex(), arrayListofNotes.get(i).dateLastEdited);
            } else {
                toRemove = i;
            }
        }
        arrayListofNotes.remove(toRemove);
    }


    public void writeToJSonArray(String title, String note, String dateCreated, String index, String dateLastEdited) {
        JSONObject noteFile = new JSONObject();

        noteFile.put("title", title);
        noteFile.put("note", note);
        noteFile.put("datecreated", dateCreated);
        noteFile.put("index", index);
        noteFile.put("datelastedited", dateLastEdited);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        File file = new File("./data/notes.json");
        if (file.length() == 0) {
            jsonArray.add(noteFile);
            jsonObject.put("notes", jsonArray);
        } else {
            JSONParser jsonParser = new JSONParser();
            try {
                //Parsing the contents of the JSON file
                jsonObject = (JSONObject) jsonParser.parse(new FileReader("./data/notes.json"));
                //Retrieving the array
                jsonArray = (JSONArray) jsonObject.get("notes");
                jsonArray.add(noteFile);
                jsonObject.put("notes", jsonArray);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter("./data/notes.json");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Note> setArrayListofNotes() throws java.text.ParseException {
        ArrayList<Note> temp = new ArrayList<>();
        String title = null;
        String content = null;
        String dateCreated = null;
        String index = "";
        String dateLastEdited = "";
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader("./data/notes.json"));
            jsonArray = (JSONArray) jsonObject.get("notes");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            title = (String) obj.get("title");
            content = (String) obj.get("note");
            dateCreated = (String) obj.get("datecreated");
            index = (String) obj.get("index");
            dateLastEdited = (String) obj.get("datelastedited");
            Note note = new Note(title, content, dateCreated, index, dateLastEdited);
            temp.add(note);
        }
        return temp;
    }

    public void addToArrayListofNotes(Note note) {
        for (int i = 0; i < arrayListofNotes.size(); i++) {
            Note readNote = arrayListofNotes.get(i);
            if (readNote.getDateCreated().equals(note.getDateCreated())) {
                deleteNote(readNote.getTitle());
                arrayListofNotes.add(note);
                return;
            }
        }
        arrayListofNotes.add(note);
    }

    public ArrayList<Note> getArrayListofNotes() {
        return arrayListofNotes;
    }

    public ArrayList<Note> sortArrayListofNotesByDate(int choice) throws java.text.ParseException {
        return (new SortByDate().sortArrayListofNotesByDate(arrayListofNotes, choice));
    }

    public ArrayList<Note> sortArrayListofNotesByName() {
        return (new SortByName().sortArrayListofNotesByName(arrayListofNotes));
    }

    public ArrayList<String> readJSONFile(String fileName) {
        String content = null;
        String title = null;
        String dateCreated = null;
        String index = "";
        String dateLastEdited = "";
        ArrayList<String> temp = new ArrayList<>();
        String line = "";
        for (int i = 0; i < arrayListofNotes.size(); i++) {
            if (arrayListofNotes.get(i).getTitle().equals(fileName)) {
                title = arrayListofNotes.get(i).getTitle();
                content = arrayListofNotes.get(i).getContent();
                dateCreated = arrayListofNotes.get(i).getDateCreated();
                index = arrayListofNotes.get(i).getIndex();
                dateLastEdited = arrayListofNotes.get(i).getDateLastEdited();
                break;
            }
        }
        temp.add(title);
        temp.add(index);
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) != '\n') {
                line += content.charAt(i);
            } else {
                if (line.contains("http") || line.contains(".co")) {
                    line += "§";
                }
                temp.add(line);
                line = "";
            }
        }
        if (line.contains("http") || line.contains("www.") || line.contains(".co")) {
            line += "§";
        }
        temp.add(line);
        temp.add(dateCreated);
        temp.add(dateLastEdited);
        return temp;
    }


    public List<Note> search(String keyword) {
        List<Note> temp = new ArrayList<>(); //use the array list and a more indepth if statement to search multiple field matches and add to arraylist
        for (Note note : arrayListofNotes) {
            if (note.getIndex().contains(keyword) || note.getTitle().trim().toLowerCase().contains(keyword.toLowerCase()) || (note.getContent().trim().toLowerCase().contains(keyword.toLowerCase()))) {
                temp.add(note);
            }
        }
        return temp;
    }
}
