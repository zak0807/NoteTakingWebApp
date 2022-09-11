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
import java.util.List;

// The servlet invoked to perform a search.
// The url http://localhost:8080/runsearch.html is mapped to calling doPost on the servlet object.

@WebServlet("/runsearch.html")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NoteModel noteModel = null;
        try {
            noteModel = NoteModelFactory.getModel();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        List<Note> searchResult = noteModel.search(request.getParameter("searchstring").trim());
        if (searchResult != null) {
            request.setAttribute("result", searchResult);
            // Invoke the JSP page.
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
            dispatch.forward(request, response);
        }
    }
}
