@param tech.radhi.portfolio.dto.UptimeStats stats = new tech.radhi.portfolio.dto.UptimeStats("-", "-")

<div id="upptime-stats" class="row justify-content-center">
    <div class="col-md-6 mb-4">
        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
            <div class="card-body p-4">
                <i class="bi bi-shield-check fs-1 mb-3 text-success"></i>
                <h3 class="card-title fw-semibold">${stats.uptimeDay()}</h3>
                <p class="text-secondary mb-0">Current Uptime</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 mb-4">
        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
            <div class="card-body p-4">
                <i class="bi bi-graph-up-arrow fs-1 mb-3 text-success"></i>
                <h3 class="card-title fw-semibold">${stats.uptimeMonth()}</h3>
                <p class="text-secondary mb-0">Availability (Last 30 Days)</p>
            </div>
        </div>
    </div>
</div>