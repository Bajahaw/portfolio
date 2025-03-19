@import tech.radhi.portfolio.content.ContentTemplate
@param ContentTemplate item
@param String show
@param String trigger

<div class="qa-item overflow-hidden mb-4 rounded-4">
    <div class="qa-question p-3 rounded-4 bg-dark text-light pointer ease" data-bs-toggle="collapse" data-bs-target="#${item.id()}"
         role="button" aria-expanded="false" aria-controls="${item.id()}" hx-get="/content/overview"
         hx-trigger="${trigger}" hx-target="#${item.id()}-response" hx-swap="innerHTML">
        <h5 class="m-0 fw-semibold"><i class="bi bi-code-square me-2"></i>${item.contentBody()}</h5>
    </div>
    <div class="${show}" id="${item.id()}">
        <div class="p-4 text-secondary ai-response" id="${item.id()}-response">
            <span class="result-streaming">⬤</span>
        </div>
    </div>
</div>