@import gg.jte.Content

@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Me - Radhi Bajahaw</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="https://backend.radhi.tech/favicon.png">
    <meta name="htmx-config" content='{"historyCacheSize": 0, "refreshOnHistoryMiss": true}'>
    <meta name="description" content="Radhi Bajahaw - Software Engineer, with Backend and DevOps specialities.">
    <meta property="og:title" content="About Me - Radhi Bajahaw" />
    <meta property="og:description" content="Software Engingeer, with Backend and DevOps specilities." />
    <meta property="og:image" content="https://backend.radhi.tech/og-portfolio.png" />
    <meta property="og:url" content="https://portfolio.radhi.tech" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="navbar" class="container">
    <header class="navbar navbar-expand d-flex justify-content-center py-3">
        <ul class="navbar-nav nav navbar-light bg-light shadow-sm border rounded-pill flex-nowrap z-1 px-1">
            <li><a id="home" href="/" hx-get="/" class="scalable nav-link px-md-3" hx-target="#content"
                   hx-trigger="click" hx-on:click="activate(this)" hx-push-url="true">Home</a></li>
            <li><a id="about" href="/about" hx-get="/about" class="scalable nav-link px-md-3" hx-target="#content"
                   hx-trigger="click" hx-on:click="activate(this)" hx-push-url="true">About</a></li>
            <li><a id="stats" href="/stats" hx-get="/stats" class="scalable nav-link px-md-3" hx-target="#content"
                   hx-trigger="click" hx-on:click="activate(this)" hx-push-url="true">Stats</a></li>
            <li><a href="/blog" hx-get="/blog" class="scalable nav-link px-md-3" hx-target="#content" hx-trigger="click"
                   hx-on:click="activate(this)" hx-push-url="true">Blog</a></li>
            <li><a href="/#contact" hx-get="/#contact" class="scalable nav-link px-md-3" hx-target="#content" hx-trigger="click"
                   hx-on:click="activate(this)" hx-push-url="true">Contact</a></li>
        </ul>
        <div id="extra-nav" class="d-flex position-absolute w-100"></div>
    </header>
</div>
<div id="content" style="min-height: 75vh">${content}</div>
<footer class="bg-dark text-light py-4 ">
    <div class="text-center">
        <p class="mb-2">&copy; 2025 Radhi Bajahaw. All rights reserved.</p>
        <p class="mb-0">
            <a href="https://github.com/Bajahaw" class="text-light mx-2"><i class="bi bi-github"></i></a>
            <a href="https://www.linkedin.com/in/radhi-bajahaw" class="text-light mx-2"><i class="bi bi-linkedin"></i></a>
            <a href="https://www.instagram.com/rad7_i" class="text-light mx-2"><i class="bi bi-instagram"></i></a>
            <a href="mailto:info@radhi.tech" class="text-light mx-2"><i class="bi bi-envelope"></i></a>
        </p>
    </div>
</footer>
<script src="js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://unpkg.com/htmx.org@2.0.4/dist/htmx.min.js"></script>
</body>
</html>