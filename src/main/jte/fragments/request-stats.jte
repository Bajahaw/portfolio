@param tech.radhi.portfolio.dto.CloudflareStats stats = new tech.radhi.portfolio.dto.CloudflareStats("-", "-")

<div id="request-stats" class="row justify-content-center">
    <div class="col-md-4 mb-4">
        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
            <div class="card-body p-4">
                <i class="bi bi-bar-chart-line-fill fs-1 mb-3 text-info"></i>
                <h3 class="card-title fw-semibold">${stats.totalRequests()}</h3>
                <p class="text-secondary mb-0">Total Requests (All Time)</p>
            </div>
        </div>
    </div>
    <div class="col-md-4 mb-4">
        <div class="card h-100 border-0 card-shadow rounded-4 text-center block">
            <div class="card-body p-4">
                <i class="bi bi-people-fill fs-1 mb-3 text-info"></i>
                <h3 class="card-title fw-semibold">${stats.uniqueVisitors()}</h3>
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