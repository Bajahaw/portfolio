@import tech.radhi.portfolio.InsightsData

@param InsightsData insightsData
@param boolean headless = false

!{var content = @`
    <section id="insights-details" class="py-5">
        <div class="container">
            <h1 class="display-4 fw-bold mt-2 text-center">Developer & Site Insights</h1>
            <p class="lead text-secondary text-center mb-5">A quick look into my coding world, site performance, and
                more.</p>

            <!-- Website Analytics -->
            <div class="mb-5">
                <%-- <h2 class="text-center fw-bold mb-4">Website Analytics</h2>--%>
                <div class="row justify-content-center">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-bar-chart-line-fill fs-1 mb-3 text-info"></i>
                                <h3 class="card-title fw-semibold">${insightsData.totalWebsiteVisits()}</h3>
                                <p class="text-secondary mb-0">Total Requests (All Time)</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-people-fill fs-1 mb-3 text-info"></i>
                                <h3 class="card-title fw-semibold">${insightsData.uniqueVisitors()}</h3>
                                <p class="text-secondary mb-0">Unique Visitors (Last 7 Days)</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-bullseye fs-1 mb-3 text-info"></i>
                                <h5 class="card-title fw-semibold">Heatmap Snapshot</h5>
                                <p class="text-secondary mb-0">(Coming Soon)</p>
                                <%--<img src="https://picsum.photos/150/80" alt="Heatmap Placeholder" class="img-fluid mt-2 rounded" style="max-height: 80px;">--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-shield-check fs-1 mb-3 text-success"></i>
                                <h3 class="card-title fw-semibold">${insightsData.uptimeStatus()}</h3>
                                <p class="text-secondary mb-0">Current Uptime</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-graph-up-arrow fs-1 mb-3 text-success"></i>
                                <h3 class="card-title fw-semibold">${insightsData.availabilityLast30Days()}</h3>
                                <p class="text-secondary mb-0">Availability (Last 30 Days)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- GitHub Stats -->
            <div class="mb-5">
                <h2 class="text-center fw-bold mb-4">GitHub Activity</h2>
                <div class="row justify-content-center">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-github fs-1 mb-3 text-primary"></i>
                                <h3 class="card-title fw-semibold">${insightsData.gitHubRepositories()}</h3>
                                <p class="text-secondary mb-0">Public Repositories</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-star-fill fs-1 mb-3 text-warning"></i>
                                <h3 class="card-title fw-semibold">${insightsData.gitHubStars()}</h3>
                                <p class="text-secondary mb-0">Total Stars Received</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-graph-up fs-1 mb-3 text-success"></i>
                                <h3 class="card-title fw-semibold">${insightsData.commitsLastMonth()}</h3>
                                <p class="text-secondary mb-0">Commits (Last Month)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Resume Skill Analyzer -->
            <div class="mb-5">
                <h2 class="text-center fw-bold mb-4">Resume Insights (AI Powered)</h2>
                <div class="row justify-content-center">
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                            <div class="card-body p-4">
                                <i class="bi bi-stars fs-1 mb-3 text-warning"></i>
                                <h4 class="card-title fw-semibold">Top Skill: ${insightsData.topResumeSkill()}</h4>
                                <p class="text-secondary mb-0">${insightsData.identifiedSkillsCount()} skills
                                    identified from your resume.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4 d-flex align-items-stretch">
                        <div class="card h-100 border-0 card-shadow rounded-4 block w-100">
                            <div class="card-body p-4 d-flex flex-column justify-content-center align-items-center">
                                <i class="bi bi-file-earmark-person fs-1 mb-3 text-primary"></i>
                                <h5 class="card-title fw-semibold text-center">Analyze Your Resume</h5>
                                <p class="text-secondary text-center mb-3">Upload your resume to get AI-powered skill (Soon)
                                    insights.</p>
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
                                <p class="text-secondary mb-0">Powered by ${insightsData.preferredBeverage()} and
                                    a passion for clean code.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4 d-flex align-items-stretch">
                        <div class="card h-100 border-0 card-shadow rounded-4 block w-100">
                            <div class="card-body p-4 d-flex flex-column justify-content-center align-items-center">
                                <i class="bi bi-keyboard fs-1 mb-3 text-info-emphasis"></i>
                                <h5 class="card-title fw-semibold text-center">My Setup</h5>
                                <p class="text-secondary text-center mb-3">Usually coding
                                    on ${insightsData.operatingSystem()} with ${insightsData.idePref()}.</p>
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