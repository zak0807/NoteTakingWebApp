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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateNote.html")
public class UpdateNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteModel noteModel = null;
        try {
            noteModel = new NoteModelFactory().getModel();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        String content = request.getParameter("noteTxt").trim();
        String title = request.getParameter("title").trim();
        String date = request.getParameter("dateCreated").trim();
        String index = request.getParameter("index").trim();
        //get the current date for the edit made
        Date dateLE = new Date();

        String dateLastEdited = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateLE);
        noteModel.addToArrayListofNotes(new Note(title, content, date, index, dateLastEdited));
        noteModel.writeToJSonArray(title, content, date, index, dateLastEdited);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/note.jsp");
        dispatch.forward(request, response);
    }
}

