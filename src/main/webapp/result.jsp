<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Photogallery</title>
        <link rel='stylesheet' href='style.css' type='text/css' media='all' />
    </head>
    <body>
        <div align="center">
            <h2>Your photo id is: ${photo_id}</h2>

            <input type="submit" value="Delete Photo" onclick="window.location='/delete/${photo_id}';" />
            <input type="submit" value="Upload New" onclick="window.location='/';" />

            <br/><br/><img src="/photo/${photo_id}" />
        </div>
    </body>
</html>
