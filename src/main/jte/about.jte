@import java.util.List
@import gg.jte.Content
@import tech.radhi.portfolio.content.ContentTemplate

@param List<ContentTemplate> questions
@param boolean headless = false
@param Content content = @`
    <section id="about-details">
        <div class="container d-flex flex-column align-items-center py-5">
            <h1 class="display-4 fw-bold mt-2">About Radhi Bajahaw</h1>
            <p class="lead text-secondary">Get to know me through my <span
                        class="text-primary text-opacity-75">AI</span> Assistant</p>
            <div id="intro"
                 class="text-secondary lead ai-response col-12 col-md-9 mt-4"
                 hx-get="/content/about-me-brief"
                 hx-trigger="load"
                 hx-swap="InnerHtml">
                <span class="result-streaming"></span>
            </div>
        </div>

        <div class="container py-5">
            <h2 class="text-center mb-4 fw-bold">Skills & Expertise</h2>
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card h-100 border-0 card-shadow rounded-4">
                        <div class="card-body text-center p-4">
                            <i class="bi bi-code-square fs-1 mb-3"></i>
                            <h4 class="card-title fw-semibold">Frontend</h4>
                            <div class="d-flex flex-wrap justify-content-center gap-2 mt-3">
                                <span class="badge bg-light text-secondary border p-2 block">HTML5</span>
                                <span class="badge bg-light text-secondary border p-2 block">CSS3</span>
                                <span class="badge bg-light text-secondary border p-2 block">JavaScript</span>
                                <span class="badge bg-light text-secondary border p-2 block">React</span>
                                <span class="badge bg-light text-secondary border p-2 block">HTMX</span>
                                <span class="badge bg-light text-secondary border p-2 block">Bootstrap</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 border-0 card-shadow rounded-4">
                        <div class="card-body text-center p-4">
                            <i class="bi bi-server fs-1 mb-3"></i>
                            <h4 class="card-title fw-semibold">Backend</h4>
                            <div class="d-flex flex-wrap justify-content-center gap-2 mt-3">
                                <span class="badge bg-light text-secondary border p-2 block">Java</span>
                                <span class="badge bg-light text-secondary border p-2 block">Kotlin</span>
                                <span class="badge bg-light text-secondary border p-2 block">Python</span>
                                <span class="badge bg-light text-secondary border p-2 block">Spring Boot</span>
                                <span class="badge bg-light text-secondary border p-2 block">PostgreSQL</span>
                                <span class="badge bg-light text-secondary border p-2 block">REST API</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 border-0 card-shadow rounded-4">
                        <div class="card-body text-center p-4">
                            <i class="bi bi-gear fs-1 mb-3"></i>
                            <h4 class="card-title fw-semibold">Tools & Practices</h4>
                            <div class="d-flex flex-wrap justify-content-center gap-2 mt-3">
                                <span class="badge bg-light text-secondary border p-2 block">Git</span>
                                <span class="badge bg-light text-secondary border p-2 block">Docker</span>
                                <span class="badge bg-light text-secondary border p-2 block">CI/CD</span>
                                <span class="badge bg-light text-secondary border p-2 block">Agile</span>
                                <span class="badge bg-light text-secondary border p-2 block">Testing</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="container py-5">
            <h2 class="text-center fw-bold mb-5">Projects Timeline</h2>
            <div class="position-relative">
                <div class="position-absolute h-100 rounded-2"
                     style="width: 5px; background-color: #dee2e6; left: 30px; top: 0;"></div>

                <div class="ps-5 mx-4">
                    <div class="position-relative mb-5">
                        <!-- Timeline dot -->
                        <div class="card card-shadow border-0 rounded-4 block">
                            <div class="row g-0">
                                <div class="col-md-4 overflow-hidden">
                                    <img src="/img/dev.svg"
                                         class="rounded-start-4 w-50 object-fit-fill"
                                         alt="Authentication System">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body p-4">
                                        <div class="d-flex align-items-center mb-3">
                                            <span class="badge bg-dark rounded-pill me-2">2025</span>
                                            <h5 class="card-title fw-semibold mb-0">Portfolio Website</h5>
                                        </div>
                                        <p class="card-text text-secondary">Personal portfolio website built with Spring
                                            Boot, JTE templates, and HTMX for a responsive and dynamic user experience.</p>
                                        <div class="d-flex flex-wrap gap-1 mt-2 h-100 align-items-end">
                                            <span class="badge bg-light text-secondary block border">Java</span>
                                            <span class="badge bg-light text-secondary block border">Spring Boot</span>
                                            <span class="badge bg-light text-secondary block border">JTE</span>
                                            <span class="badge bg-light text-secondary block border">Bootstrap</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="position-relative mb-5">
                        <!-- Timeline dot -->
                        <div class="card card-shadow border-0 rounded-4 block">
                            <div class="row g-0">
                                <div class="col-md-4 overflow-hidden">
                                    <img src="/img/dev.svg"
                                         class="rounded-start-4 w-50 object-fit-fill"
                                         alt="Authentication System">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body p-4">
                                        <div class="d-flex align-items-center mb-3">
                                            <span class="badge bg-dark rounded-pill me-2">2024</span>
                                            <h5 class="card-title fw-semibold mb-0">Authentication System</h5>
                                        </div>
                                        <p class="card-text text-secondary">Full authentication and authorization system
                                            with JWT, refresh tokens and social login.</p>
                                        <div class="d-flex flex-wrap gap-1 mt-2 h-100 align-items-end">
                                            <span class="badge bg-light text-secondary block border">Java</span>
                                            <span class="badge bg-light text-secondary block border">Spring Security</span>
                                            <span class="badge bg-light text-secondary block border">JWT</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="position-relative mb-5">
                        <!-- Timeline dot -->
                        <div class="card card-shadow border-0 rounded-4 block">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="/img/dev.svg"
                                         class="img-fluid rounded-start-4 w-50"
                                         alt="Multiplayer Game Server">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body p-4">
                                        <div class="d-flex align-items-center mb-3">
                                            <span class="badge bg-dark rounded-pill me-2">2023</span>
                                            <h5 class="card-title fw-semibold mb-0">Multiplayer Game Server</h5>
                                        </div>
                                        <p class="card-text text-secondary">Developed a server for an online
                                            multiplayer game, focusing on real-time interactions, scalable architecture,
                                            and efficient data management.</p>
                                        <div class="d-flex flex-wrap gap-1 mt-2">
                                            <span class="badge bg-light text-secondary block border">Java</span>
                                            <span class="badge bg-light text-secondary block border">Spring Boot</span>
                                            <span class="badge bg-light text-secondary block border">SSE</span>
                                            <span class="badge bg-light text-secondary block border">Web</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="container col-lg-9">
            <h2 class="text-center fw-bold m-5">Ask <span class="text-primary">AI</span> about me</h2>
            @for(int i = 0; i < questions.size(); i++)
                @if(i == 0)
                    @template.qa-item(item = questions.get(i), show = "show", trigger = "load")
                @else
                    @template.qa-item(item = questions.get(i), show = "collapse", trigger = "click once")
                @endif
            @endfor
        </div>

        <div class="text-center my-5">
            <a href="/" class="btn btn-dark rounded-pill px-3 py-2 m-1">
                <i class="bi bi-house-door me-2"></i> Back to Home
            </a>
            <a href="#" class="btn btn-outline-dark rounded-pill px-3 py-2 m-1">
                <i class="bi bi-file-earmark-text me-2"></i> View Resume
            </a>
        </div>
    </section>
`

@if(headless) ${content}
@else @template.main(content = content)
@endif

