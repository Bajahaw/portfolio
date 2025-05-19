@param boolean headless = false
@param gg.jte.Content notFound = @`
    <div class="container d-flex flex-column align-items-center p-4">
        <h1>🪹 404 </h1>
        <small class="text-muted">Nothing to show here ->
            <a hx-get="/#navbar"
               hx-target="#content"
               hx-push-url="true"
               onclick="activate(document.getElementById('home'))"
               class="text-muted"> Home page</a>
        </small>
    </div>
`
@if(headless) ${notFound}
@else @template.main(content = notFound)
@endif