<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/meta.jsp"/>
    <title>Read Text</title>
</head>
<div class="main">
    <jsp:include page="/header.jsp"/>
    <%
        String textContent = "";
        ArrayList<String> urls = new ArrayList<>();
        ArrayList<String> textfile = (ArrayList<String>) request.getAttribute("textfile");
        for (int index = 0; index < textfile.size(); index++) {
            String line = textfile.get(index);
            if ((line != null) && (index != 0) && (index != 1) && (index != textfile.size() - 1) && (index != textfile.size() - 2)) {
                if (line.contains("§")) {
                    String substring = line.substring(0, line.length() - 1);
                    urls.add(substring);
                    textContent += substring;
                } else {
                    textContent += line;
                }
            }
        }
    %>

    <form method="post" action="${pageContext.request.contextPath}/updateNote.html">>
        <p style="font-size:15px; color: black">Title</p>
        <div class="title">
            <input type="text" name="title" value="<%=textfile.get(0)%>"/>
        </div>
        <p style="font-size:15px; color: black">Index</p>
        <div class="index">
            <input type="text" name="index" value="<%=textfile.get(1)%>"/>
        </div>
        <p style="font-size:15px; color: black">URLs found in note:</p>
        <% if (urls.size() != 0) {
            String href = "";
            for (String url : urls) {
                href = url;
        %>
        <p><a href="<%=href%>"><%=href%>
        </a></p>
        <%
                }
            }
        %>
        <p style="font-size:15px; color: black">Note</p>
        <p style="font-size:15px; color: black">Created:<%=textfile.get(textfile.size() - 2)%>
        </p>
        <div class="noteContent">
            <textarea rows="20" cols="100" name="noteTxt"> <%=textContent%> </textarea>
        </div>
        <p style="font-size:15px; color: black">Last Edited:<%=textfile.get(textfile.size() - 1)%>
        </p>
        <div class="updateButton">
            <input type="hidden" name="dateCreated" value="<%=textfile.get(textfile.size()-2)%>">
            <input type="submit" name="Update" value="Update"/>
        </div>
    </form>

    <div class="deleteButton">
        <form name="deleteform" method="post" action="/deleteNote.html">
            <input type="hidden" name="title" value="<%=textfile.get(0)%>">
            <input type="submit" value="Delete"/>
        </form>
    </div>

</div>

</html>