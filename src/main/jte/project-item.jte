@import tech.radhi.portfolio.content.ProjectTemplate
@param ProjectTemplate project

<div class="position-relative mb-5">
    <div class="card card-shadow border-0 rounded-4 block">
        <div class="row g-0">
            <div class="col-md-3 p-2 d-flex align-items-center">
                <img class="rounded img-fluid"
                     src="${project.imageUrl()}"
                     alt="${project.title()}">
            </div>
            <div class="col-md-8">
                <div class="card-body p-4">
                    <div class="d-flex align-items-center mb-3">
                        <span class="badge bg-dark rounded-pill me-2">${project.year()}</span>
                        <h5 class="card-title fw-semibold mb-0">${project.title()}</h5>
                    </div>
                    <p class="card-text text-secondary">${project.description()}</p>
                    <div class="d-flex flex-wrap gap-1 mt-2">
                        @for(var tech : project.technologies())
                            <span class="badge bg-light text-secondary border">${tech}</span>
                        @endfor
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>