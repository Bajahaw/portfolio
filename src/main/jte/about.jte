@import java.util.List
@import tech.radhi.portfolio.content.ContentTemplate
@import tech.radhi.portfolio.content.ProjectTemplate

@param List<ContentTemplate> questions
@param List<ProjectTemplate> projects
@param List<String> frontend
@param List<String> backend
@param List<String> tools
@param boolean headless = false

!{var lists = List.of(frontend,backend,tools);}
!{var listNames = List.of("Frontend", "Backend", "Tools & Practices");}
!{ var skillsTemplate = @`
    @for(var list: lists)
        <div class="col-md-4">
            <div class="card h-100 border-0 card-shadow rounded-4">
                <div class="card-body text-center p-4">
                    <i class="bi bi-code-square fs-1 mb-3"></i>
                    <h4 class="card-title fw-semibold">${listNames.get(lists.indexOf(list))}</h4>
                    <div class="d-flex flex-wrap justify-content-center gap-2 mt-3">
                        @for(String skill : list)
                            <span class="badge bg-light text-secondary border p-2 block">${skill}</span>
                        @endfor
                    </div>
                </div>
            </div>
        </div>
    @endfor
`;}

!{ var  content = @`
    <section id="about-details">
        <div class="container d-flex flex-column align-items-center py-5">
            <h1 class="display-4 fw-bold mt-2 text-center">About Radhi Bajahaw</h1>
            <p class="lead text-secondary text-center">Get to know me through my
                <span class="text-nowrap">
                    <span class="text-primary text-opacity-75">AI</span> Assistant
                </span>
            </p>
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
                ${skillsTemplate}
            </div>
        </div>
        <div class="container py-5">
            <h2 class="text-center fw-bold mb-5">Projects Timeline</h2>
            <div class="position-relative">
                <div class="position-absolute h-100 ms-md-4 rounded-2"
                     style="width: 5px; background-color: #dee2e6;"></div>

                <div class="ps-4 ps-md-5 mx-md-4">
                    @for(var project: projects)
                        @template.project-item(project = project)
                    @endfor
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
`;}

@if(headless) ${content}
@else @template.main(content = content)
@endif

