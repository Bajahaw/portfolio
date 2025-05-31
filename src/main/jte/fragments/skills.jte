@param java.util.List<String> frontend = java.util.List.of("---")
@param java.util.List<String> backend = java.util.List.of("----")
@param java.util.List<String> tools = java.util.List.of("-----")

!{var lists = java.util.List.of(frontend,backend,tools);}
!{var listNames = java.util.List.of("Frontend", "Backend", "Tools & Practices");}
!{var skillsTemplate = @`
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

 ${skillsTemplate}