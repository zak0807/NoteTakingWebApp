package uk.ac.ucl.servlets;

import uk.ac.ucl.model.NoteModel;
import uk.ac.ucl.model.NoteModelFactory;
import uk.ac.ucl.model.Note;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@WebServlet("/viewNote.html")
public class ViewNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NoteModel noteModel = null;
        try {
            noteModel = new NoteModelFactory().getModel();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        String fileName = request.getParameter("file");
        String filePath = null;
        ArrayList<Note> listOfNoteObjects = new ArrayList<>();
        listOfNoteObjects = noteModel.getArrayListofNotes();
        for (Note note : listOfNoteObjects) {
            if (note.getTitle().equals(fileName)) {
                filePath = note.getTitle();
                break;
            }
        }
        if (fileName == null) {
            System.out.println("null");
        }

        request.setAttribute("textfile", noteModel.readJSONFile(filePath));
        // Invoke the JSP.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/displayNote.jsp");
        dispatch.forward(request, response);
    }
}