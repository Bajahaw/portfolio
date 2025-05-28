document.body.addEventListener('htmx:beforeSwap', (event) => {
    if (event.target.classList.contains('ai-response')) {
        event.preventDefault();
        const responseHTML = event.detail.xhr.responseText;
        chatgpt(event.target, responseHTML);
    }
});

function activate(element) {
    document.querySelectorAll('.nav-link').forEach(el => {
        if (el !== element) el.classList.remove('active');
    });
    element.classList.add('active');
}

async function prepareResume() {
    const url = 'https://backend.radhi.tech/api/files/pbc_2337082678/za752529s88748l/cv_new_v3_l23uow5lsh.pdf';
    const loadingTask = pdfjsLib.getDocument(url);
    const pdf = await loadingTask.promise;
    const page = await pdf.getPage(1);
    const scale = 1.5;
    const viewport = page.getViewport({scale});
    // Support HiDPI-screens.
    const outputScale = window.devicePixelRatio || 1;

    const canvas = document.getElementById("resume");
    const context = canvas.getContext("2d");

    canvas.width = Math.floor(viewport.width * outputScale);
    canvas.height = Math.floor(viewport.height * outputScale);

    const transform = outputScale !== 1
        ? [outputScale, 0, 0, outputScale, 0, 0]
        : null;

    const renderContext = {
        canvasContext: context,
        transform,
        viewport,
    };
    document.getElementById('loadingSpinner').style.display = 'none';
    page.render(renderContext);
}

const ADMIN_KEY = localStorage.getItem('ADMIN_KEY');
if (ADMIN_KEY) {
    const extraNav = document.getElementById('extra-nav');
    const adminButton = document.createElement('button');
    adminButton.id = 'admin';
    adminButton.className = 'btn btn-outline-secondary bg-light text-dark rounded-pill shadow-sm border scalable';
    adminButton.innerHTML = '<i class="bi bi-person-fill-lock"></i>';
    adminButton.addEventListener('click', (e) => {
        e.preventDefault();
        window.open(`/swagger-ui/index.html?token=${ADMIN_KEY}`);
    });
    extraNav.appendChild(adminButton);
}

function chatgpt(element, text) {

    const fullText = text;
    const streamContainer = element;
    streamContainer.innerHTML = '';

    const measureEl = document.createElement("div");
    const computed = getComputedStyle(streamContainer);

    measureEl.style.minWidth = streamContainer.clientWidth + "px";
    measureEl.style.maxWidth = streamContainer.clientWidth + "px";
    measureEl.style.padding = computed.padding;
    measureEl.style.font = computed.font;
    measureEl.style.lineHeight = computed.lineHeight;
    measureEl.style.boxSizing = computed.boxSizing;
    measureEl.style.position = "absolute";
    measureEl.style.visibility = "hidden";
    // measureEl.style.border = "1px solid red"; // for debug purposes
    measureEl.textContent = fullText;

    document.body.appendChild(measureEl);
    const finalHeight = measureEl.offsetHeight;
    measureEl.remove();

    requestAnimationFrame(() => {
        streamContainer.style.height = finalHeight + 'px';
    });

    let index = 0;

    function processCharacter() {
        if (index < fullText.length) {
            const sentenceBlock = document.createElement('span');
            sentenceBlock.className = 'ease-long opacity-0';
            sentenceBlock.textContent = fullText.slice(index, index + 2);
            streamContainer.append(sentenceBlock);

            const char = fullText[index + 2];
            const delay = char === '.' ? 100 : 10;
            index += 2;

            setTimeout(() => sentenceBlock.classList.add('opacity-100'), 100);
            setTimeout(processCharacter, delay);

        } else {
            setTimeout(() => {
                streamContainer.innerHTML = fullText;
                streamContainer.style.height = 'revert';
            }, 2000);
        }
    }

    processCharacter();
}

