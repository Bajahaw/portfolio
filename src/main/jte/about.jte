@import tech.radhi.portfolio.*
@import java.util.List
@import gg.jte.Content
@import tech.radhi.portfolio.content.ContentTemplate

@param List<ContentTemplate> questions
@param boolean headless = false
@param Content content = @`
    <section id="about-details d-flex justify-content-center">
        <div class="container d-flex flex-column align-items-center py-5">
            <div class="text-center mb-5"></div>
            <h1 class="display-4 fw-bold">About Radhi Bajahaw</h1>
            <p class="lead text-secondary">Get to know me through this interactive Q&A</p>
        </div>

        <div class="container w-75">
            @for(int i = 0; i < questions.size(); i++)
                @if(i == 0)
                    @template.qa-item(item = questions.get(i), show = "show", trigger = "load")
                @else
                    @template.qa-item(item = questions.get(i), show = "collapse", trigger = "click once")
                @endif
            @endfor
        </div>

        <div class="text-center mt-5">
            <a href="/" class="btn btn-dark rounded-pill px-4 py-2 me-3">
                <i class="bi bi-house-door me-1"></i> Back to Home
            </a>
            <a href="#" class="btn btn-outline-dark rounded-pill px-4 py-2">
                <i class="bi bi-file-earmark-text me-1"></i> View Resume
            </a>
        </div>
    </section>
`

@if(headless) ${content}
@else @template.main(content = content)
@endif

