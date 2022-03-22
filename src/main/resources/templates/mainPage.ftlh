<html>
<head>
    <title>Main page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<a href="/task/create">Add task</a>
<form method="get" action="/page/filter">
    <input placeholder="Name" name="nameLike">
    <label for="startDate">Start date</label>
    <input type="date" name="startDate" id="startDate">
    <label for="finishDate">Finish date</label>
    <input type="date" name="finishDate" id="finishDate">
    <label for="isDone">Is done</label>
    <select name="isDone" id="isDone">
        <option></option>
        <option value="on">Done</option>
        <option value="off">Undone</option>
    </select>
    <label for="desc">Desc</label>
    <input type="checkbox" name="desc" id="desc">
    <label for="sortBy">Sort by</label>
    <select name="sortBy" id="sortBy">
        <option></option>
        <option value="name">Name</option>
        <option value="startDate">Start date</option>
        <option value="finishDate">Finish date</option>
    </select>
    <input type="submit" value="Search">
</form>
<div id="pageContainer">
    <#if currentPage??>
        <span>
           <#if currentPage gt 0>
               <form method="get" action="/page/filter">
                <input type="hidden" name="nameLike" <#if form.nameLike??>value="${form.nameLike}"</#if>>
                <input type="hidden" name="startDate" <#if form.startDate??>value="${form.startDate}"</#if>>
                <input type="hidden" name="finishDate" <#if form.finishDate??>value="${form.finishDate}"</#if>>
                <input type="hidden" name="isDone" <#if form.isDone??><#if form.isDone>value="on"
                       <#else>value="off"</#if></#if>>
                <input type="hidden" name="desc" <#if form.desc??><#if form.desc>value="on"
                       <#else>value="off"</#if></#if>>
                <input type="hidden" name="sortBy" <#if form.sortBy??>value="${form.sortBy}"</#if>>

                <input type="hidden" name="page" value="${currentPage - 1}">

                <input type="submit" value="Prev page">
            </form>
           </#if>
            ${currentPage + 1}/${pageCount}
            <#if currentPage lt pageCount - 1>
                <form method="get" action="/page/filter">
                <input type="hidden" name="nameLike" <#if form.nameLike??>value="${form.nameLike}"</#if>>
                <input type="hidden" name="startDate" <#if form.startDate??>value="${form.startDate}"</#if>>
                <input type="hidden" name="finishDate" <#if form.finishDate??>value="${form.finishDate}"</#if>>
                <input type="hidden" name="isDone" <#if form.isDone??><#if form.isDone>value="on"
                       <#else>value="off"</#if></#if>>
                <input type="hidden" name="desc" <#if form.desc??><#if form.desc>value="on"
                       <#else>value="off"</#if></#if>>
                <input type="hidden" name="sortBy" <#if form.sortBy??>value="${form.sortBy}"</#if>>

                <input name="page" type="hidden" value="${currentPage + 1}">

                <input type="submit" value="Next page">
            </form>
            </#if>

        </span>
    </#if>


    <#if page??>
        <#list page as task>
            <h2>${task.name}</h2>
            <p>${task.description}</p>
            <p>Start:
                <time>${task.startDate}</time>
            </p>
            <p>Finish:
                <time>${task.finishDate}</time>
            </p>
            <#if task.done>Done<#else>Undone</#if>
            <a href="/task/${task.id}/edit">Edit</a>
            <a href="/task/${task.id}/delete">Delete</a>
        </#list>
    </#if>
</div>
</body>
</html>
