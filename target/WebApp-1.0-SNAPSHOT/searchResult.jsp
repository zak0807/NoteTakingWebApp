<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
</head>
<body>

<div class="main">
    <jsp:include page="/header.jsp"/>
    <p style="font-size:30px; color: black">Notes</p>
    <p style="font-size:20px; color: black">Search Results:</p>
    <%
        List<Note> notes = (List<Note>) request.getAttribute("result");
        if (notes.size() != 0) {
    %>
    <ul>
        <%
            for (Note note : notes) {
        %>
        <form name="frm" method="post" action="/viewNote.html">
            <input type="submit" name="file" value="<%=note.getTitle()%>"/>
        </form>
        <% }
        } else {%>
        <p style="font-size:30px; color: black">Nothing found!</p>
        <%}%>
    </ul>
    <jsp:include page="/footer.jsp"/>
</div>

</body>
</html>