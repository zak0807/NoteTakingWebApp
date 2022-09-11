<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Note</title>
    <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <p style="font-size:30px; color: black">Take your Notes here.</p>
    <jsp:include page="dropdownMenu.jsp"/>
    <div class="page">
        <div class="page-body">
            <p style="font-size:15px; color: black">Title</p>

            <form method="post" action="${pageContext.request.contextPath}/saveNote.html">
                <div class="title">
                    <input type="text" name="title" placeholder="Enter the title"/>
                </div>
                <p style="font-size:15px; color: black">Index</p>
                <div class="index">
                    <input type="text" name="index" placeholder="Enter the index"/>
                </div>
                <p style="font-size:15px; color: black">Note</p>
                <p style="font-size:15px; color: black">Please type your URLs on a new line.</p>
                <div class="noteContent">
                    <textarea rows="20" cols="100" id="noteTxt" name="noteTxt"
                              placeholder="Enter your note."></textarea><br/>
                </div>
                <div class="saveButton">
                    <input type="submit" name="submit" value="Save"/>
                </div>

            </form>

        </div>
    </div>


    <div id="notes" class=
            "row container-fluid">
    </div>
</div>
</body>
</html>