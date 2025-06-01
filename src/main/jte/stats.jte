@param boolean headless = false

!{var setup = java.util.List.of("Arch Linux", "Gnome 48", "Intellij Idea", "VSCode");}
!{var stack = java.util.List.of("Java", "Spring Boot", "Python", "JavaScript", "HTMX", "Docker");}
!{var content = @`
    <section id="insights-details" class="py-5">
        <div class="container">
            <h1 class="display-4 fw-bold mt-2 text-center">Developer & Site Insights</h1>
            <p class="lead text-secondary text-center mb-5">
                A quick look into my coding world, site performance, and more.
            </p>
            <!-- Website Analytics -->
            <div class="mb-5">
                <%-- <h2 class="text-center fw-bold mb-4">Website Analytics</h2>--%>
                <div hx-get="/cloudflare-stats"
                     hx-trigger="load"
                     hx-swap="innerHtml">
                    @template.fragments.request-stats()
                </div>
                <div hx-get="/upptime-stats"
                     hx-trigger="load"
                     hx-swap="innerHtml">
                    @template.fragments.upptime-stats()
                </div>
            </div>
            <!-- GitHub Stats -->
            <div hx-get="/github-stats"
                 hx-trigger="load"
                 hx-swap="innerHtml">
                @template.fragments.github-stats()
            </div>
            <!-- Resume Skill Analyzer -->
            <div class="mb-5">
                <h2 class="text-center fw-bold mb-4">Resume Insights (AI Powered)</h2>
                <div class="row justify-content-center">
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-stars fs-1 mb-3 text-warning"></i>
                                <h4 class="card-title fw-semibold">Top Skill: Java</h4>
                                <p class="text-secondary mb-0">
                                    15 skills identified from your resume.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4 d-flex align-items-stretch">
                        <div class="card h-100 border-0 card-shadow rounded-4 block w-100">
                            <div class="card-body p-4 d-flex flex-column justify-content-center align-items-center">
                                <i class="bi bi-file-earmark-person fs-1 mb-3 text-primary"></i>
                                <h5 class="card-title fw-semibold text-center">Analyze Your Resume</h5>
                                <p class="text-secondary text-center mb-3">
                                    Upload your resume to get AI-powered skill insights (Soon).
                                </p>
                                <%-- <button class="btn btn-primary rounded-pill" hx-get="/soon" hx-target="#content"--%>
                                <%-- hx-push-url="true">Upload & Analyze (Soon)--%>
                                <%-- </button>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Coding Corner -->

            <div class="mb-5">
                <h2 class="text-center fw-bold mb-4">Coding Corner</h2>
                <div class="row justify-content-center">
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 block">
                            <div class="col-md-3 p-2 d-flex align-items-center w-100">
                                <img class="rounded img-fluid"
                                     src="https://backend.radhi.tech/api/files/pbc_2337082678/68zo753w2l2g719/tech_g7hjd73xns.webp"
                                     alt="My setup">
                            </div>
                            <div class="card-body p-4 d-flex flex-column block">
                                <div class="d-flex align-items-center mb-3">
                                    <span class="bi bi-keyboard text-info-emphasis fs-3 me-2"></span>
                                    <h5 class="card-title fw-semibold mb-0">My Tech Stack</h5>
                                </div>
                                <p class="text-secondary mb-3">
                                    I primarily work with Java, Spring Boot, and Python.
                                    I admire simple, elegant, yet powerful code.
                                </p>
                                <div class="d-flex flex-wrap gap-1 mt-2">
                                    @for(var tech : stack)
                                        <span class="badge bg-light text-secondary border block">${tech}</span>
                                    @endfor
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4 d-flex align-items-stretch">
                        <div class="card h-100 border-0 card-shadow rounded-4 block w-100">
                            <div class="col-md-3 p-2 d-flex align-items-center w-100">
                                <img class="rounded img-fluid"
                                     src="https://backend.radhi.tech/api/files/pbc_2337082678/ppxsgmv2r9d2ep6/arch_mqikigl8z3.webp"
                                     alt="My setup">
                            </div>
                            <div class="card-body p-4 d-flex flex-column block">
                                <div class="d-flex align-items-center mb-3">
                                    <span class="bi bi-keyboard text-info-emphasis fs-3 me-2"></span>
                                    <h5 class="card-title fw-semibold mb-0">My Setup</h5>
                                </div>
                                <p class="text-secondary mb-3">
                                    Usually coding on Arch Linux with IntelliJ Idea,
                                    although sometimes VSCode gets the job done faster.
                                </p>
                                <div class="d-flex flex-wrap gap-1 mt-2">
                                    @for(var tech : setup)
                                        <span class="badge bg-light text-secondary border block">${tech}</span>
                                    @endfor
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
`;}

@if(headless)
    ${content}
@else
    @template.main(content = content)
@endif