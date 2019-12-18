<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>Photogallery</title>
      <link rel='stylesheet' href='style.css' type='text/css' media='all' />
  </head>
  <body>
    <table>
        <thead>
            <th>1</th>
            <th>2</th>
            <th>3</th>
        </thead>
        <tbody>
        <form action="/view" method="POST">
            <tr>
                <td><label for="photo_id">Enter photo id:</label></td>
                <td><input class="longelements" type="text" name="photo_id" id="photo_id"></td>
                <td><input type="submit" /></td>
            </tr>
        </form>
        <form action="/add_photo" enctype="multipart/form-data" method="POST">
            <tr>
                <td><label for="photo">Choose photo:</label></td>
                <td><input class="longelements" type="file" name="photo" id="photo"></td>
                <td><input type="submit" /></td>
            </tr>
        </form>
        <form action="/gallery" method="POST">
            <tr>
                <td></td>
                <td><input type="submit" value="Show all photos" /></td>
                <td></td>
            </tr>
        </form>
        </tbody>
    </table>
  </body>
</html>
