package uk.ac.ucl.model;

public class Note implements InterfaceNote {

    String title;
    String content;
    String dateCreated;
    String index;
    String dateLastEdited;

    public Note(String title, String content, String date, String index, String dateLastEdited) {
        this.title = title;
        this.content = content;
        this.dateCreated = date;
        this.index = index;
        this.dateLastEdited = dateLastEdited;
    }

    @Override
    public void setTitle(String titleName) {
        this.title = titleName;
    }

    public void setContent(String contentString) {
        this.content = contentString;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getIndex() {
        return index;
    }

    public String getDateLastEdited() {
        return dateLastEdited;
    }
}
