<html>
<head>
    <title>Edit page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form method="post" action="/task/${id}/edit">
    <input placeholder="Name" name="name" value="${task.name}">
    <input placeholder="Description" name="description" value="${task.description}">
    <label for="startDate">Start date</label>
    <input type="date" name="startDate" id="startDate" value="${task.startDate}">
    <label for="finishDate">Finish date</label>
    <input type="date" name="finishDate" id="finishDate" value="${task.startDate}">
    <label for="isDone">Is done</label>
    <input type="checkbox" name="done" id="isDone" value="<#if task.done>on<#else>off</#if>">
    <input type="submit" value="Update">
</form>
</body>
</html>
