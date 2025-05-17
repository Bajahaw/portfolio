@param boolean headless = false
@param gg.jte.Content notFound = @`
<div class="h-100 w-100 container d-flex flex-column justify-content-center align-items-center">
    <h1>🪹 404 </h1>
    <small class="text-muted">Nothing to show here -> <a href="/" class="text-muted"> Home
            page</a>
    </small>
</div>
`
@if(headless) ${notFound}
@else @template.main(content = notFound)
@endif