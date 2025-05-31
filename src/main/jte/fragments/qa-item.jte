@import tech.radhi.portfolio.dto.ContentTemplate

@param java.util.List<ContentTemplate> questions = java.util.List.of()

@for(int i = 0; i < questions.size(); i++)
    !{var item = questions.get(i);}
    !{var show = i == 0? "show" : "collapse";}
    !{var trigger = i == 0? "load" : "click once";}
    <div class="card-shadow overflow-hidden mb-4 rounded-4">
        <div data-bs-toggle="collapse" data-bs-target="#${item.id()}"
             role="button" aria-expanded="false" aria-controls="${item.id()}"
             hx-get="/content/${item.id()}-answer" hx-trigger="${trigger}"
             hx-target="#${item.id()}-response" hx-swap="innerHTML"
             class="qa-question p-3 rounded-4 bg-dark text-light pointer ease">
            <h5 class="m-0 fw-semibold"><i class="bi bi-code-square me-2"></i>${item.contentBody()}</h5>
        </div>
        <div class="${show}" id="${item.id()}">
            <div id="${item.id()}-response" class="p-4 text-secondary ai-response">
                <span class="result-streaming">⬤</span>
            </div>
        </div>
    </div>
@endfor
