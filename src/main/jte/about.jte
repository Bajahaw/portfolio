@param boolean headless = false

!{ var  content = @`
    <section id="about-details">
        <div class="container d-flex flex-column align-items-center py-5">
            <h1 class="display-4 fw-bold mt-2 text-center">About Radhi Bajahaw</h1>
            <p class="lead text-secondary text-center">Get to know me through my
                <span class="text-nowrap">
                    <span class="text-primary text-opacity-75">AI</span> Assistant
                </span>
            </p>
            <div id="intro"
                 class="text-secondary lead hyphened ai-response col-12 col-md-9 mt-4"
                 hx-get="/content/about-me-brief"
                 hx-trigger="revealed"
                 hx-swap="InnerHtml">
                <span class="result-streaming"></span>
            </div>
        </div>
        <div class="container py-5">
            <h2 class="text-center mb-4 fw-bold">Skills & Expertise</h2>
            <div hx-get="/skills"
                 hx-trigger="load"
                 hx-swap="innerHtml"
                 class="row g-4">
                @template.fragments.skills()
            </div>
        </div>
        <div class="container py-5">
            <h2 class="text-center fw-bold mb-4">Projects I've worked on ..</h2>
            <div hx-get="/projects"
                 hx-trigger="load"
                 hx-swap="innerHtml"
                 class="row g-4">
                @template.fragments.project-item()
            </div>
        </div>
        <div class="container col-lg-9">
            <h2 class="text-center fw-bold m-5">Ask <span class="text-primary">AI</span> about me</h2>
            <div hx-get="/FAQ"
                 hx-trigger="load"
                 hx-swap="outerHtml">
            </div>
        </div>

        <div class="text-center my-5 block">
            <a class="btn btn-dark rounded-pill px-3 py-2 m-1"
               hx-get="/#navbar"
               hx-target="#content"
               hx-push-url="true"
               onclick="activate(document.getElementById('home'))">
                <i class="bi bi-house-door me-2"></i> Back to Home
            </a>
            <a class="btn btn-outline-dark rounded-pill px-3 py-2 m-1"
               data-bs-toggle="modal"
               data-bs-target="#pdfModal"
               hx-get="/pdf-item"
               hx-trigger="revealed"
               hx-target="#modal-container"
               hx-on:click="prepareResume()">
                <i class="bi bi-file-earmark-text me-2"></i> View Resume
            </a>
        </div>
        <div id="modal-container"></div>
    </section>
`;}

@if(headless) ${content}
@else @template.main(content = content)
@endif

