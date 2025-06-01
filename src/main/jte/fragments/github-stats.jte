@param tech.radhi.portfolio.dto.GithubStats stats = new tech.radhi.portfolio.dto.GithubStats(0, 0, 0)

<div id="github-stats" class="mb-5">
    <h2 class="text-center fw-bold mb-4">GitHub Activity</h2>
    <div class="row justify-content-center">
        <div class="col-md-4 mb-4">
            <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                <div class="card-body p-4">
                    <i class="bi bi-github fs-1 mb-3 text-primary block"></i>
                    <h3 class="card-title fw-semibold">${stats.publicRepos()}</h3>
                    <p class="text-secondary mb-0 block">Public Repositories</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                <div class="card-body p-4">
                    <i class="bi bi-star-fill fs-1 mb-3 text-warning"></i>
                    <h3 class="card-title fw-semibold">${stats.stars()}</h3>
                    <p class="text-secondary mb-0 block">Total Stars Received</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
                <div class="card-body p-4">
                    <i class="bi bi-graph-up fs-1 mb-3 text-success"></i>
                    <h3 class="card-title fw-semibold">${stats.recentCommits()}</h3>
                    <p class="text-secondary mb-0 block">Commits (Last Month)</p>
                </div>
            </div>
        </div>
    </div>
</div>