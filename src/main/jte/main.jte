@import gg.jte.Content

@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Me - Radhi Bajahaw</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="htmx-config" content='{"historyCacheSize": 0, "refreshOnHistoryMiss": true}'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/svg+xml" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'%3E%3Ctext y='90' font-size='90'%3E👨🏽‍🚀%3C/text%3E%3C/svg%3E">
</head>
<body>
<div id="navbar" class="container">
    <header class="navbar navbar-expand d-flex justify-content-center py-3">
        <ul class="navbar-nav nav navbar-light bg-light shadow-sm border rounded-pill flex-nowrap z-1 px-1">
            <li><a id="home" href="/" class="scalable nav-link px-md-3" hx-get="/" hx-target="#content" hx-trigger="click"
                   hx-on:click="activate(this)" hx-push-url="true">Home</a></li>
            <li><a id="about" href="/about" class="scalable nav-link px-md-3" hx-get="/about" hx-target="#content"
                   hx-trigger="click" hx-on:click="activate(this)" hx-push-url="true">About</a></li>
            <li><a href="#" class="scalable nav-link px-md-3" hx-get="/social" hx-target="#content" hx-trigger="click"
                   hx-on:click="activate(this)" hx-push-url="true">Social</a></li>
            <li><a href="#" class="scalable nav-link px-md-3" hx-get="/blog" hx-target="#content" hx-trigger="click"
                   hx-on:click="activate(this)" hx-push-url="true">Blog</a></li>
            <li><a href="#" class="scalable nav-link px-md-3" hx-get="/contact" hx-target="#content" hx-trigger="click"
                   hx-on:click="activate(this)" hx-push-url="true">Contact</a></li>
        </ul>
        <div class="d-flex position-absolute w-100 mx-5 p-5">
            <%--            <button class="btn btn-outline-secondary bg-light text-dark rounded-pill shadow-sm border scalable"><i--%>
            <%--                        class="bi bi-github"></i></button>--%>
        </div>
    </header>
</div>
<div id="content">${content}</div>
<footer class="bg-dark text-light py-4 mt-sm-4">
    <div class="text-center">
        <p class="mb-2">&copy; 2025 Radhi Bajahaw. All rights reserved.</p>
        <p class="mb-0">
            <a href="https://github.com/Bajahaw" class="text-light mx-2"><i class="bi bi-github"></i></a>
            <a href="https://www.linkedin.com/in/radhi-bajahaw" class="text-light mx-2"><i
                        class="bi bi-linkedin"></i></a>
            <a href="https://www.instagram.com/rad7_i" class="text-light mx-2"><i class="bi bi-instagram"></i></a>
            <a href="mailto:info@radhi.tech" class="text-light mx-2"><i class="bi bi-envelope"></i></a>
        </p>
    </div>
</footer>
<script src="js/main.js"></script>
<script src="https://unpkg.com/htmx.org@2.0.4/dist/htmx.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</body>
</html>