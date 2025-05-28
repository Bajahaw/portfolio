@param boolean headless = false

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
                                    Upload your resume to get AI-powered skill (Soon) insights.
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
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-cup-hot fs-1 mb-3 text-danger"></i>
                                <h4 class="card-title fw-semibold">Fuel</h4>
                                <p class="text-secondary mb-0">
                                    Powered by Coffee, Tea and a passion for clean code.
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4 d-flex align-items-stretch">
                        <div class="card h-100 border-0 card-shadow rounded-4 block w-100">
                            <div class="card-body p-4 d-flex flex-column justify-content-center align-items-center">
                                <i class="bi bi-keyboard fs-1 mb-3 text-info-emphasis"></i>
                                <h5 class="card-title fw-semibold text-center">My Setup</h5>
                                <p class="text-secondary text-center mb-3">
                                    Usually coding on Arch Linux with Intellij/VSCode.
                                </p>
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