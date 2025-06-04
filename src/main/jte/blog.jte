@param boolean headless = false
@param gg.jte.Content content = @`
    <div class="container d-flex flex-column align-items-center py-5">
        <h1 class="display-4 fw-bold mt-2 text-center">Blog</h1>
        <p class="lead text-secondary text-center">
            Thoughts, tutorials, and updates on my journey as a developer.
        </p>
        <div id="intro"
             class="text-secondary lead ai-response col-12 col-md-9 mt-4"
             hx-get="/content/about-blog"
             hx-trigger="revealed"
             hx-swap="InnerHtml">
            <span class="result-streaming"></span>
        </div>
    </div>
    <section id="blog-posts" class="py-5 bg-light">
        <div class="container">
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card h-100 border-0 card-shadow rounded-4">
                        <img src="https://backend.radhi.tech/api/files/pbc_2337082678/xiv1gthnym4s3n2/year_kfxk8mzn0s.png"
                             class="img-fluid rounded-3 m-2" alt="Spring Boot">
                        <div class="card-body">
                            <h5 class="card-title block">A year of coding. What have I learned?</h5>
                            <p class="card-text text-secondary mt-3 block">Reflecting on a year of coding,
                            the challenges faced, and the lessons learned.</p>
                        </div>
                        <div class="card-footer bg-transparent border-0 block">
                            <small class="text-muted">Soon ..</small>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 mb-4">
                    <div class="card h-100 border-0 card-shadow rounded-4">
                        <img src="https://backend.radhi.tech/api/files/pbc_2337082678/68zo753w2l2g719/tech_g7hjd73xns.webp"
                             class="img-fluid rounded-3 m-2" alt="HTMX">
                        <div class="card-body">
                            <h5 class="card-title block">HTMX: Modern Web Apps without JavaScript</h5>
                            <p class="card-text text-secondary mt-3 block">Building dynamic web applications with simplicity and
                                power.</p>
                        </div>
                        <div class="card-footer bg-transparent border-0 block">
                            <small class="text-muted">Soon</small>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 mb-4">
                    <div class="card h-100 border-0 card-shadow rounded-4">
                        <img src="https://backend.radhi.tech/api/files/pbc_2337082678/ppxsgmv2r9d2ep6/arch_mqikigl8z3.webp"
                             class="img-fluid rounded-3 m-2" alt="Docker">
                        <div class="card-body">
                            <h5 class="card-title block">Dockerizing Your Java Application</h5>
                            <p class="card-text text-secondary mt-3 block">Step-by-step guide to containerize your Java app with
                                Docker.</p>
                        </div>
                        <div class="card-footer bg-transparent border-0 block">
                            <small class="text-muted">Soon ..</small>
                        </div>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-center mt-5">
                <button class="btn btn-dark rounded-pill px-4 block">
                    <i class="bi bi-arrow-clockwise me-2"></i>Load More
                </button>
            </div>
        </div>
    </section>
`

@if(headless) ${content}
@else @template.main(content = content)
@endif