<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Photogallery</title>
    <link rel='stylesheet' href='style.css' type='text/css' media='all' />
</head>
<body>
    <form action="/delete_checked" method="post">
        <table>
            <thead>
                <th class="check"></th>
                <th>Photo id</th>
                <th>Photo preview</th>
            </thead>
            <tbody>
                <c:forEach items="${photoKeys}" var="key">
                <tr>
                    <td ><input type="checkbox" name="photo_keys" value="${key}"></td>
                    <td>${key}</td>
                    <td>
                        <div class="thumbnail">
                            <img src="/photo/${key}" class="portrait"/>
                        </div>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="3"><input type="submit" value="Delete selected"></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>