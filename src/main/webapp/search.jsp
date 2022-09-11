<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title></title></head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <p style="font-size:30px; color: black">Search</p>
    <form method="POST" action="/runsearch.html">
        <input type="text" name="searchstring" placeholder="Enter search keyword here"/>
        <input type="submit" value="Search"/>
    </form>
</div>
</body>
</html>