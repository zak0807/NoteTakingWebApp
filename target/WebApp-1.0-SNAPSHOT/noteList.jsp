<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Note Taking App</title>
</head>
<body>

<div class="main">
    <jsp:include page="/header.jsp"/>
    <p style="font-size:30px; color: black">Notes:</p>
    <ul>
        <%
            List<Note> notes = (List<Note>) request.getAttribute("notes");
            if (notes.size() != 0) {
                for (Note note : notes) {
        %>
        <form name="frm" method="post" action="/viewNote.html">
            <input type="submit" name="file" value="<%=note.getTitle()%>"/>
        </form>
        <% }%>
        <%
        } else {
        %>
        <p style="font-size:30px; color: black">No files created yet!</p>
        <%
            }
        %>
        <form name="sortform" method="post" action="/sortByName.html">
            <input type="submit" value="Sort by name"/>
        </form>
        <form name="sortByDateCreatedForm" method="post" action="/sortByDate.html">
            <input type="hidden" name="choice" value="0">
            <input type="submit" value="Sort by date created"/>
        </form>
        <form name="sortByDateEditedForm" method="post" action="/sortByDate.html">
            <input type="hidden" name="choice" value="1">
            <input type="submit" value="Sort by date last edited"/>
        </form>
    </ul>
    <jsp:include page="/footer.jsp"/>
</div>

</body>
</html>
