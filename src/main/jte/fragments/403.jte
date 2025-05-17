@param boolean headless = false
@param gg.jte.Content forbidden = @`
    <div class="container d-flex flex-column align-items-center p-4">
        <h1>⛔ 403</h1>
        <small class="text-muted">You should not be here. please go back ->
            <a hx-get="/#navbar"
               hx-target="#content"
               hx-push-url="true"
               onclick="activate(document.getElementById('home'))"
               class="text-muted"> Home page</a>
        </small>
    </div>
`
@if(headless) ${forbidden}
@else @template.main(content = forbidden)
@endif