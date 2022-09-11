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


@WebServlet("/sortByDate.html")
public class SortNotesByDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NoteModel noteModel = null;
        try {
            noteModel = NoteModelFactory.getModel();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Note> sortedByNameNotes = null;
        int choice = Integer.parseInt(request.getParameter("choice"));
        try {
            sortedByNameNotes = noteModel.sortArrayListofNotesByDate(choice);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setAttribute("notes", sortedByNameNotes);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/noteList.jsp");
        dispatch.forward(request, response);
    }
}