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
                <p style="font-size:15px; color: black">Enter the URL to the picture or resource you want to
                    store.</p>
                <div class="url">
                    <input type="text" name="noteTxt" placeholder="Enter the URL"/>
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