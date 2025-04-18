@import gg.jte.Content

@param boolean headless = false
@param Content content = @`
    <section id="overview">
        <div class="container d-flex flex-column-reverse d-md-flex flex-md-row pt-5"
             style="min-height: 640px">
            <div class="col h-100 col-md-9">
                <div class="my-5">
                    <h1 class="display-4 fw-bold mb-4 text-center text-md-start">Hi, I am Radhi Bajahaw</h1>
                    <h3 class="text-secondary text-center text-md-start"> Let <span class="text-primary text-opacity-75">AI</span>
                        tell you more about me .. </h3>
                </div>
                <div class="d-flex flex-column justify-content-between mt-5 mb-0 h-50">
                    <div id="intro"
                         class="text-secondary text-larger ai-response w-100"
                         hx-get="/content/overview"
                         hx-trigger="load"
                         hx-swap="InnerHtml">
                        <span class="result-streaming"></span>
                    </div>
                    <div class="d-flex align-items-center justify-content-center justify-content-md-start my-5">
                        <a class="scalable" aria-label="Follow on GitHub" href="https://github.com/Bajahaw">
                            <i class="bi bi-github text-secondary"></i>
                        </a>
                        <a class="mx-4 scalable" aria-label="Follow on LinkedIn"
                           href="https://www.linkedin.com/in/radhi-bajahaw">
                            <i class="bi bi-linkedin text-secondary"></i>
                        </a>
                        <a class="scalable" aria-label="Follow on Instagram" href="https://www.instagram.com/rad7_i">
                            <i class="bi bi-instagram text-secondary"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col col-6 col-md-3 m-auto m-md-5">
                <img class="img-fluid tilt-image shadow-lg rounded"
                     src="https://iili.io/304b26P.md.png"
                     alt="Developer avatar illustration" width="480px" height="720px">
            </div>
        </div>
        <div class="container block d-flex justify-content-center">
            <small class="text-secondary text-center">-- This site uses experimental styling features. It is recommended to use
                Chromium-based browser for best experience --</small>
        </div>
    </section>
    <section id="about" class="bg-light mx-0 px-0">
        <div class="container d-flex flex-column pt-5 d-md-flex flex-md-row"
             style="min-height: 640px">
            <div class="col col-6 col-lg-3 col-md-5 m-auto m-lg-5 m-md-0 align-self-center block">
                <img class="img-fluid tilt-image shadow-lg rounded"
                     src="https://iili.io/306PY3G.jpg"
                     alt="Developer avatar illustration">
            </div>
            <div class="col-lg-7 col-md-8 mx-lg-5 d-flex flex-column align-items-center justify-content-center py-5 px-lg-0">
                <h1 class="block display-5 fw-bold mt-2">About Me</h1>
                <div id="about-me-brief"
                     class="block text-secondary text-sm-center text-larger ai-response my-4 w-100"
                     hx-get="/content/about-me-brief"
                     hx-trigger="revealed"
                     hx-swap="InnerHtml">
                    <span class="result-streaming"></span>
                </div>
                <div class="block text-center my-4">
                    <button class="btn btn-dark rounded-pill m-1">More About Me</button>
                    <button class="btn btn-outline-dark rounded-pill m-1"><i
                                class="bi bi-file-earmark-text me-1"></i> View Resume
                    </button>
                </div>
            </div>
        </div>
    </section>
    <section id="Contact" class="mx-0 px-0">
        <div class="container d-flex flex-column pt-5" style="min-height: 640px">
            <div class="d-flex flex-column d-md-flex flex-md-row pt-4">
                <div class="my-4 col-md-6 d-flex flex-column justify-content-center">
                    <h1 class="block display-5 fw-bold mb-5 text-center text-md-start">Get in Touch</h1>
                    <div id="social-overview"
                         class="block text-secondary text-larger ai-response"
                         hx-get="/content/social-overview"
                         hx-trigger="revealed"
                         hx-swap="InnerHtml">
                        <span class="result-streaming"></span>
                    </div>
                    <div class="block d-flex justify-content-md-start justify-content-center mt-4">
                        <button class="btn btn-dark rounded-pill m-1">
                            <i class="bi bi-instagram me-2"></i>Social Accounts</button>
                        <button class="btn btn-outline-dark rounded-pill m-1"><i
                                    class="bi bi-lightbulb me-2"></i>See Insights</button>
                    </div>
                </div>

                <div class="col-md-7 col-lg-6 mx-lg-5 block my-5 d-flex align-items-end justify-content-center">
                    <div class="card shadow-sm mx-md-4 mx-lg-5">
                        <div class="card-body">
                            <div>
                                <h5 class="card-title text-center mb-4">Contact Me</h5>
                                <p class="mb-3 text-secondary">Feel free to connect with me through the following
                                    options: </p>
                            </div>
                            <div class="d-grid gap-3">
                                <div class="form-control d-flex flex-nowrap align-items-center rounded-pill">
                                    <span class="text-secondary position-absolute w-50 text-truncate">info@radhi.tech</span>
                                    <div class="ms-auto d-flex flex-nowrap">
                                        <button class="btn btn-sm" type="button" id="copyButton"
                                                onclick="navigator.clipboard.writeText('info@radhi.tech');">
                                            <i class="bi bi-copy"></i>
                                        </button>
                                        <a href="mailto:info@radhi.tech"
                                           class="btn btn-dark rounded-pill btn-sm" type="button">
                                            <i class="bi bi-envelope"></i>
                                        </a>
                                    </div>
                                </div>
                                <a href="https://www.linkedin.com/in/radhi-bajahaw"
                                   class="btn btn-dark rounded-pill" target="_blank">
                                    <i class="bi bi-linkedin mx-1"></i> LinkedIn
                                </a>
                                <a href="https://github.com/Bajahaw" class="btn btn-dark rounded-pill mb-2"
                                   target="_blank">
                                    <i class="bi bi-github mx-1"></i> GitHub
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
`

@if(headless) ${content}
@else @template.main(content = content)
@endif
