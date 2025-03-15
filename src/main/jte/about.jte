<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>About Me - Radhi Bajahaw</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://unpkg.com/htmx.org@2.0.4"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .qa-item {
            border-radius: 20px;
            margin-bottom: 1.5rem;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
            transition: all 0.3s ease;
        }

        .qa-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.12);
        }

        .qa-question {
            padding: 1.2rem;
            border-radius: 20px;
            background: var(--bs-dark);
            color: white;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .qa-question h5 {
            margin: 0;
            font-weight: 600;
        }

        .qa-answer {
            padding: 0;
            border-radius: 0 0 20px 20px;
            overflow: hidden;
            transition: all 0.3s ease;
        }

        .qa-answer-content {
            padding: 1.5rem;
        }

        .result-streaming {
            display: inline-block;
            animation: pulse 1.5s infinite;
        }

        @keyframes pulse {
            0% {
                opacity: 0.4;
            }

            50% {
                opacity: 1;
            }

            100% {
                opacity: 0.4;
            }
        }
    </style>
</head>

<body>

<div id="navbar" class="container">
    <header class="navbar navbar-expand d-flex justify-content-center py-3">
        <ul class="navbar-nav nav navbar-light bg-light shadow-sm border rounded-pill flex-nowrap z-1">
            <li><a href="/" class="scalable nav-link px-3">Home</a></li>
            <li><a href="/about" class="scalable nav-link px-3">About</a></li>
            <li><a href="#" class="scalable nav-link px-3">Social</a></li>
            <li><a href="#" class="scalable nav-link px-3">Blog</a></li>
            <li><a href="#" class="scalable nav-link px-3">Contact</a></li>
        </ul>
        <div class="d-flex position-absolute w-100 mx-5 p-5">
            <!--  <button class="btn btn-outline-secondary bg-light text-dark rounded-pill shadow-sm border scalable"> hi </button>-->
        </div>
    </header>
</div>

<div id="content">
    <section id="about-details d-flex justify-content-center " class="">
        <div class="container d-flex flex-column align-items-center py-5">
            <div class="text-center mb-5"></div>
            <h1 class="display-4 fw-bold">About Radhi Bajahaw</h1>
            <p class="lead text-secondary">Get to know me through this interactive Q&A</p>
        </div>

        <!-- Q&A Items with softer design -->
        <div class="container w-75">
            <div class="qa-item">
                <div class="qa-question p-3 rounded-4 bg-dark text-light pointer ease" data-bs-toggle="collapse"
                     data-bs-target="#intro-collapse" role="button"
                     aria-controls="intro-collapse">
                    <h5><i class="bi bi-person-circle me-2"></i>Who are you?</h5>
                </div>
                <div class="qa-answer collapse show" id="intro-collapse">
                    <div class="qa-answer-content text-secondary ai-response" hx-get="/overview"
                         hx-trigger="revealed" hx-swap="innerHTML">
                        <span class="result-streaming">⬤</span>
                    </div>
                </div>
            </div>

            <div class="qa-item">
                <div class="qa-question" data-bs-toggle="collapse" data-bs-target="#technologies-collapse"
                     role="button" aria-expanded="false" aria-controls="technologies-collapse" hx-get="/overview"
                     hx-trigger="click once" hx-target="#technologies-response" hx-swap="innerHTML">
                    <h5><i class="bi bi-code-square me-2"></i>What technologies are your career goals?</h5>
                </div>
                <div class="qa-answer collapse" id="technologies-collapse">
                    <div class="qa-answer-content text-secondary ai-response" id="technologies-response">
                        <span class="result-streaming">⬤</span>
                    </div>
                </div>
            </div>

            <div class="qa-item">
                <div class="qa-question" data-bs-toggle="collapse" data-bs-target="#goals-collapse" role="button"
                     aria-expanded="false" aria-controls="goals-collapse" hx-get="/overview" hx-target="#goals-response"
                     hx-trigger="click once" hx-swap="innerHTML">
                    <h5><i class="bi bi-trophy me-2"></i>What are your career goals?</h5>
                </div>
                <div class="qa-answer collapse" id="goals-collapse">
                    <div class="qa-answer-content text-secondary ai-response" id="goals-response">
                        <span class="result-streaming">⬤</span>
                    </div>
                </div>
            </div>

            <div class="qa-item">
                <div class="qa-question" data-bs-toggle="collapse" data-bs-target="#hobbies-collapse" role="button"
                     aria-expanded="false" aria-controls="hobbies-collapse" hx-get="/overview" hx-trigger="click once"
                     hx-target="#hobbies-response" hx-swap="innerHTML">
                    <h5><i class="bi bi-controller me-2"></i>What do you do outside of coding?</h5>
                </div>
                <div class="qa-answer collapse" id="hobbies-collapse">
                    <div id="hobbies-response" class="qa-answer-content text-secondary ai-response">
                        <span class="result-streaming">⬤</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="text-center mt-5">
            <a href="/" class="btn btn-dark rounded-pill px-4 py-2 me-3">
                <i class="bi bi-house-door me-1"></i> Back to Home
            </a>
            <a href="#" class="btn btn-outline-dark rounded-pill px-4 py-2">
                <i class="bi bi-file-earmark-text me-1"></i> View Resume
            </a>
        </div>
    </section>
</div>

<footer class="bg-dark text-light py-4 mt-sm-4">
    <div class="text-center">
        <p class="mb-2">&copy; 2025 Radhi Bajahaw. All rights reserved.</p>
        <p class="mb-0">
            <a href="https://github.com/Bajahaw" class="text-light mx-2"><i class="bi bi-github"></i></a>
            <a href="https://www.linkedin.com/in/radhi-bajahaw" class="text-light mx-2"><i
                        class="bi bi-linkedin"></i></a>
            <a href="https://www.instagram.com/rad7_i" class="text-light mx-2"><i class="bi bi-instagram"></i></a>
            <a href="mailto:bajahawradhi@gmail.com" class="text-light mx-2"><i class="bi bi-envelope"></i></a>
        </p>
    </div>
</footer>

<script src="js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</body>

</html>