@param int status = 500
@param java.util.Date timestamp
@param String error = "Internal Server Error"
@param boolean headless = true
!{var forbidden = @`
    <div class="container d-flex flex-column align-items-center p-4">
        <h1>⚠️</h1>
        <h2 class="fw-semibold">${status}: ${error}</h2>
        <small class="text-muted">An error happened at: ${timestamp.toString()}.</small>
        <small class="text-muted"> please go back ->
            <a hx-get="/#navbar"
               hx-target="#content"
               hx-push-url="true"
               onclick="activate(document.getElementById('home'))"
               class="text-muted"> Home page</a>
        </small>
    </div>
`;}
@if(headless) ${forbidden}
@else @template.main(content = forbidden)
@endif