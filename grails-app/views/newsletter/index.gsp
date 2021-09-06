<!DOCTYPE html>
<html>
<head>
    <title>Subscribe</title>
</head>
<body>

<g:if test="${msg}">
    <h1><b>${msg}</b></h1>
</g:if>

<form method="post" action="/newsletter/subscribe">
    <fieldset>
        <ol>
            <li>
                <label for="name">Name:</label>
                <input type="text" name="name" id="name">
            </li>
            <li>
                <label for="email">Email:</label>
                <input type="text" name="email" id="email">
            </li>
        </ol>
        <input type="submit" value="Submit"/>
    </fieldset>
</form>

</body>
</html>