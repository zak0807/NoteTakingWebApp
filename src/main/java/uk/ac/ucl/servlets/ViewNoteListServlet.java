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


@WebServlet("/noteList.html")
public class ViewNoteListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the noteModel
        NoteModel noteModel = null;
        try {
            noteModel = new NoteModelFactory().getModel();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Note> notes = noteModel.getArrayListofNotes();
        request.setAttribute("notes", notes);

        // Invoke the JSP.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/noteList.jsp");
        dispatch.forward(request, response);
    }
}
