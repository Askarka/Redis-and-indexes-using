<html>
<head>
    <title>Edit page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form method="post" action="/task/create">
    <input placeholder="Name" name="name">
    <input placeholder="Description" name="description">
    <label for="startDate">Start date</label>
    <input type="date" name="startDate" id="startDate">
    <label for="finishDate">Finish date</label>
    <input type="date" name="finishDate" id="finishDate">
    <label for="isDone">Is done</label>
    <input type="checkbox" name="done" id="isDone">
    <input type="submit" value="Create">
</form>
</body>
</html>
